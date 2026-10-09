package crystal.evergun.client.config;

import crystal.evergun.client.EVERgunClient;
import crystal.guns.Guns;
import crystal.guns.config.EnchantmentsConfig;
import crystal.guns.datagen.GunTags;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
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
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;


public class EverGunConfigScreen extends Screen {

    private final Screen parent;

    private static final Identifier DECAY_ICON =
            Guns.id("textures/gui/icons/decay.png");

    private static final Identifier FROSTBITE_ICON =
            Guns.id("textures/gui/icons/frostbite.png");

    private static final Identifier CATALYST_ICON =
            Guns.id("textures/gui/icons/catalyst.png");

    private static final Identifier SHRAPNEL_ICON =
            Guns.id("textures/gui/icons/shrapnel.png");

    private static final Identifier QUICK_SHOT_ICON =
            Guns.id("textures/gui/icons/quickshot.png");

    private static final Identifier RESERVE_ICON =
            Guns.id("textures/gui/icons/reserve.png");

    private static final Identifier BG =
            Guns.id("textures/gui/icons/bg.png");

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

        int doneX = this.width / 2 - 120;
        int cancelX = this.width / 2 + 20;

        int bottomY = this.height - 35;

        int border = 2;
        int bottomButtonHeight = 20;

        // region Done
        ButtonWidget doneButton = ButtonWidget.builder(

                                Text.empty(),
                                button -> saveConfig()
                        )
                        .dimensions(
                                doneX,
                                bottomY,
                                bottomButtonWidth,
                                bottomButtonHeight
                        )
                        .build();

                        doneButton.setAlpha(0.0f);

                        addDrawableChild(doneButton);
        // endregion

        // region Cancel
        ButtonWidget cancelButton = ButtonWidget.builder(

                        Text.empty(),
                        button -> client.setScreen(parent)
                )
                .dimensions(
                        cancelX,
                        bottomY,
                        bottomButtonWidth,
                        bottomButtonHeight
                )
                .build();

        cancelButton.setAlpha(0.0f);

        addDrawableChild(cancelButton);
        // endregion

