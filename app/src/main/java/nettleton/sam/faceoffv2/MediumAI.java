package nettleton.sam.faceoffv2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

class MediumAI extends StrategyFormat {
    private Integer[] case1 = new Integer[] {0, 1, 2, 3};
    private Integer[] case2 = new Integer[] {0, 1, 4, 5};
    private Integer[] case3 = new Integer[] {0, 2, 4, 6};
    private Integer[] case4 = new Integer[] {7, 2, 3, 6};
    private Integer[] case5 = new Integer[] {7, 3, 1, 5};
    private Integer[] case6 = new Integer[] {7, 4, 5, 6};
    private Integer[] case7 = new Integer[] {0, 4, 8, 12};
    private Integer[] case8 = new Integer[] {1, 5, 9, 13};
    private Integer[] case9 = new Integer[] {0, 2, 8, 10};
    private Integer[] case10 = new Integer[] {4, 6, 12, 14};
    private Integer[] case11 = new Integer[] {1, 3, 11, 9};
    private Integer[] case12 = new Integer[] {5, 7, 13, 15};
    private Integer[] case13 = new Integer[] {0, 1, 8, 9};
    private Integer[] case14 = new Integer[] {2, 3, 10, 11};
    private Integer[] case15 = new Integer[] {4, 5, 12, 13};
    private Integer[] case16 = new Integer[] {6, 7, 14, 15};
    private Integer[] case17 = new Integer[] {8, 9, 10, 11};
    private Integer[] case18 = new Integer[] {8, 9, 12, 13};
    private Integer[] case19 = new Integer[] {8, 10, 12, 14};
    private Integer[] case20 = new Integer[] {15, 9, 11, 13};
    private Integer[] case21 = new Integer[] {15, 10, 11, 14};
    private Integer[] case22 = new Integer[] {15, 12, 13, 14};
    private Integer[] case23 = new Integer[] {3, 7, 11, 15};
    private Integer[] case24 = new Integer[] {2, 6, 10, 14};
    private Integer[][] winCases = new Integer[][]{case1, case2, case3, case4, case5, case6, case7, case8,
            case9, case10, case11, case12, case13, case14, case15, case16, case17, case18,
            case19, case20, case21, case22, case23, case24};

    public int playMethod(){
        List<Integer> available = new ArrayList<>();
        List<Integer> priority1 = new ArrayList<>();
        List<Integer> priority2 = new ArrayList<>();
        List<Integer> priority3 = new ArrayList<>();
        int aiError = new Random().nextInt(100);
        for (Integer i = 0; i < Game.board.length; i++) {
            if (Game.board[i] == 0) {
                available.add(i);
            }
        }
        for (int i = 0; i < winCases.length; i++) {
            List<List<Integer>> faceData = new ArrayList<>(numFace(winCases[i]));
            if (faceData.get(0).size() == 1) {
                priority1.addAll(faceData.get(0));
            }
            //if you can block a winning move, set it as priority 2
            if (faceData.get(1).size() == 1) {
                priority2.addAll(faceData.get(1));
            }
            //if there are any uncontested faces with 2, place there
            if (faceData.get(0).size() == 2) {
                priority3.addAll(faceData.get(0));
            }
            if (faceData.get(1).size() == 2) {
                priority3.addAll(faceData.get(1));
            }
        }
        if (priority1.size() > 0) {
            return priority1.get(new Random().nextInt(priority1.size()));
        }
        if (priority2.size() > 0 && aiError < 75) {
            return priority2.get(new Random().nextInt(priority2.size()));
        }
        if (priority3.size() > 0 && aiError < 50) {
            return priority3.get(new Random().nextInt(priority3.size()));
        }
        return available.get(new Random().nextInt(available.size()));

    }

    private List<List<Integer>> numFace(Integer[] face) {
        List<Integer> aiCount = new ArrayList<>();
        List<Integer> playerCount = new ArrayList<>();
        List<Integer> aiNeeded = new ArrayList<>();
        List<Integer> playerNeeded = new ArrayList<>();
        for (Integer i = 0; i < face.length; i++) {
            if (Game.aiFirst) {
                if (Game.board[face[i]] == 1) {
                    aiCount.add(face[i]);
                } else if (Game.board[face[i]] == 2) {
                    playerCount.add(face[i]);
                }
            } else {
                if (Game.board[face[i]] == 2) {
                    aiCount.add(face[i]);
                } else if (Game.board[face[i]] == 1) {
                    playerCount.add(face[i]);
                }
            }
        }
        List<Integer> faceList = Arrays.asList(face);
        if ((aiCount.size() > 0) && (playerCount.size() == 0)) {
            aiNeeded = subtract(faceList, aiCount);
        }
        if ((playerCount.size() > 0) && (aiCount.size() == 0)) {
            playerNeeded = subtract(faceList, playerCount);
        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(aiNeeded);
        result.add(playerNeeded);
        return result;
    }

    private <T> List<T> subtract(List<T> list1, List<T> list2) {
        List<T> result = new ArrayList<T>();
        Set<T> set2 = new HashSet<T>(list2);
        for (T t1 : list1) {
            if( !set2.contains(t1) ) {
                result.add(t1);
            }
        }
        return result;
    }
}
