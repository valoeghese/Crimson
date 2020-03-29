package modfest.teamgreen.world;

import modfest.teamgreen.ModInit;
import modfest.teamgreen.world.crimson.CrimsonBrushlandsBiome;
import modfest.teamgreen.world.crimson.CrimsonForestBiome;
import modfest.teamgreen.world.crimson.CrimsonMarshlandBiome;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModWorld {
	public static final TernarySurfaceConfig CRIMSON_SURFACE_CONFIG = new TernarySurfaceConfig(
			Blocks.GRASS_BLOCK.getDefaultState(), // temporary blocks until custom blocks are added
			Blocks.COARSE_DIRT.getDefaultState(),
			Blocks.GRAVEL.getDefaultState());

	public static final Biome CRIMSON_FOREST = new CrimsonForestBiome();
	public static final Biome CRIMSON_BRUSHLAND = new CrimsonBrushlandsBiome();
	public static final Biome CRIMSON_MARSHLAND = new CrimsonMarshlandBiome();

	public static void registerAll() {
		registerBiome(CRIMSON_FOREST, "crimson_forest");
		registerBiome(CRIMSON_BRUSHLAND, "crimson_brushland");
		registerBiome(CRIMSON_MARSHLAND, "crimson_marshland");
	}

	private static final void registerBiome(Biome biome, String id) {
		Registry.register(Registry.BIOME, ModInit.from(id), biome);
		ReflectionHacks.injectOverworldBiome(biome);
	}

	public static final int FOG_BLEND_RADIUS = 6;
	public static final float FOG_BLEND_DIVISOR;

	static {
		final float sqrtDivisor = (FOG_BLEND_RADIUS * 2) + 1;
		FOG_BLEND_DIVISOR = sqrtDivisor * sqrtDivisor;
	}
}
