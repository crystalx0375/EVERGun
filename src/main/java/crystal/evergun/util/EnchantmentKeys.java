package crystal.evergun.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;

import static crystal.evergun.EVERgun.MOD_ID;

public class EnchantmentKeys {
    public static final Map<RegistryKey<Enchantment>, Boolean> ENCHANTMENT_KEYS = new LinkedHashMap<>();

    public static final RegistryKey<Enchantment> FROST = register("frost");
    public static final RegistryKey<Enchantment> DECAY = register("decay");

    private static RegistryKey<Enchantment> register(String name) {
        RegistryKey<Enchantment> key = RegistryKey.of(
                RegistryKeys.ENCHANTMENT,
                Identifier.of(MOD_ID, name)
        );
        ENCHANTMENT_KEYS.put(key, true);
        return key;
    }
}
