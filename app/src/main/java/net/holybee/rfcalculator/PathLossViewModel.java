package net.holybee.rfcalculator;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import ch.obermuhlner.math.big.BigDecimalMath;

public class PathLossViewModel extends ViewModel {
    public static final int HERTZ = 0;
    public static final int KILOHERTZ = 1;
    public static final int MEGAHERTZ = 2;
    public static final int GIGAHERTZ = 3;
    public static final int METER = 0;
    public static final int KILOMETER = 1;
    public static final int MILE = 2;
    public static final int NAUTICALMILE = 3;

    public int frequencyUnit = KILOHERTZ;
    public int distanceUnit = KILOMETER;

    private BigDecimal frequencyHz = new BigDecimal("0");
    private BigDecimal distanceKilo = new BigDecimal("0");

    private final BigDecimal c = new BigDecimal("299792458");
    MathContext mContext = new MathContext(12);

    public BigDecimal calculateLoss(BigDecimal frequency, BigDecimal distance){
        convertFrequencyToHz(frequency);
        convertDistanceToKm(distance);

    BigDecimal fspl = new BigDecimal("20")
            .multiply(BigDecimalMath.log10(distanceKilo,mContext),mContext)
            .add(
                    new BigDecimal("20")
                            .multiply(BigDecimalMath.log10(frequencyHz,mContext),mContext)
            ).add(
                    new BigDecimal("20")
                            .multiply(BigDecimalMath.log10(
                                    new BigDecimal("4")
                                            .multiply(BigDecimalMath.pi(mContext),mContext)
                                            .divide(c,RoundingMode.HALF_UP),mContext
                            ),mContext)
            );

        return convertDistanceToUser (fspl)
                .setScale(2,RoundingMode.HALF_UP);
    }

    private void convertFrequencyToHz (BigDecimal frequency) {
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

    private BigDecimal convertFrequencyToUser () {
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

    private BigDecimal log20 (BigDecimal x) {
        return BigDecimalMath.log10(x,mContext).divide(BigDecimalMath.log10(new BigDecimal("20"),mContext), BigDecimal.ROUND_HALF_UP);

    }

    BigDecimal temp = new BigDecimal("4")
            .multiply(BigDecimalMath.pi(mContext),mContext)
            .divide(c,RoundingMode.HALF_UP);

}
