package modfest.teamgreen.world.crimson;

import net.minecraft.world.biome.Biome;

// Dark thorns lie stricken across the blood-red land.
// A few small sickly shrubs pop out every 10-metres or so, their leaves emanating a sense of death.
public class CrimsonBrushlandsBiome extends CrimsonBaseBiome {
	public CrimsonBrushlandsBiome() {
		super(new Properties()
				.category(Biome.Category.PLAINS)
				.depth(0.15f)
				.scale(0.05f));
	}
}
