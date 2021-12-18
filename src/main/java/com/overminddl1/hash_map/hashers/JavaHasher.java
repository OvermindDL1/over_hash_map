package com.overminddl1.hash_map.hashers;

public class JavaHasher implements Hasher{
    @Override
    public int init() {
        return 0;
    }

    @Override
    public int write(int state, int value) {
        return mix(state ^ value);
    }

    @Override
    public int finish(int state) {
        return state;
    }

    /** 2<sup>32</sup> &middot; &phi;, &phi; = (&#x221A;5 &minus; 1)/2. */
    private static final int INT_PHI = 0x9E3779B9;
    public static int mix(final int x) {
        final int h = x * INT_PHI;
        return h ^ (h >>> 16);
    }
}
