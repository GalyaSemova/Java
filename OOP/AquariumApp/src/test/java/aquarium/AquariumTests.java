package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests {
    private Aquarium aquarium;

    @Before
    public void setUp() {
        Fish fish1 = new Fish("fish1");
        Fish fish2 = new Fish("fish2");
        Fish fish3 = new Fish("fish3");
        aquarium = new Aquarium("test", 4);
        aquarium.add(fish1);
        aquarium.add(fish2);
        aquarium.add(fish3);

    }

    @Test(expected = NullPointerException.class)
    public void testCreateAquariumThrowsWithNullName() {
        Aquarium aquarium1 = new Aquarium(null, 4);

    }
    @Test(expected = NullPointerException.class)
    public void testCreateAquariumThrowsWithEmptyName() {
        Aquarium aquarium1 = new Aquarium("  ", 4);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAquariumThrowsWithNegativeCapacity() {
        Aquarium aquarium1 = new Aquarium("invalid", -4);

    }

    @Test
    public void testCreateAquariumCreatesNewAquarium() {
        Aquarium aquarium1 = new Aquarium("test", 4);
        assertEquals(4, aquarium1.getCapacity());
        assertEquals("test", aquarium1.getName());

    }
    @Test
    public  void testGetCountReturnsNUmOfFishes() {
        assertEquals(3, aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddFishThrowsIfNoCapacity() {
        Fish fish4 = new Fish("fish4");
        aquarium.add(fish4);
        Fish fish5 = new Fish("fish5");
        aquarium.add(fish5);
    }
    @Test
    public void testAddFishAddsFishInTheAquarium() {
        assertEquals(3, aquarium.getCount());
        Fish fish4 = new Fish("fish4");
        aquarium.add(fish4);
        assertEquals(4, aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveThrowsIfNOSuchFish(){
        Fish fish5 = new Fish("fish5");
        aquarium.remove(fish5.getName());
    }
    @Test
    public void testRemoveRemovesFishFromTheAquarium(){
        assertEquals(3, aquarium.getCount());
        aquarium.remove("fish1");
        assertEquals(2, aquarium.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSellFishThrowsIfNoSuchFish(){
        aquarium.sellFish("missing_fish");
    }
    @Test
    public void testSellFishReturnsFalseAvailbilityIftheFishIsSold(){
        Fish fish4 = new Fish("fish4");
        aquarium.add(fish4);
        assertTrue(fish4.isAvailable());
        aquarium.sellFish("fish4");
        assertFalse(fish4.isAvailable());
    }



}

