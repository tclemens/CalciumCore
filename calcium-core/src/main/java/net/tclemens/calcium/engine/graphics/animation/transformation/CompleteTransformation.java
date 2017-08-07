package net.tclemens.calcium.engine.graphics.animation.transformation;

import android.support.annotation.NonNull;

import net.tclemens.calcium.math.matrix.Matrix3D;

/**
 * This class represents an complete transformation
 *
 * @author Tim Clemens
 */
final class CompleteTransformation extends Transformation {

    /** The matrix of the transformation */
    private final Matrix3D matrix;

    /**
     * @param matrix The matrix of the transformation
     */
    CompleteTransformation(Matrix3D matrix) {

        this.matrix = matrix;
    }

    @Override
    public Matrix3D getMatrix() {

        return matrix;
    }

    @Override
    public boolean isDynamic() {

        return false;
    }

    @NonNull
    @Override
    public Transformation update(long time) {

        return this;
    }

    @NonNull
    @Override
    public Transformation finish() {

        return this;
    }
}
