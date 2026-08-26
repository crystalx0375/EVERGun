package crystal.guns.datagen;

import crystal.guns.Guns;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("java:S1192")
public class GunTags extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> ALL_COMPATIBLE = TagKey.of(RegistryKeys.ITEM, Identifier.of("evergun", "enchantable/compatible/all"));
    public static final TagKey<Item> EVERGUN_COMPATIBLE = TagKey.of(RegistryKeys.ITEM, Identifier.of("evergun", "enchantable/compatible"));
    public static final TagKey<Item> POTIONGUN_COMPATIBLE = TagKey.of(RegistryKeys.ITEM, Identifier.of("potiongun", "enchantable/compatible"));

    public GunTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ALL_COMPATIBLE)
                .add(Guns.id("evergun"))
                .add(Guns.id("potiongun"));
        getOrCreateTagBuilder(EVERGUN_COMPATIBLE)
                .add(Guns.id("evergun"));
        getOrCreateTagBuilder(POTIONGUN_COMPATIBLE)
                .add(Guns.id("potiongun"));

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "enchantable/durability")))
                .add(Guns.id("evergun"))
                .add(Guns.id("potiongun"));
    }
}