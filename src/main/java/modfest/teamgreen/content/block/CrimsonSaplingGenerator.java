package modfest.teamgreen.content.block;

import java.util.Random;

import modfest.teamgreen.world.crimson.CrimsonBiomeFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.BranchedTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class CrimsonSaplingGenerator extends SaplingGenerator {
    @Override
    protected ConfiguredFeature<BranchedTreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return random.nextInt(3) == 0 ? CrimsonBiomeFeatures.CRIMSON_LARGE_TREE_FEATURE : CrimsonBiomeFeatures.CRIMSON_TREE_FEATURE;
    }
}
