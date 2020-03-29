package modfest.teamgreen.logic;

import java.util.Random;
import java.util.function.Consumer;

import modfest.teamgreen.world.ModWorld;
import modfest.teamgreen.world.noise.OctaveOpenSimplexNoise;
import modfest.teamgreen.world.util.Int2ToDoubleCache;
import net.minecraft.world.biome.Biome;

public class CrimsonBiomeLogic {
	public CrimsonBiomeLogic(Random rand) {
		this.alterationNoise = new OctaveOpenSimplexNoise(rand, 1, 400.0);

		final double target = 50 * 50;

		this.thresholdCache = new Int2ToDoubleCache((x, z) -> {
			double xd = (double) x; // m i c r o o p t i m i s a t i o n
			double zd = (double) z;

			double sqrDist = xd * xd + zd * zd;
			double sample = this.alterationNoise.sample(xd, zd);

			if (sqrDist < target) {
				sample -= 1.0 - (sqrDist / target);
			}

			return sample;
		});
	}

	private OctaveOpenSimplexNoise alterationNoise;
	private Int2ToDoubleCache thresholdCache;

	public void apply(Biome result, int genX, int genZ, Consumer<Biome> setReturnValue) {
		double threshold = this.thresholdCache.apply(genX, genZ);

		if (threshold > 0.1f) {
			switch (result.getCategory()) {
			case MUSHROOM:
			case OCEAN:
			case RIVER:
				break;
			case TAIGA:
			case JUNGLE:
			case FOREST:
				setReturnValue.accept(ModWorld.CRIMSON_FOREST);
			case EXTREME_HILLS:
			case SWAMP:
				setReturnValue.accept(ModWorld.CRIMSON_MARSHLAND);
				break;
			default:
				setReturnValue.accept(ModWorld.CRIMSON_BRUSHLAND);
				break;
			}
		}
	}
}
