package com.oxology.weedcraft.block;

import com.oxology.weedcraft.item.WeedCraftItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

public class WeedCropBlock extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 3.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 10.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 13.0D, 16.0D),
            Block.box(0.0D, -1.0D, 0.0D, 16.0D, 15.0D, 16.0D),
    };

    private static final IntegerProperty AGE = IntegerProperty.create("age", 0, 12);

    public WeedCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, IBlockReader pGetter, BlockPos pPos, ISelectionContext pContext) {
        return SHAPE_BY_AGE[getAge(pState)];
    }

    @Override
    public boolean canSustainPlant(BlockState pState, IBlockReader pWorld, BlockPos pPos, Direction pFacing, IPlantable pPlantable) {
        return super.mayPlaceOn(pState, pWorld, pPos);
    }

    public void randomTick(BlockState pState, ServerWorld pWorld, BlockPos pPos, Random pRandom) {
        if (!pWorld.isAreaLoaded(pPos, 1)) return;
        if (pWorld.getRawBrightness(pPos, 0) >= 9) {
            int currentAge = this.getAge(pState);

            if (currentAge <= 6) {
                float growthSpeed = getGrowthSpeed(this, pWorld, pPos);

                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(pWorld, pPos, pState, pRandom.nextInt((int)(25.0F / growthSpeed) + 1) == 0)) {
                    if((currentAge+1) > 2 && (pWorld.getBlockState(pPos.above(1)).is(Blocks.AIR) || pWorld.getBlockState(pPos.above(1)).is(this))) {
                        pWorld.setBlock(pPos, getStateForAge(currentAge+1), 2);
                        pWorld.setBlock(pPos.above(1), getStateForAge(currentAge+6), 2);
                    } else {
                        pWorld.setBlock(pPos, getStateForAge(currentAge+1), 2);
                    }

                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(pWorld, pPos, pState);
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState pState, IWorldReader pGetter, BlockPos pPos) {
        return super.canSurvive(pState, pGetter, pPos) ||
                pGetter.getBlockState(pPos.below(1)).is(this) &&
                pGetter.getBlockState(pPos.below(1)).getValue(AGE) > 2;
    }

    @Override
    public void growCrops(World pWorld, BlockPos pPos, BlockState pState) {
        int nextAge = this.getAge(pState) + this.getBonemealAgeIncrease(pWorld);
        if(nextAge > 8) {
            nextAge = 8;
        }

        if(nextAge > 2 && (pWorld.getBlockState(pPos.above(1)).is(Blocks.AIR) || pWorld.getBlockState(pPos.above(1)).is(this))) {
            pWorld.setBlock(pPos, getStateForAge(nextAge-1), 2);
            pWorld.setBlock(pPos.above(1), getStateForAge((nextAge-1)+5), 2);
        } else {
            pWorld.setBlock(pPos, getStateForAge(nextAge-1), 2);
        }
    }

    @Override
    public int getMaxAge() {
        return 12;
    }

    @Override
    protected IItemProvider getBaseSeedId() {
        return WeedCraftItems.WEED_SEEDS.get();
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }
}
