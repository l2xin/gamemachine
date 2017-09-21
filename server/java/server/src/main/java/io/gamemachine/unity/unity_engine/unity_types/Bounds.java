package io.gamemachine.unity.unity_engine.unity_types;

/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

import java.io.Serializable;
import java.util.List;


/** Encapsulates an axis aligned bounding box represented by a minimum and a maximum Vector. Additionally you can query for the
 * bounding box's center, dimensions and corner points.
 *
 * @author badlogicgames@gmail.com, Xoppa */
public class Bounds implements Serializable {
    private static final long serialVersionUID = -1286036817192127343L;

    public final Vector3 min = new Vector3();
    public final Vector3 max = new Vector3();

    private final Vector3 cnt = new Vector3();
    private final Vector3 dim = new Vector3();

    /** @param out The {@link Vector3} to receive the center of the bounding box.
     * @return The vector specified with the out argument. */
    public Vector3 getCenter(Vector3 out) {
        return out.set(cnt);
    }

    public double getCenterX() {
        return cnt.x;
    }

    public double getCenterY() {
        return cnt.y;
    }

    public double getCenterZ() {
        return cnt.z;
    }

    public Vector3 getCorner000(final Vector3 out) {
        return out.set(min.x, min.y, min.z);
    }

    public Vector3 getCorner001(final Vector3 out) {
        return out.set(min.x, min.y, max.z);
    }

    public Vector3 getCorner010(final Vector3 out) {
        return out.set(min.x, max.y, min.z);
    }

    public Vector3 getCorner011(final Vector3 out) {
        return out.set(min.x, max.y, max.z);
    }

    public Vector3 getCorner100(final Vector3 out) {
        return out.set(max.x, min.y, min.z);
    }

    public Vector3 getCorner101(final Vector3 out) {
        return out.set(max.x, min.y, max.z);
    }

    public Vector3 getCorner110(final Vector3 out) {
        return out.set(max.x, max.y, min.z);
    }

    public Vector3 getCorner111(final Vector3 out) {
        return out.set(max.x, max.y, max.z);
    }

    /** @param out The {@link Vector3} to receive the dimensions of this bounding box on all three axis.
     * @return The vector specified with the out argument */
    public Vector3 getDimensions(final Vector3 out) {
        return out.set(dim);
    }

    public double getWidth() {
        return dim.x;
    }

    public double getHeight() {
        return dim.y;
    }

    public double getDepth() {
        return dim.z;
    }

    /** @param out The {@link Vector3} to receive the minimum values.
     * @return The vector specified with the out argument */
    public Vector3 getMin(final Vector3 out) {
        return out.set(min);
    }

    /** @param out The {@link Vector3} to receive the maximum values.
     * @return The vector specified with the out argument */
    public Vector3 getMax(final Vector3 out) {
        return out.set(max);
    }

    /** Constructs a new bounding box with the minimum and maximum vector set to zeros. */
    public Bounds() {
        clr();
    }

    /** Constructs a new bounding box from the given bounding box.
     *
     * @param bounds The bounding box to copy */
    public Bounds(Bounds bounds) {
        this.set(bounds);
    }

    /** Constructs the new bounding box using the given minimum and maximum vector.
     *
     * @param minimum The minimum vector
     * @param maximum The maximum vector */
    public Bounds(Vector3 minimum, Vector3 maximum) {
        this.set(minimum, maximum);
    }

    /** Sets the given bounding box.
     *
     * @param bounds The bounds.
     * @return This bounding box for chaining. */
    public Bounds set(Bounds bounds) {
        return this.set(bounds.min, bounds.max);
    }

    /** Sets the given minimum and maximum vector.
     *
     * @param minimum The minimum vector
     * @param maximum The maximum vector
     * @return This bounding box for chaining. */
    public Bounds set(Vector3 minimum, Vector3 maximum) {
        min.set(minimum.x < maximum.x ? minimum.x : maximum.x, minimum.y < maximum.y ? minimum.y : maximum.y,
                minimum.z < maximum.z ? minimum.z : maximum.z);
        max.set(minimum.x > maximum.x ? minimum.x : maximum.x, minimum.y > maximum.y ? minimum.y : maximum.y,
                minimum.z > maximum.z ? minimum.z : maximum.z);
        cnt.set(min).add(max).scl(0.5f);
        dim.set(max).sub(min);
        return this;
    }

    /** Sets the bounding box minimum and maximum vector from the given points.
     *
     * @param points The points.
     * @return This bounding box for chaining. */
    public Bounds set(Vector3[] points) {
        this.inf();
        for (Vector3 l_point : points)
            this.ext(l_point);
        return this;
    }

