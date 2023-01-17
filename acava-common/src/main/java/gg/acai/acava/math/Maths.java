package gg.acai.acava.math;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * @author Clouke
 * @since 06.12.2022 12:28
 * © Acava - All Rights Reserved
 */
public class Maths {

    public static DoubleMath doubleMath(Collection<? extends Number> collection) {
        return doubleMath(collection.stream());
    }

    public static DoubleMath doubleMath(Stream<? extends Number> stream) {
        return new DoubleMathStream(stream);
    }

    public static DoubleMath doubleMath(double... values) {
        return new DoubleMathArray(values);
    }

}
