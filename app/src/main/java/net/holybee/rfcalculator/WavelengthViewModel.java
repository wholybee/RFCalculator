package net.holybee.rfcalculator;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;

public class WavelengthViewModel extends ViewModel {

    private final Units units = new Units();

    public int frequencyUnit = Units.HERTZ;
    public int wavelengthUnit = Units.METER;
    private BigDecimal frequencyHz = new BigDecimal(0);
    private BigDecimal wavelengthMeters = new BigDecimal(0);
    private final BigDecimal c = new BigDecimal("299792458");
    MathContext mContext = new MathContext(12);

    public BigDecimal calculateFrequency(BigDecimal wavelength) {
        wavelengthMeters = units.convertLengthToMeter(wavelength, wavelengthUnit);
        frequencyHz = c.divide(wavelengthMeters,mContext);
        return units.convertFrequencyToUser(frequencyHz,frequencyUnit);
    }

    public BigDecimal calculateWavelength(BigDecimal frequency) {
        frequencyHz = units.convertFrequencyToHz(frequency, frequencyUnit);
        wavelengthMeters = c.divide(frequencyHz,mContext);
        return units.convertLengthToUser(wavelengthMeters, wavelengthUnit);
    }

}

