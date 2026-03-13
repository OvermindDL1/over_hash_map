package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.Hash;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {

    private HashMap<TupleII, String, FxHasher> map;

    @BeforeEach
    void setUp() {
        Hash<TupleII> hashKey = (hasher, state, key) -> {
            state = hasher.write(state, key.f0());
            state = hasher.write(state, key.f1());
            return state;
        };
        map = new HashMap<>(new FxHasher(), hashKey);
    }

    @Test
    void testEmptyMap() {
        assertEquals(0, map.size());
        assertNull(map.get(new TupleII(1, 1)));
    }

    @Test
    void testPutAndGet() {
        TupleII key = new TupleII(1, 2);
        assertNull(map.put(key, "value"));
        assertEquals(1, map.size());
        assertEquals("value", map.get(new TupleII(1, 2)));
        assertEquals("value", map.get(key));
    }

    @Test
    void testPutOverwrite() {
        TupleII key = new TupleII(1, 2);
        assertNull(map.put(key, "value1"));
        assertEquals(1, map.size());

        assertEquals("value1", map.put(key, "value2"));
        assertEquals(1, map.size());
        assertEquals("value2", map.get(key));
    }

    @Test
    void testPutGrow() {
        for (int i = 0; i < 1000; i++) {
            assertNull(map.put(new TupleII(i, i), "value" + i));
        }
        assertEquals(1000, map.size());
        for (int i = 0; i < 1000; i++) {
            assertEquals("value" + i, map.get(new TupleII(i, i)));
        }
    }

    @Test
    void testClear() {
        map.put(new TupleII(1, 1), "value1");
        map.put(new TupleII(2, 2), "value2");
        assertEquals(2, map.size());

        map.clear();
        assertEquals(0, map.size());
        assertNull(map.get(new TupleII(1, 1)));
        assertNull(map.get(new TupleII(2, 2)));
    }

    @Test
    void testClearAndTrim() {
        map.put(new TupleII(1, 1), "value1");
        map.put(new TupleII(2, 2), "value2");
        assertEquals(2, map.size());

        map.clearAndTrim(10);
        assertEquals(0, map.size());
        assertNull(map.get(new TupleII(1, 1)));
        assertNull(map.get(new TupleII(2, 2)));
    }

    @Test
    void testCollisionHandling() {
        // A custom hash that always hashes to the same value to force collisions on everything
        // which basically turns this into a linear array lookup...
        Hash<TupleII> badHash = (hasher, state, key) -> state;
        HashMap<TupleII, String, FxHasher> badMap = new HashMap<>(new FxHasher(), badHash);

        assertNull(badMap.put(new TupleII(1, 1), "val1"));
        assertNull(badMap.put(new TupleII(2, 2), "val2"));
        assertNull(badMap.put(new TupleII(3, 3), "val3"));

        assertEquals(3, badMap.size());
        assertEquals("val1", badMap.get(new TupleII(1, 1)));
        assertEquals("val2", badMap.get(new TupleII(2, 2)));
        assertEquals("val3", badMap.get(new TupleII(3, 3)));
    }

    @Test
    void testGetNonExistent() {
        map.put(new TupleII(1, 1), "value");
        assertNull(map.get(new TupleII(2, 2)));
    }
}
