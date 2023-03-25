package magicGame.core;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;
import magicGame.models.magics.BlackMagic;
import magicGame.models.magics.Magic;
import magicGame.models.magics.RedMagic;
import magicGame.models.region.Region;
import magicGame.models.region.RegionImpl;
import magicGame.repositories.interfaces.MagicRepository;
import magicGame.repositories.interfaces.MagicRepositoryImpl;
import magicGame.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


import static magicGame.common.ExceptionMessages.*;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static magicGame.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller{

    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        magics = new MagicRepositoryImpl();
        magicians = new MagicianRepositoryImpl();
        region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        if(type.equals("RedMagic")) {
            magic = new RedMagic(name, bulletsCount);
        } else if(type.equals("BlackMagic")) {
            magic = new BlackMagic(name, bulletsCount);
        } else {
            throw  new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }

        magics.getData().add(magic);

        return String.format(SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magic magicToAdd = magics.findByName(magicName);
        if(magicToAdd == null) {
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }

        Magician magician;
        if(type.equals("Wizard")) {
            magician = new Wizard(username, health, protection, magicToAdd);
        } else if (type.equals("BlackWidow")) {
            magician = new BlackWidow(username, health, protection, magicToAdd);
        } else {
            throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }

        magicians.getData().add(magician);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, username);
    }

    @Override
    public String startGame() {
       Collection<Magician> aliveMagicians = magicians.getData().stream().filter(m -> m.getHealth() > 0).collect(Collectors.toList());
        return region.start(aliveMagicians);
    }

    @Override
    public String report() {
        List<Magician> sortedList =  magicians.getData().stream()
                .sorted(Comparator.comparingInt(Magician::getHealth).reversed())
                .sorted(Comparator.comparing(Magician::getUsername))
                .sorted(Comparator.comparing(m -> m.getClass().getSimpleName()))
                .collect(Collectors.toList());

        return sortedList.stream()
                .map(Magician::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
