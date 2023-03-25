package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {
    private Farm farm;
    private Animal animal;


    @Before
    public void setUp(){
        farm = new Farm("farm", 4);
        animal = new Animal("dog", 55);
    }

    @Test
    public void testCreatesANewFarm() {
        assertEquals("farm", farm.getName());
        assertEquals(4, farm.getCapacity());

    }

    @Test(expected = NullPointerException.class)
    public  void testCreateFarmThrowsWhenNullName () {
        Farm noValid = new Farm(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public  void testCreateFarmThrowsWhenEmptyName () {
        Farm noValid = new Farm("    ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateFarmThrowsWhenNegativeCapacity(){
        Farm noValid = new Farm("name", -1);
    }
    @Test
    public void testGetCountReturnsNumOfAnimalsInTheFarm() {
        animalListSetUp(farm);
        assertEquals(3,farm.getCount());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowsIfThereIsNoCapacity(){
        animalListSetUp(farm);
        farm.add(animal);
        Animal anima5 = new Animal("bird", 5);
        farm.add(anima5);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddThrowsIfThereIsSuchAnimalInTheFarm(){
        farm.add(animal);
        farm.add(animal);

    }
    @Test
    public void testAddAddsAnimalToTheFarm(){
        farm.add(animal);
        assertEquals(1, farm.getCount());
    }
    @Test
    public void testRemoveReturnsNullIfNOSuchTypeAnimalINFarm() {
        animalListSetUp(farm);
        farm.remove("dog");
        assertFalse(farm.remove("dog"));
    }

    @Test
    public void testRemoveRemoveExistingAnimalsFromTheFarm(){
        animalListSetUp(farm);
        farm.remove("cow");
        assertEquals(2, farm.getCount());

    }



    private Farm animalListSetUp(Farm farm) {
        Animal animal1 = new Animal("cow", 20);
        Animal animal2 = new Animal("horse", 100);
        Animal animal3 = new Animal("cat", 10);
        farm.add(animal1);
        farm.add(animal2);
        farm.add(animal3);
        return farm;
    }


}
