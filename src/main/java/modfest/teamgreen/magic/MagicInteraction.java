package modfest.teamgreen.magic;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;
import modfest.teamgreen.magic.language.Language;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class MagicInteraction {

	//           device[0]      device[3]      device[7]
	//              v              v              v
	// start ->  device[1] (->) device[4] (->) device[8] ... etc
	//              ^              ^              ^
	//           device[2]      device[5]      device[9]
	public MagicInteraction(Attribute[] device) {
		this.components = new ArrayList<>();

		final int max = (device.length / 3);

		for (int i = 0; i < max; ++i) {
			int offset = i * 3;

			Attribute base = device[offset + 1];

			if (base == null) {
				break;
			}

			Attribute ma0 = device[offset];
			Attribute ma1 = device[offset + 2];

			if (ma0 == null) {
				if (ma1 == null) {
					this.components.add(new ConfiguredAttribute(base, ModifyingAttribute.DEFAULT));
				} else {
					this.components.add(new ConfiguredAttribute(base, ma1));
				}
			} else if (ma1 == null) {
				this.components.add(new ConfiguredAttribute(base, ma0));
			} else {
				this.components.add(new ConfiguredAttribute(base, new ModifyingAttribute.Compound(ma0, ma1)));
			}
		}

		if (this.components.isEmpty()) {
			throw new RuntimeException("Empty Magical Device created!");
		}

		this.magicName = Language.magicName(this.components);
	}

	private MagicInteraction(List<ConfiguredAttribute> components) {
		this.components = components;
		this.magicName = Language.magicName(this.components);
	}

	private final List<ConfiguredAttribute> components;
	private final String magicName;

	public String getMagicName() {
		return this.magicName;
	}

	public int apply(IWorld world, MagicUser user, @Nullable BlockPos pos) {
		int currentValue = -1; // -1 represents not started

		for (ConfiguredAttribute attribute : this.components) {
			if (currentValue == -1) {
				currentValue = attribute.activate(world, user, pos);
			} else {
				currentValue = attribute.process(world, currentValue, user, pos);
			}
		}

		return currentValue;
	}

	public int[] serialise() {
		final int length = this.components.size();
		int[] result = new int[length * 3];

		for (int i = 0; i < length; ++i) {
			int start = i * 3;
			int[] serialisedConfiguredAttribute = this.components.get(i).serialise();
			System.arraycopy(serialisedConfiguredAttribute, 0, result, start, 3);
		}

		return result;
	}

	public static MagicInteraction deserialise(int[] serialisedData) {
		final int length = serialisedData.length;
		final int caLength = length / 3;
		List<ConfiguredAttribute> configuredAttributes = new ArrayList<>();

		for (int i = 0; i < caLength; ++i) {
			int start = i * 3;
			configuredAttributes.add(ConfiguredAttribute.deserialise(serialisedData[start], serialisedData[start + 1], serialisedData[start + 2]));
		}

		return new MagicInteraction(configuredAttributes);
	}
}
