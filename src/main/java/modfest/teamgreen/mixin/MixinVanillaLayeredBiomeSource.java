package modfest.teamgreen.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.logic.CrimsonBiomeLogic;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;

@Mixin(VanillaLayeredBiomeSource.class)
public abstract class MixinVanillaLayeredBiomeSource {
	@Inject(at = @At("RETURN"), method = "<init>")
	private void injectInit(VanillaLayeredBiomeSourceConfig config, CallbackInfo info) {
		Random rand = new Random(config.getSeed() + 1);
		this.crimsonBiomeLogic = new CrimsonBiomeLogic(rand);
	}

	private CrimsonBiomeLogic crimsonBiomeLogic;

	// genX / genZ to blockX / blockZ:  << 2
	@Inject(at = @At("RETURN"), method = "getBiomeForNoiseGen", cancellable = true)
	private void injectSpecialBiomes(int genX, int genY, int genZ, CallbackInfoReturnable<Biome> cir) {
		Biome result = cir.getReturnValue();
		this.crimsonBiomeLogic.apply(result, genX, genZ, cir::setReturnValue);
	}
}
