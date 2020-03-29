package modfest.teamgreen.world.crimson;

import net.minecraft.world.biome.Biome;

public class CrimsonForestBiome extends CrimsonBaseBiome {
	public CrimsonForestBiome() {
		super(new Properties()
				.category(Biome.Category.FOREST)
				.depth(0.2f)
				.scale(0.38f));
	}
}
