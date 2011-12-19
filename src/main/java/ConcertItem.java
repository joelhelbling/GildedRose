
public class ConcertItem extends AgingItem {

	private static final int PRE_SELL_IN_APPRECIATION_RATE = 0;
	private static final int POST_SELL_IN_APPRECIATION_RATE = 0;
	private static final int SELLIN_DECREASE_RATE = 1;
	private Item item;

	public ConcertItem(Item item) {
		super(item, 
				PRE_SELL_IN_APPRECIATION_RATE, 
				POST_SELL_IN_APPRECIATION_RATE, 
				SELLIN_DECREASE_RATE);
		this.item = item;
	}
	
	@Override
	public int appreciationRate() {
		int sellIn = item.getSellIn();
		int appreciationRate;
		if (sellIn <= 0)
			appreciationRate = (-1) * item.getQuality();
		else if (sellIn <= 5)
			appreciationRate = 3;
		else if (sellIn <= 10)
			appreciationRate = 2;
		else
			appreciationRate = 1;
		return appreciationRate;
	}

}
