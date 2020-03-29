package modfest.teamgreen.world.crimson;

import net.minecraft.world.biome.Biome;

public class CrimsonMarshlandBiome extends CrimsonBaseBiome {
	public CrimsonMarshlandBiome() {
		super(new Properties()
				.category(Biome.Category.SWAMP)
				.depth(0.05f)
				.scale(0.125f));
	}
}
