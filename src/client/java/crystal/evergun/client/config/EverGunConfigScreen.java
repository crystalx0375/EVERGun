package crystal.evergun.client.config;

import crystal.evergun.client.render.CustomButtons;
import crystal.evergun.client.render.Gui;
import crystal.evergun.client.render.Panel;
import crystal.guns.config.EnchantmentsConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EverGunConfigScreen extends Screen {
    private final Screen screen;
    int panelWidth = 180;

    private boolean enableEVERGun;
    private boolean decay;
    private boolean frostbite;

    private boolean enablePotionGun;
    private boolean catalyst;
    private boolean shrapnel;
    private boolean quickShot;
    private boolean magazineExpansion;

    int globalLeftPanelX;
    int globalRightPanelX;

    @SuppressWarnings("java:S1144")
    public EverGunConfigScreen(Screen screen) {
        super(Text.translatable("evergun.config.title"));
        this.screen = screen;

        EnchantmentsConfig config = EnchantmentsConfig.get();

        enableEVERGun = config.enableEVERGun;
        decay = config.decay;
        frostbite = config.frostbite;

        enablePotionGun = config.enablePotionGun;
        catalyst = config.catalyst;
        shrapnel = config.shrapnel;
        quickShot = config.quickShot;
        magazineExpansion = config.magazineExpansion;
    }

    @Override
    protected void init() {
        super.init();
        int panelY = 55;
        int leftPanelX = this.width / 2 - panelWidth - 10;
        globalLeftPanelX = leftPanelX;
        int rightPanelX = this.width / 2 + 10;
        globalRightPanelX = rightPanelX;


        // EVERGun checkboxes
        addDrawableChild(CustomButtons.drawCheckBox(
                leftPanelX + 132,panelY + 36,
                enableEVERGun, v -> enableEVERGun = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                leftPanelX + 110,panelY + 65,
                decay, v -> decay = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                leftPanelX + 110,panelY + 95,
                frostbite, v -> frostbite = v,
                textRenderer
        ));

        // PotionGun checkboxes
        addDrawableChild(CustomButtons.drawCheckBox(
                rightPanelX + 132,panelY + 36,
                enablePotionGun, v -> enablePotionGun = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                rightPanelX + 110,panelY + 65,
                catalyst, v -> catalyst = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                rightPanelX + 110,panelY + 95,
                shrapnel, v -> shrapnel = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                rightPanelX + 110,panelY + 125,
                quickShot, v -> quickShot = v,
                textRenderer
        ));
        addDrawableChild(CustomButtons.drawCheckBox(
                rightPanelX + 110,panelY + 155,
                magazineExpansion, v -> magazineExpansion = v,
                textRenderer
        ));

        addDrawableChild(drawFooterButtons(screen, client, this.width / 2, this.height, true));
        addDrawableChild(drawFooterButtons(screen, client, this.width / 2, this.height, false));
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
        Gui.drawCustomBackground(context, this.width, this.height);

        Gui.drawHero(context, this.width / 2, textRenderer);

        Panel.drawLeftPanel(context, this.width / 2);
        Panel.appendLeftPanelIcons(context, globalLeftPanelX);
        Panel.appendLeftPanelText(context, globalLeftPanelX, textRenderer);
        Panel.addLeftHover(context, enableEVERGun, globalLeftPanelX, mouseX, mouseY, this.textRenderer);

        Panel.drawRightPanel(context, this.width / 2);
        Panel.appendRightPanelIcons(context, globalRightPanelX);
        Panel.appendRightPanelText(context, globalRightPanelX, textRenderer);
        Panel.addRightHover(context, enablePotionGun, globalRightPanelX, mouseX, mouseY, this.textRenderer);

        footerButtonsOnHover(context, this.width / 2, mouseX, mouseY);

        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * Method which create button and returning it
     * @param widthCenter center of screen on X pos
     * @param height height of screen
     * @param v true -> doneButton, false -> cancelButton
     */
    private ButtonWidget drawFooterButtons(
            Screen screen,
            MinecraftClient client,
            int widthCenter,
            int height,
            boolean v
    ) {
        final int bottomButtonWidth = 100;
        final int doneX = widthCenter + 20;
        final int cancelX = widthCenter - 120;
        final int bottomY = height - 35;
        final int bottomButtonHeight = 20;

        if (v) {
            final ButtonWidget doneButton = ButtonWidget.builder(
                            Text.empty(),
                            button -> saveConfig(screen, client)
                    )
                    .dimensions(
                            doneX,
                            bottomY,
                            bottomButtonWidth,
                            bottomButtonHeight
                    )
                    .build();
            doneButton.setAlpha(0.0f);

            return doneButton;
        } else {
            final ButtonWidget cancelButton = ButtonWidget.builder(
                            Text.empty(),
                            button -> Objects.requireNonNull(client).setScreen(screen)
                    )
                    .dimensions(
                            cancelX,
                            bottomY,
                            bottomButtonWidth,
                            bottomButtonHeight
                    )
                    .build();
            cancelButton.setAlpha(0.0f);

            return cancelButton;
        }
    }

    /**
     * Drawing footer buttons on hover (done and cancel)
     * @param context ctx to draw
     * @param widthCenter center of screen on X pos
     * @param mouseX position mouse on x pos
     * @param mouseY position mouse on y pos
     */
    private void footerButtonsOnHover(
            DrawContext context,
            int widthCenter,
            int mouseX,
            int mouseY
    ) {
        final int bottomButtonWidth = 100;
        final int doneX = widthCenter + 20;
        final int cancelX = widthCenter - 120;
        final int bottomY = height - 35;
        final int bottomButtonHeight = 20;

        // Done button
        final boolean doneHovered = mouseX >= doneX - 1
                && mouseX <= doneX + bottomButtonWidth + 1
                && mouseY >= bottomY - 1
                && mouseY <= bottomY + bottomButtonHeight + 1;

        int doneBorderColor;
        if (doneHovered) {
            doneBorderColor = 0xFF00FF00;
        } else {
            doneBorderColor = 0xFFFFFFFF;
        }

        context.fill(
                doneX - 1, bottomY - 1,
                doneX + bottomButtonWidth + 1, bottomY + bottomButtonHeight + 1,
                doneBorderColor
        );

        context.fill(
                doneX, bottomY,
                doneX + bottomButtonWidth, bottomY + bottomButtonHeight,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("gui.done"),
                doneX + bottomButtonWidth / 2,
                bottomY + (bottomButtonHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );

        // Cancel button
        final boolean cancelHovered =
                mouseX >= cancelX - 1
                        && mouseX <= cancelX + bottomButtonWidth + 1
                        && mouseY >= bottomY - 1
                        && mouseY <= bottomY + bottomButtonHeight + 1;

        int cancelBorderColor;
        if (cancelHovered) {
            cancelBorderColor = 0xFFFF0000;
        } else {
            cancelBorderColor = 0xFFFFFFFF;
        }

        context.fill(
                cancelX - 1, bottomY - 1,
                cancelX + bottomButtonWidth + 1, bottomY + bottomButtonHeight + 1,
                cancelBorderColor
        );

        context.fill(
                cancelX,
                bottomY,
                cancelX + bottomButtonWidth, bottomY + bottomButtonHeight,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                this.textRenderer,
                Text.translatable("gui.cancel"),
                cancelX + bottomButtonWidth / 2, bottomY + (bottomButtonHeight - this.textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
    }

    private void saveConfig(Screen screen, MinecraftClient client) {
        Map<String, Object> changes = new HashMap<>();

        changes.put("enable_evergun", enableEVERGun);
        changes.put("decay", decay);
        changes.put("frostbite", frostbite);

        changes.put("enable_potiongun", enablePotionGun);
        changes.put("catalyst", catalyst);
        changes.put("shrapnel", shrapnel);
        changes.put("quick_shot", quickShot);
        changes.put("magazine_expansion", magazineExpansion);

        EnchantmentsConfig.save(changes);
        EnchantmentsConfig.reload();

        Objects.requireNonNull(client).setScreen(screen);
    }
}