        // region EVERgun
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(leftPanelX + 132, panelY + 35
                        )
                        .checked(enableEVERgun)
                        .callback((checkbox, checked) -> {
                            enableEVERgun = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_evergun.tooltip"
                                        )
                                )
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
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(leftPanelX + 110, panelY + 95
                        )
                        .checked(frostbite)
                        .callback((checkbox, checked) -> {
                            frostbite = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_frostbite.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion


        // region Potiongun
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(rightPanelX + 132, panelY + 35
                        )
                        .checked(enablePotiongun)
                        .callback((checkbox, checked) -> {
                            enablePotiongun = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_potiongun.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion

        // region Catalyst
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(rightPanelX + 110, panelY + 65
                        )
                        .checked(catalyst)
                        .callback((checkbox, checked) -> catalyst = checked)
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_catalyst.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion

        // region Shrapnel
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(rightPanelX + 110, panelY + 95
                        )
                        .checked(shrapnel)
                        .callback((checkbox, checked) -> shrapnel = checked)
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_shrapnel.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion

        // region QuickShot
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(rightPanelX + 110, panelY + 125
                        )
                        .checked(quickShot)
                        .callback((checkbox, checked) -> {
                            quickShot = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_quick_shot.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion

        // region Reserve
        addDrawableChild(
                CheckboxWidget.builder(
                                Text.empty(),
                                this.textRenderer
                        )
                        .pos(rightPanelX + 110, panelY + 155
                        )
                        .checked(magazineExpansion)
                        .callback((checkbox, checked) -> {
                            magazineExpansion = checked;
                        })
                        .tooltip(
                                Tooltip.of(
                                        Text.translatable(
                                                "evergun.config.enable_reserve.tooltip"
                                        )
                                )
                        )
                        .build()
        );
        //endregion
    }
    @Override
    public void renderBackground(
            DrawContext context,
            int mouseX,
            int mouseY,
            float delta
    ) {
        context.fill(
                0,
                0,
                this.width,
                this.height,
                0x55000000
        );
    }

    @Override
    public void render(
            DrawContext context,
            int mouseX, int mouseY, float delta
    ) {

        // region Background
        drawCustomBackground(context);
        // endregion

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

        int everToggleX = leftPanelX + 15;
        int everToggleY = panelY + 35;
        int everToggleWidth = 150;
        int everToggleHeight = 20;

        int potToggleX = rightPanelX + 15;
        int potToggleY = panelY + 35;
        int potToggleWidth = 150;
        int potToggleHeight = 20;

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

//region Icons
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

        // region Decay icon
        drawGuiIcon(
                context,
                DECAY_ICON,
                leftPanelX + 35,
                panelY + 65
        );
        // endregion

        // region Decay icon
        drawGuiIcon(
                context,
                FROSTBITE_ICON,
                leftPanelX + 35,
                panelY + 95
        );
        // endregion

        // region Catalyst icon
        drawGuiIcon(
                context,
                CATALYST_ICON,
                rightPanelX + 35,
                panelY + 65
        );
        // endregion

        // region Shrapnel icon
        drawGuiIcon(
                context,
                SHRAPNEL_ICON,
                rightPanelX + 35,
                panelY + 95
        );
        // endregion

        // region QuickShot icon
        drawGuiIcon(
                context,
                QUICK_SHOT_ICON,
                rightPanelX + 35,
                panelY + 125
        );
        // endregion

        // region Reserve icon
        drawGuiIcon(
                context,
                RESERVE_ICON,
                rightPanelX + 35,
                panelY + 155
        );
        // endregion

// endregion



        // region EVERgunConfigButton
        boolean everHovered =
                mouseX >= everToggleX &&
                        mouseX <= everToggleX + everToggleWidth &&
                        mouseY >= everToggleY &&
                        mouseY <= everToggleY + everToggleHeight;

        int everBorderColor;

        if (enableEVERgun) {
            everBorderColor = 0xFF00FFFF;
        } else {
            everBorderColor = 0xFFFFFFFF;
        }

        if (everHovered) {
            everBorderColor = 0xFFFFFF55;
        }

        context.fill(
                everToggleX - 1,
                everToggleY - 1,
                everToggleX + everToggleWidth + 1,
                everToggleY + everToggleHeight + 1,
                everBorderColor
        );

        context.fill(
                everToggleX,
                everToggleY,
                everToggleX + everToggleWidth,
                everToggleY + everToggleHeight,
                0xFF000000
        );
        //endregion



        //region PotionGunConfigButton
        boolean potHovered =
                mouseX >= potToggleX &&
                        mouseX <= potToggleX + potToggleWidth &&
                        mouseY >= potToggleY &&
                        mouseY <= potToggleY + potToggleHeight;

        int potBorderColor;

        if (enablePotiongun) {
            potBorderColor = 0xFF00FFFF;
        } else {
            potBorderColor = 0xFFFFFFFF;
        }

        if (potHovered) {
            potBorderColor = 0xFFFFFF55;
        }

        context.fill(
                potToggleX - 1,
                potToggleY - 1,
                potToggleX + potToggleWidth + 1,
                potToggleY + potToggleHeight + 1,
                potBorderColor
        );

        context.fill(
                potToggleX,
                potToggleY,
                potToggleX + potToggleWidth,
                potToggleY + potToggleHeight,
                0xFF000000
        );
        //endregion

        
        
        int bottomButtonWidth = 100;
        int bottomButtonHeight = 20;

        int doneX = this.width / 2 - 120;
        int cancelX = this.width / 2 + 20;

        int bottomY = this.height - 35;

        int toggleTextArea = 110;
        int doneBorderColor = 0;
        int cancelBorderColor = 0;
        

        
        // region Done custom button
        boolean doneHovered =
                mouseX >= doneX - 1 &&
                        mouseX <= doneX + bottomButtonWidth + 1 &&
                        mouseY >= bottomY - 1 &&
                        mouseY <= bottomY + bottomButtonHeight + 1;

        if (doneHovered) {
            doneBorderColor = 0xFF00FF00;
        } else {
            doneBorderColor = 0xFFFFFFFF;
        }

        context.fill(
                doneX - 1,
                bottomY - 1,
                doneX + bottomButtonWidth + 1,
                bottomY + bottomButtonHeight + 1,
                doneBorderColor
        );

        context.fill(
                doneX,
                bottomY,
                doneX + bottomButtonWidth,
                bottomY + bottomButtonHeight,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("gui.done"),
                doneX + bottomButtonWidth / 2,
                bottomY + (bottomButtonHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
        // endregion

        // region Cancel custom button
        boolean cancelHovered =
                mouseX >= cancelX - 1 &&
                        mouseX <= cancelX + bottomButtonWidth + 1 &&
                        mouseY >= bottomY - 1 &&
                        mouseY <= bottomY + bottomButtonHeight + 1;

        if (cancelHovered) {
            cancelBorderColor = 0xFFFF0000;
        } else {
            cancelBorderColor = 0xFFFFFFFF;
        }

        context.fill(
                cancelX - 1,
                bottomY - 1,
                cancelX + bottomButtonWidth + 1,
                bottomY + bottomButtonHeight + 1,
                cancelBorderColor
        );

        context.fill(
                cancelX,
                bottomY,
                cancelX + bottomButtonWidth,
                bottomY + bottomButtonHeight,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("gui.cancel"),
                cancelX + bottomButtonWidth / 2,
                bottomY + (bottomButtonHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
        // endregion

        //region EVERGUN WEAPON ENABLE NAHUY
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.enable_evergun"),
                everToggleX + toggleTextArea / 2,
                everToggleY + (everToggleHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
        //endregion

        //region POTION WEAPON ENABLE BLYAT
        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.enable_potiongun"),
                potToggleX + toggleTextArea / 2,
                potToggleY + (potToggleHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
        //endregion



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

        // region Frostbite text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.frostbite"),
                leftPanelX + 55,
                panelY + 100,
                0xAAAAAA
        );
        //endregion

        // region Catalyst text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.catalyst"),
                rightPanelX + 55,
                panelY + 70,
                0xAAAAAA
        );
        // endregion

        // region Shrapnel text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.shrapnel"),
                rightPanelX + 55,
                panelY + 100,
                0xAAAAAA
        );
        // endregion

        // region QuickShot text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.quick_shot"),
                rightPanelX + 55,
                panelY + 130,
                0xAAAAAA
        );
        // endregion

        // region Reserve text
        context.drawTextWithShadow(
                this.textRenderer,
                Text.translatable("evergun.config.magazine_expansion"),
                rightPanelX + 55,
                panelY + 160,
                0xAAAAAA
        );
        // endregion
    }

    private void drawGuiIcon (
            DrawContext context,
            Identifier texture,
            int x,
            int y
    ) {
        context.drawTexture(
                texture,
                x,
                y,
                0,
                0,
                16,
                16,
                16,
                16
        );
    }

    private void drawCustomBackground(DrawContext context) {
        context.drawTexture(
                BG,
                0,
                0,
                0.0f,
                0.0f,
                this.width,
                this.height,
                1920,
                1080
        );
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
