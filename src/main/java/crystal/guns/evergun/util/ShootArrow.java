package crystal.guns.evergun.util;

import crystal.guns.enchantment.EnchantmentKeys;
import crystal.guns.Guns;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
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

@SuppressWarnings("java:S3011")
public class ShootArrow {
    private static void setEnchantmentsToArrow(final World world, final ItemStack gun, final PersistentProjectileEntity projectile) {
        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        final int flameLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.FLAME), gun);
        final int punchLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.PUNCH), gun);
        final int piercingLevel = EnchantmentHelper.getLevel(registry.getOrThrow(Enchantments.PIERCING), gun);

        final int frostLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.FROST), gun);
        final int witherLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.DECAY), gun);

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
                    method.setAccessible(true);
                    method.invoke(projectile, (byte) piercingLevel);
                } catch (Exception e) {
                    Guns.LOGGER.error("Failed to reflect", e);
                }
            }
        }
    }

    private static void createAndSpawnProjectile(final World world, final LivingEntity shooter, final ItemStack gun, final PersistentProjectileEntity projectileEntity, final int powerLevel) {
        final Vec3d lookVec = shooter.getRotationVector();

        gun.damage(1, shooter, shooter.getPreferredEquipmentSlot(gun));
        projectileEntity.setPosition(shooter.getX(), shooter.getEyeY() - 0.15, shooter.getZ());
        projectileEntity.setVelocity(lookVec.x, lookVec.y, lookVec.z, 3 + (powerLevel * 0.15F), 1);
        world.spawnEntity(projectileEntity);
    }

    private static PersistentProjectileEntity createArrow(final World world, final LivingEntity entity, final ItemStack gun, final ItemStack arrowStack) {
        final ArrowItem arrowItem = (ArrowItem) (arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
        final PersistentProjectileEntity projectile = arrowItem.createArrow(world, arrowStack, entity, gun);

        setEnchantmentsToArrow(world, gun, projectile);

        projectile.setSound(SoundEvents.ITEM_CROSSBOW_HIT);
        if (entity instanceof PlayerEntity) {
            projectile.setCritical(true);
        }

        return projectile;
    }

    public static void shoot(final World world, final LivingEntity shooter, final ItemStack gun, final ItemStack projectile, final boolean creative) {
        if (world.isClient) return;
        final PersistentProjectileEntity projectileEntity = createArrow(world, shooter, gun, projectile);
        final int powerLevel = EnchantmentHelper.getLevel(world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(Enchantments.POWER), gun);

        if (creative) {
            projectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }

        createAndSpawnProjectile(world, shooter, gun, projectileEntity, powerLevel);

        world.playSound(
                null,
                shooter.getX(), shooter.getY(), shooter.getZ(),
                SoundEvents.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS,
                1.0F,
                1.0F
        );
    }
}
