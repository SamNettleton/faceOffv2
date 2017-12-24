package nettleton.sam.faceoffv2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class HardAI extends StrategyFormat {
    public int playMethod(){
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < Game.board.length; i++) {
            if (Game.board[i] == 0) {
                available.add(i);
            }
        }
        //int aiChoice = available.get(new Random().nextInt(available.size()));
        return available.get(new Random().nextInt(available.size()));
    }
}
