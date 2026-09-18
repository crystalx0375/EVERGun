package crystal.evergun.client.config;

import crystal.guns.config.EnchantmentsConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.client.gui.widget.ButtonWidget;

public class EverGunConfigScreen extends Screen {

    private final Screen parent;

    private boolean enableEVERgun = EnchantmentsConfig.get().enableEVERgun;

    public EverGunConfigScreen(Screen parent) {
        super(Text.translatable("evergun.config.title"));
        this.parent = parent;


    }

    @Override
    protected void init() {
        super.init();

        addDrawableChild(
                net.minecraft.client.gui.widget.ButtonWidget.builder(
                        getEnableEVERgunText(),
                        button -> {
                            enableEVERgun = !enableEVERgun;
                            button.setMessage(getEnableEVERgunText());
                        }
                )
                        .dimensions(this.width /2 - 50,80,100,20)
                        .build()
        );
    }

    @Override
    public void render(
            net.minecraft.client.gui.DrawContext context,
            int mouseX, int mouseY, float delta
    ) {
        super.render(context, mouseX, mouseY, delta);

        context.drawCenteredTextWithShadow(
                this.textRenderer, Text.translatable("evergun.config.title"), this.width / 2, 30, 0xFFFFFF
        );
    }

    private Text getEnableEVERgunText() {
        return Text.literal(enableEVERgun ? "☑ " : "☐ ")
                ;
    }
}
