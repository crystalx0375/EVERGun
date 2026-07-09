package crystal.evergun.util;

import crystal.evergun.EVERgun;
import crystal.evergun.register.EVERGunItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class Settings {
    public static final Item GUN = Registry.register(
            Registries.ITEM,
            EVERgun.id("evergun"),
            new EVERGunItem(new Item.Settings().maxDamage(532)
                    .maxCount(1)
                    .fireproof()
            )
    );
}

