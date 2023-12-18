package com.oxology.weedcraft.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oxology.weedcraft.WeedCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.*;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.opengl.GL;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.lang.reflect.Field;

@Mixin(ForgeIngameGui.class)
public abstract class ForgeIngameGuiMixin extends IngameGui {
    float opacity = 0;
    boolean fadingIn = true;
    private ShaderGroup shaderGroup;
    boolean justBlurred = false;

    public ForgeIngameGuiMixin(Minecraft mc) {
        super(mc);
    }

    @Inject(method = "render", at = @At(value = "FIELD", ordinal = 0,
            target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;minecraft:Lnet/minecraft/client/Minecraft;"))
    private void renderTint(MatrixStack matrices, float partialTicks, CallbackInfo ci)
    {
        renderGameOverlay();
        if(fadingIn) {
            opacity += partialTicks/10;
        } else {
            opacity -= partialTicks/10;
        }

        if(opacity <= 0) {
            opacity = 0;
            fadingIn = true;
        } else if(opacity >= 1) {
            opacity = 1;
            fadingIn = false;
        }
    }

    public void renderGameOverlay() {
        draw(new ResourceLocation(WeedCraft.MOD_ID, "textures/vignette3.png"), 0.0f, 1.0f, 1.0f, opacity);
        draw(new ResourceLocation(WeedCraft.MOD_ID, "textures/vignette2.png"), 1.0f, 1.0f, 1.0f, 0.5f);
        blurScreen();
    }

    private void draw(ResourceLocation image, float r, float g, float b, float opacity) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        //RenderSystem.defaultBlendFunc();
        //RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        RenderSystem.color4f(r, g, b, opacity);
        RenderSystem.disableAlphaTest();
        minecraft.getTextureManager().bind(image);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuilder();
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.vertex(0.0D, minecraft.getWindow().getGuiScaledHeight(), -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferBuilder.vertex(minecraft.getWindow().getGuiScaledWidth(), minecraft.getWindow().getGuiScaledHeight(), -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferBuilder.vertex(minecraft.getWindow().getGuiScaledWidth(), 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferBuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tessellator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.enableAlphaTest();
        RenderSystem.color4f(0.8F, 0.2F, 0.7F, 1.0F);
    }

    private void blurScreen()  {
        if (!justBlurred && minecraft.player.hasEffect(Effects.REGENERATION) && minecraft.gameRenderer.currentEffect() == null) {
            minecraft.gameRenderer.loadEffect(new ResourceLocation("shaders/post/blur.json"));
            justBlurred = true;
        }

        if(!minecraft.player.hasEffect(Effects.REGENERATION)) {
            justBlurred = false;
            minecraft.gameRenderer.shutdownEffect();
        }
    }
}