package crystal.evergun.client.render;

import crystal.guns.Guns;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Gui {
    /**
     * Custom background render
     * @param context ctx to draw
     * @param width width of screen
     * @param height height of screen
     */
    public static void drawCustomBackground(DrawContext context, int width, int height) {
        context.drawTexture(
                Guns.id("textures/gui/icons/bg.png"),
                0, 0,
                0.0f, 0.0f,
                width, height,
                width, height
        );
    }

    /**
     * Upper text (ENCHANTMENTS & WEAPONS)
     * @param context ctx to draw
     * @param widthCenter center of screen on X pos
     */
    public static void drawHero(DrawContext context, int widthCenter, TextRenderer textRenderer) {
        context.drawCenteredTextWithShadow(
                textRenderer,
                Text.translatable("evergun.config.title").formatted(Formatting.BOLD, Formatting.AQUA),
                widthCenter, 30,
                0xFFFFFF
        );
    }
}
