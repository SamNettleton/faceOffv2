package nettleton.sam.faceoffv2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import java.util.Random;


public class Game extends AppCompatActivity {
    DrawView drawView;
    public static int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static int turnCount = 0;
    public static boolean gameFinished = false;
    public static boolean aiFirst;
    public final GetStrategyFactory gameFactory = new GetStrategyFactory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(this);
        setContentView(drawView);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //set up game based on HomeScreen.gameType
        startGame();

        drawView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (gameFinished) { newGame(); }
                    else {
                        double xCoord = event.getX();
                        double yCoord = event.getY();
                        int selectedPoint = closestDot(xCoord, yCoord);
                        if (!gameFinished) {checkPoint(selectedPoint);}
                    }
                    drawView.invalidate();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed(){
        AlertDialog.Builder ab = new AlertDialog.Builder(Game.this);
        ab.setTitle("Confirm Exit");
        ab.setMessage("Are you sure you want to end the game?");
        ab.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                resetBoard();
                Game.this.finish();
            }
        });
        ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ab.show();
    }

    public void startGame() {
        if (!HomeScreen.gameType.equals("LOCALGAME")) {
            //determine if AI goes first
            Random random = new Random();
            aiFirst = random.nextBoolean();
            if (aiFirst) {
                Toast.makeText(Game.this,
                        ("Your opponent was chosen to go first!"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Game.this,
                        ("You were chosen to go first!"), Toast.LENGTH_SHORT).show();
            }
            //start the AI's move if it goes first
            if (aiFirst) {
                AIMove();
            }
        }
    }

    private int[] getScreenDimension(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        int[] screenInformation = new int[2];
        screenInformation[0] = width;
        screenInformation[1] = height;

        return screenInformation;
    }

    public int closestDot(double tapX, double tapY) {
        int[] xyScreen = getScreenDimension();
        double screenX = xyScreen[0];
        double screenY = xyScreen[1];
        double scaleXscreen = screenX / DrawView.xScaleFactor;
        double scaleYscreen = screenY / DrawView.yScaleFactor;
        double minDist = 1000;
        int vertexNum = -1;
        for (int i = 0; i < 16; i++) {
            double dist = Math.sqrt(Math.pow((tapX - DrawView.cubeCoords[i][0]*(scaleXscreen)), 2)
                + Math.pow((tapY - DrawView.cubeCoords[i][1]*(scaleYscreen)), 2));
            if (dist < minDist) {
                vertexNum = i;
                minDist = dist;
            }
        }
        return vertexNum;
    }

    public void checkPoint(int pointChosen) {
        int playerInTurn = (turnCount % 2) + 1;
        String[] gameMessages = {getResources().getString(R.string.info_box_blue),
                getResources().getString(R.string.info_box_red)};
        if (board[pointChosen] == 0) {
            board[pointChosen] = playerInTurn;
            DrawView.previousPoint = pointChosen;
            turnCount += 1;
            DrawView.txt = gameMessages[turnCount % 2];
            checkEndGame();
        } else {
            Toast.makeText(Game.this,
                    ("This point has already been selected!"), Toast.LENGTH_SHORT).show();
        }
    }

    public void checkEndGame() {
        int[] case1 = new int[] {0, 1, 2, 3};
        int[] case2 = new int[] {0, 1, 4, 5};
        int[] case3 = new int[] {0, 2, 4, 6};
        int[] case4 = new int[] {7, 2, 3, 6};
        int[] case5 = new int[] {7, 3, 1, 5};
        int[] case6 = new int[] {7, 4, 5, 6};
        int[] case7 = new int[] {0, 4, 8, 12};
        int[] case8 = new int[] {1, 5, 9, 13};
        int[] case9 = new int[] {0, 2, 8, 10};
        int[] case10 = new int[] {4, 6, 12, 14};
        int[] case11 = new int[] {1, 3, 11, 9};
        int[] case12 = new int[] {5, 7, 13, 15};
        int[] case13 = new int[] {0, 1, 8, 9};
        int[] case14 = new int[] {2, 3, 10, 11};
        int[] case15 = new int[] {4, 5, 12, 13};
        int[] case16 = new int[] {6, 7, 14, 15};
        int[] case17 = new int[] {8, 9, 10, 11};
        int[] case18 = new int[] {8, 9, 12, 13};
        int[] case19 = new int[] {8, 10, 12, 14};
        int[] case20 = new int[] {15, 9, 11, 13};
        int[] case21 = new int[] {15, 10, 11, 14};
        int[] case22 = new int[] {15, 12, 13, 14};
        int[] case23 = new int[] {3, 7, 11, 15};
        int[] case24 = new int[] {2, 6, 10, 14};
        int[][] winCases = new int[][]{case1, case2, case3, case4, case5, case6, case7, case8,
                case9, case10, case11, case12, case13, case14, case15, case16, case17, case18,
                case19, case20, case21, case22, case23, case24};
        String[] winMessages = {getResources().getString(R.string.winner_red),
                getResources().getString(R.string.winner_blue),
                getResources().getString(R.string.tie_game)};
        for (int i = 0; i < winCases.length; i++) {
            if (board[winCases[i][0]] != 0 && (board[winCases[i][0]] == board[winCases[i][1]]) &&
                    (board[winCases[i][0]] == board[winCases[i][2]]) &&
                    (board[winCases[i][0]] == board[winCases[i][3]])){
                for (int j = 0; j < 4; j++) {
                    //turn the winning points winning color
                    board[winCases[i][j]] = 3;
                    //change the message to the winning message
                    DrawView.txt = winMessages[turnCount % 2];
                    gameFinished = true;
                }
            }
        }
        if (turnCount == 16 && !gameFinished) {
            DrawView.txt = winMessages[2];
            gameFinished = true;
        }
        if ((!HomeScreen.gameType.equals("LOCALGAME") && !gameFinished) &&
                ((turnCount % 2 == 0 && aiFirst) || (turnCount % 2 == 1 && !aiFirst))) {
                AIMove();
        }
    }

    public void newGame() {
        resetBoard();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void resetBoard() {
        for (int i = 0; (i < board.length); i++) {
            board[i] = 0;
        }
        DrawView.txt = getResources().getString(R.string.info_box_blue);
        turnCount = 0;
        gameFinished = false;
    }

    public void AIMove() {
            if (!gameFinished) {
                StrategyFormat playStyle = gameFactory.getPlayMethod(HomeScreen.gameType);
                int aiChoice = playStyle.playMethod();
                checkPoint(aiChoice);
            }
    }

}
