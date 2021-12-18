package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.Hash;
import com.overminddl1.hash_map.hashers.Hasher;
import com.overminddl1.hash_map.hashers.JavaHasher;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectLinkedOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectLinkedOpenHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectOpenHashMap;
import speiger.src.collections.objects.utils.ObjectStrategy;

import java.util.Objects;


@State(Scope.Thread)
public class Puts {
    private FxHasher hasher_fx = new FxHasher();
    private JavaHasher hasher_java = new JavaHasher();
    private ObjectStrategy<TupleII> pc_hasher_fx = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            int hash = hasher_fx.init();
            hash = hasher_fx.write(hash, o.f0());
            hash = hasher_fx.write(hash, o.f1());
            return (int) hasher_fx.finish(hash);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private ObjectStrategy<TupleII> pc_hasher_java = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            return Objects.hashCode(o);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private ObjectStrategy<TupleII> pc_hasher_ijava = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            int hash = hasher_java.init();
            hash = hasher_java.write(hash, o.f0());
            hash = hasher_java.write(hash, o.f1());
            return (int) hasher_java.finish(hash);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private Object2ObjectLinkedOpenHashMap<TupleII, Integer> pc_hashmap_linked = new Object2ObjectLinkedOpenHashMap<>();
    private Object2ObjectOpenHashMap<TupleII, Integer> pc_hashmap = new Object2ObjectOpenHashMap<>();
    private Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_fx = new Object2ObjectLinkedOpenCustomHashMap<>(this.pc_hasher_fx);
    private Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_fx = new Object2ObjectOpenCustomHashMap<>(this.pc_hasher_fx);
    private Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_java = new Object2ObjectLinkedOpenCustomHashMap<>(this.pc_hasher_java);
    private Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_java = new Object2ObjectOpenCustomHashMap<>(this.pc_hasher_java);
    private Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_ijava = new Object2ObjectLinkedOpenCustomHashMap<>(this.pc_hasher_ijava);
    private Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_ijava = new Object2ObjectOpenCustomHashMap<>(this.pc_hasher_ijava);
    private java.util.HashMap<TupleII, Integer> java_hashmap = new java.util.HashMap<>();
    private java.util.LinkedHashMap<TupleII, Integer> java_linked_hashmap = new java.util.LinkedHashMap<>();
    private com.overminddl1.hash_map.HashMap<TupleII, Integer, FxHasher> over_hashmap_fx = new HashMap<>(hasher_fx, (hasher, state, key) -> {
        state = hasher.write(state, key.f0());
        state = hasher.write(state, key.f1());
        return state;
    });
    private com.overminddl1.hash_map.HashMap<TupleII, Integer, JavaHasher> over_hashmap_java = new HashMap<>(hasher_java, (hasher, state, key) -> {
        state = hasher.write(state, key.f0());
        state = hasher.write(state, key.f1());
        return state;
    });

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
        this.idx = 0;
    }

    @Benchmark
    public void put_pc_hashmap_linked(Blackhole bh) {
        bh.consume(pc_hashmap_linked.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap(Blackhole bh) {
        bh.consume(pc_hashmap.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_fx(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_fx.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_fx(Blackhole bh) {
        bh.consume(pc_hashmap_custom_fx.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_java(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_java.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_java(Blackhole bh) {
        bh.consume(pc_hashmap_custom_java.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_ijava(Blackhole bh) {
        bh.consume(pc_hashmap_linked_custom_ijava.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_pc_hashmap_custom_ijava(Blackhole bh) {
        bh.consume(pc_hashmap_custom_ijava.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_java_hashmap(Blackhole bh) {
        bh.consume(java_hashmap.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_java_linked_hashmap(Blackhole bh) {
        bh.consume(java_linked_hashmap.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmap_fx(Blackhole bh) {
        bh.consume(over_hashmap_fx.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }

    @Benchmark
    public void put_over_hashmap_java(Blackhole bh) {
        bh.consume(over_hashmap_java.put(new TupleII(idx, idx+1), value));
        idx += 1;
    }
}
