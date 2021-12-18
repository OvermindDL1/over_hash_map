package com.overminddl1.hash_map.hashers;

public final class FxHasher implements Hasher {
    public static final int ROTATE = 5;
    public static final long SEED64 = 0x517cc1b727220a95L;
    public static final int SEED32 = (int)(SEED64 & 0xFFFF_FFFFL);
    public static final int SEED = SEED32;

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
        return (Integer.rotateLeft(state, ROTATE) ^ value) * SEED;
    }
}
