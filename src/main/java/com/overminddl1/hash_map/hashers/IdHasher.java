package com.overminddl1.hash_map.hashers;

public final class IdHasher implements Hasher {

    @Override
    public final int init() {
        return 0;
    }

    @Override
    public int finish(int state) {
        return state;
    }

    @Override
    public final int write(int state, int value) {
        return value;
    }
}
