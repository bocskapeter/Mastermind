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
import static mastermind.Table.ANSWERS;

/**
 *
 * @author Peter Bocska <peter.bocska@gmail.com>
 */
public class Solver {

    /**
     *
     */
    private Table table;

    /**
     *
     */
    private final ArrayList<int[]> solutionSpace;

    /**
     *
     */
    private int steps = 0;

    /**
     *
     */
    private int[] theSolution;

    /**
     *
     * @param newTable new table
     */
    public Solver(final Table newTable) {
        this.table = newTable;
        this.solutionSpace = new ArrayList<>();

        int n = this.table.getColors();
        int k = this.table.getPlaces();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
        }

        getSollutionSpace(a, n, k);

    }

    /**
     *
     * @return steps
     */
    public final int getSteps() {
        return steps;
    }

    /**
     *
     * @return solution
     */
    public final int[] getTheSolution() {
        return theSolution;
    }

    /**
     *
     */
    public final void solve() {
        int[] aTry = solutionSpace.get((new Random())
                .nextInt(solutionSpace.size()));

        int[] anAnswer = this.table.guess(aTry);
        steps = 1;
        if (this.table.isSolved()) {
            this.theSolution = aTry;
            return;
        }

        ArrayList<int[]> possibleSolutions = new ArrayList<>();
        possibleSolutions.addAll(solutionSpace);

        ArrayList<int[]> answers = new ArrayList<>();
        answers.add(anAnswer);

        ArrayList<int[]> temp = new ArrayList<>();

        while (!possibleSolutions.isEmpty()) {
            for (int[] i : possibleSolutions) {
                for (int[] an : answers) {
                    if (simulation(aTry, i, an)) {
                        temp.add(i);
                    }
                }
            }
            possibleSolutions = new ArrayList<>();
            possibleSolutions.addAll(temp);
            temp = new ArrayList<>();
            aTry = possibleSolutions.get((new Random())
                    .nextInt(possibleSolutions.size()));
            anAnswer = this.table.guess(aTry);
            steps++;
            if (this.table.isSolved()) {
                this.theSolution = aTry;
                return;
            }
            answers.add(anAnswer);
        }
    }

    /**
     *
     * @param guess guess
     * @param solution solution
     * @param answer answer
     * @return boolean
     */
    private boolean simulation(final int[] guess, final int[] solution,
            final int[] answer) {
        int[] result = new int[ANSWERS];
        for (int i = 0; i < guess.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                if (guess[i] == solution[j]) {
                    if (i == j) {
                        result[0]++;
                    } else {
                        result[1]++;
                    }
                }
            }
        }
        for (int i = 0; i < ANSWERS; i++) {
            if (result[i] != answer[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param a permutation
     * @param n colors
     * @param k places
     */
    private void getSollutionSpace(final int[] a, final int n, final int k) {
        if (k == 0) {
            int[] sollution = Arrays.copyOfRange(a, n - k, a.length);
            this.solutionSpace.add(sollution);
            return;
        }
        for (int i = 0; i < n; i++) {
            swap(a, i, n - 1);
            getSollutionSpace(a, n - 1, k - 1);
            swap(a, i, n - 1);
        }
    }

    /**
     *
     * @param a permutation
     * @param i i
     * @param j j
     */
    private void swap(final int[] a, final int i, final int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Override
    public final String toString() {
        return "Solver{" + "steps=" + steps
                + ", solution=" + Arrays.toString(theSolution) + '}';
    }
}
