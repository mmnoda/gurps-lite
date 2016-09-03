package me.mmnoda.rpg.domain.model.dice;

import java.math.BigInteger;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 *
 */
public class NumberOfFaces {

    public static final NumberOfFaces _6 = valueOf(BigInteger.valueOf(6));
    public static final NumberOfFaces _100 = valueOf(BigInteger.valueOf(100));

    private final BigInteger faces;

    private NumberOfFaces(BigInteger faces) {
        this.faces = faces;
    }

    public static NumberOfFaces valueOf(BigInteger faces) {
        return new NumberOfFaces(faces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof NumberOfFaces) {
            final NumberOfFaces other = (NumberOfFaces) obj;
            return Objects.equals(this.faces, other.faces);
        }

        return false;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("faces", faces)
                .toString();
    }

    public BigInteger toBigInteger() {
        return faces;
    }

    public int intValue() {
        return faces.intValue();
    }
}
