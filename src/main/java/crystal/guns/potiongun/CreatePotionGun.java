package crystal.guns.potiongun;

import crystal.guns.util.GetListFromStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import crystal.guns.enchantment.EnchantmentKeys;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.enchantment.EnchantmentHelper;

import java.util.List;
import java.util.Objects;

import static crystal.guns.potiongun.util.OnPotionGunUsage.onUsage;
import static crystal.guns.potiongun.util.ShootArrow.shoot;
import static crystal.guns.util.nbt.GunNbt.remove;
import static crystal.guns.util.nbt.GutState.*;

public class CreatePotionGun extends Item {
    public CreatePotionGun(Settings settings) {
        super(settings);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.CROSSBOW;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        final ItemStack stack = user.getStackInHand(hand);

        final var registry = world.getRegistryManager().getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        final int magExpLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.MAGAZINE_EXPANSION), stack);

        final int ammo = getMagazine(stack);
        final int maxCapacity = 4 + magExpLevel;

        final boolean canReload = ammo < maxCapacity;
        final boolean wantsToReload = ammo == 0 || (ammo > 0 && user.isSneaking() && canReload);

        if (wantsToReload) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(stack);
        } else if (ammo > 0) {
            final int quickShotLevel = EnchantmentHelper.getLevel(registry.getOrThrow(EnchantmentKeys.QUICK_SHOT), stack);
            
            final ItemStack potionToShoot = remove(stack, world.getRegistryManager(), 1);
            setMagazine(stack, ammo - 1);
            shoot(world, user, stack, potionToShoot);
            user.getItemCooldownManager().set(stack.getItem(), Math.max(1, 5 - quickShotLevel));

            return TypedActionResult.consume(stack);
        }
        return TypedActionResult.pass(stack);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) return;
        onUsage(world, user, player, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        setAnimation(stack, 0);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient) return null;
        world.playSound(
                null,
                user.getX(), user.getY(), user.getZ(),
                SoundEvents.BLOCK_BREWING_STAND_BREW,
                SoundCategory.PLAYERS,
                0.6f, 0.9f
        );
        setAnimation(stack, 0);

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        final List<ItemStack> list = GetListFromStack.search(stack, context.getRegistryLookup(), 1);
        final int magazine = getMagazine(stack);
        if (!list.isEmpty()) {
            final int magazineIndex = magazine - 1;

            if (magazineIndex >= 0 && magazineIndex < list.size()) {
                final ItemStack projectileStack = list.get(magazineIndex);

                tooltip.add(Text.translatable("item.minecraft.crossbow.projectile")
                        .append(ScreenTexts.SPACE)
                        .append(projectileStack.toHoverableText()).formatted(Formatting.GRAY)
                        .append(ScreenTexts.SPACE)
                        .append((Text.literal(magazine + "/" + (4 + EnchantmentHelper.getLevel(Objects.requireNonNull(context.getRegistryLookup()).getWrapperOrThrow(RegistryKeys.ENCHANTMENT).getOrThrow(EnchantmentKeys.MAGAZINE_EXPANSION), stack)))).formatted(Formatting.AQUA))
                );
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }
}
