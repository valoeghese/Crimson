package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.Features;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;

public class CrimsonForestBiome extends CrimsonBaseBiome {
	public CrimsonForestBiome() {
		super(new Properties()
				.category(Biome.Category.FOREST)
				.depth(0.2f)
				.scale(0.38f));
		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, Features.CRIMSON_TREE_FEATURE);
	}
}
