package modfest.teamgreen.world.crimson;

import modfest.teamgreen.world.BiomeFog;
import modfest.teamgreen.world.DefaultedBiome;
import modfest.teamgreen.world.ModWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public abstract class CrimsonBaseBiome extends DefaultedBiome implements BiomeFog {
	public CrimsonBaseBiome(Properties properties) {
		super(properties, new SettingDefaults()
				.precipitation(Biome.Precipitation.NONE)
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, ModWorld.CRIMSON_SURFACE_CONFIG)
				.temperature(0.8f)
				.downfall(0.1f)
				.waterColor(0xd81406)
				.waterFogColor(0xf42264));

		this.addStructureFeature(Feature.MINESHAFT.configure(new MineshaftFeatureConfig(0.004D, MineshaftFeature.Type.NORMAL)));
		this.addStructureFeature(Feature.STRONGHOLD.configure(FeatureConfig.DEFAULT));
		DefaultBiomeFeatures.addLandCarvers(this);
		DefaultBiomeFeatures.addDefaultStructures(this);
		DefaultBiomeFeatures.addDungeons(this);
		DefaultBiomeFeatures.addMineables(this);
		DefaultBiomeFeatures.addDefaultOres(this);
		DefaultBiomeFeatures.addDefaultDisks(this);
		DefaultBiomeFeatures.addSprings(this);
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
		return 4.3f;
	}

	@Override
	public Vec3d getFogColour(int x, int z) {
		return CRIMSON_FOG_COLOUR;
	}

	private static final Vec3d CRIMSON_FOG_COLOUR = new Vec3d(0.85, 0.2, 0.2);
}
