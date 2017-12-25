package nettleton.sam.faceoffv2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


class HardAI extends StrategyFormat {
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
        List<Integer> priority1 = new ArrayList<>();
        List<Integer> priority2 = new ArrayList<>();
        //List<Integer> priority3 = new ArrayList<>();
        List<Integer> priority4 = new ArrayList<>();
        List<Integer> priority5 = new ArrayList<>();
        List<Integer> priority6 = new ArrayList<>();
        List<Integer> available = new ArrayList<>();
        //get available spaces on board
        for (int i = 0; i < Game.board.length; i++) {
            if (Game.board[i] == 0) {
                available.add(i);
            }
        }
        //check to see how many dots there are on each uncontested faces
        for (int i = 0; i < winCases.length; i++) {
            List<List<Integer>> faceData = new ArrayList<>(numFace(winCases[i]));
            //if there is a winning move, set it as priority 1
            Log.d("TESTINGai", Integer.toString(faceData.get(0).size()));
            Log.d("TESTINGplayer", Integer.toString(faceData.get(1).size()));
            if (faceData.get(0).size() == 1) {
                priority1.addAll(faceData.get(0));
            }
            //if you can block a winning move, set it as priority 2
            if (faceData.get(1).size() == 1) {
                priority2.addAll(faceData.get(1));
            }
            //if there is a face with two uncontested points, set as priority 4
            //(priority 3 is calculated after for loop)
            if (faceData.get(0).size() == 2) {
                priority4.addAll(faceData.get(0));
            }
            if (faceData.get(1).size() == 2) {
                priority4.addAll(faceData.get(1));
            }
            //priority 5 any of player 1's faces with 1
            if (Game.aiFirst) {
                if (faceData.get(0).size() == 3) {
                    priority5.addAll(faceData.get(0));
                }
                if (faceData.get(1).size() == 3) {
                    priority6.addAll(faceData.get(1));
                }
            }
            //priority 6 any of player 2's faces with 1
            if (!Game.aiFirst) {
                if (faceData.get(0).size() == 3) {
                    priority6.addAll(faceData.get(0));
                }
                if (faceData.get(1).size() == 3) {
                    priority5.addAll(faceData.get(1));
                }
            }
        }
        //priority 3 is any duplicate value in priority 4
        List<Integer> priority3 = new ArrayList<>(findDuplicates(priority4));
        List<List<Integer>> priorityList = new ArrayList<>();
        priorityList.add(priority1);
        priorityList.add(priority2);
        priorityList.add(priority3);
        priorityList.add(priority4);
        priorityList.add(priority5);
        priorityList.add(priority6);
        //int aiError = new Random().nextInt(100);
        Log.d("TESTING1", Integer.toString(priority1.size()));
        Log.d("TESTING2", Integer.toString(priority2.size()));
        Log.d("TESTING3", Integer.toString(priority3.size()));
        Log.d("TESTING4", Integer.toString(priority4.size()));
        Log.d("TESTING5", Integer.toString(priority5.size()));
        Log.d("TESTING6", Integer.toString(priority6.size()));
        for (int i = 0; i < priorityList.size(); i++) {
            if (priorityList.get(i).size() != 0) {
                return (priorityList.get(i).get(new Random().nextInt(priorityList.get(i).size())));
            }
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

    private Set<Integer> findDuplicates(List<Integer> listContainingDuplicates) {
        final Set<Integer> setToReturn = new HashSet<>();
        final Set<Integer> set1 = new HashSet<>();
        for (Integer yourInt : listContainingDuplicates)
        {
            if (!set1.add(yourInt))
            {
                setToReturn.add(yourInt);
            }
        }
        return setToReturn;
    }
}
