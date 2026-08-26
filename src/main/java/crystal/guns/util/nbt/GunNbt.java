package crystal.guns.util.nbt;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

@SuppressWarnings("java:S1192")
public class GunNbt {
    private GunNbt() {}

    private static void addToNbt(final World world, final ItemStack crossbow, final ItemStack potion, final String s) {
        final var registries = world.getRegistryManager();
        if (potion.isEmpty()) return;

        crossbow.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, nbtComponent ->
                nbtComponent.apply(nbt -> {
                    final NbtList list = nbt.getList(s, NbtElement.COMPOUND_TYPE);
                    final NbtElement potionNbt = potion.encode(registries);
                    list.addFirst(potionNbt);

                    nbt.put(s, list);
                    nbt.putInt("magazine", list.size());
                    nbt.putBoolean("charged", true);
                })
        );
    }

    private static ItemStack removeToNbt(final ItemStack stack, final RegistryWrapper.WrapperLookup registries, final String s) {
        final ItemStack[] result = {new ItemStack(Items.POTION)};

        stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp ->
                comp.apply(nbt -> {
                    final NbtList list = nbt.getList(s, NbtElement.COMPOUND_TYPE);
                    if (!list.isEmpty()) {
                        final NbtCompound potionNbt = list.getCompound(list.size() - 1);
                        result[0] = ItemStack.fromNbtOrEmpty(registries, potionNbt);
                        list.removeLast();

                        nbt.putInt("magazine", list.size());
                        nbt.putBoolean("charged", true);
                    }
                })
        );
        return result[0];
    }

    public static void add(final World world, final ItemStack gun, final ItemStack stack, final int query) {
        if (query == 0) addToNbt(world, gun, stack, "arrows");
        if (query == 1) addToNbt(world, gun, stack, "potions");
    }

    public static ItemStack remove(final ItemStack stack, final RegistryWrapper.WrapperLookup registries, final int query) {
        if (query == 0) return removeToNbt(stack, registries, "arrows");
        if (query == 1) return removeToNbt(stack, registries, "potions");
        return null;
    }
}
