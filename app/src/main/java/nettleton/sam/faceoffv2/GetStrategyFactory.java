package nettleton.sam.faceoffv2;

/**
 * Created by Sam on 10/1/2017.
 */

class GetStrategyFactory {
    public StrategyFormat getPlayMethod(String gameType){
        if(gameType == null){
            return null;
        }
        else if(gameType.equalsIgnoreCase("EASYGAME")) {
            return new EasyAI();
        }
        else if(gameType.equalsIgnoreCase("MEDIUMGAME")){
            return new MediumAI();
        }
        else if(gameType.equalsIgnoreCase("HARDGAME")) {
            return new HardAI();
        }
        return null;
    }
}