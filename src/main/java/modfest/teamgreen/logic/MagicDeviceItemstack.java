package modfest.teamgreen.logic;

import modfest.teamgreen.item.MagicDeviceItem;
import modfest.teamgreen.magic.MagicInteraction;

public interface MagicDeviceItemstack {
	MagicDeviceItem.MagicDeviceData getData();
	void setInteraction(MagicInteraction interaction);
}
