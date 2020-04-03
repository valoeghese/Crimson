package modfest.teamgreen;

import java.util.function.Predicate;

import modfest.teamgreen.block.ModBlocks;
import modfest.teamgreen.gui.MagicDeviceCraftingController;
import modfest.teamgreen.item.ModItems;
import modfest.teamgreen.magic.AttributeDefinitions;
import modfest.teamgreen.world.ModWorld;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModInit implements ModInitializer {
	public static final ModConfig CONFIG = ModConfig.load();
	public static final String MOD_ID = "crimson"; // until we come up with a better name
	public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "group"), () -> new ItemStack(ModItems.MAGIC_DEVICE.get()));

	@Override
	public void onInitialize() {
		// access CONFIG.exampleField etc
		registerAll();
		addGeneration();
	}

	private void registerAll() {
		ModItems.ensureInit();
		ModBlocks.ensureInit();
		ModWorld.registerAll();
		ContainerProviderRegistry.INSTANCE.registerFactory(MagicDeviceCraftingController.ID, (syncId, id, player, buf) -> new MagicDeviceCraftingController(syncId, player.inventory));
		AttributeDefinitions.ensureInit();
	}

	private void addGeneration() {
		// addFeatureTo( ... )
	}

	public static <C extends FeatureConfig, F extends Feature<C>, T extends ConfiguredFeature<C, F>> void addFeatureTo(final GenerationStep.Feature step, final T feature, final Predicate<Biome> predicate) {
		Registry.BIOME.forEach(biome -> {
			if (predicate.test(biome)) {
				biome.addFeature(step, feature);
			}
		});
		RegistryEntryAddedCallback.event(Registry.BIOME).register((rawId, id, biome) -> {
			if (predicate.test(biome)) {
				biome.addFeature(step, feature);
			}
		});
	}

	public static void registerFeature(String id, Feature<?> feature) {
		Registry.register(Registry.FEATURE, from(id), feature);
	}

	public static Identifier from(String s) {
		return new Identifier(MOD_ID, s);
	}
}
