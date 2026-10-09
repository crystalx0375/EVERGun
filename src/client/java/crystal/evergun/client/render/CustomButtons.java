package crystal.evergun.client.render;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class CustomButtons {
    /**
     * Support method
     * @param x x pos
     * @param y y pos
     * @param initial initial boolean
     * @param changed boolean at the end of changing
     */
    public static CheckboxWidget drawCheckBox(int x, int y, boolean initial, Consumer<Boolean> changed, TextRenderer textRenderer) {
        return CheckboxWidget.builder(Text.empty(), textRenderer)
                .pos(x, y)
                .checked(initial)
                .callback((checkbox, checked) -> changed.accept(checked))
                .tooltip(Tooltip.of(Text.translatable("evergun.config.enable_evergun.tooltip")))
                .build();
    }
}
