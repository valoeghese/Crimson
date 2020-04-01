package modfest.teamgreen.world;

import modfest.teamgreen.block.ModBlocks;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class Features {
    public static final ConfiguredFeature<BranchedTreeFeatureConfig, ?> CRIMSON_TREE_FEATURE = Feature.ACACIA_TREE.configure(new BranchedTreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.CRIMSON_LOG.get().getDefaultState()), new SimpleBlockStateProvider(ModBlocks.CRIMSON_LEAVES.get().getDefaultState()), new AcaciaFoliagePlacer(2, 0)).baseHeight(5).heightRandA(2).heightRandB(2).trunkHeight(0).noVines().build());
}
