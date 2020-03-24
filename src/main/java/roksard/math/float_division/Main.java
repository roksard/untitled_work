package roksard.math.float_division;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    static void calcBigDecimal(BigDecimal priceWithNds, BigDecimal nds) {
        System.out.println("calcBigDecimal: ");
        BigDecimal priceWithoutNds = priceWithNds.multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(100).add(nds), 5, RoundingMode.HALF_UP);
        System.out.println(priceWithoutNds);
    }

    static double calcDouble(double priceWithNds, double nds) {
        System.out.println("calcDouble: ");
        double priceWithoutNds = priceWithNds * 100 / (100 + nds);
        System.out.println(priceWithoutNds);
        return priceWithoutNds;
    }

    public static void main(String[] args) {
        double price = 27.00003;
        double nds = 20;
        calcBigDecimal(BigDecimal.valueOf(price), BigDecimal.valueOf(nds));
        double resultDouble = calcDouble(price, nds);
        System.out.println("trying to round up to 5 current double result: ");
        System.out.println(BigDecimal.valueOf(resultDouble).setScale(5, RoundingMode.HALF_UP));

        //using Double:
        //ToPrecision(spec.specificationUnitCost / (100 + ndsRate) * 100, spec.is_5DecimalPlacesWithNds ? 5 : 2)
        double specificationUnitCost = 27.00003;
        double ndsd = 20;

    }
}
