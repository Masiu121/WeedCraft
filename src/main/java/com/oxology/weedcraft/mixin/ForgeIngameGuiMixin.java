package com.oxology.weedcraft.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.effect.WeedCraftEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.*;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
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
    private ResourceLocation blur_light;
    private ResourceLocation blur_medium;
    private ResourceLocation blur_strong;
    private ResourceLocation vignette1;
    private ResourceLocation vignette2;
    private ResourceLocation vignette3;

    public ForgeIngameGuiMixin(Minecraft minecraft) {
        super(minecraft);
    }

    @Inject(method = "render", at = @At(value = "FIELD", ordinal = 0, target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;minecraft:Lnet/minecraft/client/Minecraft;"))
    private void renderTint(MatrixStack matrices, float partialTicks, CallbackInfo ci) {
        if(blur_light == null) {
            blur_light = new ResourceLocation(WeedCraft.MOD_ID, "shaders/post/blur_light.json");
            blur_medium = new ResourceLocation(WeedCraft.MOD_ID, "shaders/post/blur_medium.json");
            blur_strong = new ResourceLocation(WeedCraft.MOD_ID, "shaders/post/blur_strong.json");
            vignette1 = new ResourceLocation(WeedCraft.MOD_ID, "textures/vignette1.png");
            vignette2 = new ResourceLocation(WeedCraft.MOD_ID, "textures/vignette2.png");
            vignette3 = new ResourceLocation(WeedCraft.MOD_ID, "textures/vignette3.png");
        }

        ClientPlayerEntity player = minecraft.player;
        if(player == null) return;
        if(!player.hasEffect(WeedCraftEffects.HIGH_EFFECT.get())) {
            if(minecraft.gameRenderer.currentEffect() != null)
                minecraft.gameRenderer.shutdownEffect();

            return;
        }

        EffectInstance effect = player.getEffect(WeedCraftEffects.HIGH_EFFECT.get());
        switch (effect.getAmplifier()) {
            case 0:
                draw(vignette1, 0.0f, 1.0f, 1.0f);
                draw(vignette2, 1.0f, 1.0f, 1.0f);
                break;
            case 1:
                draw(vignette2, 0.0f, 1.0f, 1.0f);
                draw(vignette3, 1.0f, 1.0f, 1.0f);
                if(minecraft.gameRenderer.currentEffect() == null) {
                    minecraft.gameRenderer.loadEffect(blur_light);
                }
                break;
            case 2:
                draw(vignette2, 0.0f, 1.0f, 1.0f);
                draw(vignette3, 1.0f, 1.0f, 1.0f);
                if((minecraft.gameRenderer.currentEffect() != null && minecraft.gameRenderer.currentEffect().getName().equals("weedcraft:shaders/post/blur_light.json")) || minecraft.gameRenderer.currentEffect() == null) {
                    minecraft.gameRenderer.loadEffect(blur_medium);
                }
                break;
            case 3:
                draw(vignette2, 0.0f, 1.0f, 1.0f);
                draw(vignette3, 1.0f, 1.0f, 1.0f);
                if((minecraft.gameRenderer.currentEffect() != null && minecraft.gameRenderer.currentEffect().getName().equals("weedcraft:shaders/post/blur_medium.json")) || minecraft.gameRenderer.currentEffect() == null) {
                    minecraft.gameRenderer.loadEffect(blur_strong);
                }
                break;
        }
    }

    private void draw(ResourceLocation texture, float r, float g, float b) {
        RenderSystem.disableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.depthMask(false);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        RenderSystem.color4f(r, g, b, 1.0f);
        RenderSystem.disableAlphaTest();
        minecraft.getTextureManager().bind(texture);

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
}