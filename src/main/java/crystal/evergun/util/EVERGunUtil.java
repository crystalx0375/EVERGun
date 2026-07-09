package crystal.evergun.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import static crystal.evergun.util.SetNbt.*;


public class EVERGunUtil {
    private static final String ARROWS = "arrows";

    public static void onUsage(World world, LivingEntity user, PlayerEntity player, ItemStack stack, int remainingUseTicks) {
        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        final int quickChargeLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.QUICK_CHARGE), stack);

        final int currentDel = 20 - (quickChargeLevel * 2);

        setAnimation(stack, (float) ((remainingUseTicks % currentDel) * -1) / currentDel);

        if ((remainingUseTicks - 1) % currentDel == 0) {
            final int magazine = getMagazine(stack);

            if (magazine < 6) {
                final ItemStack ammoStack = findArrows(player);
                if (player.getAbilities().creativeMode || !ammoStack.isEmpty()) {
                    final ItemStack arrowToSave = ammoStack.copy();
                    arrowToSave.setCount(1);

                    if (!world.isClient && !player.getAbilities().creativeMode) {
                        ammoStack.decrement(1);
                    }

                    addProjectile(world, stack, arrowToSave);

                    world.playSound(
                            null,
                            user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE,
                            SoundCategory.PLAYERS,
                            1.0f, 1.0f + (magazine * 0.1f)
                    );
                }
            }
        }
    }

    private static void addProjectile(final World world, final ItemStack crossbow, final ItemStack arrow) {
        final var registries = world.getRegistryManager();
        if (arrow.isEmpty()) return;

        crossbow.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, nbtComponent ->
                nbtComponent.apply(nbt -> {
                    final NbtList list = nbt.getList(ARROWS, NbtElement.COMPOUND_TYPE);
                    final NbtElement arrowNbt = arrow.encode(registries);
                    list.add(arrowNbt);

                    nbt.put(ARROWS, list);
                    nbt.putInt("magazine", list.size());
                    nbt.putBoolean("charged", true);
                })
        );
    }

    public static ItemStack removeProjectile(final ItemStack stack, RegistryWrapper.WrapperLookup registries) {
        final ItemStack[] result = {new ItemStack(Items.ARROW)};

        stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, comp ->
                comp.apply(nbt -> {
                    final NbtList list = nbt.getList(ARROWS, NbtElement.COMPOUND_TYPE);
                    if (!list.isEmpty()) {
                        final NbtCompound arrowNbt = list.getCompound(list.size() - 1);
                        result[0] = ItemStack.fromNbtOrEmpty(registries, arrowNbt);
                        list.removeLast();

                        nbt.putInt("magazine", list.size());
                        nbt.putBoolean("charged", !list.isEmpty());
                    }
                })
        );
        return result[0];
    }

    private static ItemStack findArrows(final PlayerEntity player) {
        if (player.getOffHandStack().isIn(ItemTags.ARROWS)) return player.getOffHandStack();
        if (player.getMainHandStack().isIn(ItemTags.ARROWS)) return player.getMainHandStack();

        for (int i = 0; i < player.getInventory().size(); ++i) {
            final ItemStack itemStack = player.getInventory().getStack(i);
            if (itemStack.isIn(ItemTags.ARROWS)) return itemStack;
        }
        return ItemStack.EMPTY;
    }
}
