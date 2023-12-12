package com.oxology.weedcraft.block;

import com.oxology.weedcraft.util.WeedVariant;
import com.oxology.weedcraft.item.WeedCraftItems;
import com.oxology.weedcraft.item.WeedSeedsItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class WeedPotBlock extends Block {
    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);
    public static final EnumProperty<WeedVariant> VARIANT = EnumProperty.create("variant", WeedVariant.class);

    public WeedPotBlock(Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState state, IBlockReader block, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return state.getValue(VARIANT) != WeedVariant.NONE && state.getValue(AGE) < 7;
    }

    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isAreaLoaded(pos, 1)) return;
        if (world.getRawBrightness(pos, 0) >= 9) {
            int currentAge = state.getValue(AGE);
            boolean grow = random.nextInt((int)(25.0F / 4) + 1) == 0;
            if(grow) world.setBlock(pos, state.setValue(AGE, currentAge+1), 2);
        }
    }

    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickBlock event) {
        BlockState state = event.getWorld().getBlockState(event.getPos());
        if(state.is(WeedCraftBlocks.POT.get())) {
            if(state.getValue(VARIANT) == WeedVariant.NONE) {
                Item item = event.getPlayer().getItemInHand(Hand.MAIN_HAND).getItem();
                if(item instanceof WeedSeedsItem) {
                    if(!event.getPlayer().isCreative())
                        event.getPlayer().getItemInHand(Hand.MAIN_HAND).shrink(1);

                    event.getWorld().setBlock(event.getPos(), state.setValue(VARIANT, ((WeedSeedsItem) item).getWeedVariant()), 2);
                }
            } else {
                if(event.getPlayer().getItemInHand(Hand.MAIN_HAND).sameItem(new ItemStack(Items.SHEARS))) {
                    if(state.getValue(AGE) == 7) {
                        event.getWorld().addFreshEntity(new ItemEntity(
                                event.getWorld(),
                                event.getPos().getX(),
                                event.getPos().getY(),
                                event.getPos().getZ(),
                                new ItemStack(WeedCraftItems.getPlantByVariant(state.getValue(VARIANT)).get())
                        ));
                        event.getWorld().addFreshEntity(new ItemEntity(
                                event.getWorld(),
                                event.getPos().getX(),
                                event.getPos().getY(),
                                event.getPos().getZ(),
                                new ItemStack(WeedCraftItems.getSeedsByVariant(state.getValue(VARIANT)).get())
                        ));
                    }

                    event.getWorld().addFreshEntity(new ItemEntity(
                            event.getWorld(),
                            event.getPos().getX(),
                            event.getPos().getY(),
                            event.getPos().getZ(),
                            new ItemStack(WeedCraftItems.getSeedsByVariant(state.getValue(VARIANT)).get())
                    ));

                    event.getWorld().setBlock(event.getPos(), state.setValue(VARIANT, WeedVariant.NONE).setValue(AGE, 0), 2);
                }
            }
        }
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
        pBuilder.add(VARIANT);
    }
}
