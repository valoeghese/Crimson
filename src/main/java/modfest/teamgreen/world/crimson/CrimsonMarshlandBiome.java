package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.CrimsonBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class CrimsonMarshlandBiome extends CrimsonBaseBiome {
	public CrimsonMarshlandBiome() {
		super(new Properties()
				.category(Biome.Category.SWAMP)
				.depth(0.03f)
				.scale(0.125f));

		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addSwampVegetation(this);

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_BUSH_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(1, 0.1f, 1))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_VINE_TREE_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.05f, 4))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_THORN_FEATURE.createDecoratedFeature(
				Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(2))));
		DefaultBiomeFeatures.addTaigaGrass(this);
	}
}
