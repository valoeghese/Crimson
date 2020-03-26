package modfest.teamgreen;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;

import net.fabricmc.loader.api.FabricLoader;
import tk.valoeghese.zoesteriaconfig.api.ZoesteriaConfig;
import tk.valoeghese.zoesteriaconfig.api.container.WritableConfig;
import tk.valoeghese.zoesteriaconfig.api.template.ConfigTemplate;

public class ModConfig {
	public int exampleField;

	public static ModConfig load() {
		File file = new File(FabricLoader.getInstance().getConfigDirectory().getPath() + "/" + ModInit.MOD_ID + ".cfg");	
		ModConfig result = new ModConfig();

		boolean write;

		try {
			// if new file was created (i.e. doesn't already exist), write to it
			write = file.createNewFile();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}

		// load config
		WritableConfig loaded = ZoesteriaConfig.loadConfigWithDefaults(file, defaults());

		result.exampleField = loaded.getIntegerValue("exampleContainer.example");
		// or loaded.getCotnainer("exampleContainer").getIntegerValue("example")

		// write to file if newly created
		if (write || loaded.getIntegerValue("configVersion") < configVersionLatest) {
			loaded.writeToFile(file);
		}

		return result;
	}

	private static ConfigTemplate defaults() {
		ConfigTemplate.Builder templateBuilder = ConfigTemplate.builder();
		// config version int
		templateBuilder.addDataEntry("configVersion", String.valueOf(configVersionLatest));
		// example container
		templateBuilder.addContainer("exampleContainer", c -> c
				.addDataEntry("example", "1"));
		return templateBuilder.build();
	}

	private static int configVersionLatest = 0;
}
