
public class ConjuredItem extends AgingItem {
	
	private static final int PRE_SELL_IN_APPRECIATION_RATE = -2;
	private static final int POST_SELL_IN_APPRECIATION_RATE = -4;
	private static final int SELLIN_DECREASE_RATE = 1;

	public ConjuredItem(Item item) {
		super(item, 
				PRE_SELL_IN_APPRECIATION_RATE, 
				POST_SELL_IN_APPRECIATION_RATE, 
				SELLIN_DECREASE_RATE);
	}
}
