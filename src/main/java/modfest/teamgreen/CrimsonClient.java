package modfest.teamgreen;

import modfest.teamgreen.content.block.ModBlocks;
import modfest.teamgreen.crafting.gui.MagicDeviceCraftingController;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.render.RenderLayer;

public class CrimsonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenProviderRegistry.INSTANCE.registerFactory(MagicDeviceCraftingController.ID, (syncId, id, player, buf) -> new MagicDeviceCraftingController.Screen(new MagicDeviceCraftingController(syncId, player.inventory), player));
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMSON_THORN.get(), RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CRIMSON_TENDRILS.get(), RenderLayer.getTranslucent());
	}
}
