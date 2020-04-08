package modfest.teamgreen.world;

import java.util.function.Predicate;

import modfest.teamgreen.CrimsonConfig.OreGen;
import modfest.teamgreen.CrimsonInit;
import modfest.teamgreen.content.block.CrimsonBlocks;
import modfest.teamgreen.world.crimson.CrimsonBrushlandsBiome;
import modfest.teamgreen.world.crimson.CrimsonForestBiome;
import modfest.teamgreen.world.crimson.CrimsonMarshlandBiome;
import modfest.teamgreen.world.crimson.CrimsonRiverBiome;
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

public class CrimsonWorld {
	public static final TernarySurfaceConfig CRIMSON_SURFACE_CONFIG = new TernarySurfaceConfig(
			Blocks.GRASS_BLOCK.getDefaultState(), // temporary blocks until custom blocks are added
			Blocks.COARSE_DIRT.getDefaultState(),
			Blocks.GRAVEL.getDefaultState());

	public static final Biome CRIMSON_FOREST = new CrimsonForestBiome(CrimsonInit.CONFIG.biomeGen.get("crimsonForest"));
	public static final Biome CRIMSON_BRUSHLAND = new CrimsonBrushlandsBiome(CrimsonInit.CONFIG.biomeGen.get("crimsonBrushlands"));
	public static final Biome CRIMSON_MARSHLAND = new CrimsonMarshlandBiome(CrimsonInit.CONFIG.biomeGen.get("crimsonMarshland"));
	public static final Biome CRIMSON_RIVER = new CrimsonRiverBiome(CrimsonInit.CONFIG.biomeGen.get("crimsonRiver"));

	public static void registerAll() {
		registerBiome(CRIMSON_FOREST, "crimson_forest");
		registerBiome(CRIMSON_BRUSHLAND, "crimson_brushland");
		registerBiome(CRIMSON_MARSHLAND, "crimson_marshland");
		registerBiome(CRIMSON_RIVER, "crimson_river");

		// lazulite
		OreGen lazulite = CrimsonInit.CONFIG.oreGen.get("lazulite");
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CrimsonBlocks.LAZULITE_ORE.get().getDefaultState(), lazulite.size)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(lazulite.count, lazulite.bottomOffset, lazulite.topOffset, lazulite.maxY))),
				b -> true);
		// realgar
		OreGen realgar = CrimsonInit.CONFIG.oreGen.get("realgar");
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CrimsonBlocks.REALGAR_ORE.get().getDefaultState(), realgar.size)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(realgar.count, realgar.bottomOffset, realgar.topOffset, realgar.maxY))),
				b -> true);
		// vanadinite
		OreGen vanadinite = CrimsonInit.CONFIG.oreGen.get("vanadinite");
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CrimsonBlocks.VANADINITE_ORE.get().getDefaultState(), vanadinite.size) 
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(vanadinite.count, vanadinite.bottomOffset, vanadinite.topOffset, vanadinite.maxY))),
				b -> true);
		// celestine
		OreGen celestine = CrimsonInit.CONFIG.oreGen.get("celestine");
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CrimsonBlocks.CELESTINE_ORE.get().getDefaultState(), celestine.size)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(celestine.count, celestine.bottomOffset, celestine.topOffset, celestine.maxY))),
				b -> true);
		// bornite
		OreGen bornite = CrimsonInit.CONFIG.oreGen.get("bornite");
		addFeatureTo(GenerationStep.Feature.UNDERGROUND_ORES,
				Feature.ORE.configure(
						new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CrimsonBlocks.BORNITE.get().getDefaultState(), bornite.size)
						).createDecoratedFeature(Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(bornite.count, bornite.bottomOffset, bornite.topOffset, bornite.maxY))),
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
}
