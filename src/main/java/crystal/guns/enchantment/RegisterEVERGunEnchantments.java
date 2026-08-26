package crystal.guns.enchantment;

import crystal.guns.datagen.GunTags;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import static crystal.guns.enchantment.EnchantmentKeys.ENCHANTMENT_KEYS;

public class RegisterEVERGunEnchantments {
    private RegisterEVERGunEnchantments() {}

    protected static void frost(RegistryWrapper<Item> itemRegistry, FabricDynamicRegistryProvider.Entries entries) {
        if (ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.FROST)) {
            final TagKey<Item> itemTagKey = GunTags.EVERGUN_COMPATIBLE;

            entries.add(EnchantmentKeys.FROST, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(itemTagKey),
                                    itemRegistry.getOrThrow(itemTagKey),
                                    2,
                                    1,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    4,
                                    AttributeModifierSlot.MAINHAND
                            )
                    )
                    .build(EnchantmentKeys.FROST.getValue()));
        }
    }

    protected static void decay(RegistryWrapper<Item> itemRegistry, FabricDynamicRegistryProvider.Entries entries) {
        if (EnchantmentKeys.ENCHANTMENT_KEYS.containsKey(EnchantmentKeys.DECAY)) {
            final TagKey<Item> itemTagKey = GunTags.EVERGUN_COMPATIBLE;

            entries.add(EnchantmentKeys.DECAY, Enchantment.builder(
                            Enchantment.definition(
                                    itemRegistry.getOrThrow(itemTagKey),
                                    itemRegistry.getOrThrow(itemTagKey),
                                    2,
                                    1,
                                    Enchantment.leveledCost(10, 8),
                                    Enchantment.leveledCost(20, 8),
                                    4,
                                    AttributeModifierSlot.MAINHAND
                            )
                    )
                    .build(EnchantmentKeys.DECAY.getValue()));
        }
    }
}
