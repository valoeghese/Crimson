package modfest.teamgreen.magic.attribute;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public interface ModifyingAttribute {
	BlockPos[] positions(BlockPos base, int strength);

	static ModifyingAttribute DEFAULT = new Default() {};

	static interface Default extends ModifyingAttribute {
		@Override
		default BlockPos[] positions(BlockPos base, int strength) {
			return new BlockPos[] {base};
		}
	}

	static class Compound implements ModifyingAttribute {
		public Compound(Attribute ma0, Attribute ma1) {
			this.ma0 = ma0;
			this.ma1 = ma1;
		}

		private final Attribute ma0, ma1;

		public int[] serialise(int baseSaveId) {
			return new int[] {baseSaveId, this.ma0.getSaveId(), this.ma1.getSaveId()};
		}

		@Override
		public BlockPos[] positions(BlockPos base, int strength) {
			BlockPos[] pos0 = this.ma0.positions(base, MathHelper.floor(strength / 2.0));
			BlockPos[] pos1 = this.ma1.positions(base, MathHelper.floor(strength / 2.0));

			if (pos0.length == 1) {
				if (pos1.length == 1) {
					BlockPos pos0r = pos0[0]; // pos 0 raw
					BlockPos pos1r = pos1[0]; // pos 1 raw
					return new BlockPos[] {new BlockPos(average(pos0r.getX(), pos1r.getX()), average(pos0r.getY(), pos1r.getY()), average(pos0r.getZ(), pos1r.getZ()))};
				} else {
					return mix(pos1, pos0[0]);
				}
			} else if (pos1.length == 1) {
				return mix(pos0, pos1[0]);
			} else {
				BlockPos[] result = new BlockPos[pos0.length + pos1.length];
				System.arraycopy(pos0, 0, result, 0, pos0.length);
				System.arraycopy(pos1, 0, result, pos0.length, pos1.length);
				return result;
			}
		}

		private static BlockPos[] mix(BlockPos[] arr, BlockPos pos) {
			final int len = arr.length;
			BlockPos[] result = new BlockPos[len];

			for (int i = 0; i < len; ++i) {
				BlockPos pos1 = arr[i];
				result[i] = new BlockPos(average(pos.getX(), pos1.getX()), average(pos.getY(), pos1.getY()), average(pos.getZ(), pos1.getZ()));
			}

			return result;
		}

		private static int average(int a, int b) {
			return (a + b) / 2;
		}

		public static String getConnectiveMorpheme(Compound ma) {
			return Attribute.getConnectiveMorpheme(ma.ma0) + Attribute.getConnectiveMorpheme(ma.ma1);
		}

		public static String getConnectiveCloseFrontMorpheme(Compound ma) {
			return Attribute.getConnectiveCloseFrontMorpheme(ma.ma0) + Attribute.getConnectiveCloseFrontMorpheme(ma.ma1);
		}
	}
}
