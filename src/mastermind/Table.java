/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mastermind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Peter Bocska <peter.bocska@gmail.com>
 */
public class Table {

    /**
     *
     */
    private final int colors;

    /**
     *
     */
    private final int places;

    /**
     *
     */
    public static final int ANSWERS = 2;

    /**
     *
     */
    private int[] solution;

    /**
     *
     */
    private boolean solved;

    /**
     *
     * @return colors
     */
    public final int getColors() {
        return colors;
    }

    /**
     *
     * @return places
     */
    public final int getPlaces() {
        return places;
    }

    /**
     *
     * @return solved
     */
    public final boolean isSolved() {
        return solved;
    }

    /**
     *
     * @param newColors new colors
     * @param newPlaces new places
     */
    public Table(final int newColors,
            final int newPlaces) {
        this.colors = newColors;
        this.places = newPlaces;
    }

    /**
     *
     */
    public final void newGame() {
        solution = new int[places];
        this.solved = false;
        ArrayList<Integer> pins = new ArrayList<>();
        for (int i = 0; i < colors; i++) {
            pins.add(i);
        }
        for (int i = 0; i < places; i++) {
            solution[i] = pins.remove((new Random()).nextInt(pins.size()));
        }
    }

    /**
     *
     * @param guess guess
     * @return answer
     */
    public final int[] guess(final int[] guess) {
        if (guess.length != places) {
            throw new IllegalArgumentException("Wrong guess lenght!");
        }
        ArrayList<Integer> check = new ArrayList<>();
        for (int g : guess) {
            if (check.contains(g)) {
                throw new IllegalArgumentException("Repeated colors!");
            } else {
                check.add(g);
            }
        }
        int[] answer = new int[ANSWERS];
        for (int i = 0; i < places; i++) {
            for (int j = 0; j < places; j++) {
                if (guess[i] == solution[j]) {
                    if (i == j) {
                        answer[0]++;
                    } else {
                        answer[1]++;
                    }
                }
            }
        }
        if (answer[0] == places) {
            this.solved = true;
        }
        return answer;
    }

    @Override
    public final String toString() {
        return "Table{" + "colors=" + colors + ", places=" + places
                + ", solution=" + Arrays.toString(solution) + '}';
    }

}
