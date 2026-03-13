package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.Hash;
import com.overminddl1.hash_map.hashers.HashInt;

public final class HashMapInt2Object<V, Hasher extends com.overminddl1.hash_map.hashers.Hasher> {
    private final Hasher hasher;
    private final HashInt hash_key;
    private int[] keys;
    private V[] values;
    private int items;
    private int capacity;

    public HashMapInt2Object(Hasher hasher, HashInt hash_key) {
        this(hasher, hash_key, 0);
    }

    public HashMapInt2Object(Hasher hasher, HashInt hash_key, int capacity) {
        this.hasher = hasher;
        this.hash_key = hash_key;
        this.init_buckets(capacity);
    }

    @SuppressWarnings("unchecked")
    private void init_buckets(int capacity) {
        int buckets = capacity_to_buckets(capacity);
        this.keys = new int[buckets];
        this.values = (V[]) new Object[buckets];
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

    private int make_insert_hash(int key) {
        int state = hasher.init();
        state = hash_key.hash(hasher, state, key);
        return hasher.finish(state);
    }

    @SuppressWarnings("unchecked")
    public boolean reserve(int additional) {
        if (this.capacity - this.items >= additional) {
            return false;
        }
        int[] old_keys = this.keys;
        V[] old_values = this.values;
        int old_items = this.items;
        this.init_buckets(this.capacity + additional);
        int i = 0;
        while(i < old_values.length) {
            if(old_values[i] != null) {
                int key = old_keys[i];
                int bucket = this.find(this.make_insert_hash(key), key);
                this.keys[bucket] = key;
                this.values[bucket] = old_values[i];
                i += 1;
            } else {
//                Integer j = (Integer)old[i+1];
//                if(j != null) {
//                    i += j;
//                } else {
                    i += 1;
//                }
            }
        }
        this.items = old_items;
        return true;
    }

    private int find(int hash, int key) {
        int idx = (hash & (this.values.length - 1));
        while(true) {
            if(this.values[idx] == null) {
                return idx;
//            } else if(this.keys_values[idx] instanceof Integer) {
//                idx += ((Integer)this.keys_values[idx]).intValue();
            } else if(this.keys[idx] == key) {
                return idx;
            } else {
                idx = (idx + 1) % this.values.length;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public V put(int key, V value) {
        this.reserve(1);
        int hash = this.make_insert_hash(key);
        int bucket = this.find(hash, key);
        V old = this.values[bucket];
        this.keys[bucket] = key;
        this.values[bucket] = value;
        this.items += old == null ? 1 : 0;
        return old;
    }

    @SuppressWarnings("unchecked")
    public V get(int key) {
        int hash = this.make_insert_hash(key);
        int bucket = this.find(hash, key);
        return (V)this.values[bucket + 1];
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
