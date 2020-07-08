package player;

import dice.Dice;

import java.util.UUID;

public class Player {
    final String id;
    final String name;

    public Player(String name) {
        this.id = UUID.randomUUID().toString();;
        this.name = name;
    }

    public int diceRoll() {
        return Dice.roll();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