    /** Sets the bounding box minimum and maximum vector from the given points.
     *
     * @param points The points.
     * @return This bounding box for chaining. */
    public Bounds set(List<Vector3> points) {
        this.inf();
        for (Vector3 l_point : points)
            this.ext(l_point);
        return this;
    }

    /** Sets the minimum and maximum vector to positive and negative infinity.
     *
     * @return This bounding box for chaining. */
    public Bounds inf() {
        min.set(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        max.set(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
        cnt.set(0, 0, 0);
        dim.set(0, 0, 0);
        return this;
    }

    /** Extends the bounding box to incorporate the given {@link Vector3}.
     * @param point The vector
     * @return This bounding box for chaining. */
    public Bounds ext(Vector3 point) {
        return this.set(min.set(min(min.x, point.x), min(min.y, point.y), min(min.z, point.z)),
                max.set(Math.max(max.x, point.x), Math.max(max.y, point.y), Math.max(max.z, point.z)));
    }

    /** Sets the minimum and maximum vector to zeros.
     * @return This bounding box for chaining. */
    public Bounds clr() {
        return this.set(min.set(0, 0, 0), max.set(0, 0, 0));
    }

    /** Returns whether this bounding box is valid. This means that {@link #max} is greater than {@link #min}.
     * @return True in case the bounding box is valid, false otherwise */
    public boolean isValid() {
        return min.x < max.x && min.y < max.y && min.z < max.z;
    }

    /** Extends this bounding box by the given bounding box.
     *
     * @param a_bounds The bounding box
     * @return This bounding box for chaining. */
    public Bounds ext(Bounds a_bounds) {
        return this.set(min.set(min(min.x, a_bounds.min.x), min(min.y, a_bounds.min.y), min(min.z, a_bounds.min.z)),
                max.set(max(max.x, a_bounds.max.x), max(max.y, a_bounds.max.y), max(max.z, a_bounds.max.z)));
    }

    /** Extends this bounding box by the given sphere.
     *
     * @param center Sphere center
     * @param radius Sphere radius
     * @return This bounding box for chaining. */
    public Bounds ext(Vector3 center, double radius) {
        return this.set(min.set(min(min.x, center.x - radius), min(min.y, center.y - radius), min(min.z, center.z - radius)),
                max.set(max(max.x, center.x + radius), max(max.y, center.y + radius), max(max.z, center.z + radius)));
    }


    /** Returns whether the given bounding box is contained in this bounding box.
     * @param b The bounding box
     * @return Whether the given bounding box is contained */
    public boolean contains(Bounds b) {
        return !isValid()
                || (min.x <= b.min.x && min.y <= b.min.y && min.z <= b.min.z && max.x >= b.max.x && max.y >= b.max.y && max.z >= b.max.z);
    }

    /** Returns whether the given bounding box is intersecting this bounding box (at least one point in).
     * @param b The bounding box
     * @return Whether the given bounding box is intersected */
    public boolean intersects(Bounds b) {
        if (!isValid()) return false;

        // test using SAT (separating axis theorem)

        double lx = Math.abs(this.cnt.x - b.cnt.x);
        double sumx = (this.dim.x / 2.0f) + (b.dim.x / 2.0f);

        double ly = Math.abs(this.cnt.y - b.cnt.y);
        double sumy = (this.dim.y / 2.0f) + (b.dim.y / 2.0f);

        double lz = Math.abs(this.cnt.z - b.cnt.z);
        double sumz = (this.dim.z / 2.0f) + (b.dim.z / 2.0f);

        return (lx <= sumx && ly <= sumy && lz <= sumz);

    }

    /** Returns whether the given vector is contained in this bounding box.
     * @param v The vector
     * @return Whether the vector is contained or not. */
    public boolean contains(Vector3 v) {
        return min.x <= v.x && max.x >= v.x && min.y <= v.y && max.y >= v.y && min.z <= v.z && max.z >= v.z;
    }

    @Override
    public String toString() {
        return "[" + min + "|" + max + "]";
    }

    /** Extends the bounding box by the given vector.
     *
     * @param x The x-coordinate
     * @param y The y-coordinate
     * @param z The z-coordinate
     * @return This bounding box for chaining. */
    public Bounds ext(double x, double y, double z) {
        return this.set(min.set(min(min.x, x), min(min.y, y), min(min.z, z)), max.set(max(max.x, x), max(max.y, y), max(max.z, z)));
    }

    static final double min(final double a, final double b) {
        return a > b ? b : a;
    }

    static final double max(final double a, final double b) {
        return a > b ? a : b;
    }
}