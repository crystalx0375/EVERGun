package crystal.guns.potiongun.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import crystal.guns.enchantment.EnchantmentKeys;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import static crystal.guns.util.SearchInventory.findStack;
import static crystal.guns.util.nbt.GunNbt.*;
import static crystal.guns.util.nbt.GutState.getMagazine;
import static crystal.guns.util.nbt.GutState.setAnimation;

public class OnPotionGunUsage {
    private OnPotionGunUsage() {}

    public static void onUsage(@NotNull final World world, final LivingEntity user, final PlayerEntity player, final ItemStack stack, final int remainingUseTicks) {
        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        final int quickChargeLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.QUICK_CHARGE), stack);

        final int currentDel = 20 - (quickChargeLevel * 2);
        if ((remainingUseTicks - 1) % currentDel == 0) {
            final int magazine = getMagazine(stack);
            final int maxCapacity = 4 + EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.MAGAZINE_EXPANSION), stack);

            if (magazine < maxCapacity) {
                final ItemStack potionStack = findStack(player, 1);
                if (potionStack != null && !potionStack.isEmpty()) {
                    final ItemStack potionToSave = potionStack.copy();
                    potionToSave.setCount(1);
                    add(world, stack, potionToSave, 1);

                    if (!world.isClient && !player.getAbilities().creativeMode) {
                        potionStack.decrement(1);
                    }

                    world.playSound(
                            null,
                            user.getX(), user.getY(), user.getZ(),
                            SoundEvents.ITEM_CROSSBOW_LOADING_MIDDLE,
                            SoundCategory.PLAYERS,
                            0.6f, 1.0f + (magazine * 0.1f)
                    );
                    world.playSound(
                            null,
                            user.getX(), user.getY(), user.getZ(),
                            SoundEvents.BLOCK_BREWING_STAND_BREW,
                            SoundCategory.PLAYERS,
                            0.4f, 2.0f + (magazine * 0.1f)
                    );
                }
            }
        }
        setAnimation(stack, (float) ((remainingUseTicks % currentDel) * -1) / currentDel);
    }
}
