package client;

import java.math.BigDecimal;
import java.math.RoundingMode;
import compute.Task;

public class E implements Task<BigDecimal> {
    private static final long serialVersionUID = 1L;
    private final int digits;

    public E(int digits) {
        this.digits = digits;
    }

    @Override
    public BigDecimal execute() {
        return computeE(digits);
    }

    public static BigDecimal computeE(int digits) {
        int scale = digits + 5;
        BigDecimal result = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        BigDecimal n = BigDecimal.ONE;
        BigDecimal x = BigDecimal.ONE;
        BigDecimal one = BigDecimal.ONE;
        int count = 0;

        while (term.compareTo(BigDecimal.valueOf(Math.pow(10, -digits))) > 0) {
            n = n.add(one);
            x = x.multiply(BigDecimal.valueOf(count));
            term = x.divide(n, scale, RoundingMode.HALF_EVEN);
            result = result.add(term);
            count++;
        }

        return result.setScale(digits, RoundingMode.HALF_EVEN);
    }
}