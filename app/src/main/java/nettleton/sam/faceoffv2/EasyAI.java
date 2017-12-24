package nettleton.sam.faceoffv2;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


class EasyAI extends StrategyFormat {
    private List<Integer> suggested = new ArrayList<>();
    private List<Integer> available = new ArrayList<>();
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
        for (int i = 0; i < Game.board.length; i++) {
            if (Game.board[i] == 0) {
                available.add(i);
            }
        }
        for (int i = 0; i < winCases.length; i++) {
            List<Integer> three = new ArrayList<>(threeFace(winCases[i]));
            if (three.size() == 1) {
                suggested.addAll(three);
            }
        }
        if (suggested.size() != 0) {
            return suggested.get(new Random().nextInt(suggested.size()));
        } else {
            //int aiChoice = available.get(new Random().nextInt(available.size()));
            return available.get(new Random().nextInt(available.size()));
        }
    }

    private List<Integer> threeFace(Integer[] face) {
        List<Integer> p1count = new ArrayList<>();
        List<Integer> p2count = new ArrayList<>();
        for (Integer i = 0; i < face.length; i++) {
            if (Game.board[face[i]] == 1) {
                p1count.add(face[i]);
            } else if (Game.board[face[i]] == 2) {
                p2count.add(face[i]);
            }
        }
        List<Integer> faceList = Arrays.asList(face);
        if ((p1count.size() == 3) && (p2count.size() == 0)) {
            faceList = subtract(faceList, p1count);
        }
        Log.d("P2COUNT", "Made it! 0");
        if ((p2count.size() == 3) && (p1count.size() == 0)) {
            Log.d("P2COUNT", "Made it! 1");
            faceList = subtract(faceList, p2count);
            Log.d("P2COUNT", "Made it! 2");
        }
        return faceList;
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
