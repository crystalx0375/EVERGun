package crystal.guns.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;

public class SearchInventory {
    private static ItemStack findProjectile(final PlayerEntity player) {
        if (player.getOffHandStack().isIn(ItemTags.ARROWS)) return player.getOffHandStack();
        if (player.getMainHandStack().isIn(ItemTags.ARROWS)) return player.getMainHandStack();

        for (int i = 0; i < player.getInventory().size(); ++i) {
            final ItemStack itemStack = player.getInventory().getStack(i);
            if (itemStack.isIn(ItemTags.ARROWS)) return itemStack;
        }
        return ItemStack.EMPTY;
    }

    private static ItemStack findPotions(final PlayerEntity player) {
        if (isThrowablePotion(player.getOffHandStack())) return player.getOffHandStack();
        if (isThrowablePotion(player.getMainHandStack())) return player.getMainHandStack();

        for (int i = 0; i < player.getInventory().size(); ++i) {
            final ItemStack itemStack = player.getInventory().getStack(i);
            if (isThrowablePotion(itemStack)) return itemStack;
        }
        return ItemStack.EMPTY;
    }

    private static boolean isThrowablePotion(final ItemStack stack) {
        return stack.isOf(Items.SPLASH_POTION) || stack.isOf(Items.LINGERING_POTION);
    }

    public static ItemStack findStack(final PlayerEntity player, final int query) {
        if (query == 0) return findProjectile(player);
        if (query == 1) return findPotions(player);
        return null;
    }
}
