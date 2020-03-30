package modfest.teamgreen.magic.attribute;

import java.util.HashMap;
import java.util.Map;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.IWorld;

public abstract class BasicAttribute implements ModifyingAttribute, Attribute {
	protected BasicAttribute(Identifier id) {
		this.id = id;
		this.saveId = currentSaveId++;
		ID_TO_ATTRIBUTE.put(id, this);
		SAVE_ID_MAP.put(this.saveId, this);
	}

	private final Identifier id;
	private final int saveId;

	public Identifier getId() {
		return this.id;
	}

	public int getSaveId() {
		return this.saveId;
	}

	public static BasicAttribute getById(Identifier id) {
		return ID_TO_ATTRIBUTE.get(id);
	}

	public static BasicAttribute getBySaveId(int id) {
		return SAVE_ID_MAP.get(id);
	}

	private static int currentSaveId = 0;
	private static final Map<Identifier, BasicAttribute> ID_TO_ATTRIBUTE = new HashMap<>();
	private static final Int2ObjectMap<BasicAttribute> SAVE_ID_MAP = new Int2ObjectArrayMap<>();
}
