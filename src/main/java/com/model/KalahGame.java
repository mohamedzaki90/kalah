package com.model;

import java.util.stream.IntStream;

public class KalahGame {

    private static final int LENGTH = 6;
    public static final int TOP_PLAYER = 0;
    public static final int DOWN_PLAYER = 1;
    private boolean playAgain;

    private int[] topPits = { 6, 6, 6, 6, 6, 6 };
    private int leftKalah = 0;

    private int[] downPits = { 6, 6, 6, 6, 6, 6 };
    private int rightKalah = 0;

    public int[] getTopPits() {
        return topPits;
    }

    public int getLeftKalah() {
        return leftKalah;
    }

    public int[] getDownPits() {
        return downPits;
    }

    public int getRightKalah() {
        return rightKalah;
    }

    public boolean isPlayAgain() {
        return playAgain;
    }

    public String getWinner() {
        if (leftKalah == rightKalah) {
            return "No one, Fair";
        }
        return leftKalah > rightKalah ? "Top Player" : "Down Player";
    }

    public void play(int index, int player) {
        if (index < 0 || index >= LENGTH)
            return;

        if (TOP_PLAYER == player) {
            if (topPits[index] == 0)
                return;
            moveLeft(index);
        } else {
            if (downPits[index] == 0)
                return;
            moveRight(index);
        }
    }

    public boolean isGameOver() {
        if (IntStream.of(topPits).sum() == 0 || IntStream.of(downPits).sum() == 0) {
            return true;
        }
        return false;
    }

    /**
     * This fills Top Pits with stones as follows: empty selected pit then add stone
     * to each pit in the left side if we reached the start and still have stones
     * add one to the leftKalah if we still have stones fill the down pits to the
     * right side if we reach the end don't put in the rightKalah and continue from
     * the end of the top pits to the left
     * 
     * @param index
     *            identifies the selected pit
     */
    public void moveLeft(int index) {
        playAgain = false;
        int stones = topPits[index];
        topPits[index] = 0;
        while (stones > 0) {
            /*
             * fill top pits to the left with stone at each pit
             */
            while (--index >= 0 && stones > 0) {
                topPits[index]++;
                stones--;
            }
            /*
             * if there's other stones add one to the leftKalah
             */
            if (stones > 0) {
                playAgain = true;
                leftKalah++;
                stones--;
            } else if (index + 1 < LENGTH && topPits[index + 1] == 1 && downPits[index + 1] > 0) {
                /*
                 * if last stone was in own empty and the opposite pit has stones
                 */
                leftKalah += downPits[index + 1] + 1;
                topPits[index + 1] = 0;
                downPits[index + 1] = 0;
            }
            /*
             * fill the down pits to the right
             */
            for (int i = 0; stones > 0 && i < LENGTH; i++, stones--) {
                playAgain = false;
                downPits[i]++;
            }

            index = LENGTH;
        }

    }

    /**
     * This fills down pits with stones as follows: empty selected pit then add
     * stone to each pit in the right side if we reached the end and still have
     * stones add one to the rightKalah if we still have stones fill the top pits to
     * the left side if we reach the start don't put in the leftKalah and continue
     * from the start of the down pits to the right
     * 
     * @param index
     *            identifies the selected pit
     */
    private void moveRight(int index) {
        playAgain = false;
        int stones = downPits[index];
        downPits[index] = 0;
        while (stones > 0) {
            /*
             * fill down pits to the right one stone at each pit
             */
            while (++index < LENGTH && stones > 0) {
                downPits[index]++;
                stones--;
            }
            /*
             * if there's other stones add one to the rightKalah
             */
            if (stones > 0) {
                playAgain = true;
                rightKalah++;
                stones--;
            } else if (index > 0 && downPits[index - 1] == 1 && topPits[index - 1] > 0) {
                /*
                 * if last stone was in own empty and the opposite pit has stones
                 */
                rightKalah += topPits[index - 1] + 1;
                downPits[index - 1] = 0;
                topPits[index - 1] = 0;
            }
            /*
             * fill the top pits to the left
             */
            for (int i = LENGTH - 1; stones > 0 && i >= 0; i--, stones--) {
                playAgain = false;
                topPits[i]++;
            }
            index = -1;
        }

    }
}
