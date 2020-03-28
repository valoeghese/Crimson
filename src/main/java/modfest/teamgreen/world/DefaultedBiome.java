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

	public static class Properties extends SettingBehaviour<Properties> {
		// Indicates that the SettingBehaviour is not a SettingDefaults. For Convenience.
	}

	public static class SettingDefaults extends SettingBehaviour<SettingDefaults> implements Function<Properties, Settings> {
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

			result.surfaceBuilder(properties.surfaceBuilder == null ? this.surfaceBuilder : properties.surfaceBuilder);
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

	@SuppressWarnings("unchecked")
	private static class SettingBehaviour<T extends SettingBehaviour<T>> {
		ConfiguredSurfaceBuilder<?> surfaceBuilder;
		Biome.Precipitation precipitation;
		Biome.Category category;
		Float depth;
		Float scale;
		Float temperature;
		Float downfall;
		Integer waterColor;
		Integer waterFogColor;

		public T category(Category category) {
			this.category = category;
			return (T) this;
		}

		public T precipitation(Precipitation precipitation) {
			this.precipitation = precipitation;
			return (T) this;
		}

		public <SC extends SurfaceConfig> T configureSurfaceBuilder(SurfaceBuilder<SC> surfaceBuilder, SC surfaceConfig) {
			this.surfaceBuilder = new ConfiguredSurfaceBuilder<>(surfaceBuilder, surfaceConfig);
			return (T) this;
		}

		public T depth(float depth) {
			this.depth = depth;
			return (T) this;
		}

		public T scale(float scale) {
			this.scale = scale;
			return (T) this;
		}

		public T temperature(float temperature) {
			this.temperature = temperature;
			return (T) this;
		}

		public T downfall(float downfall) {
			this.downfall = downfall;
			return (T) this;
		}

		public T waterColor(int waterColor) {
			this.waterColor = waterColor;
			return (T) this;
		}

		public T waterFogColor(int waterFogColor) {
			this.waterFogColor = waterFogColor;
			return (T) this;
		}
	}
}
