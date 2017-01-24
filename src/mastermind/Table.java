/*
 * Copyright 2017 Peter Bocska <peter.bocska@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
