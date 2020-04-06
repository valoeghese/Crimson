package modfest.teamgreen.world.crimson;

import modfest.teamgreen.CrimsonConfig.BiomeGen;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class CrimsonMarshlandBiome extends CrimsonBaseBiome {
	public CrimsonMarshlandBiome(BiomeGen biomeGen) {
		super(biomeGen, new Properties()
				.category(Biome.Category.SWAMP));

		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addSwampVegetation(this);

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_BUSH_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.1f, 1))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_VINE_TREE_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.05f, 4))));
		DefaultBiomeFeatures.addTaigaGrass(this);
	}

	@Override
	public float modifyFogDistanceChunks(float originalDistanceChunks) {
		return 3.8f;
	}
}
