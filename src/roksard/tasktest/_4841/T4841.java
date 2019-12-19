package roksard.tasktest._4841;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class T4841 {
    static void testContractSecurities(
            boolean isIncreaseWinnerInitialPrice,
            BigDecimal contractPrice,
            BigDecimal lotSecuritySum,
            BigDecimal nmcLot,
            Integer lotPurchaseSmp,
            BigDecimal contractSecurityPercent
    ) {

        BigDecimal securityPercent;

        if (isIncreaseWinnerInitialPrice) {
            securityPercent = BigDecimal.ZERO.compareTo(contractPrice) != 0
                    ? lotSecuritySum
                        .divide(contractPrice, 2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                    : BigDecimal.ZERO;
        } else {
            if (lotPurchaseSmp == 3) {
                securityPercent = BigDecimal.ZERO.compareTo(contractPrice) != 0
                        ? lotSecuritySum
                            .divide(contractPrice, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                        : BigDecimal.ZERO;

            } else {
                securityPercent = BigDecimal.ZERO.compareTo(nmcLot) != 0
                        ? lotSecuritySum
                            .divide(nmcLot, 2, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                        : BigDecimal.ZERO;
            }
        }

        System.out.print("%=" + securityPercent + " / ");
        if (securityPercent.compareTo(contractSecurityPercent) < 0) {
            System.out.println("validation failed");
        } else {
            System.out.println("validation ok");
        }

    }

    public static void main1(String[] args) {

        boolean isIncreaseWinnerInitialPrice;
        BigDecimal contractPrice;
        BigDecimal lotSecuritySum;
        BigDecimal nmcLot;
        Integer lotPurchaseSmp;
        BigDecimal contractSecurityPercent;

        isIncreaseWinnerInitialPrice = true;
        contractPrice = BigDecimal.valueOf(0);
        lotSecuritySum = BigDecimal.valueOf(0);
        nmcLot = BigDecimal.valueOf(0);
        lotPurchaseSmp = 3;
        contractSecurityPercent = BigDecimal.valueOf(10);
        testContractSecurities(isIncreaseWinnerInitialPrice, contractPrice, lotSecuritySum, nmcLot, lotPurchaseSmp, contractSecurityPercent);

        isIncreaseWinnerInitialPrice = true;
        contractPrice = BigDecimal.valueOf(10);
        lotSecuritySum = BigDecimal.valueOf(0);
        nmcLot = BigDecimal.valueOf(0);
        lotPurchaseSmp = 3;
        contractSecurityPercent = BigDecimal.valueOf(10);
        testContractSecurities(isIncreaseWinnerInitialPrice, contractPrice, lotSecuritySum, nmcLot, lotPurchaseSmp, contractSecurityPercent);

        isIncreaseWinnerInitialPrice = true;
        contractPrice = BigDecimal.valueOf(100);
        lotSecuritySum = BigDecimal.valueOf(9);
        nmcLot = BigDecimal.valueOf(0);
        lotPurchaseSmp = 3;
        contractSecurityPercent = BigDecimal.valueOf(10);
        testContractSecurities(isIncreaseWinnerInitialPrice, contractPrice, lotSecuritySum, nmcLot, lotPurchaseSmp, contractSecurityPercent);

        isIncreaseWinnerInitialPrice = false;
        contractPrice = BigDecimal.valueOf(100);
        lotSecuritySum = BigDecimal.valueOf(19);
        nmcLot = BigDecimal.valueOf(0);
        lotPurchaseSmp = 3;
        contractSecurityPercent = BigDecimal.valueOf(10);
        testContractSecurities(isIncreaseWinnerInitialPrice, contractPrice, lotSecuritySum, nmcLot, lotPurchaseSmp, contractSecurityPercent);

        isIncreaseWinnerInitialPrice = false;
        contractPrice = BigDecimal.valueOf(0);
        lotSecuritySum = BigDecimal.valueOf(11);
        nmcLot = BigDecimal.valueOf(100);
        lotPurchaseSmp = 2;
        contractSecurityPercent = BigDecimal.valueOf(10);
        testContractSecurities(isIncreaseWinnerInitialPrice, contractPrice, lotSecuritySum, nmcLot, lotPurchaseSmp, contractSecurityPercent);
    }


    static BigDecimal calcPercentSecurity(BigDecimal a, BigDecimal b) {
        return a
                .multiply(BigDecimal.valueOf(100))
                .divide(b, 2, RoundingMode.HALF_UP);

    }

    public static void main(String[] args) {
        System.out.println(calcPercentSecurity(BigDecimal.valueOf(1200), BigDecimal.valueOf(1958000)));
    }
}
