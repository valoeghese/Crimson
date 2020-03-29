package modfest.teamgreen.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.world.noise.OctaveOpenSimplexNoise;
import modfest.teamgreen.world.util.Int2ToDoubleCache;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;

@Mixin(VanillaLayeredBiomeSource.class)
public class MixinVanillaLayeredBiomeSource {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void injectInit(VanillaLayeredBiomeSourceConfig config, CallbackInfo info) {
		Random rand = new Random(config.getSeed() + 1);
		this.crimson_alterationNoise = new OctaveOpenSimplexNoise(rand, 1, 400.0);

		final double target = 50 * 50;

		this.crimson_thresholdCache = new Int2ToDoubleCache((x, z) -> {
			double xd = (double) x; // m i c r o o p t i m i s a t i o n
			double zd = (double) z;

			double sqrDist = xd * xd + zd * zd;
			double sample = this.crimson_alterationNoise.sample(xd, zd);

			if (sqrDist < target) {
				sample -= 1.0 - (sqrDist / target);
			}

			return sample;
		});
	}

	private OctaveOpenSimplexNoise crimson_alterationNoise;
	private Int2ToDoubleCache crimson_thresholdCache;

	@Inject(at = @At("RETURN"), method = "getBiomeForNoiseGen", cancellable = true)
	// genX / genZ to blockX / blockZ:  << 2
	private void injectSpecialBiomes(int genX, int genY, int genZ, CallbackInfoReturnable<Biome> cir) {
		Biome result = cir.getReturnValue();
		double threshold = this.crimson_thresholdCache.apply(genX, genZ);
	}
}
