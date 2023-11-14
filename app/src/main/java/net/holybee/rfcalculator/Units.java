package net.holybee.rfcalculator;

import android.util.Log;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Units {
    MathContext mContext = new MathContext(12);
    public static final int HERTZ = 0;
    public static final int KILOHERTZ = 1;
    public static final int MEGAHERTZ = 2;
    public static final int GIGAHERTZ = 3;
    public static final int MILLIMETER = 4;
    public static final int CENTIMETER = 5;
    public static final int METER = 6;
    public static final int KILOMETER = 7;
    public static final int MILE = 8;
    public static final int NAUTICALMILE =9;
    public static final int FEET = 10;


    public BigDecimal convertFrequencyToHz (BigDecimal frequency, int frequencyUnit) {
        BigDecimal frequencyHz = new BigDecimal("0");
        switch (frequencyUnit) {
            case HERTZ: {
                frequencyHz = frequency;
                break;
            }
            case KILOHERTZ: {
                frequencyHz = frequency
                        .multiply(new BigDecimal("1000"),mContext);
                break;
            }
            case MEGAHERTZ: {
                frequencyHz = frequency
                        .multiply(new BigDecimal("1000000"),mContext);
                break;
            }
            case GIGAHERTZ: {

                frequencyHz = frequency
                        .multiply(new BigDecimal("1000000000"),mContext);

                break;
            }
        }
        return frequencyHz;
    }

    public BigDecimal convertLengthToMeter(BigDecimal length, int wavelengthUnit) {
        BigDecimal lengthMeters = new BigDecimal("0");
        switch (wavelengthUnit) {
            case MILLIMETER: {
                lengthMeters = length
                        .divide(new BigDecimal("1000"),mContext);
                break;
            }
            case CENTIMETER: {
                lengthMeters = length
                        .divide(new BigDecimal("100"),mContext);
                break;
            }
            case METER: {
                lengthMeters = length;
                break;
            }
            case KILOMETER: {
                lengthMeters = length
                        .multiply(new BigDecimal("1000"));
                break;
            }
            case FEET: {
                lengthMeters = length
                        .multiply(new BigDecimal("0.304799990"), mContext)
                        .setScale(2, RoundingMode.HALF_UP);
                break;
            }
        }
        return lengthMeters;
    }

    public BigDecimal convertFrequencyToUser (BigDecimal frequencyHz, int frequencyUnit) {
        switch (frequencyUnit) {
            case KILOHERTZ: {
                return frequencyHz
                        .divide(new BigDecimal("1000"),mContext)
                        .setScale(2, RoundingMode.HALF_UP);

            }
            case MEGAHERTZ: {
                return frequencyHz
                        .divide(new BigDecimal("1000000"),mContext)
                        .setScale(3,RoundingMode.HALF_UP);

            }
            case GIGAHERTZ: {
                return frequencyHz
                        .divide(new BigDecimal("1000000000"),mContext)
                        .setScale(3,RoundingMode.HALF_UP);

            }
            default: {
                return frequencyHz
                        .setScale(1, RoundingMode.HALF_UP);

            }
        }
    }

    public BigDecimal convertLengthToUser(BigDecimal lengthMeters, int lengthUnit) {
        Log.e("UNITS Length to User:", "Lengthmeter "+lengthMeters.toPlainString() );
        Log.e("UNITS", "Unit "+lengthUnit);
        switch (lengthUnit) {
            case MILLIMETER: {
                return lengthMeters
                        .multiply(new BigDecimal("1000"),mContext)
                        .setScale(1,RoundingMode.HALF_UP);

            }
            case CENTIMETER: {
                return lengthMeters
                        .multiply(new BigDecimal("100"),mContext)
                        .setScale(1,RoundingMode.HALF_UP);

            }
            case KILOMETER: {
                return lengthMeters
                        .divide(new BigDecimal("1000"),mContext)
                        .setScale(3,RoundingMode.HALF_UP);

            }
            case FEET: {
                return lengthMeters
                        .divide(new BigDecimal("0.304799990"), mContext)
                        .setScale(2, RoundingMode.HALF_UP);

            }
            default: {
                return lengthMeters
                        .setScale(2,RoundingMode.HALF_UP);

            }
        }
    }
    public BigDecimal convertDistanceToUser (BigDecimal distance, int distanceUnit) {
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

    public BigDecimal convertDistanceToKm (BigDecimal distance, int distanceUnit) {
        BigDecimal distanceKilo;
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
                break;
            }
            case NAUTICALMILE: {
                distanceKilo = distance.multiply(new BigDecimal("1.852"),mContext);
                break;
            }
            default: {
                distanceKilo = new BigDecimal("0");
            }
        }
        return distanceKilo;
    }

}
