package ca.blutopia.removehud.access;

import net.minecraft.client.gui.DrawContext;

public interface IEditorInGameHud {
    void EditorMode(DrawContext context) throws NoSuchFieldException, IllegalAccessException;
}
