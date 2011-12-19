
public class NormalItem extends AgingItem {

	private static final int PRE_SELL_IN_APPRECIATION_RATE = -1;
	private static final int POST_SELL_IN_APPRECIATION_RATE = -2;
	private static final int SELLIN_DECREASE_RATE = 1;

	public NormalItem(Item item) {
		super(item, 
				PRE_SELL_IN_APPRECIATION_RATE, 
				POST_SELL_IN_APPRECIATION_RATE, 
				SELLIN_DECREASE_RATE);
	}

}
