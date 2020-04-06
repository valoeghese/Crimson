package modfest.teamgreen.world.crimson;

import modfest.teamgreen.CrimsonConfig.BiomeGen;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

// Dark thorns lie stricken across the blood-red land.
// A few small sickly shrubs pop out every 10-metres or so, their leaves emanating a sense of death.
public class CrimsonBrushlandsBiome extends CrimsonBaseBiome {
	public CrimsonBrushlandsBiome(BiomeGen biomeGen) {
		super(biomeGen, new Properties()
				.category(Biome.Category.PLAINS));

		DefaultBiomeFeatures.addDesertLakes(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_BUSH_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.4f, 3))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_LARGE_TREE_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.03f, 1))));
		DefaultBiomeFeatures.addDefaultGrass(this);
	}
}
