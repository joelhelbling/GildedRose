import java.util.ArrayList;
import java.util.List;


public class GildedRose {

	private static final String CONJURED_ITEM = "Conjured Mana Cake";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void mainDisabled(String[] args) {
        System.out.println("OMGHAI!");
        updateQuality(stockTheShelves());
	}
	
	public static List<Item> stockTheShelves() {
		items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item(AGED_BRIE, 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item(SULFURAS, 0, 80));
        items.add(new Item(BACKSTAGE_PASSES, 15, 20));
        items.add(new Item(CONJURED_ITEM, 3, 6));
        return items;
	}
	
    public static void updateQuality(List<Item> items) {
        for (Item item : items) {
        	AgingItem agingItem = AgingItemFactory.create(item);
        	agingItem.updateQuality();
        }
    }
}