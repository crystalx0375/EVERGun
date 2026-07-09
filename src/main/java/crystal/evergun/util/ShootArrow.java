package crystal.evergun.util;

import crystal.evergun.EVERgun;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ShootArrow {
    public static void shoot(final World world, final LivingEntity shooter, final ItemStack crossbow, final ItemStack projectile, final boolean creative) {
        if (world.isClient) return;

        final PersistentProjectileEntity projectileEntity = createArrow(world, shooter, crossbow, projectile);

        if (creative) {
            projectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }

        final var power = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.POWER);
        final int level = EnchantmentHelper.getLevel(power, crossbow);

        world.playSound(
                null,
                shooter.getX(), shooter.getY(), shooter.getZ(),
                SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS,
                1.0F,
                1.0F
        );

        final Vec3d lookVec = shooter.getRotationVector();

        crossbow.damage(1, shooter, shooter.getPreferredEquipmentSlot(crossbow));
        projectileEntity.setPosition(shooter.getX(), shooter.getEyeY() - 0.15, shooter.getZ());
        projectileEntity.setVelocity(lookVec.x, lookVec.y, lookVec.z, 3 + (level * 0.15F), 1);
        world.spawnEntity(projectileEntity);
    }

    private static PersistentProjectileEntity createArrow(World world, LivingEntity entity, ItemStack crossbow, ItemStack arrowStack) {
        final ArrowItem arrowItem = (ArrowItem) (arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
        final PersistentProjectileEntity projectile = arrowItem.createArrow(world, arrowStack, entity, crossbow);

        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        final int frostLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.FROST), crossbow);
        final int witherLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.DECAY), crossbow);
        final int flameLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.FLAME), crossbow);
        final int punchLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.PUNCH), crossbow);
        final int piercingLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.PIERCING), crossbow);

        if (projectile instanceof ArrowEntity) {
            if (frostLevel > 0) {
                projectile.getCommandTags().add("frost_" + frostLevel);
            }
            if (witherLevel > 0) {
                projectile.getCommandTags().add("wither_" + witherLevel);
            }
            if (punchLevel > 0) {
                projectile.getCommandTags().add("punch_" + punchLevel);
            }
            if (flameLevel > 0) {
                projectile.setOnFireForTicks(100);
            }
            if (piercingLevel > 0) {
                try {
                    java.lang.reflect.Method method = PersistentProjectileEntity.class.getDeclaredMethod(
                            "setPierceLevel", byte.class);
                    method.invoke(projectile, (byte) piercingLevel);
                } catch (Exception e) {
                    EVERgun.LOGGER.error("Failed to reflect");
                }
            }
        }
        projectile.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        if (entity instanceof PlayerEntity) {
            projectile.setCritical(true);
        }

        return projectile;
    }
}
