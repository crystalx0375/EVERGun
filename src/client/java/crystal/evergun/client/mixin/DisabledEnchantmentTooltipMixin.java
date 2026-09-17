package crystal.evergun.client.mixin;

import crystal.guns.Guns;
import crystal.guns.config.EnchantmentsConfig;
import crystal.guns.enchantment.EnchantmentKeys;
import crystal.guns.evergun.CreateEVERGun;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class DisabledEnchantmentTooltipMixin {

    @Inject(
            method = "getName",
            at = @At("RETURN"),
            cancellable = true
    )

        private static void addDisabledText(

            RegistryEntry<Enchantment> enchantment,
            int level,
            CallbackInfoReturnable<Text> cir
    ) {
        EnchantmentsConfig config = EnchantmentsConfig.get();

        boolean disabled =
                (enchantment.matchesKey(EnchantmentKeys.DECAY) && !config.decay)
                        || (enchantment.matchesKey(EnchantmentKeys.FROST) && !config.frostbite)
                        || (enchantment.matchesKey(EnchantmentKeys.CATALYST) && !config.catalyst)
                        || (enchantment.matchesKey(EnchantmentKeys.SHRAPNEL) && !config.shrapnel)
                        || (enchantment.matchesKey(EnchantmentKeys.MAGAZINE_EXPANSION) && !config.magazineExpansion)
                        || (enchantment.matchesKey(EnchantmentKeys.QUICK_SHOT) && !config.quickShot);


        if (!disabled) {
            return;
        }

        Text disabledText = Text.literal("  ")
                .append(
                        Text.translatable(
                                "enchantment.evergun.disabled"
                        ).formatted(
                                Formatting.DARK_RED,
                                Formatting.ITALIC
                        )
                );

        cir.setReturnValue(
                cir.getReturnValue().copy().append(disabledText)
        );
    }


}

