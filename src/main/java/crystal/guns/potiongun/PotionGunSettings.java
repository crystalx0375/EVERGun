package crystal.guns.potiongun;

import crystal.guns.Guns;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class PotionGunSettings {
    private PotionGunSettings() {}

    public static final Item GUN = Registry.register(
            Registries.ITEM,
            Guns.id("potiongun"),
            new CreatePotionGun(new Item.Settings().maxDamage(532)
                    .maxCount(1)
                    .fireproof()
            )
    );
}

