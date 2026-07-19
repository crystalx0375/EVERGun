package crystal.evergun.register;

import crystal.evergun.util.EnchantmentKeys;
import crystal.evergun.util.Settings;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static crystal.evergun.EVERgun.MOD_ID;

public class CustomTag extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> EVERGUN_COMPATIBLE = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "enchantable/evergun_compatible"));

    public static final TagKey<Item> CROSSBOWS = TagKey.of(
            RegistryKeys.ITEM,
            Identifier.of("evergun", "enchantable/crossbows")
    );

    public CustomTag(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(CROSSBOWS).add(Items.CROSSBOW);
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "enchantable/crossbow"))).add(Settings.GUN);
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "enchantable/bow"))).add(Settings.GUN);
        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("minecraft", "enchantable/durability"))).add(Settings.GUN);
        getOrCreateTagBuilder(EVERGUN_COMPATIBLE).add(Settings.GUN);
    }
}
