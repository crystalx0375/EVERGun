package crystal.guns;

import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Guns implements ModInitializer {
	public static final String MOD_ID = "evergun";
	public static final Logger LOGGER = LoggerFactory.getLogger("EVERGun");

	@Override
	public void onInitialize() {
        final Item evergun = EverGunSettings.GUN;
        final Item potiongun = PotionGunSettings.GUN;

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(itemGroup -> {
            itemGroup.add(evergun);
            itemGroup.add(potiongun);
        });
		LOGGER.info("Loading Guns");
    }

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
