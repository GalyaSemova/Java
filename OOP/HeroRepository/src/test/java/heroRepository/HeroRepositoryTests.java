package heroRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTests {
    private HeroRepository heroRepository;
    private Hero hero1;
    private Hero hero2;
    private  Hero hero3;

    @Before
    public void setUp(){
        heroRepository = new HeroRepository();

    }

    @Test
    public void testGetCountReturnsNumOfHeroes() {
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 2);
        hero3 = new Hero("name3", 3);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        assertEquals(3, heroRepository.getCount());


    }
    @Test(expected = NullPointerException.class)
    public void testCreateHeroThrowsIfNullHero() {
        heroRepository.create(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCreateHeroThrowsIfHeroAlreadyExist() throws UnsupportedOperationException{
        hero1 = new Hero("name1", 1);
        heroRepository.create(hero1);
        hero2 = new Hero("name1", 2);

        heroRepository.create(new Hero("name1",  1));
        assertEquals(1, heroRepository.getCount());
        heroRepository.create(hero2);

    }
    @Test
    public void testCreateCreatesHeroInRepository(){
        hero1 = new Hero("name1", 1);
        heroRepository.create(hero1);
        assertEquals(1, heroRepository.getHeroes().size());
    }
    @Test(expected = NullPointerException.class)
    public void testRemoveThrowsIfNullName(){
        hero1 = new Hero("name1", 1);
        heroRepository.create(hero1);
        heroRepository.remove(null);

    }
    @Test
    public void testRemovesReturnsTrueIfHeroIsRemoved(){
        hero1 = new Hero("name1", 1);
        heroRepository.create(hero1);
        assertEquals(1, heroRepository.getCount());
        heroRepository.remove("name1");
        assertEquals(0, heroRepository.getCount());
    }
    @Test
    public void testGetHeroWithHighestLevelReturnsNullIfNoHigherLevel(){
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 1);
        hero3 = new Hero("name3", 1);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        heroRepository.getHeroWithHighestLevel();
    }

    @Test
    public void testGetHeroWithHighestLevelReturnsHeroOnHighestLevel(){
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 1);
        hero3 = new Hero("name3", 10);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        Hero actual = heroRepository.getHeroWithHighestLevel();
        assertEquals(hero3, actual);
    }
    @Test
    public void testGetHeroReturnsNullIfThereIsNoSuchHero() {
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 1);
        hero3 = new Hero("name3", 10);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        Hero actual = heroRepository.getHero("name-invalid");
    }
    @Test
    public void testGetHeroReturnsHeroWithTheGivenName() {
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 1);
        hero3 = new Hero("name3", 10);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        Hero actual = heroRepository.getHero("name1");
        assertEquals(hero1, actual);
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testGetHeroesReturnUnmodifiableCollection() {
        hero1 = new Hero("name1", 1);
        hero2 = new Hero("name2", 1);
        hero3 = new Hero("name3", 10);
        heroRepository.create(hero1);
        heroRepository.create(hero2);
        heroRepository.create(hero3);
        heroRepository.getHeroes().clear();

    }




}
