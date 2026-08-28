package crystal.guns.enchantment;

import crystal.guns.datagen.GunTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.registry.tag.TagKey;

public class RegisterPotionGunEnchantments {
    private RegisterPotionGunEnchantments() {}

    private static final TagKey<Item> POTIONGUN_COMPATIBLE = GunTags.POTIONGUN_COMPATIBLE;

    protected static void catalyst(final RegistryWrapper<Item> itemRegistry, final FabricDynamicRegistryProvider.Entries entries) {
        if (EnchantmentKeys.ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.CATALYST)) {
            entries.add(EnchantmentKeys.CATALYST, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    2,
                                    4,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    3, AttributeModifierSlot.MAINHAND)
                    )
                    .build(EnchantmentKeys.CATALYST.getValue()));
        }
    }

    protected static void shrapnel(final RegistryWrapper<Item> itemRegistry, final FabricDynamicRegistryProvider.Entries entries) {
        if (EnchantmentKeys.ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.SHRAPNEL)) {
            entries.add(EnchantmentKeys.SHRAPNEL, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    2,
                                    3,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    2, AttributeModifierSlot.MAINHAND)
                    )
                    .build(EnchantmentKeys.SHRAPNEL.getValue()));
        }
    }

    protected static void quickShot(final RegistryWrapper<Item> itemRegistry, final FabricDynamicRegistryProvider.Entries entries, final RegistryWrapper<Enchantment> enchantmentRegistry) {
        if (EnchantmentKeys.ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.QUICK_SHOT)) {
            entries.add(EnchantmentKeys.QUICK_SHOT, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    5,
                                    5,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    4, AttributeModifierSlot.MAINHAND)
                    )
                    .exclusiveSet(enchantmentRegistry.getOrThrow(TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("potiongun", "incompatible_with_others"))))
                    .build(EnchantmentKeys.QUICK_SHOT.getValue()));
        }
    }

    protected static void magazineExpansion(final RegistryWrapper<Item> itemRegistry, final FabricDynamicRegistryProvider.Entries entries, final RegistryWrapper<Enchantment> enchantmentRegistry) {
        if (EnchantmentKeys.ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.MAGAZINE_EXPANSION)) {
            entries.add(EnchantmentKeys.MAGAZINE_EXPANSION, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    itemRegistry.getOrThrow(POTIONGUN_COMPATIBLE),
                                    2,
                                    1,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    5,
                                    AttributeModifierSlot.MAINHAND)
                    )
                    .exclusiveSet(enchantmentRegistry.getOrThrow(TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of("potiongun", "incompatible_with_others"))))
                    .build(EnchantmentKeys.MAGAZINE_EXPANSION.getValue()));

        }
    }
}
