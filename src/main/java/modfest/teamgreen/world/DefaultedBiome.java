package modfest.teamgreen.world;

import java.util.function.Function;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;

public class DefaultedBiome extends Biome {
	protected DefaultedBiome(Properties properties, SettingDefaults defaults) {
		super(defaults.apply(properties));
	}

	public static class Properties extends SettingBehaviour {
		// Indicates that the SettingBehaviour is not a SettingDefaults. For Convenience.
	}

	public static class SettingDefaults extends SettingBehaviour implements Function<Properties, Settings> {
		public SettingDefaults() {
			this.depth = 0.0f;
			this.scale = 0.1f;
			this.temperature = 0.5f;
			this.downfall = 0.5f;
			this.waterColor = 4159204;
			this.waterFogColor = 329011;
		}

		@Override
		public Settings apply(Properties properties) {
			Settings result = new Biome.Settings().parent(null);

			result.category(properties.category == null ? this.category : properties.category);
			result.precipitation(properties.precipitation == null ? this.precipitation : properties.precipitation);
			result.depth(properties.depth == null ? this.depth : properties.depth);
			result.scale(properties.scale == null ? this.scale : properties.scale);
			result.temperature(properties.temperature == null ? this.temperature : properties.temperature);
			result.downfall(properties.downfall == null ? this.downfall : properties.downfall);
			result.waterColor(properties.waterColor == null ? this.waterColor : properties.waterColor);
			result.waterFogColor(properties.waterFogColor == null ? this.waterFogColor : properties.waterFogColor);

			return result;
		}
	}

	private static class SettingBehaviour {
		ConfiguredSurfaceBuilder<?> surfaceBuilder;
		Biome.Precipitation precipitation;
		Biome.Category category;
		Float depth;
		Float scale;
		Float temperature;
		Float downfall;
		Integer waterColor;
		Integer waterFogColor;

		public SettingBehaviour category(Category category) {
			this.category = category;
			return this;
		}

		public SettingBehaviour precipitation(Precipitation precipitation) {
			this.precipitation = precipitation;
			return this;
		}

		public <SC extends SurfaceConfig> SettingBehaviour configureSurfaceBuilder(SurfaceBuilder<SC> surfaceBuilder, SC surfaceConfig) {
			this.surfaceBuilder = new ConfiguredSurfaceBuilder<>(surfaceBuilder, surfaceConfig);
			return this;
		}

		public SettingBehaviour depth(float depth) {
			this.depth = depth;
			return this;
		}

		public SettingBehaviour scale(float scale) {
			this.scale = scale;
			return this;
		}

		public SettingBehaviour temperature(float temperature) {
			this.temperature = temperature;
			return this;
		}

		public SettingBehaviour downfall(float downfall) {
			this.downfall = downfall;
			return this;
		}

		public SettingBehaviour waterColor(int waterColor) {
			this.waterColor = waterColor;
			return this;
		}

		public SettingBehaviour waterFogColor(int waterFogColor) {
			this.waterFogColor = waterFogColor;
			return this;
		}
	}
}
