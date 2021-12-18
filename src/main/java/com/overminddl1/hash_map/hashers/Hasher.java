package com.overminddl1.hash_map.hashers;

public interface Hasher {
    int init();

    default int write(int state, long value) {
        return write(state, (int) value);
    }

    int write(int state, int value);

    default int write(int state, short value) {
        return write(state, (int) value);
    }

    default int write(int state, byte value) {
        return write(state, (int) value);
    }

    int finish(int state);
}
