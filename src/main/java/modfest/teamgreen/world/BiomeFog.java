package modfest.teamgreen.world;

import net.minecraft.util.math.Vec3d;

public interface BiomeFog {
	/**
	 * @return the fog view distance in chunks. Will only be changed if it is less than the original view distance
	 */
	default float modifyFogDistanceChunks(float originalDistanceChunks) {
		return originalDistanceChunks;
	}

	default Vec3d getFogColour(int x, int z) {
		return DEFAULT_FOG_COLOUR;
	}

	static Vec3d DEFAULT_FOG_COLOUR = new Vec3d(0.5, 0.5, 0.5);
}
