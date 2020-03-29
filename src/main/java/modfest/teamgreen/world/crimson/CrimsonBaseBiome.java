package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.BiomeFog;
import modfest.teamgreen.world.DefaultedBiome;
import modfest.teamgreen.world.ModWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public abstract class CrimsonBaseBiome extends DefaultedBiome implements BiomeFog {
	public CrimsonBaseBiome(Properties properties) {
		super(properties, new SettingDefaults()
				.precipitation(Biome.Precipitation.NONE)
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, ModWorld.CRIMSON_SURFACE_CONFIG)
				.temperature(0.8f)
				.downfall(0.1f));
	}

	@Override
	public int getGrassColorAt(double x, double z) {
		return 0xed4621;
	}

	@Override
	public int getFoliageColor() {
		return 0xe51666;
	}

	@Override
	public int getSkyColor() {
		return 0xe57a62;
	}

	@Override
	public float modifyFogDistanceChunks(float originalDistanceChunks) {
		return 4.5f;
	}
}
