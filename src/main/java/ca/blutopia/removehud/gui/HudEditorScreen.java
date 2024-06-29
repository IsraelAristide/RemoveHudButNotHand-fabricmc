package ca.blutopia.removehud.gui;

import ca.blutopia.removehud.RemoveHud;
import ca.blutopia.removehud.access.IEditorInGameHud;
import ca.blutopia.removehud.config.HUDItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.text.Text;

import static org.lwjgl.glfw.GLFW.*;

public class HudEditorScreen extends Screen {

    ButtonWidget moveLeftButton;
    ButtonWidget moveRightButton;
    ButtonWidget moveUpButton;
    ButtonWidget moveDownButton;
    // CyclingButtonWidget<OriginPoint> widg;
    CyclingButtonWidget<HUDItems> wid2;
    long _windowHandle;
    private SelectedItem _selected;

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        switch (keyCode) {
            case GLFW_KEY_W:
                _moveX(-1);
                break;
            case GLFW_KEY_S:
                _moveX(1);
                break;
            case GLFW_KEY_A:
                _moveY(-1);
                break;
            case GLFW_KEY_D:
                _moveY(1);
                break;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    public HudEditorScreen() {

        super(Text.of("HUD editor"));

        RemoveHud.HudManagerInstance.ConfigInstance.EnableEditor = true;

        assert this.client != null;

        _selected = new SelectedItem(HUDItems.Food);

        moveLeftButton = ButtonWidget.builder(Text.of("<"), this::MoveSelectedItemLeft)
                .dimensions(
                        0, 0,
                        20, 20
                )
                .build();

        moveRightButton = ButtonWidget.builder(Text.of(">"), this::MoveSelectedItemRight)
                .dimensions(
                        20, 0,
                        20, 20
                )
                .build();

        moveUpButton = ButtonWidget.builder(Text.of("^"), this::MoveSelectedItemUp)
                .dimensions(
                        40, 0,
                        20, 20
                )
                .build();

        moveDownButton = ButtonWidget.builder(Text.of("v"), this::MoveSelectedItemDown)
                .dimensions(
                        60, 0,
                        20, 20
                )
                .build();
//
//        try {
//            widg = new CyclingButtonWidget.Builder<OriginPoint>(x -> {
//                return switch (x) {
//
//                    case ORIGIN -> Text.of("origin");
//                    case TOPLEFT -> Text.of("topleft");
//                    case BOTTOMLEFFT -> Text.of("bottomleft");
//                    case TOPRIGHT -> Text.of("topright");
//                    case BOTTONRIGHT -> Text.of("bottomright");
//                };
//            }).initially(_selected.getOrigin())
//                    .values(OriginPoint.values())
//                    .build(20, 30, 120, 20, Text.of("Origin"), (sender, value) -> {
//                        try {
//                            _selected.setOrigin(value);
//                        } catch (NoSuchMethodException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//        } catch (NoSuchMethodException e) {
//            throw new RuntimeException(e);
//        }


        wid2 = new CyclingButtonWidget.Builder<HUDItems>(x->{
            return Text.of(x.name());
        }).initially(_selected.getSelected())
                .values(HUDItems.values())
                .build((MinecraftClient.getInstance().getWindow().getScaledWidth()/2)-60, 0, 120, 20, Text.of("Hud Item"), (sender,value) -> {
                    _selected.setSelected(value);
//                    try {
//                        widg.setValue(_selected.getOrigin());
//                    } catch (NoSuchMethodException e) {
//                        throw new RuntimeException(e);
//                    }
                });


        _windowHandle = MinecraftClient.getInstance().getWindow().getHandle();

    }

    public void MoveSelectedItemLeft(ButtonWidget button) {
        _moveY(-1);
    }

    private void _moveX(int offset) {
        try {
            _selected.MoveY(offset);
        } catch (NoSuchMethodException e) {
            return;
        }
    }

    public void MoveSelectedItemRight(ButtonWidget button) {
        _moveY(1);
    }

    public void MoveSelectedItemDown(ButtonWidget button) {
        _moveX(1);
    }

    private void _moveY(int offset) {
        try {
            _selected.MoveX(offset);
        } catch (NoSuchMethodException e) {
            return;
        }
    }

    public void MoveSelectedItemUp(ButtonWidget button) {
        _moveX(-1);
    }


    @Override
    protected void init() {

        super.init();
        RemoveHud.HudManagerInstance.ConfigInstance.EnableEditor = true;
//        addDrawableChild(moveLeftButton);
//        addDrawableChild(moveRightButton);
//        addDrawableChild(moveDownButton);
//        addDrawableChild(moveUpButton);

        // addDrawableChild(widg);
        addDrawableChild(wid2);

    }

    @Override
    public boolean shouldCloseOnEsc() {
        RemoveHud.HudManagerInstance.ConfigInstance.EnableEditor = false;
        return super.shouldCloseOnEsc();
    }

    @Override
    public void close() {
        RemoveHud.HudManagerInstance.ConfigInstance.EnableEditor = false;
        super.close();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {

        if (!checkButtonOver(mouseX, mouseY)) return false;

        try {
            _selected.MoveX(deltaX > 0? 1 : deltaX == 0? 0 : -1);
        } catch (NoSuchMethodException e) {
            return false;
        }

        try {
            _selected.MoveY(deltaY > 0? 1 : deltaY == 0? 0 : -1);
        } catch (NoSuchMethodException e) {
            return false;
        }

        // System.out.println(deltaX + " / " + deltaY);

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);

    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {

        glfwSetInputMode(_windowHandle, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        return super.mouseClicked(mouseX, mouseY, button);

    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {

        glfwSetInputMode(_windowHandle, GLFW_CURSOR, GLFW_CURSOR_NORMAL);

        return super.mouseReleased(mouseX, mouseY, button);

    }


    private boolean checkButtonOver(double mouseX, double mouseY) {
        if (moveRightButton.isMouseOver(mouseX, mouseY))
            return false;

        if (moveLeftButton.isMouseOver(mouseX, mouseY))
            return false;

        if (moveDownButton.isMouseOver(mouseX, mouseY))
            return false;

        if (moveUpButton.isMouseOver(mouseX, mouseY))
            return false;
        return true;
    }

    @Override
    public void blur() {
        return;
    }

    @Override
    protected void applyBlur(float delta) {
        return;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        assert client != null;
        try {
            ((IEditorInGameHud) client.inGameHud).EditorMode(context);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void renderDarkening(DrawContext context) {
        return;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}