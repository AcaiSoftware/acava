package gg.acai.acava.codec;

/**
 * @author Clouke
 * @since 09.12.2022 17:01
 * © Acava - All Rights Reserved
 */
@FunctionalInterface
public interface Encoder<E, P> {

    /**
     * Encodes the payload into the expected type.
     *
     * @param payload The payload to encode.
     * @return The encoded payload.
     */
    E encode(P payload);

}
