package modfest.teamgreen.world;

import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModWorld {
	public static final TernarySurfaceConfig CRIMSON_SURFACE_CONFIG = new TernarySurfaceConfig(
			Blocks.GRASS_BLOCK.getDefaultState(), // temporary blocks until custom blocks are added
			Blocks.COARSE_DIRT.getDefaultState(),
			Blocks.GRAVEL.getDefaultState());

	public static void registerAll() {

	}

	public static final int FOG_BLEND_RADIUS = 6;
	public static final float FOG_BLEND_DIVISOR;

	static {
		final float sqrtDivisor = (FOG_BLEND_RADIUS * 2) + 1;
		FOG_BLEND_DIVISOR = sqrtDivisor * sqrtDivisor;
	}
}
