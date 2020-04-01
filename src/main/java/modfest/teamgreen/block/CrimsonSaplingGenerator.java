package modfest.teamgreen.block;

import modfest.teamgreen.world.Features;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

import java.util.Random;

public class CrimsonSaplingGenerator extends SaplingGenerator {
    @Override
    protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return Features.CRIMSON_TREE_FEATURE;
    }
}
