package magicGame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MagicianTests {

    private Magician magician;

    @Before
    public void setUp() {
        this.magician = new Magician("magician1", 100);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateMagicianThrowsIfNullName() {
        Magician invalid = new Magician(null, 20);
    }
    @Test(expected = NullPointerException.class)
    public void testCreateMagicianThrowsIfEmptyName() {
        Magician invalid = new Magician("   ", 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateMagicianThrowsIfNegativeHealth() {
        Magician invalid = new Magician("Invalid", -20);
    }

    @Test
    public void testCreateCreatesNewMagician() {
        assertEquals("magician1", magician.getUsername());
        assertEquals(100, magician.getHealth());
    }
    @Test(expected = UnsupportedOperationException.class)
    public void testGetMagicsShouldReturnUnmodifiableCollection(){
        magician.getMagics().clear();
    }
   @Test(expected = IllegalArgumentException.class)
    public void testTakeDamageReducesHealthThrowsIfInitialHealthLessThan0(){
        Magician invalid = new Magician("Invalid", -20);
        magician.takeDamage(6);

    }

    @Test
    public void testTakeDamageSetHealth0IfMagicianIsDead() {
        magician.takeDamage(200);
        assertEquals(0, magician.getHealth());
    }
    @Test
    public void testTakeDamageReducesIfMagicianIsDead() {
        magician.takeDamage(50);
        assertEquals(50, magician.getHealth());
    }

    @Test(expected = NullPointerException.class)
    public void testAddMagicThrowsIfMagicToAddIsNull() {
        magician.addMagic(null);
    }

    @Test
    public void testAddMagicAddsMagicToTheMagicianCollectionOfMagics() {
        assertEquals(0, magician.getMagics().size());
        Magic magic = new Magic("new_magic", 50);
        magician.addMagic(magic);
        assertEquals(1, magician.getMagics().size());
    }

    @Test
    public void testRemoveMagicRemovesAMagicFromMagiciansCollection() {
        assertEquals(0, magician.getMagics().size());
        Magic magic = new Magic("new_magic", 50);
        magician.addMagic(magic);
        assertEquals(1, magician.getMagics().size());
        magician.removeMagic(magic);
        assertEquals(0, magician.getMagics().size());


    }
    @Test
    public void testGetMagicReturnsMagicWithGivenName() {
        Magic magic = new Magic("new_magic", 50);
        magician.addMagic(magic);
        Magic magic1 = new Magic("magic2", 100);
        magician.addMagic(magic1);
        Magic magicActual = magician.getMagic("magic2");
        assertEquals(magic1, magicActual);

    }
    @Test
    public void testGetMagicReturnsNullIfNoMagicWithSuchName() {
        Magic magic = new Magic("new_magic", 50);
        magician.addMagic(magic);
        Magic magic1 = new Magic("magic2", 100);
        magician.addMagic(magic1);
        Magic magicActual = magician.getMagic("missing_magic");
        assertEquals(null, magicActual);

    }



}
