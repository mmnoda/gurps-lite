package me.mmnoda.rpg.model.dice.component;

import com.google.common.base.Objects;

import java.math.BigInteger;

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
        return Objects.hashCode(faces);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NumberOfFaces) {
            final NumberOfFaces other = (NumberOfFaces) obj;
            return Objects.equal(this.faces, other.faces);
        }
        return false;
    }

    @Override
    public String toString() {
        return faces.toString();
    }

    public BigInteger toBigInteger() {
        return faces;
    }

    public int intValue() {
        return faces.intValue();
    }
}
