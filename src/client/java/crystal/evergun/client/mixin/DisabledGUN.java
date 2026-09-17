package crystal.evergun.client.mixin;

import crystal.guns.config.EnchantmentsConfig;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class DisabledGUN {

    @Inject(
            method = "getName",
            at = @At("RETURN"),
            cancellable = true
    )

    private void addDisabledTextforWeapon(CallbackInfoReturnable<Text> cir) {
        ItemStack stack = (ItemStack) (Object) this;
        EnchantmentsConfig config = EnchantmentsConfig.get();

        boolean disabled =
                (stack.isOf(EverGunSettings.GUN) && !config.enableEVERgun)
                        || (stack.isOf(PotionGunSettings.GUN) && !config.enablePotiongun);

        if (!disabled) {
            return;
        }

        Text disabledText = Text.literal("  ")
                .append(
                        Text.translatable("enchantment.evergun.disabled")
                                .formatted(
                                        Formatting.DARK_AQUA,
                                        Formatting.ITALIC
                                )
                );

        cir.setReturnValue(
                cir.getReturnValue().copy().append(disabledText)
        );
    }
}

