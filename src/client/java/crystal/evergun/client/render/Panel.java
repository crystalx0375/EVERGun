package crystal.evergun.client.render;

import crystal.guns.Guns;
import crystal.guns.evergun.EverGunSettings;
import crystal.guns.potiongun.PotionGunSettings;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class Panel {
    static final int PANEL_WIDTH = 180;
    static final int PANEL_HEIGHT = 200;
    static final int PANEL_Y = 55;

    static final int BORDER = 2;

    static final int LEFT_Y = PANEL_Y + 35;
    static final int LEFT_WIDTH = 150;
    static final int LEFT_HEIGHT = 20;

    static final int RIGHT_Y = PANEL_Y + 35;
    static final int RIGHT_WIDTH = 150;
    static final int RIGHT_HEIGHT = 20;

    /**
     * Creating left panel first layer in config
     * @param context draw panels
     * @param centerWidth center of screen on X pos
     */
    public static void drawLeftPanel(DrawContext context, int centerWidth) {
        final int leftPanelX = centerWidth - PANEL_WIDTH - 10;
        context.fill(
                leftPanelX - BORDER, PANEL_Y - BORDER,
                leftPanelX + PANEL_WIDTH + BORDER, PANEL_Y + PANEL_HEIGHT + BORDER,
                0xFF555555
        );

        context.fill(
                leftPanelX, PANEL_Y,
                leftPanelX + PANEL_WIDTH, PANEL_Y + PANEL_HEIGHT,
                0xCC000000
        );
    }

    /**
     * Creating icons in config
     * @param context draw icons
     * @param leftPanelX position on left panel
     */
    public static void appendLeftPanelIcons(DrawContext context, int leftPanelX) {
        context.drawItem(
                new ItemStack(EverGunSettings.GUN),
                leftPanelX + 44,
                PANEL_Y + 5
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/decay.png"),
                leftPanelX + 35,
                PANEL_Y + 65
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/frostbite.png"),
                leftPanelX + 35,
                PANEL_Y + 95
        );
    }

    /**
     * Creating text on left panel in config
     * @param context draw text
     * @param leftPanelX position on left panel
     */
    public static void appendLeftPanelText(DrawContext context, int leftPanelX, TextRenderer textRenderer) {
        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.everganus"),
                leftPanelX + PANEL_WIDTH / 2, PANEL_Y + 10,
                0xFFFFFF
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.decay"),
                leftPanelX + 55, PANEL_Y + 70,
                0xAAAAAA
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.frostbite"),
                leftPanelX + 55, PANEL_Y + 100,
                0xAAAAAA
        );
    }

    /**
     * Adding hover to buttons (changing color)
     * @param context draw buttons
     * @param enableEVERGun boolean, in which change color of button
     * @param leftPanelX position on left panel
     * @param mouseX mouse coordinate on X
     * @param mouseY mouse coordinate on Y
     */
    public static void addLeftHover(DrawContext context, boolean enableEVERGun, int leftPanelX, int mouseX, int mouseY, TextRenderer textRenderer) {
        final int leftX = leftPanelX + 15;
        final boolean hovered = mouseX >= leftX
                && mouseX <= LEFT_Y + LEFT_WIDTH
                && mouseY >= LEFT_Y
                && mouseY <= LEFT_Y + LEFT_HEIGHT;

        int color;

        if (enableEVERGun) {
            color = 0xFF00FFFF;
        } else {
            color = 0xFFFFFFFF;
        }

        if (hovered) {
            color = 0xFFFFFF55;
        }

        context.fill(
                leftX - 1,
                LEFT_Y - 1,
                leftX + LEFT_WIDTH + 1,
                LEFT_Y + LEFT_HEIGHT + 1,
                color
        );

        context.fill(
                leftX,
                LEFT_Y,
                leftX + LEFT_WIDTH,
                LEFT_Y + LEFT_HEIGHT,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.enable_evergun"),
                leftX + 55, LEFT_Y + (LEFT_HEIGHT - textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
    }

    /**
     * Creating right panel first layer
     * @param context draw panels (right and left)
     * @param centerWidth center of screen on X axis
     */
    public static void drawRightPanel(DrawContext context, int centerWidth) {
        final int rightPanelX = centerWidth + 10;
        context.fill(
                rightPanelX - BORDER, PANEL_Y - BORDER,
                rightPanelX + PANEL_WIDTH + BORDER, PANEL_Y + PANEL_HEIGHT + BORDER,
                0xFF555555
        );

        context.fill(
                rightPanelX, PANEL_Y,
                rightPanelX + PANEL_WIDTH, PANEL_Y + PANEL_HEIGHT,
                0xCC000000
        );
    }

    /**
     * Creating icons in config
     * @param context draw icons on panel
     * @param rightPanelX position on right panel
     */
    public static void appendRightPanelIcons(DrawContext context, int rightPanelX) {
        context.drawItem(
                new ItemStack(PotionGunSettings.GUN),
                rightPanelX + 39,
                PANEL_Y + 5
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/catalyst.png"),
                rightPanelX + 35,
                PANEL_Y + 65
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/shrapnel.png"),
                rightPanelX + 35,
                PANEL_Y + 95
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/quickshot.png"),
                rightPanelX + 35,
                PANEL_Y + 125
        );
        drawIcon(
                context,
                Guns.id("textures/gui/icons/reserve.png"),
                rightPanelX + 35,
                PANEL_Y + 155
        );
    }

    /**
     * Creating text on left panel in config
     * @param context draw text
     * @param rightPanelX position on right panel
     */
    public static void appendRightPanelText(DrawContext context, int rightPanelX, TextRenderer textRenderer) {
        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.potionganus"),
                rightPanelX + PANEL_WIDTH / 2, PANEL_Y + 10,
                0xFFFFFF
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.catalyst"),
                rightPanelX + 55, PANEL_Y + 70,
                0xAAAAAA
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.shrapnel"),
                rightPanelX + 55, PANEL_Y + 100,
                0xAAAAAA
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.quick_shot"),
                rightPanelX + 55, PANEL_Y + 130,
                0xAAAAAA
        );

        context.drawTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.magazine_expansion"),
                rightPanelX + 55, PANEL_Y + 160,
                0xAAAAAA
        );
    }

    /**
     * Adding hover to buttons (changing color)
     * @param context draw buttons
     * @param enableEVERGun boolean, in which change color of button
     * @param rightPanelX position on right panel
     * @param mouseX mouse coordinate on X
     * @param mouseY mouse coordinate on Y
     */
    public static void addRightHover(DrawContext context, boolean enableEVERGun, int rightPanelX, int mouseX, int mouseY, TextRenderer textRenderer) {
        final int rightX = rightPanelX + 15;
        final boolean hovered = mouseX >= rightX
                && mouseX <= rightX + RIGHT_WIDTH
                && mouseY >= RIGHT_Y
                && mouseY <= RIGHT_Y + RIGHT_WIDTH;

        int color;

        if (enableEVERGun) {
            color = 0xFF00FFFF;
        } else {
            color = 0xFFFFFFFF;
        }

        if (hovered) {
            color = 0xFFFFFF55;
        }

        context.fill(
                rightX - 1,
                RIGHT_Y - 1,
                rightX + RIGHT_WIDTH + 1,
                RIGHT_Y + RIGHT_HEIGHT + 1,
                color
        );

        context.fill(
                rightX,
                RIGHT_Y,
                rightX + RIGHT_WIDTH,
                RIGHT_Y + RIGHT_HEIGHT,
                0xFF000000
        );

        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.enable_potiongun"),
                rightX + 55, RIGHT_Y + (RIGHT_HEIGHT - textRenderer.fontHeight) / 2,
                0xFFFFFF
        );
    }

    /**
     * Support method
     * @param texture texture (png, which will be in config in positon of x and y)
     * @param x pos x of icon
     * @param y pos y of icon
     */
    private static void drawIcon(
            DrawContext context,
            Identifier texture,
            int x,
            int y
    ) {
        context.drawTexture(
                texture,
                x, y,
                0, 0,
                16, 16,
                16, 16
        );
    }
}
