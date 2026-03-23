package com.overminddl1.hash_map;

import java.util.Objects;

public class TupleII {
    public int f0, f1;
    public TupleII(int f0, int f1) {
        this.f0 = f0;
        this.f1 = f1;
    }

    public int f0() {
        return f0;
    }

    public int f1() {
        return f1;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(this.f0, this.f1);
        int result = 17;
        result = 31 * result + this.f0;
        result = 31 * result + this.f1;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TupleII other) {
            return this.f0 == other.f0 && this.f1 == other.f1;
        }
        return false;
    }
}
