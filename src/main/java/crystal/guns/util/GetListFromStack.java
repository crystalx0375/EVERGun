package crystal.guns.util;

import com.google.common.collect.Lists;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;

import java.util.Collections;
import java.util.List;

public class GetListFromStack {
    private GetListFromStack() {}

    private static List<ItemStack> getProjectiles(final ItemStack stack, final RegistryWrapper.WrapperLookup registries) {
        final List<ItemStack> list = Lists.newArrayList();
        final NbtComponent nbtComponent = stack.get(net.minecraft.component.DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null) {
            return list;
        }

        final NbtCompound nbt = nbtComponent.copyNbt();
        if (nbt.contains("arrows", 9)) {
            final NbtList nbtList = nbt.getList("arrows", 10);
            for (int i = 0; i < nbtList.size(); ++i) {
                final NbtCompound arrowNbt = nbtList.getCompound(i);
                ItemStack arrowStack = ItemStack.fromNbtOrEmpty(registries, arrowNbt);

                if (!arrowStack.isEmpty()) {
                    list.add(arrowStack);
                }
            }
        }
        return list;
    }

    private static List<ItemStack> getPotions(final ItemStack stack, final RegistryWrapper.WrapperLookup registries) {
        final List<ItemStack> list = Lists.newArrayList();
        final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null) {
            return list;
        }

        final NbtCompound nbt = nbtComponent.copyNbt();
        if (nbt.contains("potions", 9)) {
            final NbtList nbtList = nbt.getList("potions", 10);
            for (int i = 0; i < nbtList.size(); ++i) {
                final NbtCompound potionNbt = nbtList.getCompound(i);
                final ItemStack potionStack = ItemStack.fromNbtOrEmpty(registries, potionNbt);

                if (!potionStack.isEmpty()) {
                    list.add(potionStack);
                }
            }
        }
        return list;
    }

    public static List<ItemStack> search(final ItemStack stack, final RegistryWrapper.WrapperLookup registries, final int query) {
        if (query == 0) return getProjectiles(stack, registries);
        if (query == 1) return getPotions(stack, registries);
        return Collections.emptyList();
    }
}
