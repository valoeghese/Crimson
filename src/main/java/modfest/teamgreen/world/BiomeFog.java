package modfest.teamgreen.world;

public interface BiomeFog {
	/**
	 * @return the fog view distance in chunks. Will only be changed if it is less than the original view distance
	 */
	default float modifyFogDistanceChunks(float originalDistanceChunks) {
		return originalDistanceChunks;
	}
}
