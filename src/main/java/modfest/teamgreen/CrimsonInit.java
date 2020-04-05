package modfest.teamgreen;

import modfest.teamgreen.content.block.ModBlocks;
import modfest.teamgreen.content.item.ModItems;
import modfest.teamgreen.crafting.gui.MagicDeviceCraftingController;
import modfest.teamgreen.magic.AttributeDefinitions;
import modfest.teamgreen.mixin.AccessorBrewingRecipeRegistry;
import modfest.teamgreen.world.ModWorld;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;

public class CrimsonInit implements ModInitializer {
	public static final CrimsonConfig CONFIG = CrimsonConfig.load();
	public static final String MOD_ID = "crimson"; // until we come up with a better name
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "group"), () -> new ItemStack(ModItems.MAGIC_DEVICE.get()));

	@Override
	public void onInitialize() {
		// access CONFIG.exampleField etc
		registerAll();
		AccessorBrewingRecipeRegistry.registerPotionRecipe(Potions.AWKWARD, ModItems.REALGAR.get(), Potions.POISON);
	}

	private void registerAll() {
		ModItems.ensureInit();
		ModBlocks.ensureInit();
		ModWorld.registerAll();
		ContainerProviderRegistry.INSTANCE.registerFactory(MagicDeviceCraftingController.ID, (syncId, id, player, buf) -> new MagicDeviceCraftingController(syncId, player.inventory));
		AttributeDefinitions.ensureInit();
	}

	public static void registerFeature(String id, Feature<?> feature) {
		Registry.register(Registry.FEATURE, from(id), feature);
	}

	public static Identifier from(String s) {
		return new Identifier(MOD_ID, s);
	}
}
