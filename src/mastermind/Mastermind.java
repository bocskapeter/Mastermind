/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
