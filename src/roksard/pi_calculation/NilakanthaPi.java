package roksard.pi_calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static roksard.pi_calculation.PiCalculationParams.divisionRounding;
import static roksard.pi_calculation.PiCalculationParams.iterations;

/**
 * π = 3 + 4/(2*3*4) - 4/(4*5*6) + 4/(6*7*8) - 4/(8*9*10) + 4/(10*11*12) - 4/(12*13*14)
 */
public class NilakanthaPi {
    public static void main(String[] args) {
        BigDecimal four = BigDecimal.valueOf(4);
        BigDecimal pi = BigDecimal.valueOf(3);
        int signum = 1;
        for (int i = 2; i < iterations; i+= 2) {
            pi = pi.add(four.divide(BigDecimal.valueOf( i*(i+1)*(i+2) ), divisionRounding, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(signum)));
            signum *= -1;
        }
        System.out.println(pi);
    }
}
