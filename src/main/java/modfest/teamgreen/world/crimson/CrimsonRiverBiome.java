package modfest.teamgreen.world.crimson;

import modfest.teamgreen.CrimsonConfig.BiomeGen;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;

public class CrimsonRiverBiome extends CrimsonBaseBiome {
	public CrimsonRiverBiome(BiomeGen biomeGen) {
		super(biomeGen, new Properties()
				.category(Biome.Category.FOREST));

		DefaultBiomeFeatures.addDefaultMushrooms(this);
		DefaultBiomeFeatures.addDefaultVegetation(this);
	}
}
