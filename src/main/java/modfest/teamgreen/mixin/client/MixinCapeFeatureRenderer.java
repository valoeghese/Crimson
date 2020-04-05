package modfest.teamgreen.mixin.client;

import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import modfest.teamgreen.CrimsonInit;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.feature.CapeFeatureRenderer;
import net.minecraft.util.Identifier;


@Mixin(CapeFeatureRenderer.class)
public class MixinCapeFeatureRenderer {
	private static final UUID NOT_VALO = UUID.fromString("8ea1da2f-0efa-4044-9e6f-4a3bf4e8a9a5");
	private static final UUID NOT_THXMPIN = UUID.fromString("b60c565c-8b0c-4249-a314-9b8645371b5e");
	private static final UUID NOT_HYDOS = UUID.fromString("3c439ef2-6182-41ae-a1f3-b4d8def57821");
	private static final UUID NOT_RAMDISK = UUID.fromString("836c8cc4-bd0a-45b3-bced-72bbb3af2d0d");

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getCapeTexture()Lnet/minecraft/util/Identifier;"))
	private Identifier getCapeTexture(AbstractClientPlayerEntity player) {
		UUID uuid = player.getUuid();

		if (NOT_VALO.equals(uuid) || NOT_THXMPIN.equals(uuid) || NOT_HYDOS.equals(uuid)) {
			return CrimsonInit.from("cape_misaka.png");
		}

		if (NOT_RAMDISK.equals(uuid)) {
			return CrimsonInit.from("ramdisk.png");
		}

		return player.getCapeTexture();
	}
}