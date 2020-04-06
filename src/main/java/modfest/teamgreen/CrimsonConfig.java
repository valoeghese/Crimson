package modfest.teamgreen;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import net.fabricmc.loader.api.FabricLoader;
import tk.valoeghese.zoesteriaconfig.api.ZoesteriaConfig;
import tk.valoeghese.zoesteriaconfig.api.container.Container;
import tk.valoeghese.zoesteriaconfig.api.container.WritableConfig;
import tk.valoeghese.zoesteriaconfig.api.template.ConfigTemplate;

public class CrimsonConfig {
	public final Map<String, OreGen> oreGen = new HashMap<>();
	public final Map<String, BiomeGen> biomeGen = new HashMap<>();
	public int magicDeviceMaxSections;
	public int tendrilsGenCount;
	public double crimsonNoiseCutoff;
	public double crimsonNoisePeriod;
	public double crimsonFadeRadius;
	public int magicDeviceDurability;

	public static CrimsonConfig load() {
		File file = new File(FabricLoader.getInstance().getConfigDirectory().getPath() + "/" + CrimsonInit.MOD_ID + ".cfg");	
		CrimsonConfig result = new CrimsonConfig();

		boolean write;

		try {
			// if new file was created (i.e. doesn't already exist), write to it
			write = file.createNewFile();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		// load config
		WritableConfig loaded = ZoesteriaConfig.loadConfigWithDefaults(file, defaults());

		// ore configs
		Map<String, Object> oreGen = loaded.getMap("oreGen");
		oreGen.forEach((key, value) -> {
			result.oreGen.put(key, new OreGen(loaded.getContainer("oreGen." + key)));
		});

		// biome configs
		Map<String, Object> biomeGen = loaded.getMap("biomeGen");
		biomeGen.forEach((key, value) -> {
			result.biomeGen.put(key, new BiomeGen(loaded.getContainer("biomeGen." + key)));
		});

		result.magicDeviceMaxSections = loaded.getIntegerValue("magicDeviceMaxSections");
		result.magicDeviceDurability = loaded.getIntegerValue("magicDeviceDurability");

		// worldGen
		Container worldGen = loaded.getContainer("worldGen");
		result.tendrilsGenCount = worldGen.getIntegerValue("tendrilsGenCount");
		result.crimsonNoiseCutoff = worldGen.getDoubleValue("crimsonNoiseCutoff");
		result.crimsonNoisePeriod = worldGen.getDoubleValue("crimsonNoisePeriod");
		result.crimsonFadeRadius = worldGen.getDoubleValue("crimsonFadeRadius");

		// write to file if newly created
		if (write || loaded.getIntegerValue("configVersion") < configVersionLatest) {
			updateConfigVersion(loaded);
			loaded.writeToFile(file);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private static void updateConfigVersion(WritableConfig loaded) {
		try {
			Field fParserMap = Class.forName("tk.valoeghese.zoesteriaconfig.impl.parser.ImplZoesteriaConfigAccess").getDeclaredField("parserMap");
			fParserMap.setAccessible(true);
			Map<String, Object> parserMap = (Map<String, Object>) fParserMap.get(loaded);
			parserMap.put("configVersion", configVersionLatest);
		} catch (NoSuchFieldException | SecurityException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private static ConfigTemplate defaults() {
		ConfigTemplate.Builder templateBuilder = ConfigTemplate.builder();
		// config version int
		templateBuilder.addDataEntry("configVersion", String.valueOf(configVersionLatest));
		// example container
		templateBuilder.addContainer("oreGen", c -> {
			addOreGen((name, defaults) -> c.addContainer(name, c1 -> c1
					.addDataEntry("count", String.valueOf(defaults.count))
					.addDataEntry("topOffset", String.valueOf(defaults.topOffset))
					.addDataEntry("bottomOffset", String.valueOf(defaults.bottomOffset))
					.addDataEntry("maxY", String.valueOf(defaults.maxY))
					.addDataEntry("size", String.valueOf(defaults.size))));
		});
		templateBuilder.addContainer("biomeGen", c -> {
			addBiomeGen((name, defaults) -> c.addContainer(name, c1 -> c1
					.addDataEntry("depth", String.valueOf(defaults.depth))
					.addDataEntry("scale", String.valueOf(defaults.scale))
					.addDataEntry("thornGenCount", String.valueOf(defaults.thornGenCount))
					.addDataEntry("fogDistanceChunks", String.valueOf(defaults.fogDistanceChunks))));
		});
		templateBuilder.addContainer("worldGen", c -> c
				.addDataEntry("tendrilsGenCount", "1")
				.addDataEntry("crimsonNoiseCutoff", "0.19")
				.addDataEntry("crimsonNoisePeriod", "80.0")
				.addDataEntry("crimsonFadeRadius", "50.0"));
		templateBuilder.addDataEntry("magicDeviceMaxSections", "4");
		templateBuilder.addDataEntry("magicDeviceDurability", "60");
		return templateBuilder.build();
	}

	private static void addOreGen(DefaultOreAdder c) {
		c.add("lazulite", new OreGen(3, 0, 0, 33, 6));
		c.add("realgar", new OreGen(5, 0, 0, 20, 6));
		c.add("vanadinite", new OreGen(2, 12, 0, 18, 10));
		c.add("celestine", new OreGen(1, 0, 0, 16, 3));
		c.add("bornite", new OreGen(17, 0, 0, 100, 12));
	}

	private static void addBiomeGen(DefaultBiomeAdder c) {
		c.add("crimsonForest", new BiomeGen(0.2f, 0.38f, 1, 4.3f));
		c.add("crimsonMarshland", new BiomeGen(0.03f, 0.125f, 2, 3.8f));
		c.add("crimsonBrushlands", new BiomeGen(0.15f, 0.05f, 5, 4.3f));
		c.add("crimsonRiver", new BiomeGen(-0.5f, 0.0f, 0, 4.3f));
	}

	public static class OreGen {
		public int count;
		public int bottomOffset;
		public int topOffset;
		public int maxY;
		public int size;

		private OreGen(Container container) {
			this.count = container.getIntegerValue("count");
			this.bottomOffset = container.getIntegerValue("bottomOffset");
			this.topOffset = container.getIntegerValue("topOffset");
			this.maxY = container.getIntegerValue("maxY");
			this.size = container.getIntegerValue("size");		}

		private OreGen(int count, int bottomOffset, int topOffset, int maxY, int size) {
			this.count = count;
			this.bottomOffset = bottomOffset;
			this.topOffset = topOffset;
			this.maxY = maxY;
			this.size = size;
		}
	}

	public static class BiomeGen {
		public float depth;
		public float scale;
		public int thornGenCount;
		public float fogDistanceChunks;

		private BiomeGen(Container container) {
			this.depth = container.getFloatValue("depth");
			this.scale = container.getFloatValue("scale");
			this.thornGenCount = container.getIntegerValue("thornGenCount");
			this.fogDistanceChunks = container.getFloatValue("fogDistanceChunks");
		}

		private BiomeGen(float depth, float scale, int thornGenCount, float fogDistanceChunks) {
			this.depth = depth;
			this.scale = scale;
			this.thornGenCount = thornGenCount;
			this.fogDistanceChunks = fogDistanceChunks;
		}
	}

	@FunctionalInterface
	private static interface DefaultOreAdder {
		void add(String name, OreGen defaults);
	}

	@FunctionalInterface
	private static interface DefaultBiomeAdder {
		void add(String name, BiomeGen defaults);
	}

	private static int configVersionLatest = 1;
}
