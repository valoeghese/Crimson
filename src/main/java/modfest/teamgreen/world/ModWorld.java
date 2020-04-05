package modfest.teamgreen.world;

import java.util.function.Predicate;

import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.block.ModBlocks;
import modfest.teamgreen.world.crimson.CrimsonBrushlandsBiome;
import modfest.teamgreen.world.crimson.CrimsonForestBiome;
import modfest.teamgreen.world.crimson.CrimsonMarshlandBiome;
import modfest.teamgreen.world.crimson.CrimsonRiver;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModWorld {
	public static final TernarySurfaceConfig CRIMSON_SURFACE_CONFIG = new TernarySurfaceConfig(
			Blocks.GRASS_BLOCK.getDefaultState(), // temporary blocks until custom blocks are added
			Blocks.COARSE_DIRT.getDefaultState(),
			Blocks.GRAVEL.getDefaultState());

	public static final Biome CRIMSON_FOREST = new CrimsonForestBiome();
	public static final Biome CRIMSON_BRUSHLAND = new CrimsonBrushlandsBiome();
	public static final Biome CRIMSON_MARSHLAND = new CrimsonMarshlandBiome();
	public static final Biome CRIMSON_RIVER = new CrimsonRiver();

	public static void registerAll() {
		registerBiome(CRIMSON_FOREST, "crimson_forest");
		registerBiome(CRIMSON_BRUSHLAND, "crimson_brushland");
		registerBiome(CRIMSON_MARSHLAND, "crimson_marshland");
		registerBiome(CRIMSON_RIVER, "crimson_river");

		// lazulite
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.LAZULITE_ORE.get().getDefaultState(), 6)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(3, 0, 0, 33))),
				b -> true);
		// realgar
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.REALGAR_ORE.get().getDefaultState(), 6)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(5, 0, 0, 20))),
				b -> true);
		// vanadinite
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.VANADINITE_ORE.get().getDefaultState(), 10)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(2, 12, 0, 18))),
				b -> true);
		// celestine
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.CELESTINE_ORE.get().getDefaultState(), 3)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(1, 0, 0, 16))),
				b -> true);
		// bornite
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ModBlocks.BORNITE.get().getDefaultState(), 12)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(17, 0, 0, 100))),
				b -> true);
	}

	private static final void registerBiome(Biome biome, String id) {
		Registry.register(Registry.BIOME, CrimsonInit.from(id), biome);
		ReflectionHacks.injectOverworldBiome(biome);
	}

	private static <C extends FeatureConfig, F extends Feature<C>, T extends ConfiguredFeature<C, F>> void addFeatureTo(final GenerationStep.Feature step, final T feature, final Predicate<Biome> predicate) {
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

	public static final int FOG_BLEND_RADIUS = 8;
	public static final float FOG_BLEND_DIVISOR;

	static {
		final float sqrtDivisor = (FOG_BLEND_RADIUS * 2) + 1;
		FOG_BLEND_DIVISOR = sqrtDivisor * sqrtDivisor;
	}
}
