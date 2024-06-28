package ca.blutopia.removehud.mixin;

import ca.blutopia.removehud.HUDManager;
import ca.blutopia.removehud.RemoveHud;
import ca.blutopia.removehud.config.ModConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.*;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class RemoveHudButNotHand {

    private static final ModConfig ModConfig = RemoveHud.HudManagerInstance.ConfigInstance;
    private static final HUDManager HUD_MANAGER = RemoveHud.HudManagerInstance;

    @Inject(method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderHotBar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.HotBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 1)
    private int modifyHotbarX(int value) {
        return value + ModConfig.HotBarXOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyHotbarY(int value) {
        return value + ModConfig.HotBarYOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 1)
    private int modifyHotbarItemX(int value) {
        return value + ModConfig.HotBarXOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 2)
    private int modifyHotbarItemY(int value) {
        return value + ModConfig.HotBarYOffset;
    }

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.Crosshairs) {
            ci.cancel();
        }
    }

    @Inject(method = "renderVignetteOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void renderVignette(DrawContext context, Entity entity, CallbackInfo ci) {
        if (!ModConfig.Vignette) {
            ci.cancel();
        }
    }

    @Inject(method = "renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", at = @At("HEAD"), cancellable = true)
    public void renderOverlays(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (!ModConfig.OtherOverlays) {
            ci.cancel();
        }
    }

    @Inject(method = "renderPortalOverlay(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("HEAD"), cancellable = true)
    public void renderPortalOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        if (!ModConfig.PortalOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderSpyglassOverlay(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("HEAD"), cancellable = true)
    public void renderSpyglassOverlay(DrawContext context, float scale, CallbackInfo ci) {
        if (!ModConfig.SpyglassOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V", at = @At("HEAD"), cancellable = true)
    public void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (!ModConfig.Hp) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V"),
            index = 2)
    private int modifyHealthBarX(int value) {
        return value + ModConfig.HpXOffset;
    }

    @ModifyArg(
            method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V"),
            index = 3)
    private int modifyHealthBarY(int value) {
        return value + ModConfig.HpYOffset;
    }

    @Inject(method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V", at = @At("HEAD"), cancellable = true)
    private static void renderArmor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        if (!ModConfig.Armor) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 1)
    private static int modifyArmorBarX(int value) {
        return value + ModConfig.ArmorXOffset;
    }

    @ModifyArg(
            method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private static int modifyArmorBarY(int value) {
        return value + ModConfig.ArmorYOffset;
    }

    @Inject(method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V", at = @At("HEAD"), cancellable = true)
    public void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (!ModConfig.Food) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 1)
    private int modifyFoodBarX(int value) {
        return value + ModConfig.FoodXOffset;
    }

    @ModifyArg(
            method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyFoodBarY(int value) {
        return value + ModConfig.FoodYOffset;
    }

    @Redirect(method = "renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 0))
    private void renderAirBubbles(DrawContext instance, Identifier texture, int x, int y, int width, int height) {
        if (ModConfig.Air) {
            instance.drawGuiTexture(texture, x + ModConfig.AirXOffset, y + ModConfig.AirYOffset, width, height);
        }
    }
    @Redirect(method = "renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
    private void renderBurstingAirBubble(DrawContext instance, Identifier texture, int x, int y, int width, int height) {
        if (ModConfig.Air) {
            instance.drawGuiTexture(texture, x + ModConfig.AirXOffset, y + ModConfig.AirYOffset, width, height);
        }
    }

    @Inject(method = "renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    public void renderMountHealth(DrawContext context, CallbackInfo ci) {
        if (!ModConfig.Mounthealth) {
            ci.cancel();
        }
    }

    @Inject(method = "renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", at = @At("HEAD"), cancellable = true)
    public void renderMountJumpBar(JumpingMount mount, DrawContext context, int x, CallbackInfo ci) {
        if (!ModConfig.Mounthealth) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    public void renderHeldItemTooltip(DrawContext context, CallbackInfo ci) {
        if (!ModConfig.Itemtooltip) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 2)
    private int modifyHeldItemTooltipX(int value) {
        return value + ModConfig.ItemtooltipXOffset;
    }

    @ModifyArg(
            method = "renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 3)
    private int modifyHeldItemTooltipY(int value) {
        return value + ModConfig.ItemtooltipXOffset;
    }

    @Redirect(method = "renderMainHud(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderSpectatorMenu(SpectatorHud instance, DrawContext context) {
        if (ModConfig.SpectatorMenu) {
            instance.renderSpectatorMenu(context);
        }
    }

    @Redirect(method = "renderMainHud(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderSpectatorHud(SpectatorHud instance, DrawContext context) {
        if (ModConfig.SpectatorHud) {
            instance.render(context);
        }
    }

    @Inject(method = "renderStatusEffectOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.StatusEffectOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderScoreboardSidebar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.Scoreboard) {
            ci.cancel();
        }
    }

    @Inject(method = "renderPlayerList(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderPlayerList(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.PlayerList) {
            ci.cancel();
        }
    }

    @Inject(method = "renderChat(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderChat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.ChatHud) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAutosaveIndicator(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderAutosaveIndicator(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.Autosave) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", at = @At("HEAD"), cancellable = true)
    public void renderExperienceBar(DrawContext context, int x, CallbackInfo ci) {
        if (!ModConfig.Expbar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 1)
    private int modifyExperienceBarBackgroundX(int value) {
        return value + ModConfig.ExpbarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyExperienceBarBackgroundY(int value) {
        return value + ModConfig.ExpbarYOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIIIIIII)V"),
            index = 5)
    private int modifyExperienceBarForegroundX(int value) {
        return value + ModConfig.ExpbarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIIIIIII)V"),
            index = 6)
    private int modifyExperienceBarForegroundY(int value) {
        return value + ModConfig.ExpbarYOffset;
    }

    @Inject(method = "renderExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderExperienceLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.Expbar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"),
            index = 2)
    private int modifyExperienceLevelX(int value) {
        return value + ModConfig.ExpbarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"),
            index = 3)
    private int modifyExperienceLevelY(int value) {
        return value + ModConfig.ExpbarYOffset;
    }

    @Inject(method = "renderOverlayMessage(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderOverlayMessage(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.OverlayMessage) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderOverlayMessage(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 2)
    private int modifyOverlayMessageX(int value) {
        return value + ModConfig.OverlayMessageXOffset;
    }

    @ModifyArg(
            method = "renderOverlayMessage(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 3)
    private int modifyOverlayMessageY(int value) {
        return value + ModConfig.OverlayMessageYOffset;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(CallbackInfo ci) {
        if (ModConfig.removeHud) {
            ci.cancel();
        }
    }
}
