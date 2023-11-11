package net.holybee.rfcalculator;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class WaveLengthViewModel extends ViewModel {
    public static final int HERTZ = 0;
    public static final int KILOHERTZ = 1;
    public static final int MEGAHERTZ = 2;
    public static final int GIGAHERTZ = 3;
    public static final int MILLIMETER = 0;
    public static final int CENTIMETER = 1;
    public static final int METER = 2;
    public static final int KILOMETER = 3;

    public int frequencyUnit = HERTZ;
    public int wavelengthUnit = METER;
    public float frequencyDisplay = 0;
    public float wavelengthDisplay = 0;
    private BigDecimal frequencyHz = new BigDecimal(0);
    private BigDecimal wavelengthMeters = new BigDecimal(0);
    private final BigDecimal c = new BigDecimal("299792458");
    MathContext mContext = new MathContext(9);

    public BigDecimal calculateFrequency(BigDecimal wavelength) {
        convertWavelengthToMeter(wavelength);
        frequencyHz = c.divide(wavelengthMeters,mContext);
        return convertFrequencyToUser();
    }

    public BigDecimal calculateWavelength(BigDecimal frequency) {
        convertFrequencyToHz(frequency);
        wavelengthMeters = c.divide(frequencyHz,mContext);
        return convertWavelengthToUser();
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
                        .multiply(new BigDecimal(" 1000000000"),mContext);
                break;
            }
        }
    }

    private void convertWavelengthToMeter (BigDecimal wavelength) {
        switch (wavelengthUnit) {
            case MILLIMETER: {
                wavelengthMeters = wavelength
                        .divide(new BigDecimal("1000"),mContext);
                break;
            }
            case CENTIMETER: {
                wavelengthMeters = wavelength
                        .divide(new BigDecimal("100"),mContext);
                break;
            }
            case METER: {
                wavelengthMeters = wavelength;
                break;
            }
            case KILOMETER: {
                wavelengthMeters = wavelength
                        .multiply(new BigDecimal("1000"));
                break;
            }
        }
    }

    private BigDecimal convertFrequencyToUser () {
        switch (frequencyUnit) {
            case KILOHERTZ: {
                return frequencyHz
                        .divide(new BigDecimal("1000"),mContext)
                        .setScale(2,RoundingMode.HALF_UP);

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

    private BigDecimal convertWavelengthToUser () {
        switch (wavelengthUnit) {
            case MILLIMETER: {
                return wavelengthMeters
                        .multiply(new BigDecimal("1000"),mContext)
                        .setScale(1,RoundingMode.HALF_UP);

            }
            case CENTIMETER: {
                return wavelengthMeters
                        .multiply(new BigDecimal("100"),mContext)
                        .setScale(1,RoundingMode.HALF_UP);

            }
            case KILOMETER: {
                return wavelengthMeters
                        .divide(new BigDecimal("1000"),mContext)
                        .setScale(3,RoundingMode.HALF_UP);

            }
            default: {
                return wavelengthMeters
                        .setScale(2,RoundingMode.HALF_UP);

            }
        }
    }
}

