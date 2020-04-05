package modfest.teamgreen.world.crimson;

import com.google.common.collect.ImmutableList;

import modfest.teamgreen.content.block.ModBlocks;
import net.minecraft.world.gen.decorator.LeaveVineTreeDecorator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class CrimsonBiomeFeatures {
	public static final ConfiguredFeature<BranchedTreeFeatureConfig, ?> CRIMSON_TREE_FEATURE = Feature.ACACIA_TREE.configure(
			new BranchedTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(
							ModBlocks.CRIMSON_LOG.get().getDefaultState()),
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LEAVES.get().getDefaultState()),
					new AcaciaFoliagePlacer(2, 0))
			.baseHeight(6)
			.heightRandA(2)
			.heightRandB(4)
			.trunkHeight(4)
			.build()
			);

	public static final ConfiguredFeature<BranchedTreeFeatureConfig, ?> CRIMSON_LARGE_TREE_FEATURE = Feature.NORMAL_TREE.configure(
			new BranchedTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LOG.get().getDefaultState()),
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LEAVES.get().getDefaultState()),
					new BlobFoliagePlacer(1, 0))
			.baseHeight(14)
			.heightRandA(4)
			.foliageHeight(1 )
			.trunkHeight(12)
			.noVines()
			.build());

	public static final ConfiguredFeature<BranchedTreeFeatureConfig, ?> CRIMSON_VINE_TREE_FEATURE = Feature.NORMAL_TREE.configure(
			new BranchedTreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LOG.get().getDefaultState()),
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LEAVES.get().getDefaultState()),
					new BlobFoliagePlacer(3, 1))
			.baseHeight(4)
			.heightRandA(3)
			.foliageHeight(3)
			.maxWaterDepth(2)
			.treeDecorators(ImmutableList.of(new LeaveVineTreeDecorator()))
			.build());

	public static final ConfiguredFeature<TreeFeatureConfig, ?> CRIMSON_BUSH_FEATURE = Feature.JUNGLE_GROUND_BUSH.configure(
			new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LOG.get().getDefaultState()),
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_LEAVES.get().getDefaultState())
					)
			.build());

	public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> CRIMSON_THORN_FEATURE = Feature.RANDOM_PATCH.configure(
			new RandomPatchFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_THORN.get().getDefaultState()),
					new SimpleBlockPlacer())
			.tries(16)
			.build());

	public static final ConfiguredFeature<RandomPatchFeatureConfig, ?> CRIMSON_TENDRILS_FEATURE = Feature.RANDOM_PATCH.configure(
			new RandomPatchFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.CRIMSON_TENDRILS.get().getDefaultState()),
					new SimpleBlockPlacer())
			.tries(16)
			.build());
}
