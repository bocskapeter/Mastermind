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

/**
 *
 * @author Peter Bocska <peter.bocska@gmail.com>
 */
public final class Mastermind {

    /**
     *
     */
    private Mastermind() {
    }

    /**
     *
     */
    private static final byte COLORS = 8;
    /**
     *
     */
    private static final byte PLACES = 4;
    /**
     *
     */
    private static final double ALL_STEPS = 10000;

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        Table table = new Table(COLORS, PLACES);
        Solver solver = new Solver(table);
        double sum = 0;
        for (int i = 0; i < ALL_STEPS; i++) {
            table.newGame();
            solver.solve();
            sum = sum + solver.getSteps();
        }
        System.out.println("Average:" + sum / ALL_STEPS);
    }

}
