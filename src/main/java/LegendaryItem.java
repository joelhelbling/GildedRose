
public class LegendaryItem extends AgingItem {
	
	private static final int PRE_SELL_IN_APPRECIATION_RATE = 0;
	private static final int POST_SELL_IN_APPRECIATION_RATE = 0;
	private static final int SELLIN_DECREASE_RATE = 0;

	public LegendaryItem(Item item) {
		super(item, 
				PRE_SELL_IN_APPRECIATION_RATE, 
				POST_SELL_IN_APPRECIATION_RATE, 
				SELLIN_DECREASE_RATE);
	}
}
