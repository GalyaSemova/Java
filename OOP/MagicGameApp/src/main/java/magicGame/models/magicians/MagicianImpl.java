package magicGame.models.magicians;

import magicGame.models.magics.Magic;

import static magicGame.common.ExceptionMessages.*;

public abstract class MagicianImpl implements Magician{
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    protected MagicianImpl(String username, int health, int protection, Magic magic) {
        setUsername(username);
        setHealth(health);
        setProtection(protection);
        setMagic(magic);
    }

    private void setUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            throw new NullPointerException(INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    private void setHealth(int health) {
        if(health < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    private void setProtection(int protection) {
        if(protection < 0) {
            throw new IllegalArgumentException(INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }

    private void setMagic(Magic magic) {
        if(magic == null) {
            throw new NullPointerException(INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public Magic getMagic() {
        return magic;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public void takeDamage(int points) {
        if(points <= protection) {
            setProtection(getProtection() - points);
        } else {
            int pointsToRemoveFromHealth = points - protection;
            setProtection(0);
            setHealth(Math.max(0, getHealth() - pointsToRemoveFromHealth));
        }
    }

    @Override
    public String toString() {
        //{magician type}: {magician username}
        // Health: {magician health}
        // Protection: {magician protection}
        // Magic: {magician magic name}"
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s", getClass().getSimpleName(), username)).append(System.lineSeparator());
        sb.append(String.format("Health: %d", health)).append(System.lineSeparator());
        sb.append(String.format("Protection: %d", protection)).append(System.lineSeparator());
        sb.append(String.format("Magic: %s", getMagic().getName())).append(System.lineSeparator());
        return sb.toString().trim();
    }
}
