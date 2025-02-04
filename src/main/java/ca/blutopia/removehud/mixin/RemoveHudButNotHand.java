package ca.blutopia.removehud.mixin;

import ca.blutopia.removehud.ModConfig;
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
    @Inject(method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderHotBar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.HotBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyHotbarX(int value) {
        return value + ModConfig.INSTANCE.HotBarXOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3)
    private int modifyHotbarY(int value) {
        return value + ModConfig.INSTANCE.HotBarYOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 1)
    private int modifyHotbarItemX(int value) {
        return value + ModConfig.INSTANCE.HotBarXOffset;
    }

    @ModifyArg(
            method = "renderHotbar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(Lnet/minecraft/client/gui/DrawContext;IILnet/minecraft/client/render/RenderTickCounter;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"),
            index = 2)
    private int modifyHotbarItemY(int value) {
        return value + ModConfig.INSTANCE.HotBarYOffset;
    }

    @Inject(method = "renderCrosshair(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderCrosshair(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.Crosshairs) {
            ci.cancel();
        }
    }

    @Inject(method = "renderVignetteOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void renderVignette(DrawContext context, Entity entity, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.Vignette) {
            ci.cancel();
        }
    }

    @Inject(method = "renderOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/util/Identifier;F)V", at = @At("HEAD"), cancellable = true)
    public void renderOverlays(DrawContext context, Identifier texture, float opacity, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.OtherOverlays) {
            ci.cancel();
        }
    }

    @Inject(method = "renderPortalOverlay(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("HEAD"), cancellable = true)
    public void renderPortalOverlay(DrawContext context, float nauseaStrength, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.PortalOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderSpyglassOverlay(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("HEAD"), cancellable = true)
    public void renderSpyglassOverlay(DrawContext context, float scale, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.SpyglassOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V", at = @At("HEAD"), cancellable = true)
    public void renderHealthBar(DrawContext context, PlayerEntity player, int x, int y, int lines, int regeneratingHeartIndex, float maxHealth, int lastHealth, int health, int absorption, boolean blinking, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.HpBar) {
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
        return value + ModConfig.INSTANCE.HpXOffset;
    }

    @ModifyArg(
            method = "renderHealthBar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIIIFIIIZ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/hud/InGameHud;drawHeart(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/gui/hud/InGameHud$HeartType;IIZZZ)V"),
            index = 3)
    private int modifyHealthBarY(int value) {
        return value + ModConfig.INSTANCE.HpYOffset;
    }

    @Inject(method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V", at = @At("HEAD"), cancellable = true)
    private static void renderArmor(DrawContext context, PlayerEntity player, int i, int j, int k, int x, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.ArmorBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private static int modifyArmorBarX(int value) {
        return value + ModConfig.INSTANCE.ArmorXOffset;
    }

    @ModifyArg(
            method = "renderArmor(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;IIII)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3)
    private static int modifyArmorBarY(int value) {
        return value + ModConfig.INSTANCE.ArmorYOffset;
    }

    @Inject(method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V", at = @At("HEAD"), cancellable = true)
    public void renderFood(DrawContext context, PlayerEntity player, int top, int right, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.HungerBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyFoodBarX(int value) {
        return value + ModConfig.INSTANCE.FoodXOffset;
    }

    @ModifyArg(
            method = "renderFood(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3)
    private int modifyFoodBarY(int value) {
        return value + ModConfig.INSTANCE.FoodYOffset;
    }

    @Inject(method = "renderAirBubbles(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;III)V", at = @At("HEAD"), cancellable = true)
    public void renderAirBubbles(DrawContext context, PlayerEntity player, int heartCount, int top, int left, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.AirBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderAirBubbles(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;III)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyAirBubblesX(int value) {
        return value + ModConfig.INSTANCE.AirXOffset;
    }

    @ModifyArg(
            method = "renderAirBubbles(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/entity/player/PlayerEntity;III)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3)
    private int modifyAirBubblesY(int value) {
        return value + ModConfig.INSTANCE.AirYOffset;
    }

    @Inject(method = "renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    public void renderMountHealth(DrawContext context, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.MountHealth) {
            ci.cancel();
        }
    }

    @Inject(method = "renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", at = @At("HEAD"), cancellable = true)
    public void renderMountJumpBar(JumpingMount mount, DrawContext context, int x, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.MountJumpbar) {
            ci.cancel();
        }
    }

    @Inject(method = "renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", at = @At("HEAD"), cancellable = true)
    public void renderHeldItemTooltip(DrawContext context, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.HeldItemTooltip) {
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
        return value + ModConfig.INSTANCE.HeldItemTooltipXOffset;
    }

    @ModifyArg(
            method = "renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 3)
    private int modifyHeldItemTooltipY(int value) {
        return value + ModConfig.INSTANCE.HeldItemTooltipYOffset;
    }

    @Redirect(method = "renderMainHud(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderSpectatorMenu(SpectatorHud instance, DrawContext context) {
        if (ModConfig.INSTANCE.SpectatorMenu) {
            instance.renderSpectatorMenu(context);
        }
    }

    @Redirect(method = "renderMainHud(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
    public void renderSpectatorHud(SpectatorHud instance, DrawContext context) {
        if (ModConfig.INSTANCE.SpectatorHud) {
            instance.render(context);
        }
    }

    @Inject(method = "renderStatusEffectOverlay(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderStatusEffectOverlay(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.StatusEffectOverlay) {
            ci.cancel();
        }
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderScoreboardSidebar(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.ScoreBoard) {
            ci.cancel();
        }
    }

    @Inject(method = "renderPlayerList(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderPlayerList(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.PlayerList) {
            ci.cancel();
        }
    }

    @Inject(method = "renderChat(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderChat(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.ChatHud) {
            ci.cancel();
        }
    }

    @Inject(method = "renderAutosaveIndicator(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderAutosaveIndicator(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.Autosave) {
            ci.cancel();
        }
    }

    @Inject(method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", at = @At("HEAD"), cancellable = true)
    public void renderExperienceBar(DrawContext context, int x, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.ExpBar) {
            ci.cancel();
        }
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 2)
    private int modifyExperienceBarBackgroundX(int value) {
        return value + ModConfig.INSTANCE.ExpBarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"),
            index = 3)
    private int modifyExperienceBarBackgroundY(int value) {
        return value + ModConfig.INSTANCE.ExpBarYOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIIIIIII)V"),
            index = 6)
    private int modifyExperienceBarForegroundX(int value) {
        return value + ModConfig.INSTANCE.ExpBarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIIIIIII)V"),
            index = 7)
    private int modifyExperienceBarForegroundY(int value) {
        return value + ModConfig.INSTANCE.ExpBarYOffset;
    }

    @Inject(method = "renderExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderExperienceLevel(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.ExpBar) {
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
        return value + ModConfig.INSTANCE.ExpBarXOffset;
    }

    @ModifyArg(
            method = "renderExperienceLevel(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;IIIZ)I"),
            index = 3)
    private int modifyExperienceLevelY(int value) {
        return value + ModConfig.INSTANCE.ExpBarYOffset;
    }

    @Inject(method = "renderOverlayMessage(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V", at = @At("HEAD"), cancellable = true)
    public void renderOverlayMessage(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!ModConfig.INSTANCE.OverlayMessage) {
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
        return value + ModConfig.INSTANCE.OverlayMessageXOffset;
    }

    @ModifyArg(
            method = "renderOverlayMessage(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/DrawContext;drawTextWithBackground(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIII)I"),
            index = 3)
    private int modifyOverlayMessageY(int value) {
        return value + ModConfig.INSTANCE.OverlayMessageYOffset;
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    public void render(CallbackInfo ci) {
        if (ModConfig.INSTANCE.removeHud) {
            ci.cancel();
        }
    }
}
