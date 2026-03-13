package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.Hash;
import com.overminddl1.hash_map.hashers.JavaHasher;

public final class HashMap<K, V, Hasher extends com.overminddl1.hash_map.hashers.Hasher> {
    private final Hasher hasher;
    private final Hash<K> hash_key;
    private Object[] keys_values;
    private int items;
    private int capacity;

    public HashMap(Hasher hasher, Hash<K> hash_key) {
        this(hasher, hash_key, 0);
    }

    public HashMap(Hasher hasher, Hash<K> hash_key, int capacity) {
        this.hasher = hasher;
        this.hash_key = hash_key;
        this.init_buckets(capacity);
    }

    @SuppressWarnings("unchecked")
    private void init_buckets(int capacity) {
        int buckets = capacity_to_buckets(capacity);
        this.keys_values = new Object[buckets * 2];
        this.capacity = (buckets*7) / 8;
        this.items = 0;
    }

    private int capacity_to_buckets(int capacity) {
        if(capacity < 8) {
            return 8;
        }
        int adjusted_cap = (capacity * 8) / 7;
        return 1 << (32 - Integer.numberOfLeadingZeros(adjusted_cap - 1));
    }

    private int make_insert_hash(K key) {
        int state = hasher.init();
        state = hash_key.hash(hasher, state, key);
        return hasher.finish(state);
    }

    @SuppressWarnings("unchecked")
    public boolean reserve(int additional) {
        if (this.capacity - this.items >= additional) {
            return false;
        }
        Object[] old = this.keys_values;
        int old_items = this.items;
        this.init_buckets(this.capacity + additional);
        for (int i = 0; i < old.length; i += 2) {
            Object k = old[i];
            if (k != null) {
                K key = (K) k;
                int bucket = this.find(this.make_insert_hash(key), key);
                this.keys_values[bucket] = key;
                this.keys_values[bucket + 1] = old[i + 1];
            }
        }
        this.items = old_items;
        return true;
    }

    private int find(int hash, K key) {
        int len = this.keys_values.length;
        int idx = (hash & (len / 2 - 1)) << 1;
        while(true) {
            Object k = this.keys_values[idx];
            if(k == null) {
                return idx;
            } else if(k.equals(key)) {
                return idx;
            } else {
                idx = (idx + 2) & (len - 1);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        this.reserve(1);
        int hash = this.make_insert_hash(key);
        int bucket = this.find(hash, key);
        V old = (V) this.keys_values[bucket + 1];
        this.keys_values[bucket] = key;
        this.keys_values[bucket + 1] = value;
        this.items += old == null ? 1 : 0;
        return old;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        int hash = this.make_insert_hash(key);
        int bucket = this.find(hash, key);
        return (V)this.keys_values[bucket + 1];
    }

    public void clear() {
        this.init_buckets(this.capacity);
    }

    public void clearAndTrim(int capacity) {
        this.init_buckets(capacity);
    }

    public int size() {
        return this.items;
    }
}
