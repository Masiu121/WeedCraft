package com.oxology.weedcraft.item;

import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.effect.WeedCraftEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class WeedJointItem extends Item {
    public WeedJointItem() {
        super(new Properties().tab(WeedCraft.creativeTab));
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable IRecipeType<?> recipeType) {
        return 25;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        int amplifier = 0;
        int time = 800;
        if(player.hasEffect(WeedCraftEffects.HIGH_EFFECT.get())) {
            amplifier = player.getEffect(WeedCraftEffects.HIGH_EFFECT.get()).getAmplifier()+1;
        }

        switch (amplifier) {
            case 1:
                time = 1200;
                break;
            case 2:
                time = 1600;
                break;
            case 3:
                time = 2000;
                break;
        }

        player.addEffect(new EffectInstance(WeedCraftEffects.HIGH_EFFECT.get(), time, amplifier));

        return super.use(world, player, hand);
    }
}
