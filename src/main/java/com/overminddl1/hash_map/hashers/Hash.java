package com.overminddl1.hash_map.hashers;

public interface Hash<K> {
    int hash(Hasher hasher, int state, K key);
}
