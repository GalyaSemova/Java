package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    private static final int INITIAL_KG = 9;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
    }

    @Override
    public int getKilograms() {
        return super.getKilograms() + INITIAL_KG;
    }


    @Override
    public void eating() {
        setKilograms(getKilograms() + 3);

    }
}
