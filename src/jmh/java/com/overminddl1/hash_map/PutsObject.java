package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.JavaHasher;
import io.github.bluuewhale.hashsmith.SwissMap;
import it.unimi.dsi.fastutil.Hash;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectLinkedOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectLinkedOpenHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectOpenHashMap;
import speiger.src.collections.objects.utils.ObjectStrategy;

import java.util.Objects;


@State(Scope.Thread)
public class PutsObject {
    private static final Integer value = 42;
    private final int COUNT = 1;
    private final int PRESIZE = 10_000_000;
    private final FxHasher hasher_fx = new FxHasher();
    private final JavaHasher hasher_java = new JavaHasher();
    private final ObjectStrategy<TupleII> pc_hasher_fx = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            int hash = hasher_fx.init();
            hash = hasher_fx.write(hash, o.f0());
            hash = hasher_fx.write(hash, o.f1());
            return hasher_fx.finish(hash);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_fx = new Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.pc_hasher_fx);
    private final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_fx = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_fx);
    private final ObjectStrategy<TupleII> pc_hasher_java = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            return Objects.hashCode(o);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private final ObjectStrategy<TupleII> pc_hasher_ijava = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            int hash = hasher_java.init();
            hash = hasher_java.write(hash, o.f0());
            hash = hasher_java.write(hash, o.f1());
            return hasher_java.finish(hash);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_ijava = new Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.pc_hasher_ijava);
    private final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_ijava = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_ijava);
    private final Hash.Strategy<TupleII> fastutils_hasher_fx = new Hash.Strategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            int hash = hasher_fx.init();
            hash = hasher_fx.write(hash, o.f0());
            hash = hasher_fx.write(hash, o.f1());
            return hasher_fx.finish(hash);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    private final it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap<TupleII, Integer> fastutils_opencustomhashmap_fx = new it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap<>(PRESIZE, this.fastutils_hasher_fx);
    private final it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> fastutils_openlinkedcustomhashmap_fx = new it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.fastutils_hasher_fx);
    private final Object2ObjectLinkedOpenHashMap<TupleII, Integer> pc_hashmap_linked = new Object2ObjectLinkedOpenHashMap<>(PRESIZE);
    private final Object2ObjectOpenHashMap<TupleII, Integer> pc_hashmap = new Object2ObjectOpenHashMap<>(PRESIZE);
    private final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_java = new Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.pc_hasher_java);
    private final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_java = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_java);
    private final java.util.HashMap<TupleII, Integer> java_hashmap = new java.util.HashMap<>(PRESIZE);
    private final java.util.LinkedHashMap<TupleII, Integer> java_linked_hashmap = new java.util.LinkedHashMap<>(PRESIZE);
    private final com.overminddl1.hash_map.HashMap<TupleII, Integer, FxHasher> over_hashmap_fx = new HashMap<>(hasher_fx, (hasher, state, key) -> {
        state = hasher.write(state, key.f0());
        state = hasher.write(state, key.f1());
        return state;
    }, PRESIZE);
    private final com.overminddl1.hash_map.HashMap<TupleII, Integer, JavaHasher> over_hashmap_java = new HashMap<>(hasher_java, (hasher, state, key) -> {
        state = hasher.write(state, key.f0());
        state = hasher.write(state, key.f1());
        return state;
    }, PRESIZE);
    private final it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap<TupleII, Integer> fastutils_openhashmap = new it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap<>(PRESIZE);
    private final it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap<TupleII, Integer> fastutils_linkedopenhashmap = new it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap<>(PRESIZE);
    private final SwissMap<TupleII, Integer> swissmap = new SwissMap<>(PRESIZE);
    private int idx = 0;

    @Setup(Level.Iteration)
    public void setup() {
        // pc_hashmap_linked.clearAndTrim(0);
        // pc_hashmap.clearAndTrim(0);
        // pc_hashmap_linked_custom_fx.clearAndTrim(0);
        // pc_hashmap_custom_fx.clearAndTrim(0);
        // pc_hashmap_linked_custom_java.clearAndTrim(0);
        // pc_hashmap_custom_java.clearAndTrim(0);
        // pc_hashmap_linked_custom_ijava.clearAndTrim(0);
        // pc_hashmap_custom_ijava.clearAndTrim(0);
        pc_hashmap_linked.clear();
        pc_hashmap.clear();
        pc_hashmap_linked_custom_fx.clear();
        pc_hashmap_custom_fx.clear();
        pc_hashmap_linked_custom_java.clear();
        pc_hashmap_custom_java.clear();
        pc_hashmap_linked_custom_ijava.clear();
        pc_hashmap_custom_ijava.clear();
        // java_hashmap = new java.util.HashMap<>();
        java_hashmap.clear();
        // java_linked_hashmap = new java.util.LinkedHashMap<>();
        java_linked_hashmap.clear();
        // // over_hashmap_fx.clearAndTrim(0);
        // // over_hashmap_java.clearAndTrim(0);
        over_hashmap_fx.clear();
        over_hashmap_java.clear();
        // over_hashmap_fx = new HashMap<>(hasher_fx, (hasher, state, key) -> {
        //     state = hasher.write(state, key.f0());
        //     state = hasher.write(state, key.f1());
        //     return state;
        // });
        // over_hashmap_java = new HashMap<>(hasher_java, (hasher, state, key) -> {
        //     state = hasher.write(state, key.f0());
        //     state = hasher.write(state, key.f1());
        //     return state;
        // });
        fastutils_openhashmap.clear(); //fastutils_openhashmap.trim();
        fastutils_linkedopenhashmap.clear(); //fastutils_linkedopenhashmap.trim();
        fastutils_opencustomhashmap_fx.clear(); //fastutils_opencustomhashmap_fx.trim();
        fastutils_openlinkedcustomhashmap_fx.clear(); //fastutils_openlinkedcustomhashmap_fx.trim();
        swissmap.clear(); //swissmap.trim(); // Ugh missing trim...
        // swissmap = new SwissMap<>();
        this.idx = 0;
    }

    @Benchmark
    public void put_pc_hashmap_linked(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_linked.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_linked_custom_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_custom_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_custom_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_java(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_linked_custom_java.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_custom_java(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_custom_java.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_linked_custom_ijava(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_linked_custom_ijava.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_pc_hashmap_custom_ijava(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(pc_hashmap_custom_ijava.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_java_hashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(java_hashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_java_linked_hashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(java_linked_hashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_over_hashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(over_hashmap_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_over_hashmap_java(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(over_hashmap_java.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_fastutils_openhashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(fastutils_openhashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    // @Benchmark
    // public void put_fastutils_linkedopenhashmap(Blackhole bh) {
    //     for(int i=0; i<COUNT; i++) {
    //     bh.consume(fastutils_linkedopenhashmap.put(new TupleII(idx, idx+1), value));
    //     idx += 1;
    //     }
    // }

    @Benchmark
    public void put_fastutils_opencustomhashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(fastutils_opencustomhashmap_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    // @Benchmark
    // public void put_fastutils_openlinkedcustomhashmap_fx(Blackhole bh) {
    //     for(int i=0; i<COUNT; i++) {
    //     bh.consume(fastutils_openlinkedcustomhashmap_fx.put(new TupleII(idx, idx+1), value));
    //     idx += 1;
    //     }
    // }

    @Benchmark
    public void put_swissmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(swissmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }
}
