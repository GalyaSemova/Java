package magicGame.models.region;

import magicGame.models.magicians.BlackWidow;
import magicGame.models.magicians.Magician;
import magicGame.models.magicians.Wizard;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class RegionImpl implements Region{

    @Override
    public String start(Collection<Magician> magicians) {

        List<Magician> wizards = magicians.stream().filter(m -> m.getClass().getSimpleName().equals("Wizard") && m.getHealth() > 0 && m.getMagic().getBulletsCount() > 0)
                .collect(Collectors.toList());
        List<Magician> blackWidows = magicians.stream().filter(m -> m.getClass().getSimpleName().equals("BlackWidow") && m.getHealth() > 0 && m.getMagic().getBulletsCount() > 0)
                .collect(Collectors.toList());

        String output = null;



        for (Magician wizard : wizards) {
            while(wizard.isAlive() && blackWidows.iterator().hasNext()) {
                if(blackWidows.isEmpty()) {
                    break;
                }
                int bullets = wizard.getMagic().fire();//points
                Magician currentBlackWidow = blackWidows.iterator().next();
                currentBlackWidow.takeDamage(bullets);
                if(!currentBlackWidow.isAlive()) {
                    blackWidows.remove(currentBlackWidow);
                }
            }
            wizards.remove(wizard);

        }
        if(!blackWidows.isEmpty()) {
                output = "Black widows win!";
        } else {
            output = "Wizards win!";
        }
        return output;
    }
}
