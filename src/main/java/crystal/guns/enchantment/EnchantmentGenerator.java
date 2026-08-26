package crystal.guns.enchantment;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static crystal.guns.datagen.GunTags.*;

public class EnchantmentGenerator extends FabricDynamicRegistryProvider {

    public EnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries e) {
        final var i = registries.getWrapperOrThrow(RegistryKeys.ITEM);
        final var n = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        RegisterPotionGunEnchantments.catalyst(i, e);
        RegisterPotionGunEnchantments.shrapnel(i, e);
        RegisterPotionGunEnchantments.quickShot(i, e, n);
        RegisterPotionGunEnchantments.magazineExpansion(i, e, n);

        RegisterEVERGunEnchantments.frost(i, e);
        RegisterEVERGunEnchantments.decay(i, e);

        modify(e, Enchantments.POWER, Enchantment.definition(
                i.getOrThrow(ALL_COMPATIBLE),
                1,
                5,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                4,
                AttributeModifierSlot.MAINHAND
        ));
        modify(e, Enchantments.PUNCH, Enchantment.definition(
                i.getOrThrow(EVERGUN_COMPATIBLE),
                1,
                2,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                4,
                AttributeModifierSlot.MAINHAND
        ));
        modify(e, Enchantments.FLAME, Enchantment.definition(
                i.getOrThrow(EVERGUN_COMPATIBLE),
                1,
                1,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                4,
                AttributeModifierSlot.MAINHAND
        ));
        modify(e, Enchantments.PIERCING, Enchantment.definition(
                i.getOrThrow(EVERGUN_COMPATIBLE),
                1,
                4,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                4,
                AttributeModifierSlot.MAINHAND
        ));
        modify(e, Enchantments.QUICK_CHARGE, Enchantment.definition(
                i.getOrThrow(ALL_COMPATIBLE),
                1,
                3,
                Enchantment.constantCost(20),
                Enchantment.constantCost(50),
                4,
                AttributeModifierSlot.MAINHAND
        ));
    }

    private void modify(final Entries entries, RegistryKey<Enchantment> key, final Enchantment.Definition definition) {
        entries.add(key, Enchantment.builder(definition).build(key.getValue()));
    }

    @Override
    public String getName() {
        return "Enchantments";
    }
}