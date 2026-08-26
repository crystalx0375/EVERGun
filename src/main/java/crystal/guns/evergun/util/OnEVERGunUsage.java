package crystal.guns.evergun.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

import static crystal.guns.util.SearchInventory.findStack;
import static crystal.guns.util.nbt.GunNbt.add;
import static crystal.guns.util.nbt.GutState.getMagazine;
import static crystal.guns.util.nbt.GutState.setAnimation;


public class OnEVERGunUsage {
    public static void onUsage(World world, LivingEntity user, PlayerEntity player, ItemStack stack, int remainingUseTicks) {
        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        final int quickChargeLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.QUICK_CHARGE), stack);

        final int currentDel = 20 - (quickChargeLevel * 2);

        if ((remainingUseTicks - 1) % currentDel == 0) {
            final int magazine = getMagazine(stack);

            if (magazine < 6) {
                final ItemStack arrowStack = findStack(player, 0);
                if (arrowStack != null && !arrowStack.isEmpty()) {
                    final ItemStack arrowToSave = arrowStack.copy();
                    add(world, stack, arrowToSave, 0);

                    if (!world.isClient && !player.getAbilities().creativeMode) {
                        arrowStack.decrement(1);
                    }

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
        setAnimation(stack, (float) ((remainingUseTicks % currentDel) * -1) / currentDel);
    }
}
