package modfest.teamgreen.magic;

import java.util.ArrayList;
import java.util.List;

import modfest.teamgreen.magic.attribute.Attribute;
import modfest.teamgreen.magic.attribute.ModifyingAttribute;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class MagicInteraction {

	//           device[0]      device[3]      device[7]
	//              v              v              v
	// start ->  device[1] (->) device[4] (->) device[8] ... etc
	//              ^              ^              ^
	//           device[2]      device[5]      device[9]
	public MagicInteraction(Attribute[] device) {
		final int max = (device.length / 3);

		for (int i = 0; i < max; ++i) {
			int offset = i * 3;

			Attribute base = device[offset + 1];

			if (base == null) {
				break;
			}

			ModifyingAttribute ma0 = device[offset];
			ModifyingAttribute ma1 = device[offset + 2];

			if (ma0 == null) {
				if (ma1 == null) {
					this.components.add(new ConfiguredAttribute(base, ModifyingAttribute.NO_MODIFIER));
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
	}

	public void apply(IWorld world, MagicUser user, BlockPos pos) {
		int currentValue = -1; // -1 represents not started

		for (ConfiguredAttribute attribute : this.components) {
			if (currentValue == -1) {
				currentValue = attribute.activate(world, user, pos);
			} else {
				currentValue = attribute.process(world, currentValue, user, pos);
			}
		}
	}

	private final List<ConfiguredAttribute> components = new ArrayList<>();
}
