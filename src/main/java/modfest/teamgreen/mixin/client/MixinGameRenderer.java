package modfest.teamgreen.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.world.BiomeFog;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {
	@Shadow
	@Final
	private MinecraftClient client;

	@Inject(at = @At("RETURN"), method = "getViewDistance", cancellable = true)
	private void strengthenFog(CallbackInfoReturnable<Float> cir) {
		final float originalResult = cir.getReturnValueF();
		final float originalResultChunks = originalResult * 0.0625f; // originalResult / 16.0f

		final World world = this.client.world;
		final DimensionType dimensionType = world.getDimension().getType();

		if (dimensionType == DimensionType.OVERWORLD) { // Add checks here for more dimensions
			// overworld biomes are 2d, so we only need x and z
			final BlockPos playerPos = this.client.player.getBlockPos();
			int x = playerPos.getX();
			int z = playerPos.getZ();

			BlockPos.Mutable pos = new BlockPos.Mutable();

			float modifiedResultChunks = 0.0f;

			for (int sampleX = x - crimson_blendRadius; sampleX <= x + crimson_blendRadius; ++sampleX) {
				pos.setX(sampleX);

				for (int sampleZ = z - crimson_blendRadius; sampleZ <= z + crimson_blendRadius; ++sampleZ) {
					pos.setZ(sampleZ);

					Biome biome = world.getBiome(pos); // get biome
					modifiedResultChunks += biome instanceof BiomeFog ? ((BiomeFog) biome).modifyFogDistanceChunks(originalResultChunks) : originalResult; // add fog distance
				}
			}

			final float sqrtDivisor = (crimson_blendRadius * 2) + 1;
			modifiedResultChunks /= sqrtDivisor * sqrtDivisor;

			float modifiedResult = modifiedResultChunks * 16.0f;

			if (modifiedResult < originalResult) { // we don't want to mess with the player's max render distance
				cir.setReturnValue(modifiedResult);
			}
		}
	}

	static final int crimson_blendRadius = 6;
}