package com.oxology.weedcraft.effect;

import com.oxology.weedcraft.WeedCraft;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WeedCraftEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, WeedCraft.MOD_ID);

    public static final RegistryObject<Effect> HIGH_EFFECT = EFFECTS.register("high_effect", () -> new WeedHighEffect(2));
}
