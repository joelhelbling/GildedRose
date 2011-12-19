
public class AgingItemFactory {
	
	private static final String CONJURED_ITEM = "Conjured Mana Cake";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

	public static AgingItem create(Item item) {
		AgingItem ai;
		if (BACKSTAGE_PASSES.equals(item.getName())) {
    		ai = new ConcertItem(item);
    	} else if (SULFURAS.equals(item.getName())) {
    		ai = new LegendaryItem(item);
    	} else if (AGED_BRIE.equals(item.getName())) {
    		ai = new CheeseItem(item);
    	} else if (CONJURED_ITEM.equals(item.getName())) {
    		ai = new ConjuredItem(item);
    	} else {
    		ai = new NormalItem(item);
    	}
		return ai;

	}
}
