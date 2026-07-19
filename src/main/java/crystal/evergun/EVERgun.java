package crystal.evergun;

import crystal.evergun.util.Settings;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EVERgun implements ModInitializer {
	public static final String MOD_ID = "evergun";
	public static final Logger LOGGER = LoggerFactory.getLogger("EVERGun");

	@Override
	public void onInitialize() {
        final Item registeredGUN = Settings.GUN;
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register((itemGroup) -> itemGroup.add(registeredGUN));
		LOGGER.info("Loading EVERGun");
    }

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
