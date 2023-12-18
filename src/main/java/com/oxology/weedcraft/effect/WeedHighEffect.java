package com.oxology.weedcraft.effect;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class WeedHighEffect extends Effect {
    public WeedHighEffect(int color) {
        super(EffectType.NEUTRAL, color);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        super.applyEffectTick(entity, amplifier);
    }
}
