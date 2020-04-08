package modfest.teamgreen.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.world.BiomeFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;

@Mixin(OverworldDimension.class)
public class MixinOverworldDimension {
	@Inject(at = @At("RETURN"), method = "getFogColor", cancellable = true)
	private void modifyFogColour(float skyAngle, float tickDelta, CallbackInfoReturnable<Vec3d> cir) {
		final MinecraftClient client = MinecraftClient.getInstance();
		final World world = client.world;
		final DimensionType dimType = world.getDimension().getType();

		Vec3d original = cir.getReturnValue();
		double defaultR = original.getX();
		defaultR *= defaultR;
		double defaultG = original.getY();
		defaultG *= defaultG;
		double defaultB = original.getZ();
		defaultB *= defaultB;

		if (dimType == DimensionType.OVERWORLD) { // add dimension type checks here for fog colour there
			final BlockPos playerPos = client.player.getBlockPos();
			final int x = playerPos.getX();
			final int z = playerPos.getZ();

			boolean modified = false;

			// use squares for colour blending because of how colour is perceived
			float accumulatedR = 0.0f;
			float accumulatedG = 0.0f;
			float accumulatedB = 0.0f;

			BlockPos.Mutable pos = new BlockPos.Mutable();

			for (int sampleX = x - 8; sampleX <= x + 8; ++sampleX) {
				pos.setX(sampleX);

				for (int sampleZ = z - 8; sampleZ <= z + 8; ++sampleZ) {
					pos.setZ(sampleZ);

					Biome biome = world.getBiome(pos); // get biome

					if (biome instanceof BiomeFog) {
						modified = true; // faster than checking if it's already set to true first

						Vec3d biomeFogColour = ((BiomeFog) biome).getFogColour(sampleX, sampleZ);

						// Micro Optimisation: Halve method calls
						double
						r = biomeFogColour.getX(),
						g = biomeFogColour.getY(),
						b = biomeFogColour.getZ();

						accumulatedR += r * r;
						accumulatedG += g * g;
						accumulatedB += b * b;
					} else {
						accumulatedR += defaultR;
						accumulatedG += defaultG;
						accumulatedB += defaultB;
					}
				}
			}

			if (modified) { // set return value if modified
				final double divisor = 17 * 17;
				cir.setReturnValue(new Vec3d(Math.sqrt(accumulatedR / divisor), Math.sqrt(accumulatedG / divisor), Math.sqrt(accumulatedB / divisor)));
			}
		}
	}
}
