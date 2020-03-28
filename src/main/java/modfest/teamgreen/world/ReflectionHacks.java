package modfest.teamgreen.world;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;

// proudly stolen from fabric api
public final class ReflectionHacks {
	private static Method injectBiomeMethod = null;

	public static void injectOverworldBiome(Biome biome) {
		try {
			if (injectBiomeMethod == null) {
				injectBiomeMethod = VanillaLayeredBiomeSource.class.getDeclaredMethod("fabric_injectBiome", Biome.class);
				injectBiomeMethod.setAccessible(true);
			}

			injectBiomeMethod.invoke(null, biome);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
