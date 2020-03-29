package modfest.teamgreen.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.world.BiomeFog;
import modfest.teamgreen.world.ModWorld;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;

@Mixin(OverworldDimension.class)
public class MixinOverworldDimension {
	@Inject(at = @At("HEAD"), method = "getFogColor", cancellable = true)
	private void modifyFogColour(float skyAngle, float tickDelta, CallbackInfoReturnable<Vec3d> cir) {
		final MinecraftClient client = MinecraftClient.getInstance();
		final World world = client.world;
		final DimensionType dimType = world.getDimension().getType();

		if (dimType == DimensionType.OVERWORLD) { // add dimension type checks here for fog colour there
			final BlockPos playerPos = client.player.getBlockPos();
			final int x = playerPos.getX();
			final int z = playerPos.getZ();

			boolean modified = false;

			// use squares for colour blending because of how colour is perceived
			float sqrR = 0.0f;
			float sqrG = 0.0f;
			float sqrB = 0.0f;

			BlockPos.Mutable pos = new BlockPos.Mutable();

			for (int sampleX = x - ModWorld.FOG_BLEND_RADIUS; sampleX <= x + ModWorld.FOG_BLEND_RADIUS; ++sampleX) {
				pos.setX(sampleX);

				for (int sampleZ = z - ModWorld.FOG_BLEND_RADIUS; sampleZ <= z + ModWorld.FOG_BLEND_RADIUS; ++sampleZ) {
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

						sqrR += r * r;
						sqrG += g * g;
						sqrB += b * b;
					} else {
						sqrR += 0.25f; // 0.5 * 0.5
						sqrG += 0.25f; // 0.5 * 0.5
						sqrB += 0.25f; // 0.5 * 0.5
					}
				}
			}

			if (modified) { // set return value if modified
				cir.setReturnValue(new Vec3d(Math.sqrt(sqrR), Math.sqrt(sqrG), Math.sqrt(sqrB)));
			}
		}
	}
}
