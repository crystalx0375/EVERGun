package crystal.evergun.client.config;

import crystal.evergun.client.EVERgunClient;
import crystal.guns.Guns;
import crystal.guns.config.EnchantmentsConfig;
import crystal.guns.datagen.GunTags;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.item.Item;
import net.minecraft.item.ItemKeys;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Formatting;
import crystal.guns.evergun.EverGunSettings;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.widget.CheckboxWidget;

public class EverGunConfigScreen extends Screen {

    private final Screen parent;

    private boolean enableEVERgun;
    private boolean decay;
    private boolean frostbite;

    private boolean enablePotiongun;
    private boolean catalyst;
    private boolean shrapnel;
    private boolean quickShot;
    private boolean magazineExpansion;
    public EverGunConfigScreen(Screen parent) {
        super(Text.translatable("evergun.config.title"));
        this.parent = parent;

        EnchantmentsConfig config = EnchantmentsConfig.get();

        enableEVERgun = config.enableEVERgun;
        decay = config.decay;
        frostbite = config.frostbite;

        enablePotiongun = config.enablePotiongun;
        catalyst = config.catalyst;
        shrapnel = config.shrapnel;
        quickShot = config.quickShot;
        magazineExpansion = config.magazineExpansion;
    }

    @Override
    protected void init() {
        super.init();


        int panelWidth = 180;
        int panelHeight = 200;
        int gap = 20;

        int leftPanelX = this.width / 2 - panelWidth - gap /2;
        int rightPanelX = this.width / 2 + gap / 2;

        int panelY = 55;

        int buttonWidth = 150;
        int buttonHeight = 20;

        int bottomButtonWidth = 100;
        int bottomGap = 10;
        int bottomButtonY = this.height - 30;


        int doneX = this.width / 2 - bottomButtonWidth + bottomGap / 2;
        int cancelX = this.width / 2 - bottomButtonWidth - bottomGap / 2;

        int border = 2;

        // region Done
        addDrawableChild(
                ButtonWidget.builder(
                                Text.translatable("gui.done"),
                                button -> saveConfig()
                        )
                        .dimensions(doneX - 50, bottomButtonY + 19, 100, 12)
                        .build()
        );
        //endregion

        // region Cancel
        addDrawableChild(
                ButtonWidget.builder(
                                Text.translatable("gui.cancel"),
                                button -> client.setScreen(parent)
                        )
                        .dimensions(cancelX + 150, bottomButtonY + 19, 100, 12)
                        .build()
        );
        //endregion

        // region EVERgun
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                        getEnableEVERgunText(),
                        button -> {
                            enableEVERgun = !enableEVERgun;
                            button.setMessage(getEnableEVERgunText());

                        }
                )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_evergun.tooltip")))
                        .dimensions(
                                leftPanelX + (panelWidth - buttonWidth) / 2, panelY + 35,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion

        // region Decay
        addDrawableChild(
                CheckboxWidget.builder(
                        Text.empty(),
                        this.textRenderer
                        )
                        .pos(leftPanelX + 110, panelY + 65
                        )
                        .checked(decay)
                        .callback((checkbox, checked) -> {
                            decay = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_decay.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion

        // region Frostbite
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnableFrostbiteText(),
                                button -> {
                                    frostbite = !frostbite;
                                    button.setMessage(getEnableFrostbiteText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_frostbite.tooltip")))
                        .dimensions(
                                leftPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 95,
                                buttonWidth,
                                buttonHeight)
                        .build()
        );
        //endregion


        // region Potiongun
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnablePotiongunText(),
                                button -> {
                                    enablePotiongun = !enablePotiongun;
                                    button.setMessage(getEnablePotiongunText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_potiongun.tooltip")))
                        .dimensions(
                                rightPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 35,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion

        // region Catalyst
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnableCatalystText(),
                                button -> {
                                    catalyst = !catalyst;
                                    button.setMessage(getEnableCatalystText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_catalyst.tooltip")))
                        .dimensions(
                                rightPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 65,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion

        // region Shrapnel
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnableShrapnelText(),
                                button -> {
                                    shrapnel = !shrapnel;
                                    button.setMessage(getEnableShrapnelText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_shrapnel.tooltip")))
                        .dimensions(
                                rightPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 95,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion

        // region QuickShot
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnableQuickShotText(),
                                button -> {
                                    quickShot = !quickShot;
                                    button.setMessage(getEnableQuickShotText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_quick_shot.tooltip")))
                        .dimensions(
                                rightPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 125,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion

        // region Reserve
        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                                getEnableReserveText(),
                                button -> {
                                    magazineExpansion = !magazineExpansion;
                                    button.setMessage(getEnableReserveText());
                                }
                        )
                        .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_reserve.tooltip")))
                        .dimensions(
                                rightPanelX + (panelWidth - buttonWidth) / 2,
                                panelY + 155,
                                buttonWidth,
                                buttonHeight
                        )
                        .build()
        );
        //endregion
    }

    @Override
    public void render(
            net.minecraft.client.gui.DrawContext context,
            int mouseX, int mouseY, float delta
    ) {

        context.fill(
                0,
                0,
                this.width,
                this.height,
                0x66000000
        );


        // region Main title
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.title")
                        .formatted(Formatting.BOLD, Formatting.AQUA),
                this.width / 2,
                30,
                0xFFFFFF
        );
        // endregion


        // region Panel settings
        int panelWidth = 180;
        int panelHeight = 200;
        int gap = 20;

        int leftPanelX = this.width / 2 - panelWidth - gap / 2;
        int rightPanelX = this.width / 2 + gap / 2;

        int panelY = 55;

        int border = 2;
        // endregion


        // region Item stacks
        final ItemStack evergunStack =
                new ItemStack(EverGunSettings.GUN);

        final ItemStack potiongunStack =
                new ItemStack(PotionGunSettings.GUN);
        // endregion


        // region Left panel border
        context.fill(
                leftPanelX - border,
                panelY - border,
                leftPanelX + panelWidth + border,
                panelY + panelHeight + border,
                0xFF555555
        );
        // endregion


        // region Left panel background
        context.fill(
                leftPanelX,
                panelY,
                leftPanelX + panelWidth,
                panelY + panelHeight,
                0xCC000000
        );
        // endregion


        // region Right panel border
        context.fill(
                rightPanelX - border,
                panelY - border,
                rightPanelX + panelWidth + border,
                panelY + panelHeight + border,
                0xFF555555
        );
        // endregion


        // region Right panel background
        context.fill(
                rightPanelX,
                panelY,
                rightPanelX + panelWidth,
                panelY + panelHeight,
                0xCC000000
        );
        // endregion


        // region Icon of EVERgun
        context.drawItem(
                evergunStack,
                leftPanelX + 44,
                panelY + 5
        );
        // endregion


        // region Icon of Potiongun
        context.drawItem(
                potiongunStack,
                rightPanelX + 39,
                panelY + 5
        );
        // endregion


        // region EVERgun title
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.everganus"),
                leftPanelX + panelWidth / 2,
                panelY + 10,
                0xFFFFFF
        );
        // endregion


        // region Potiongun title
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.potionganus"),
                rightPanelX + panelWidth / 2,
                panelY + 10,
                0xFFFFFF
        );
        // endregion


        // region Widgets
        super.render(context, mouseX, mouseY, delta);
        // endregion


        // region Decay text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.decay"),
                leftPanelX + 55,
                panelY + 70,
                0xAAAAAA
        );
        // endregion
    }

    private Text getEnableEVERgunText() {
        return Text.translatable( "evergun.config.enable_evergun")
                .append(Text.literal(enableEVERgun ? " ☑ " : " ☐ "));
    }

    private Text getEnableDecayText() {
        return Text.translatable( "evergun.config.decay")
                .append(Text.literal(decay ? " ☑ " : " ☐ "));
    }

    private Text getEnableFrostbiteText() {
        return Text.translatable( "evergun.config.frostbite")
                .append(Text.literal(frostbite ? " ☑ " : " ☐ "));
    }


    private Text getEnablePotiongunText() {
        return Text.translatable( "evergun.config.enable_potiongun")
                .append(Text.literal(enablePotiongun ? " ☑ " : " ☐ "));
    }

    private Text getEnableCatalystText() {
        return Text.translatable( "evergun.config.catalyst")
                .append(Text.literal(catalyst ? " ☑ " : " ☐ "));
    }

    private Text getEnableShrapnelText() {
        return Text.translatable( "evergun.config.shrapnel")
                .append(Text.literal(shrapnel ? " ☑ " : " ☐ "));
    }

    private Text getEnableQuickShotText() {
        return Text.translatable( "evergun.config.quick_shot")
                .append(Text.literal(quickShot ? " ☑ " : " ☐ "));
    }

    private Text getEnableReserveText() {
        return Text.translatable( "evergun.config.magazine_expansion")
                .append(Text.literal(magazineExpansion ? " ☑ " : " ☐ "));
    }

    private void saveConfig() {
        Map<String, Object> changes = new HashMap<>();

        changes.put("enable_evergun", enableEVERgun);
        changes.put("decay", decay);
        changes.put("frostbite", frostbite);

        changes.put("enable_potiongun", enablePotiongun);
        changes.put("catalyst", catalyst);
        changes.put("shrapnel", shrapnel);
        changes.put("quick_shot", quickShot);
        changes.put("magazine_expansion", magazineExpansion);

        EnchantmentsConfig.save(changes);
        EnchantmentsConfig.reload();

        client.setScreen(parent);
    }
}
