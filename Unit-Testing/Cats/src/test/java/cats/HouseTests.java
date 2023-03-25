package cats;


import org.junit.Assert;
import org.junit.Before;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class HouseTests {

    private House house;
    private Collection<Cat> cats;

    @Before
    public void setUp(){
        house = new House("house", 3);
        cats = new ArrayList<>();
    }

    @Test
    public  void testCreateHouse() {
        assertEquals("house", house.getName());
        assertEquals(3,house.getCapacity());
    }
    @Test(expected = NullPointerException.class)
    public void testCreateHouseThrowsWhenNameNull() {
        House house = new House(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateHouseThrowsWhenNameEmpty() {
        House house = new House("   ", 3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHouseThrowsWhenNegativeCapacity() {
        House house = new House("house", -1);
    }

    @Test
    public void testGetCountReturnsNumOfCatsInHouse(){
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        assertEquals(3, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatThrowsIfNoCapacity(){
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        Cat cat4 = new Cat("four");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        house.addCat(cat4);

    }

    @Test
    public void testAddCatAddsCat(){
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        Cat cat4 = new Cat("four");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        assertEquals(3, house.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCatThrowsIfInvalidCat() {
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        Cat cat4 = new Cat("four");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        house.removeCat("noSuchCat");

    }

    @Test
    public void testRemoveCatRemovesCat(){
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        assertEquals(3, house.getCount());
        house.removeCat(cat1.getName());
        assertEquals(2,house.getCount());


    }
    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleThrowsWhenNoCat(){

        house.catForSale("noSuchCat");


    }
    @Test
    public void testCatForSaleWhenCatIsNoHungry(){
        Cat cat1 = new Cat("one");
        Cat cat2 = new Cat("two");
        Cat cat3 = new Cat("three");
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        house.catForSale(cat1.getName());
        assertFalse(cat1.isHungry());





    }









}
