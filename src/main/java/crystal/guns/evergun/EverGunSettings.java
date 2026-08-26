package crystal.guns.evergun;

import crystal.guns.Guns;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class EverGunSettings {
    public static final Item GUN = Registry.register(
            Registries.ITEM,
            Guns.id("evergun"),
            new CreateEVERGun(new Item.Settings().maxDamage(532)
                    .maxCount(1)
                    .fireproof()
            )
    );
}

