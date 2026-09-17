package crystal.evergun.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import crystal.guns.config.EnchantmentsConfig;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class ModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::createConfigScreen;
    }

    private Screen createConfigScreen(Screen parent) {
        EnchantmentsConfig config = EnchantmentsConfig.get();

        Map<String, Object> changes = new HashMap<>();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)

                .setTitle(Text.translatable("evergun.config.title").formatted(Formatting.BOLD,  Formatting.AQUA)
                );

        var entryBuilder = builder.entryBuilder();

        Text title = Text.translatable("evergun.config.title").formatted(Formatting.BOLD, Formatting.AQUA);
        builder.setTitle(title);

        var everganus = builder.getOrCreateCategory(
                Text.translatable("evergun.config.everganus").formatted(Formatting.BOLD)
        );

        var potionganus = builder.getOrCreateCategory(
                Text.translatable("evergun.config.potionganus").formatted(Formatting.BOLD)
        );




        everganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.enable_evergun"),
                                config.enableEVERgun
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("enable_evergun", value)
                        )
                        .build()
        );

        everganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.decay"),
                                config.decay
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("decay", value)
                        )
                        .build()
        );

        everganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.frostbite"),
                                config.frostbite
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("frostbite", value)
                        )
                        .build()
        );

        potionganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.enable_potiongun"),
                                config.enablePotiongun
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("enable_potiongun", value)
                        )
                        .build()
        );

        potionganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.catalyst"),
                                config.catalyst
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("catalyst", value)
                        )
                        .build()
        );

        potionganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.shrapnel"),
                                config.shrapnel
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("shrapnel", value)
                        )
                        .build()
        );

        potionganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.quick_shot"),
                                config.quickShot
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("quick_shot", value)
                        )
                        .build()
        );

        potionganus.addEntry(
                entryBuilder.startBooleanToggle(
                                Text.translatable("evergun.config.magazine_expansion"),
                                config.magazineExpansion
                        )
                        .setDefaultValue(true)
                        .setSaveConsumer(value ->
                                changes.put("magazine_expansion", value)
                        )
                        .build()
        );

        builder.setSavingRunnable(() -> {
            EnchantmentsConfig.save(changes);
            EnchantmentsConfig.reload();
        });

        return builder.build();
    }
}