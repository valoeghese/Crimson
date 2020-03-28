package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.DefaultedBiome;
import net.minecraft.world.biome.Biome;

public class CrimsonBaseBiome extends DefaultedBiome {
	protected CrimsonBaseBiome(Properties properties) {
		super(properties, new SettingDefaults()
				.precipitation(Biome.Precipitation.NONE)
				.temperature(0.8f)
				.downfall(0.1f));
	}
}
