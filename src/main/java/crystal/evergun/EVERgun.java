package crystal.evergun;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EVERgun implements ModInitializer {
	public static final String MOD_ID = "evergun";
	public static final Logger LOGGER = LoggerFactory.getLogger("EVERGun");

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
