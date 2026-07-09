package crystal.evergun.register;

import com.google.common.collect.Lists;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

import static crystal.evergun.util.EVERGunUtil.onUsage;
import static crystal.evergun.util.EVERGunUtil.removeProjectile;
import static crystal.evergun.util.SetNbt.*;
import static crystal.evergun.util.ShootArrow.shoot;

public class EVERGunItem extends Item {
    public EVERGunItem(Settings settings) {
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
        final int ammo = getMagazine(stack);

        if (ammo > 0) {
            final ItemStack arrowToShoot = removeProjectile(stack, world.getRegistryManager());

            setMagazine(stack, ammo - 1);
            shoot(world, user, stack, arrowToShoot, user.getAbilities().creativeMode);
            user.getItemCooldownManager().set(stack.getItem(), 5);

            return TypedActionResult.consume(stack);
        }
        user.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
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
                SoundEvents.ITEM_CROSSBOW_LOADING_END,
                SoundCategory.PLAYERS,
                1.0f, 1.0f
        );
        setAnimation(stack, 0);

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        final List<ItemStack> list = getProjectiles(stack, context.getRegistryLookup());
        final int magazine = getMagazine(stack);
        if (!list.isEmpty()) {
            final int magazineIndex = magazine - 1;

            if (magazineIndex >= 0 && magazineIndex < list.size()) {
                final ItemStack projectileStack = list.get(magazineIndex);

                tooltip.add(Text.translatable("item.minecraft.crossbow.projectile")
                        .append(ScreenTexts.SPACE)
                        .append(projectileStack.toHoverableText()).formatted(Formatting.GRAY)
                        .append(ScreenTexts.SPACE)
                        .append((Text.literal(magazine + "/6")).formatted(Formatting.AQUA))
                );
            }
        }
        super.appendTooltip(stack, context, tooltip, type);
    }

    private static List<ItemStack> getProjectiles(ItemStack stack, RegistryWrapper.WrapperLookup registries) {
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
}
