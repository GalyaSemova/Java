package shopAndGoods;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ShopTest {
    private Shop shop;

    @Before
    public void setUp (){
        this.shop = new Shop();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetShelvesShouldReturnUnmodifiableCollection() {
        shop.getShelves().clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsWhenNonExistingShelf() throws OperationNotSupportedException {
        shop.addGoods("invalidTestShelf", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddGoodsThrowsWhenExistingGood() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        shop.addGoods("Shelves1", goods);

    }
    @Test
    public void testAddGoodsReturnsCorrectMessageOnAddition() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        String expected = "Goods: test_code is placed successfully!";
        String actual = shop.addGoods("Shelves1", goods);
        assertEquals(expected,actual);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsWhenNonExistingShelf() throws OperationNotSupportedException {
        shop.addGoods("invalidTestShelf", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveGoodsThrowsForDifferentGoodsOnTheSameShelf() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        Goods goods2 = new Goods("test_good2", "test_code2");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods2);

    }
    @Test
    public void testRemoveGoodsReturnsCorrectMessageOnAddition() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        String expected = "Goods: test_code is removed successfully!";
        String actual = shop.removeGoods("Shelves1", goods);
        assertEquals(expected,actual);

    }

    @Test
    public void testRemoveGoodsShouldSetTheGoodValueToNull() throws OperationNotSupportedException {
        Goods goods = new Goods("test_good", "test_code");
        shop.addGoods("Shelves1", goods);
        shop.removeGoods("Shelves1", goods);
        Goods emptySlot = shop.getShelves().get("Shelves1");
        assertNull(emptySlot);

    }




}