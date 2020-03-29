package modfest.teamgreen.world.util;

public final class Int2ToDoubleCache {
	public Int2ToDoubleCache(Int2ToDoubleFunction function) {
		this.function = function;
	}

	private int x, y = 0;
	private boolean init = false;
	private double value = 0;
	private final Int2ToDoubleFunction function;

	public double apply(int x, int y) {
		boolean retrieve = init && (this.x == x && this.y == y);

		if (!retrieve) {
			this.value = this.function.apply(x, y);
		}

		return this.value;
	}
}
