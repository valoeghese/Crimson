package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.CrimsonBiomeFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class CrimsonForestBiome extends CrimsonBaseBiome {
	public CrimsonForestBiome() {
		super(new Properties()
				.category(Biome.Category.FOREST)
				.depth(0.2f)
				.scale(0.38f));

		DefaultBiomeFeatures.addDefaultLakes(this);
		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_TREE_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(2, 0.1f, 1))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_LARGE_TREE_FEATURE.createDecoratedFeature(
				Decorator.COUNT_EXTRA_HEIGHTMAP.configure(new CountExtraChanceDecoratorConfig(0, 0.4f, 1))));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_THORN_FEATURE.createDecoratedFeature(
				Decorator.COUNT_CHANCE_HEIGHTMAP_DOUBLE.configure(new CountChanceDecoratorConfig(0, 0.3f))));
		DefaultBiomeFeatures.addForestGrass(this);
	}
}
