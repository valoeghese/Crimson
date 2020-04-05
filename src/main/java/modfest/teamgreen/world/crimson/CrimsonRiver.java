package modfest.teamgreen.world.crimson;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class CrimsonRiver extends CrimsonBaseBiome {
	public CrimsonRiver() {
		super(new Properties()
				.category(Biome.Category.FOREST)
				.depth(-0.5f)
				.scale(0.0f));

		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);
	}
}
