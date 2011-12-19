
public class AgingItem {
	private Item item;
	private int preSellInAppreciationRate;
	private int postSellInAppreciationRate;
	private int sellInDecreaseRate;
	
	private static final int MAX_QUALITY = 50;
	private static final int MIN_QUALITY = 0;
	
	public AgingItem(Item item, int preSellInAppreciationRate, int postSellInAppreciationRate, int sellInDecreaseRate) {
		this.item = item;
		this.preSellInAppreciationRate = preSellInAppreciationRate;
		this.postSellInAppreciationRate = postSellInAppreciationRate;
		this.sellInDecreaseRate = sellInDecreaseRate;
	}
	
	public void updateQuality() {
		item.setQuality(newQuality());
		item.setSellIn(newSellIn());
	}

	public int appreciationRate() {
		return (item.getSellIn() <= 0) ? 
				postSellInAppreciationRate : 
					preSellInAppreciationRate;
	}

	private int newQuality() {
		if (appreciationRate() == 0)
			return item.getQuality();
		else
			return Math.max(
					Math.min(
							item.getQuality() + appreciationRate(), 
							MAX_QUALITY
					), MIN_QUALITY);
	}
	
	private int newSellIn() {
		return item.getSellIn() - sellInDecreaseRate;
	}
}
