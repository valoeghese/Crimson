package modfest.teamgreen.logic;

import modfest.teamgreen.world.BiomeFog;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class CrimsonClientFogCalculations {
	public static float calculateFogDistanceChunks(World world, double x, double z, float originalResultChunks, float originalResult) {
		int xi = MathHelper.floor(x);
		int zi = MathHelper.floor(z);
		int xl = (xi >> 4) << 4;
		int zl = (zi >> 4) << 4;
		int xh = xl + 16;
		int zh = zl + 16;

		float xProgress = (float) (x - (double) xl);
		xProgress /= 16.0f;
		float zProgress = (float) (z - (double) zl);
		zProgress /= 16.0f;

		return MathHelper.lerp(xProgress, 
				MathHelper.lerp(zProgress, getFDC(world, xl, zl, originalResultChunks, originalResult), getFDC(world, xl, zh, originalResultChunks, originalResult)),
				MathHelper.lerp(zProgress, getFDC(world, xh, zl, originalResultChunks, originalResult), getFDC(world, xh, zh, originalResultChunks, originalResultChunks)));
	}

	private static float getFDC(World world, int x, int z, float originalResultChunks, float originalResult) {
		Biome biome = world.getBiome(new BlockPos(x, 64, z)); // get biome
		return biome instanceof BiomeFog ? ((BiomeFog) biome).modifyFogDistanceChunks(originalResultChunks) : originalResult; // add fog distance
	}
}
