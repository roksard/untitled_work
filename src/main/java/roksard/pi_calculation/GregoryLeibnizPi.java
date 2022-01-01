package roksard.pi_calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static roksard.pi_calculation.PiCalculationParams.divisionRounding;
import static roksard.pi_calculation.PiCalculationParams.iterations;

/**
 * π = (4/1) - (4/3) + (4/5) - (4/7) + (4/9) - (4/11) + (4/13) - (4/15) ...
 */
public class GregoryLeibnizPi implements Runnable {
    BigDecimal pi = null;
    long iteration = 0;

    @Override
    public void run() {
        BigDecimal four = BigDecimal.valueOf(4);
        pi = four;
        pi.setScale(divisionRounding);
        for (iteration = 3; iteration < iterations; iteration+= 4) {
            pi = pi.subtract(four.divide(BigDecimal.valueOf(iteration), divisionRounding, RoundingMode.HALF_UP))
                    .add(four.divide(BigDecimal.valueOf(iteration+2), divisionRounding, RoundingMode.HALF_UP));
        }
    }

    synchronized public BigDecimal getPi() {
        return pi;
    }
    synchronized public double getProgress() {
        return ((double)iteration) / iterations * 100;
    }
}
