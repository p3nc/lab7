package client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import compute.Task;

public class Pi implements Task<BigDecimal> {
    private static final long serialVersionUID = 227L;
    private static final BigDecimal FOUR = BigDecimal.valueOf(4);
    private static final RoundingMode roundingMode = RoundingMode.HALF_EVEN;
    private final int digits;

    public Pi(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computePi(digits);
    }

    public static BigDecimal computePi(int digits) {
        int scale = digits + 5;
        BigDecimal arctan1_5 = arctan(5, scale);
        BigDecimal arctan1_239 = arctan(239, scale);
        BigDecimal pi = FOUR.multiply(arctan1_5).subtract(arctan1_239);
        return pi.setScale(digits, RoundingMode.HALF_EVEN);
    }

    public static BigDecimal arctan(int X, int scale) {
        BigDecimal result, term, x = BigDecimal.valueOf(X);
        BigDecimal one = BigDecimal.ONE;
        BigDecimal three = BigDecimal.valueOf(3);
        BigDecimal five = BigDecimal.valueOf(5);
        BigDecimal seven = BigDecimal.valueOf(7);

        result = x.divide(one, scale, roundingMode);
        term = x.pow(3).divide(three, scale, roundingMode);
        result = result.subtract(term);
        term = x.pow(5).divide(five, scale, roundingMode);
        result = result.add(term);
        term = x.pow(7).divide(seven, scale, roundingMode);
        result = result.subtract(term);

        return result;
    }
}