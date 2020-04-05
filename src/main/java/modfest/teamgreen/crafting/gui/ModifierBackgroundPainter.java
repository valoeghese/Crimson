package modfest.teamgreen.crafting.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import modfest.teamgreen.CrimsonInit;
import net.minecraft.util.Identifier;

public class ModifierBackgroundPainter implements BackgroundPainter {
	public ModifierBackgroundPainter(boolean top) {
		this.texture = CrimsonInit.from(top ? "slot_down.png" : "slot_up.png");
	}

	private final Identifier texture;

	@Override
	public void paintBackground(int left, int top, WWidget panel) {
		ScreenDrawing.texturedRect(left - 1, top - 1, panel.getWidth(), panel.getHeight(), this.texture, 0xFF_FFFFFF);
	}

}
