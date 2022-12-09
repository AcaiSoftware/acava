package gg.acai.acava.math;

import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 06.12.2022 12:28
 * © Acava - All Rights Reserved
 */
public class Maths {

    public static DoubleMath doubleMath(Stream<? extends Number> stream) {
        return new DoubleMathStream(stream);
    }

    public static DoubleMath doubleMath(double... values) {
        return new DoubleMathArray(values);
    }

}
