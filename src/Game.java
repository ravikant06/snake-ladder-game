import ladder.Ladder;
import player.Player;
import snake.Snake;

import java.util.List;
import java.util.Map;

public class Game {
    Map<Player,Integer> players;
    List<Snake> snakes;
    List<Ladder> ladders;
    final int boardSize;
    public Game(int size) {
        this.boardSize = size;
    }

    public Map<Player, Integer> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Player, Integer> players) {
        this.players = players;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public void start() {
        if(boardSize == 0 || players == null || players.size() == 0) {
            throw new IllegalStateException("Please initialize the game first");
        }
        initPlayerPos();
        play();
    }
    private void initPlayerPos() {
        for(Player p : players.keySet()) {
            players.put(p,0);
        }
    }

    private void play() {
        boolean beingPlayed = true;
        while (beingPlayed) {
            for(Player p : players.keySet()) {

                int roll = p.diceRoll();
                int initPos = players.get(p);
                if(initPos + roll > boardSize) {
                    continue;
                }
                int finalPos = initPos + roll;
                for(Snake snake : snakes) {
                    if(snake.getStart() == finalPos) {
                        finalPos = snake.getEnd();
                    }
                }
                for(Ladder ladder : ladders) {
                    if(ladder.getStart() == finalPos) {
                        finalPos = ladder.getEnd();
                    }
                }
                players.put(p,finalPos);
                System.out.println(p.getName() + " rolled a  " + roll + " and moved from " + initPos + " to " + finalPos);
                if(finalPos == boardSize) {
                    System.out.println("Player : " + p.getName() + "WON");
                    beingPlayed = false;
                    break;
                }
            }
        }
    }
}
