package modfest.teamgreen;

import java.util.function.Predicate;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModInit implements ModInitializer {
	public static final ModConfig CONFIG = ModConfig.load();
	public static final String MOD_ID = "placeholder";

	@Override
	public void onInitialize() {
		// access CONFIG.exampleField
		registerAll();
		addGeneration();
	}

	private void registerAll() {
		// registerItem( ... )
		// registerBlock( ... )
		// registerFeature( ... )
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

	public static void registerItem(String id, Item item) {
		Registry.register(Registry.ITEM, from(id), item);
	}

	public static void registerBlock(String id, Block block, ItemGroup group) {
		Registry.register(Registry.BLOCK, from(id), block);
		Registry.register(Registry.ITEM, from(id), new BlockItem(block, new Item.Settings().group(group)));
	}

	public static void registerFeature(String id, Feature<?> feature) {
		Registry.register(Registry.FEATURE, from(id), feature);
	}

	public static Identifier from(String s) {
		return new Identifier(MOD_ID, s);
	}
}
