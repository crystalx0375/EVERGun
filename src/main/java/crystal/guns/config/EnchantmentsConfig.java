package crystal.guns.config;

import crystal.guns.Guns;
import crystal.guns.util.SimpleConfig;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.Map;

public class EnchantmentsConfig {
    private static int VERSION = 1;
    private static EnchantmentsConfig instance;

    public final boolean decay;
    public final boolean frostbite;
    public final boolean catalyst;
    public final boolean shrapnel;
    public final boolean quickShot;
    public final boolean magazineExpansion;


    private EnchantmentsConfig () {
        SimpleConfig config = SimpleConfig.of("enchantments_config")
                .provider(this::defaultConfig)
                .version(VERSION)
                .request();

        decay = config.getOrDefault("decay", true);
        frostbite = config.getOrDefault("frostbite", true);
        catalyst = config.getOrDefault("catalyst", true);
        shrapnel = config.getOrDefault("shrapnel", true);
        quickShot = config.getOrDefault("quick_shot", true);
        magazineExpansion = config.getOrDefault("magazine_expansion", true);

    }


    @SuppressWarnings("java:S3400")
    private String defaultConfig (String filename) {
        return """
               # Enchantments
               # If false, enchantment won't work and won't appear in world
               
               decay = true
               frostbite = true
               catalyst = true
               shrapnel = true
               quick_shot = true
               magazine_expansion = true
               """;
    }

    public static void save(Map<String, Object> changes) {
        Path path = FabricLoader.getInstance().getConfigDir()
                .resolve("enchantments_config.properties");
        SimpleConfig.writer(path, changes);
    }

    public static void reload() {
        instance = new EnchantmentsConfig();
        Guns.LOGGER.info("ENCHANTMENTS GUNS reloaded!");
    }

    public static EnchantmentsConfig get() {
        if (instance == null) {
            instance = new EnchantmentsConfig();
        }
        return instance;
    }


}
