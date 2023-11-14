package net.holybee.rfcalculator;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class LosViewModel extends ViewModel {
    private final Units units = new Units();

    public int heightUnit = Units.METER;
    public int distanceUnit = Units.KILOMETER;
    private BigDecimal heightMeter = new BigDecimal("0");
    private BigDecimal distanceKilo = new BigDecimal("0");
    private BigDecimal radioDistanceKilo = new BigDecimal("0");
    private final BigDecimal losMultiplier = new BigDecimal("3.57");
    private final BigDecimal radioMultiplier = new BigDecimal("4.12");


    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
        return x.add(BigDecimal.valueOf(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    public BigDecimal calculateLos (BigDecimal height) {
        heightMeter = units.convertLengthToMeter(height, heightUnit);
        distanceKilo = sqrt(heightMeter).multiply(losMultiplier);
            return units.convertDistanceToUser(distanceKilo, distanceUnit);
    }

    public BigDecimal calculateRadio (BigDecimal height) {
        heightMeter = units.convertLengthToMeter(height, heightUnit);
        radioDistanceKilo = sqrt(heightMeter).multiply(radioMultiplier);
            return units.convertDistanceToUser(radioDistanceKilo, distanceUnit);
    }

}
