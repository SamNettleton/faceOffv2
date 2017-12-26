package nettleton.sam.faceoffv2;

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

public class Tutorial extends AppCompatActivity {
    DrawView drawView;
    public static int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static Integer stepCount = -1;
    public static int[] boardMoves = {16, 16, 16, 16, 16, 1, 5, 4, 16, 9, 13, 16};
    public static boolean gameFinished = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView = new DrawView(this);
        setContentView(drawView);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        increaseStep();
        SharedPreferences settingSave = this.getSharedPreferences("settingSave", MODE_PRIVATE);
        final SharedPreferences.Editor edit = settingSave.edit();

        drawView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    double xCoord = event.getX();
                    double yCoord = event.getY();
                    if (stepCount > 10) {
                        edit.putBoolean("tutorialCompleted", true);
                        edit.apply();
                        DrawView.txt = getResources().getString(R.string.info_box_blue);
                        HomeScreen.tutorialCompleted = true;
                        finish();
                    }
                    //all the steps that have a message, but don't require a click in a specific spot
                    else if (!(stepCount == 0 || stepCount == 1 || stepCount == 2 || stepCount == 3 ||
                            stepCount == 4 || stepCount == 8 || stepCount == 11)) {
                        int selectedPoint = closestDot(xCoord, yCoord);
                        if (selectedPoint == boardMoves[stepCount]) {
                            increaseStep();
                        } else {
                            DrawView.txt = getResources().getString(R.string.follow_directions);
                        }
                    } else {
                        //if (tappedBox(xCoord, yCoord)) {
                        increaseStep();
                        //}
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
        AlertDialog.Builder ab = new AlertDialog.Builder(Tutorial.this);
        ab.setTitle("Confirm Exit");
        ab.setMessage("Are you sure you want to end the tutorial?");
        ab.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                resetBoard();
                Tutorial.this.finish();
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

    public void increaseStep() {
        String[] tutorialMessages = {getResources().getString(R.string.tutorial0),
                getResources().getString(R.string.tutorial1), getResources().getString(R.string.tutorial2),
                getResources().getString(R.string.tutorial3), getResources().getString(R.string.tutorial4),
                getResources().getString(R.string.tutorial5), getResources().getString(R.string.tutorial6),
                getResources().getString(R.string.tutorial7), getResources().getString(R.string.tutorial8),
                getResources().getString(R.string.tutorial9), getResources().getString(R.string.tutorial10),
                getResources().getString(R.string.tutorial11) };
        int[][] tutorialBoards = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {3, 3, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0},
                {3, 0, 3, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 4, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 4, 1, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                {2, 1, 0, 0, 1, 1, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0},
                {2, 1, 0, 0, 1, 1, 0, 0, 0, 4, 2, 0, 0, 0, 2, 0},
                {2, 1, 0, 2, 1, 1, 0, 0, 0, 1, 2, 0, 0, 4, 2, 0},
                {2, 3, 0, 2, 1, 3, 0, 0, 0, 3, 2, 0, 0, 3, 2, 0}};
        stepCount += 1;
        board = tutorialBoards[stepCount];
        DrawView.txt = tutorialMessages[stepCount];
    }

    public void resetBoard() {
        for (int i = 0; (i < board.length); i++) {
            board[i] = 0;
        }
        DrawView.txt = getResources().getString(R.string.info_box_blue);
        gameFinished = false;
    }



}
