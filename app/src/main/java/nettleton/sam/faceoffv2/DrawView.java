package nettleton.sam.faceoffv2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class DrawView extends View {
    public static double[][] cubeCoords = {{.5, .5}, {1.5, .5}, {.5, 1.5}, {1.5, 1.5}, {1, 1},
            {2, 1}, {1, 2}, {2, 2}, {1, 2.5}, {2, 2.5}, {1, 3.5}, {2, 3.5}, {1.5, 3}, {2.5, 3},
            {1.5, 4}, {2.5, 4}};
    public static int colorPlayer1 = Color.BLUE;
    public static int colorPlayer2 = Color.RED;
    public static int colorWin = Color.GREEN;
    public static int previousPoint = -1;
    public static int secondaryColorP1 = Color.CYAN;
    public static int secondaryColorP2 = Color.MAGENTA;
    public static double xScaleFactor = 3;
    public static double yScaleFactor = 5;
    Paint paint = new Paint();
    int[] xyScreen = getScreenDimension();
    float width = xyScreen[0];
    float height = xyScreen[1];
    //float viewWidth = View.getWidth();
    //float viewHeight = View.getHeight();
    double scaleX = width / xScaleFactor;
    double scaleY = height / yScaleFactor;
    public static String txt = "Blue's Turn";
    Rect rectangle = new Rect();

    float[] coord0 = {(float)(cubeCoords[0][0]*scaleX), (float)(cubeCoords[0][1]*scaleY)};
    float[] coord1 = {(float)(cubeCoords[1][0]*scaleX), (float)(cubeCoords[1][1]*scaleY)};
    float[] coord2 = {(float)(cubeCoords[2][0]*scaleX), (float)(cubeCoords[2][1]*scaleY)};
    float[] coord3 = {(float)(cubeCoords[3][0]*scaleX), (float)(cubeCoords[3][1]*scaleY)};
    float[] coord4 = {(float)(cubeCoords[4][0]*scaleX), (float)(cubeCoords[4][1]*scaleY)};
    float[] coord5 = {(float)(cubeCoords[5][0]*scaleX), (float)(cubeCoords[5][1]*scaleY)};
    float[] coord6 = {(float)(cubeCoords[6][0]*scaleX), (float)(cubeCoords[6][1]*scaleY)};
    float[] coord7 = {(float)(cubeCoords[7][0]*scaleX), (float)(cubeCoords[7][1]*scaleY)};
    float[] coord8 = {(float)(cubeCoords[8][0]*scaleX), (float)(cubeCoords[8][1]*scaleY)};
    float[] coord9 = {(float)(cubeCoords[9][0]*scaleX), (float)(cubeCoords[9][1]*scaleY)};
    float[] coord10 = {(float)(cubeCoords[10][0]*scaleX), (float)(cubeCoords[10][1]*scaleY)};
    float[] coord11 = {(float)(cubeCoords[11][0]*scaleX), (float)(cubeCoords[11][1]*scaleY)};
    float[] coord12 = {(float)(cubeCoords[12][0]*scaleX), (float)(cubeCoords[12][1]*scaleY)};
    float[] coord13 = {(float)(cubeCoords[13][0]*scaleX), (float)(cubeCoords[12][1]*scaleY)};
    float[] coord14 = {(float)(cubeCoords[14][0]*scaleX), (float)(cubeCoords[14][1]*scaleY)};
    float[] coord15 = {(float)(cubeCoords[15][0]*scaleX), (float)(cubeCoords[15][1]*scaleY)};

    public DrawView(Context context) {
        super(context);
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //fill background with black
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        canvas.drawRect(0, 0, width, height, paint);
        //lines for cube #1
        paint.setColor(Color.GRAY);
        canvas.drawLine(coord0[0], coord0[1], coord1[0], coord1[1], paint);
        canvas.drawLine(coord0[0], coord0[1], coord2[0], coord2[1], paint);
        canvas.drawLine(coord0[0], coord0[1], coord4[0], coord4[1], paint);
        canvas.drawLine(coord3[0], coord3[1], coord1[0], coord1[1], paint);
        canvas.drawLine(coord3[0], coord3[1], coord2[0], coord2[1], paint);
        canvas.drawLine(coord3[0], coord3[1], coord7[0], coord7[1], paint);
        canvas.drawLine(coord5[0], coord5[1], coord1[0], coord1[1], paint);
        canvas.drawLine(coord5[0], coord5[1], coord4[0], coord4[1], paint);
        canvas.drawLine(coord5[0], coord5[1], coord7[0], coord7[1], paint);
        canvas.drawLine(coord6[0], coord6[1], coord2[0], coord2[1], paint);
        canvas.drawLine(coord6[0], coord6[1], coord4[0], coord4[1], paint);
        canvas.drawLine(coord6[0], coord6[1], coord7[0], coord7[1], paint);
        //lines for cube #2
        canvas.drawLine(coord8[0], coord8[1], coord9[0], coord9[1], paint);
        canvas.drawLine(coord8[0], coord8[1], coord10[0], coord10[1], paint);
        canvas.drawLine(coord8[0], coord8[1], coord12[0], coord12[1], paint);
        canvas.drawLine(coord11[0], coord11[1], coord9[0], coord9[1], paint);
        canvas.drawLine(coord11[0], coord11[1], coord10[0], coord10[1], paint);
        canvas.drawLine(coord11[0], coord11[1], coord15[0], coord15[1], paint);
        canvas.drawLine(coord13[0], coord13[1], coord9[0], coord9[1], paint);
        canvas.drawLine(coord13[0], coord13[1], coord12[0], coord12[1], paint);
        canvas.drawLine(coord13[0], coord13[1], coord15[0], coord15[1], paint);
        canvas.drawLine(coord14[0], coord14[1], coord10[0], coord10[1], paint);
        canvas.drawLine(coord14[0], coord14[1], coord12[0], coord12[1], paint);
        canvas.drawLine(coord14[0], coord14[1], coord15[0], coord15[1], paint);
        //lines for connecting segments
        canvas.drawLine(coord0[0], coord0[1], coord8[0], coord8[1], paint);
        canvas.drawLine(coord1[0], coord1[1], coord9[0], coord9[1], paint);
        canvas.drawLine(coord2[0], coord2[1], coord10[0], coord10[1], paint);
        canvas.drawLine(coord3[0], coord3[1], coord11[0], coord11[1], paint);
        canvas.drawLine(coord4[0], coord4[1], coord12[0], coord12[1], paint);
        canvas.drawLine(coord5[0], coord5[1], coord13[0], coord13[1], paint);
        canvas.drawLine(coord6[0], coord6[1], coord14[0], coord14[1], paint);
        canvas.drawLine(coord7[0], coord7[1], coord15[0], coord15[1], paint);
        //draw points
        if (!HomeScreen.gameType.equals("TUTORIALGAME")) {
            List<Integer> pointColors = getBoard();
            paint.setColor(pointColors.get(0));
            canvas.drawCircle(coord0[0], coord0[1], 20, paint);
            paint.setColor(pointColors.get(1));
            canvas.drawCircle(coord1[0], coord1[1], 20, paint);
            paint.setColor(pointColors.get(2));
            canvas.drawCircle(coord2[0], coord2[1], 20, paint);
            paint.setColor(pointColors.get(3));
            canvas.drawCircle(coord3[0], coord3[1], 20, paint);
            paint.setColor(pointColors.get(4));
            canvas.drawCircle(coord4[0], coord4[1], 20, paint);
            paint.setColor(pointColors.get(5));
            canvas.drawCircle(coord5[0], coord5[1], 20, paint);
            paint.setColor(pointColors.get(6));
            canvas.drawCircle(coord6[0], coord6[1], 20, paint);
            paint.setColor(pointColors.get(7));
            canvas.drawCircle(coord7[0], coord7[1], 20, paint);
            paint.setColor(pointColors.get(8));
            canvas.drawCircle(coord8[0], coord8[1], 20, paint);
            paint.setColor(pointColors.get(9));
            canvas.drawCircle(coord9[0], coord9[1], 20, paint);
            paint.setColor(pointColors.get(10));
            canvas.drawCircle(coord10[0], coord10[1], 20, paint);
            paint.setColor(pointColors.get(11));
            canvas.drawCircle(coord11[0], coord11[1], 20, paint);
            paint.setColor(pointColors.get(12));
            canvas.drawCircle(coord12[0], coord12[1], 20, paint);
            paint.setColor(pointColors.get(13));
            canvas.drawCircle(coord13[0], coord13[1], 20, paint);
            paint.setColor(pointColors.get(14));
            canvas.drawCircle(coord14[0], coord14[1], 20, paint);
            paint.setColor(pointColors.get(15));
            canvas.drawCircle(coord15[0], coord15[1], 20, paint);
        } else {
            List<Integer> pointColors = getTutorialBoard();
            paint.setColor(pointColors.get(0));
            canvas.drawCircle(coord0[0], coord0[1], 20, paint);
            paint.setColor(pointColors.get(1));
            canvas.drawCircle(coord1[0], coord1[1], 20, paint);
            paint.setColor(pointColors.get(2));
            canvas.drawCircle(coord2[0], coord2[1], 20, paint);
            paint.setColor(pointColors.get(3));
            canvas.drawCircle(coord3[0], coord3[1], 20, paint);
            paint.setColor(pointColors.get(4));
            canvas.drawCircle(coord4[0], coord4[1], 20, paint);
            paint.setColor(pointColors.get(5));
            canvas.drawCircle(coord5[0], coord5[1], 20, paint);
            paint.setColor(pointColors.get(6));
            canvas.drawCircle(coord6[0], coord6[1], 20, paint);
            paint.setColor(pointColors.get(7));
            canvas.drawCircle(coord7[0], coord7[1], 20, paint);
            paint.setColor(pointColors.get(8));
            canvas.drawCircle(coord8[0], coord8[1], 20, paint);
            paint.setColor(pointColors.get(9));
            canvas.drawCircle(coord9[0], coord9[1], 20, paint);
            paint.setColor(pointColors.get(10));
            canvas.drawCircle(coord10[0], coord10[1], 20, paint);
            paint.setColor(pointColors.get(11));
            canvas.drawCircle(coord11[0], coord11[1], 20, paint);
            paint.setColor(pointColors.get(12));
            canvas.drawCircle(coord12[0], coord12[1], 20, paint);
            paint.setColor(pointColors.get(13));
            canvas.drawCircle(coord13[0], coord13[1], 20, paint);
            paint.setColor(pointColors.get(14));
            canvas.drawCircle(coord14[0], coord14[1], 20, paint);
            paint.setColor(pointColors.get(15));
            canvas.drawCircle(coord15[0], coord15[1], 20, paint);
        }


        // Initialize a typeface object to draw text on canvas
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
        // Set the paint font
        paint.setTypeface(typeface);
        paint.setTextAlign(Paint.Align.CENTER);
        if (!HomeScreen.gameType.equals("TUTORIALGAME")) {
            paint.setTextSize(60);
            paint.setColor(Color.GRAY);
            paint.getTextBounds(
                    txt, // text
                    0, // start
                    txt.length(), // end
                    rectangle // bounds
            );
            canvas.drawText(
                    txt, // Text to draw
                    canvas.getWidth()/2, // x
                    canvas.getHeight() - Math.abs(rectangle.height())/2, // y
                    paint // Paint
            );
        } else {
            if (Tutorial.stepCount < 2 || Tutorial.stepCount == 8) {
                paint.setColor(0x66000000);
                canvas.drawRect(0, 0, width, height, paint);
            }

            paint.setColor(Color.WHITE);
            canvas.drawRect(0, (float)(4.2 * scaleY), // start
                    canvas.getWidth(), canvas.getHeight(), // end
                    paint // Paint
            );
            paint.setColor(Color.BLACK);
            paint.setTextSize((float)(.15 * scaleY));
            paint.setTextAlign(Paint.Align.CENTER);
            String[] txtParts = txt.split("-");
            canvas.drawText(txtParts[0], canvas.getWidth()/2, (float)(4.35 * scaleY), paint);
            canvas.drawText(txtParts[1], canvas.getWidth()/2, (float)(4.55 * scaleY), paint);
            canvas.drawText(txtParts[2], canvas.getWidth()/2, (float)(4.75 * scaleY), paint);
            paint.setTextAlign(Paint.Align.CENTER);
        }
    }

    private int[] getScreenDimension(){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        int[] screenInformation = new int[2];
        screenInformation[0] = width;
        screenInformation[1] = height;

        return screenInformation;
    }

    public List<Integer> getBoard(){
        List<Integer> boardList = new ArrayList<>();
        for (int i = 0; i < Game.board.length; i++) {
            if (Game.board[i] == 0) {
                boardList.add(Color.WHITE);
            }
            else if (Game.board[i] == 1) {
                if (i == previousPoint) {
                    boardList.add(secondaryColorP1);
                } else {
                    boardList.add(colorPlayer1);
                }
            }
            else if (Game.board[i] == 2) {
                if (i == previousPoint) {
                    boardList.add(secondaryColorP2);
                } else {
                    boardList.add(colorPlayer2);
                }
            }
            else {
                boardList.add(colorWin);
            }
        }
        return boardList;
    }

    public List<Integer> getTutorialBoard(){
        List<Integer> boardList = new ArrayList<>();
        for (int i = 0; i < Tutorial.board.length; i++) {
            if (Tutorial.board[i] == 0) {
                boardList.add(Color.WHITE);
            }
            else if (Tutorial.board[i] == 1) {
                if (i == previousPoint) {
                    boardList.add(secondaryColorP1);
                } else {
                    boardList.add(colorPlayer1);
                }
            }
            else if (Tutorial.board[i] == 2) {
                if (i == previousPoint) {
                    boardList.add(secondaryColorP2);
                } else {
                    boardList.add(colorPlayer2);
                }
            }
            else if (Tutorial.board[i] == 4) {
                boardList.add(Color.YELLOW);
            }
            else {
                boardList.add(colorWin);
            }
        }
        return boardList;
    }
}