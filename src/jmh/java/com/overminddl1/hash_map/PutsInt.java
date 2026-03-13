package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.IdHasher;
import com.overminddl1.hash_map.hashers.JavaHasher;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import speiger.src.collections.ints.maps.impl.customHash.Int2ObjectLinkedOpenCustomHashMap;
import speiger.src.collections.ints.maps.impl.customHash.Int2ObjectOpenCustomHashMap;
import speiger.src.collections.ints.maps.impl.hash.Int2ObjectLinkedOpenHashMap;
import speiger.src.collections.ints.maps.impl.hash.Int2ObjectOpenHashMap;
import speiger.src.collections.ints.utils.IntStrategy;
import speiger.src.collections.objects.utils.ObjectStrategy;

import java.util.Objects;


@State(Scope.Thread)
public class PutsInt {
    private FxHasher hasher_fx = new FxHasher();
    private JavaHasher hasher_java = new JavaHasher();
    private IdHasher hasher_id = new IdHasher();
    private IntStrategy pc_hasher_fx = new IntStrategy() {
        @Override
        public int hashCode(int o) {
            int hash = hasher_fx.init();
            hash = hasher_fx.write(hash, o);
            return hasher_fx.finish(hash);
        }

        @Override
        public boolean equals(int a, int b) {
            return a == b;
        }
    };
    private IntStrategy pc_hasher_java = new IntStrategy() {
        @Override
        public int hashCode(int o) {
            return Objects.hashCode(o);
        }

        @Override
        public boolean equals(int a, int b) {
            return Objects.equals(a, b);
        }
    };
    private IntStrategy pc_hasher_ijava = new IntStrategy() {
        @Override
        public int hashCode(int o) {
            int hash = hasher_java.init();
            hash = hasher_java.write(hash, o);
            return (int) hasher_java.finish(hash);
        }

        @Override
        public boolean equals(int a, int b) {
            return a == b;
        }
    };
    final int CAPACITY = 0; //10000000;
    private Int2ObjectLinkedOpenHashMap<TupleII> pc_hashmap_linked = new Int2ObjectLinkedOpenHashMap<>(CAPACITY);
    private Int2ObjectOpenHashMap<TupleII> pc_hashmap = new Int2ObjectOpenHashMap<>(CAPACITY);
    private Int2ObjectLinkedOpenCustomHashMap<TupleII> pc_hashmap_linked_custom_fx = new Int2ObjectLinkedOpenCustomHashMap<>(CAPACITY, this.pc_hasher_fx);
    private Int2ObjectOpenCustomHashMap<TupleII> pc_hashmap_custom_fx = new Int2ObjectOpenCustomHashMap<>(CAPACITY, this.pc_hasher_fx);
    private Int2ObjectLinkedOpenCustomHashMap<TupleII> pc_hashmap_linked_custom_java = new Int2ObjectLinkedOpenCustomHashMap<>(CAPACITY, this.pc_hasher_java);
    private Int2ObjectOpenCustomHashMap<TupleII> pc_hashmap_custom_java = new Int2ObjectOpenCustomHashMap<>(CAPACITY, this.pc_hasher_java);
    private Int2ObjectLinkedOpenCustomHashMap<TupleII> pc_hashmap_linked_custom_ijava = new Int2ObjectLinkedOpenCustomHashMap<>(CAPACITY, this.pc_hasher_ijava);
    private Int2ObjectOpenCustomHashMap<TupleII> pc_hashmap_custom_ijava = new Int2ObjectOpenCustomHashMap<>(CAPACITY, this.pc_hasher_ijava);
    private java.util.HashMap<Integer, TupleII> java_hashmap = new java.util.HashMap<>(CAPACITY);
    private java.util.LinkedHashMap<Integer, TupleII> java_linked_hashmap = new java.util.LinkedHashMap<>(CAPACITY);
    private HashMap<Integer, TupleII, FxHasher> over_hashmap_fx = new HashMap<>(hasher_fx, (hasher, state, key) -> {
        state = hasher.write(state, key);
        return state;
    }, CAPACITY);
    private HashMap<Integer, TupleII, JavaHasher> over_hashmap_java = new HashMap<>(hasher_java, (hasher, state, key) -> {
        state = hasher.write(state, key);
        return state;
    }, CAPACITY);
    private HashMapInt2Object<TupleII, FxHasher> over_hashmapint_fx = new HashMapInt2Object<>(hasher_fx, (hasher, state, key) -> {
        state = hasher.write(state, key);
        return state;
    }, CAPACITY);
    private HashMapInt2Object<TupleII, JavaHasher> over_hashmapint_java = new HashMapInt2Object<>(hasher_java, (hasher, state, key) -> {
        state = hasher.write(state, key);
        return state;
    }, CAPACITY);
    private HashMapInt2Object<TupleII, IdHasher> over_hashmapint_id = new HashMapInt2Object<>(hasher_id, (hasher, state, key) -> {
        state = hasher.write(state, key);
        return state;
    }, CAPACITY);

    private int idx = 0;
    private static Integer value = 42;

    @Setup
    public void setup() {
        pc_hashmap_linked.clearAndTrim(0);
        pc_hashmap.clearAndTrim(0);
        pc_hashmap_linked_custom_fx.clearAndTrim(0);
        pc_hashmap_custom_fx.clearAndTrim(0);
        pc_hashmap_linked_custom_java.clearAndTrim(0);
        pc_hashmap_custom_java.clearAndTrim(0);
        pc_hashmap_linked_custom_ijava.clearAndTrim(0);
        pc_hashmap_custom_ijava.clearAndTrim(0);
        java_hashmap = new java.util.HashMap<>();
        java_linked_hashmap = new java.util.LinkedHashMap<>();
        over_hashmap_fx.clearAndTrim(0);
        over_hashmap_java.clearAndTrim(0);
        over_hashmapint_fx.clearAndTrim(0);
        over_hashmapint_java.clearAndTrim(0);
        over_hashmapint_id.clearAndTrim(0);
        this.idx = 0;
    }

    static final TupleII VALUE = new TupleII(0, 0);

    @Benchmark
    public void put_pc_hashmap_linked(Blackhole bh) {
        bh.consume(pc_hashmap_linked.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap(Blackhole bh) {
        bh.consume(pc_hashmap.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_fx(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_fx.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_fx(Blackhole bh) {
        bh.consume(pc_hashmap_custom_fx.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_java(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_java.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_java(Blackhole bh) {
        bh.consume(pc_hashmap_custom_java.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_ijava(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_ijava.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_ijava(Blackhole bh) {
        bh.consume(pc_hashmap_custom_ijava.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_java_hashmap(Blackhole bh) {
        bh.consume(java_hashmap.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_java_linked_hashmap(Blackhole bh) {
        bh.consume(java_linked_hashmap.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmap_fx(Blackhole bh) {
        bh.consume(over_hashmap_fx.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmap_java(Blackhole bh) {
        bh.consume(over_hashmap_java.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmapint_fx(Blackhole bh) {
        bh.consume(over_hashmapint_fx.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmapint_java(Blackhole bh) {
        bh.consume(over_hashmapint_java.put(idx, VALUE));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmapint_id(Blackhole bh) {
        bh.consume(over_hashmapint_id.put(idx, VALUE));
        idx += 1;
    }
}
