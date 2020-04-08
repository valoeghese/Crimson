package modfest.teamgreen.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import modfest.teamgreen.logic.CrimsonClientFogCalculations;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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

		if (dimensionType == DimensionType.OVERWORLD) { // Add dimension type checks here for fog distance there
			final Vec3d playerPos = this.client.player.getPos();
			double x = playerPos.getX();
			double z = playerPos.getZ();

			final float modifiedResultChunks = CrimsonClientFogCalculations.calculateFogDistanceChunks(world, x, z, originalResultChunks, originalResult);

			float modifiedResult = modifiedResultChunks * 16.0f;

			if (modifiedResult < originalResult) { // we don't want to mess with the player's max render distance
				cir.setReturnValue(modifiedResult);
			}
		}
	}
}