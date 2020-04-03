package modfest.teamgreen;

import modfest.teamgreen.gui.MagicDeviceCraftingController;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.recipe.RecipeType;

public class CrimsonClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ScreenProviderRegistry.INSTANCE.registerFactory(MagicDeviceCraftingController.ID, (syncId, id, player, buf) -> new MagicDeviceCraftingController.Screen(new MagicDeviceCraftingController(syncId, player.inventory), player));
	}
}
