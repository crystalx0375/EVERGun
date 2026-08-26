package crystal.evergun.client;

import crystal.guns.Guns;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;

@SuppressWarnings("java:S1192")
public class Animation {
    private static void registerEVERGun() {
        ModelPredicateProviderRegistry.register(EverGunSettings.GUN, Guns.id("animation"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                return nbt.getFloat("animation");
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(EverGunSettings.GUN, Guns.id("animation_with_arrow"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                final int a = nbt.getInt("magazine");
                if (a > 0 && a < 6) {
                    return nbt.getFloat("animation");
                }
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(EverGunSettings.GUN, Guns.id("ammo"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                return (float) nbtComponent.copyNbt().getInt("magazine");
            }
            return 0.0F;
        });
    }

    private static void regisrerPotionGun() {
        ModelPredicateProviderRegistry.register(PotionGunSettings.GUN, Guns.id("animation"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                return nbt.getFloat("animation");
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(PotionGunSettings.GUN, Guns.id("animation_with_arrow"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                final int a = nbt.getInt("magazine");
                if (a > 0 && a < 6) {
                    return nbt.getFloat("animation");
                }
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(PotionGunSettings.GUN, Guns.id("ammo"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                return (float) nbtComponent.copyNbt().getInt("magazine");
            }
            return 0.0F;
        });
    }

    public static void register() {
        registerEVERGun();
        regisrerPotionGun();
    }
}
