package modfest.teamgreen.world.crimson;

import modfest.teamgreen.CrimsonConfig.BiomeGen;
import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.world.BiomeFog;
import modfest.teamgreen.world.DefaultedBiome;
import modfest.teamgreen.world.CrimsonWorld;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.MineshaftFeature;
import net.minecraft.world.gen.feature.MineshaftFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;

public abstract class CrimsonBaseBiome extends DefaultedBiome implements BiomeFog {
	public CrimsonBaseBiome(BiomeGen biomeGen, Properties properties) {
		super(properties, new SettingDefaults()
				.precipitation(Biome.Precipitation.NONE)
				.configureSurfaceBuilder(SurfaceBuilder.DEFAULT, CrimsonWorld.CRIMSON_SURFACE_CONFIG)
				.depth(biomeGen.depth)
				.scale(biomeGen.scale)
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

		this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_TENDRILS_FEATURE.createDecoratedFeature(
				Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(CrimsonInit.CONFIG.tendrilsGenCount))));

		if (biomeGen.thornGenCount > 0) {
			this.addFeature(GenerationStep.Feature.VEGETAL_DECORATION, CrimsonBiomeFeatures.CRIMSON_THORN_FEATURE.createDecoratedFeature(
					Decorator.COUNT_HEIGHTMAP_DOUBLE.configure(new CountDecoratorConfig(biomeGen.thornGenCount))));
		}

		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.CHICKEN, 3, 1, 1));
		this.addSpawn(EntityCategory.CREATURE, new SpawnEntry(EntityType.RABBIT, 10, 1, 2));

		this.addSpawn(EntityCategory.AMBIENT, new SpawnEntry(EntityType.BAT, 10, 8, 8));

		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SPIDER, 250, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE, 95, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SKELETON, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.SLIME, 100, 4, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.ENDERMAN, 12, 1, 4));
		this.addSpawn(EntityCategory.MONSTER, new SpawnEntry(EntityType.WITCH, 5, 1, 1));

		this.fogDistanceChunks = biomeGen.fogDistanceChunks;
	}

	private final float fogDistanceChunks;

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
	public float getMaxSpawnLimit() {
		return 0.03f;
	}

	@Override
	public float modifyFogDistanceChunks(float originalDistanceChunks) {
		return this.fogDistanceChunks;
	}

	@Override
	public Vec3d getFogColour(int x, int z) {
		return CRIMSON_FOG_COLOUR;
	}

	private static final Vec3d CRIMSON_FOG_COLOUR = new Vec3d(0.85, 0.2, 0.2);
}
