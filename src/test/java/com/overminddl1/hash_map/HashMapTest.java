package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    @Test
    void put_grow() {
        HashMap<TupleII, String, FxHasher> map = new HashMap<>(new FxHasher(), (hasher, state, key) -> {
            state = hasher.write(state, key.f0());
            state = hasher.write(state, key.f1());
            return state;
        });
        for (int i = 0; i < 100; i++) {
            map.put(new TupleII(i, i), "value");
        }
    }

    @Test
    void put() {
        HashMap<TupleII, String, FxHasher> map = new HashMap<>(new FxHasher(), (hasher, state, key) -> {
            state = hasher.write(state, key.f0());
            state = hasher.write(state, key.f1());
            return state;
        });
        TupleII same = new TupleII(1, 2);
        assertEquals(0, map.size());
        map.put(same, "value");
        assertEquals(1, map.size());
        assertEquals("value", map.get(new TupleII(1, 2)));
        assertEquals("value", map.get(same));
    }
}