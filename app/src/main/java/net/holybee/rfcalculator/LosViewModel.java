package net.holybee.rfcalculator;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class LosViewModel extends ViewModel {
    public static final int METER = 0;
    public static final int FEET = 1;
    public static final int KILOMETER = 2;
    public static final int MILE = 3;
    public static final int NAUTICALMILE =4;
    public int heightUnit = METER;
    public int distanceUnit = KILOMETER;
    private BigDecimal heightMeter = new BigDecimal("0");
    private BigDecimal distanceKilo = new BigDecimal("0");
    private BigDecimal radioDistanceKilo = new BigDecimal("0");
    private final BigDecimal losMultiplier = new BigDecimal("3.57");
    private final BigDecimal radioMultiplier = new BigDecimal("4.12");
    MathContext mContext = new MathContext(9);

    public static BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = BigDecimal.valueOf(Math.sqrt(value.doubleValue()));
        return x.add(BigDecimal.valueOf(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
    }

    public BigDecimal calculateLos (BigDecimal height) {
        convertHeightToMeters(height);
        distanceKilo = sqrt(heightMeter).multiply(losMultiplier);
            return convertDistanceToUser(distanceKilo);
    }

    public BigDecimal calculateRadio (BigDecimal height) {
        convertHeightToMeters(height);
        radioDistanceKilo = sqrt(heightMeter).multiply(radioMultiplier);
            return convertDistanceToUser(radioDistanceKilo);
    }

    public BigDecimal convertHeight () {
       return convertHeightToUser();
    }

    public void convertHeightToMeters (BigDecimal height) {
        switch (heightUnit) {
            case FEET: {
                heightMeter = height.multiply(new BigDecimal("0.304799990"),mContext);
                break;
            }
            case METER: {
                heightMeter = height;
                break;
            }
        }
    }

    private void convertDistanceToKm (BigDecimal distance) {
        switch (distanceUnit) {
            case METER: {
                distanceKilo = distance.divide(new BigDecimal("1000"),mContext);
                break;
            }
            case KILOMETER: {
                distanceKilo = distance;
                break;
            }
            case MILE: {
                distanceKilo = distance.multiply(new BigDecimal("1.609344"),mContext);
            }
            case NAUTICALMILE: {
                distanceKilo = distance.multiply(new BigDecimal("1.852"),mContext);
            }
        }
    }

    private BigDecimal convertHeightToUser () {
        if (heightUnit == FEET) {
            return heightMeter
                    .multiply(new BigDecimal("3.28084"), mContext)
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return heightMeter
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal convertDistanceToUser (BigDecimal distance) {
        switch (distanceUnit) {
            case METER: {
                return distance
                        .multiply(new BigDecimal("1000"),mContext)
                        .setScale(2, RoundingMode.HALF_UP);
            }
            case MILE: {
                return distance
                        .multiply(new BigDecimal("0.62137119"),mContext)
                        .setScale(2,RoundingMode.HALF_UP);

            }
            case NAUTICALMILE: {
                return distance
                        .multiply(new BigDecimal("0.539956803"),mContext)
                        .setScale(2,RoundingMode.HALF_UP);
            }
            default: {
                return distance
                        .setScale(2,RoundingMode.HALF_UP);
            }
        }
    }

}
