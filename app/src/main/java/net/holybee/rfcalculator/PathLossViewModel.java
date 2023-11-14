package net.holybee.rfcalculator;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import ch.obermuhlner.math.big.BigDecimalMath;

public class PathLossViewModel extends ViewModel {
    Units units = new Units();

    public int frequencyUnit = Units.MEGAHERTZ;
    public int distanceUnit = Units.KILOMETER;

    MathContext mContext = new MathContext(12);
    private final BigDecimal c = new BigDecimal("299792458");
    private final BigDecimal pi = BigDecimalMath.pi(mContext);


    public BigDecimal calculateLoss(BigDecimal frequency, BigDecimal distance){
        BigDecimal frequencyHz = units.convertFrequencyToHz(frequency, frequencyUnit);
        BigDecimal distanceKilo = units.convertDistanceToKm(distance, distanceUnit);

    Log.e("FSPL","kilo = " + distanceKilo.toPlainString());
    Log.e("FSPL", "freq = " + frequencyHz.toPlainString());

    BigDecimal log10d = BigDecimalMath.log10(distanceKilo.multiply(new BigDecimal("1000")),mContext);

    Log.e("FSPL","log10d = " + log10d.toPlainString());

    BigDecimal log10f = BigDecimalMath.log10(frequencyHz,mContext);
    BigDecimal pi4overc = new BigDecimal("4").multiply(pi).divide(c,mContext);
    BigDecimal log104pioverc = BigDecimalMath.log10(pi4overc,mContext);

    BigDecimal fspl2 = (new BigDecimal("20").multiply(log10d,mContext))
            .add(new BigDecimal("20").multiply(log10f),mContext)
            .add(new BigDecimal("20").multiply(log104pioverc),mContext);




        return fspl2
                .setScale(2,RoundingMode.HALF_UP);
    }


}
