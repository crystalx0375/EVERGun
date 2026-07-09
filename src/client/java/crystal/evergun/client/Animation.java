package crystal.evergun.client;

import crystal.evergun.EVERgun;
import crystal.evergun.util.Settings;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;

public class Animation {
    private static final String ANIMATION_KEY = "animation";
    private static final String MAGAZINE_KEY = "magazine";


    public static void register() {
        ModelPredicateProviderRegistry.register(Settings.GUN, EVERgun.id(ANIMATION_KEY), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                return nbt.getFloat(ANIMATION_KEY);
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(Settings.GUN, EVERgun.id("animation_with_arrow"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                final var nbt = nbtComponent.copyNbt();
                final int a = nbt.getInt(MAGAZINE_KEY);
                if (a > 0 && a < 6) {
                    return nbt.getFloat(ANIMATION_KEY);
                }
            }
            return 0.0F;
        });

        ModelPredicateProviderRegistry.register(Settings.GUN, EVERgun.id("ammo"), (stack, world, entity, seed) -> {
            final NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (nbtComponent != null) {
                return (float) nbtComponent.copyNbt().getInt(MAGAZINE_KEY);
            }
            return 0.0F;
        });
    }
}
