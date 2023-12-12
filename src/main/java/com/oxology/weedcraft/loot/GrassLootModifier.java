package com.oxology.weedcraft.loot;

import com.google.gson.JsonObject;
import com.oxology.weedcraft.WeedCraft;
import com.oxology.weedcraft.util.WeedVariant;
import com.oxology.weedcraft.item.WeedCraftItems;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = WeedCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GrassLootModifier extends LootModifier {
    @SubscribeEvent
    public static void registerModifiers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> ev)
    {
        ev.getRegistry().register(new GrassLootModifierSerializer().setRegistryName(WeedCraft.MOD_ID, "weed_seeds_modifier"));
    }

    protected GrassLootModifier(ILootCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Nonnull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        WeedVariant weedVariant;
        do {
            weedVariant = WeedVariant.values()[new Random().nextInt(WeedVariant.values().length)];
        } while(weedVariant == WeedVariant.NONE);

        generatedLoot.add(new ItemStack(WeedCraftItems.getSeedsByVariant(weedVariant).get()));
        return generatedLoot;
    }

    public static class GrassLootModifierSerializer extends GlobalLootModifierSerializer<GrassLootModifier> {
        @Override
        public GrassLootModifier read(ResourceLocation location, JsonObject object, ILootCondition[] ailootcondition) {
            return new GrassLootModifier(ailootcondition);
        }

        @Override
        public JsonObject write(GrassLootModifier instance) {
            return new JsonObject();
        }
    }
}
