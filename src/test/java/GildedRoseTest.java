import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class GildedRoseTest {

	private static final String NORMAL_ITEM = "+5 Dexterity Vest";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	private static final String CONJURED_ITEM = "Conjured Mana Cake";
	private List<Item> items;
	private Item item;

	@Before
	public void setUp() {
		items = new ArrayList<Item>();
	}

	@Test
	public void regularItemQualityDecreases() {
		stock(NORMAL_ITEM, 1, 10);
		ageOneDay();
		assertSellInAndQuality("before sell-in", 0, 10 - 1);
		ageOneDay();
		assertSellInAndQuality("after sell-in", -1, 9 - 2);
	}
	
	@Test
	public void regularItemQualityIsNeverLessThanZero() {
		stock(NORMAL_ITEM, 1, 0);
		ageOneDay();
		assertSellInAndQuality("zero minus one", 0, 0);
	}
	
	@Test
	public void regularItemQualityOfOneMinusTwoIsJustZero() {
		stock(NORMAL_ITEM, 0, 1);
		ageOneDay();
		assertSellInAndQuality("one minus two", -1, 0);
	}
	
	@Test
	public void cheeseQualityIncreases() {
		stock(AGED_BRIE, 1, 10);
		ageOneDay();
		assertSellInAndQuality("before sell-in", 0, 10 + 1);
		ageOneDay();
		assertSellInAndQuality("after sell-in", -1, 11 + 2);
	}
	
	@Test
	public void cheeseQualityIsNeverGreaterThan50() {
		stock(AGED_BRIE, 1, 50);
		ageOneDay();
		assertSellInAndQuality("50 plus one", 0, 50);
	}
	
	@Test
	public void cheeseQualityOf49PlusTwoIs50() {
		stock(AGED_BRIE, 0, 49);
		ageOneDay();
		assertSellInAndQuality("49 plus two", -1, 50);
	}
	
	@Test
	public void backStagePassesIncreaseInQuality() {
		stock(BACKSTAGE_PASSES, 11, 10);
		ageOneDay();
		assertSellInAndQuality("more than 10 days out", 10, 10 + 1);
		ageOneDay();
		assertSellInAndQuality("less than 10 days out", 9, 11 + 2);
		ageDays(4);
		assertSellInAndQuality(5, 13 + 8);
		ageOneDay();
		assertSellInAndQuality("less than 5 days out", 4, 21 + 3);
		ageDays(4);
		assertSellInAndQuality(0, 24 + 12);
		ageOneDay();
		assertSellInAndQuality("after the concert", -1, 0);
	}

	@Test
	public void backStagePassQualityIsNeverMoreThan50() {
		stock(BACKSTAGE_PASSES, 11, 50);
		ageOneDay();
		assertSellInAndQuality("50 plus 1", 10, 50);
	}
	
	@Test
	public void backStagePassQualityOf49plusTwoIs50() {
		stock(BACKSTAGE_PASSES, 9, 49);
		ageOneDay();
		assertSellInAndQuality("49 plus 2", 8, 50);
	}
	
	@Test
	public void backStagePassQualityOf49plusThreeIs50() {
		stock(BACKSTAGE_PASSES, 4, 49);
		ageOneDay();
		assertSellInAndQuality("49 plus 3", 3, 50);
	}
	
	@Test
	public void backStagePassQualityOf48plusThreeIs50() {
		stock(BACKSTAGE_PASSES, 4, 48);
		ageOneDay();
		assertSellInAndQuality("48 plus 3", 3, 50);
	}
	
	@Test
	public void legendaryQualityAndSellInNeverChange() {
		stock(SULFURAS, 1, 80);
		ageOneDay();
		assertSellInAndQuality(1, 80);
	}
	
	@Test
	public void conjuredItemQualityDecreases() {
		stock(CONJURED_ITEM, 1, 10);
		ageOneDay();
		assertSellInAndQuality("before sell-in", 0, 10 - 2);
		ageOneDay();
		assertSellInAndQuality("after sell-in", -1, 8 - 4);
	}

	@Test
	public void conjuredItemQualityIsNeverLessThanZero() {
		stock(CONJURED_ITEM, 1, 0);
		ageOneDay();
		assertSellInAndQuality("zero minus two", 0, 0);
	}

	@Test
	public void conjuredItemQualityOfOneMinusTwoIsJustZero() {
		stock(CONJURED_ITEM, 1, 1);
		ageOneDay();
		assertSellInAndQuality("one minus two", 0, 0);
	}
	
	@Test
	public void conjuredItemQualityOfZeroMinusFourIsStillZero() {
		stock(CONJURED_ITEM, -1, 0);
		ageOneDay();
		assertSellInAndQuality("zero minus four", -2, 0);
	}
	
	@Test
	public void conjuredItemQualityOfOneMinusFourIsJustZero() {
		stock(CONJURED_ITEM, -1, 1);
		ageOneDay();
		assertSellInAndQuality("one minus four", -2, 0);
	}
	
	@Test
	public void conjuredItemQualityOfTwoMinusFourIsJustZero() {
		stock(CONJURED_ITEM, -1, 2);
		ageOneDay();
		assertSellInAndQuality("two minus four", -2, 0);
	}
	
	@Test
	public void conjuredItemQualityOfThreeMinusFourIsJustZero() {
		stock(CONJURED_ITEM, -1, 3);
		ageOneDay();
		assertSellInAndQuality("three minus four", -2, 0);
	}
	

	// helpers
	
	private void stock(String name, int sellIn, int quality) {
		item = new Item(name, sellIn, quality);
		items.add(item);
	}
	
	private void ageDays(int days) {
		for (int i=0; i<days;i++) {
			ageOneDay();
		}
	}
	
	private void ageOneDay() {
		GildedRose.updateQuality(items);
	}
	
	private void assertSellInAndQuality(int expectedSellIn, int expectedQuality) {
		assertSellInAndQuality("", expectedSellIn, expectedQuality);
	}

	private void assertSellInAndQuality(String comment, int expectedSellIn, int expectedQuality) {
		if (comment.length() > 0)
			comment = " (" + comment + ")";
		assertThat("sellIn", item.sellIn, (is(expectedSellIn)));
		assertThat("quality" + comment, item.quality, is(expectedQuality));
	}

}