package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.JavaHasher;
import io.github.bluuewhale.hashsmith.SwissMap;
import it.unimi.dsi.fastutil.Hash;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectLinkedOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.customHash.Object2ObjectOpenCustomHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectLinkedOpenHashMap;
import speiger.src.collections.objects.maps.impl.hash.Object2ObjectOpenHashMap;
import speiger.src.collections.objects.utils.ObjectStrategy;

import java.util.Objects;
import java.util.function.IntFunction;

public class Maps {
    private static final int PRESIZE = 10_000_000;
    public final FxHasher hasher_fx = new FxHasher();
    public final JavaHasher hasher_java = new JavaHasher();
    public final ObjectStrategy<TupleII> pc_hasher_fx = new ObjectStrategy<TupleII>() {
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
    public final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_fx = new Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.pc_hasher_fx);
    public final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_fx = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_fx);
    public final ObjectStrategy<TupleII> pc_hasher_java = new ObjectStrategy<TupleII>() {
        @Override
        public int hashCode(TupleII o) {
            return Objects.hashCode(o);
        }

        @Override
        public boolean equals(TupleII a, TupleII b) {
            return Objects.equals(a, b);
        }
    };
    public final ObjectStrategy<TupleII> pc_hasher_ijava = new ObjectStrategy<TupleII>() {
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
    public final Hash.Strategy<TupleII> fastutils_hasher_fx = new Hash.Strategy<TupleII>() {
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
    public final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_ijava;
    public final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_ijava;
    public final it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap<TupleII, Integer> fastutils_opencustomhashmap_fx;
    public final it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> fastutils_openlinkedcustomhashmap_fx;
    public final Object2ObjectLinkedOpenHashMap<TupleII, Integer> pc_hashmap_linked;
    public final Object2ObjectOpenHashMap<TupleII, Integer> pc_hashmap;
    public final Object2ObjectLinkedOpenCustomHashMap<TupleII, Integer> pc_hashmap_linked_custom_java;
    public final Object2ObjectOpenCustomHashMap<TupleII, Integer> pc_hashmap_custom_java;
    public final java.util.HashMap<TupleII, Integer> java_hashmap;
    public final java.util.LinkedHashMap<TupleII, Integer> java_linked_hashmap;
    public final com.overminddl1.hash_map.HashMap<TupleII, Integer, FxHasher> over_hashmap_fx;
    public final com.overminddl1.hash_map.HashMap<TupleII, Integer, JavaHasher> over_hashmap_java;
    public final it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap<TupleII, Integer> fastutils_openhashmap;
    public final it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap<TupleII, Integer> fastutils_linkedopenhashmap;
    public final SwissMap<TupleII, Integer> swissmap;

    public Maps(int capacity) {
        pc_hashmap_linked_custom_ijava = new Object2ObjectLinkedOpenCustomHashMap<>(capacity, this.pc_hasher_ijava);
        pc_hashmap_custom_ijava = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_ijava);
        fastutils_opencustomhashmap_fx = new it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap<>(PRESIZE, this.fastutils_hasher_fx);
        fastutils_openlinkedcustomhashmap_fx = new it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.fastutils_hasher_fx);
        pc_hashmap_linked = new Object2ObjectLinkedOpenHashMap<>(PRESIZE);
        pc_hashmap = new Object2ObjectOpenHashMap<>(PRESIZE);
        pc_hashmap_linked_custom_java = new Object2ObjectLinkedOpenCustomHashMap<>(PRESIZE, this.pc_hasher_java);
        pc_hashmap_custom_java = new Object2ObjectOpenCustomHashMap<>(PRESIZE, this.pc_hasher_java);
        java_hashmap = new java.util.HashMap<>(PRESIZE);
        java_linked_hashmap = new java.util.LinkedHashMap<>(PRESIZE);
        over_hashmap_fx = new HashMap<>(hasher_fx, (hasher, state, key) -> {
            state = hasher.write(state, key.f0());
            state = hasher.write(state, key.f1());
            return state;
        }, PRESIZE);
        over_hashmap_java = new HashMap<>(hasher_java, (hasher, state, key) -> {
            state = hasher.write(state, key.f0());
            state = hasher.write(state, key.f1());
            return state;
        }, PRESIZE);
        fastutils_openhashmap = new it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap<>(PRESIZE);
        fastutils_linkedopenhashmap = new it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap<>(PRESIZE);
        swissmap = new SwissMap<>(PRESIZE);
    }

    public void clear() {
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
    }

    public void fill(int count, IntFunction<TupleII> f, Integer value) {
        for (int i = 0; i < count; i++) {
            pc_hashmap_linked.put(f.apply(i), value);
            pc_hashmap.put(f.apply(i), value);
            pc_hashmap_linked_custom_fx.put(f.apply(i), value);
            pc_hashmap_custom_fx.put(f.apply(i), value);
            pc_hashmap_linked_custom_java.put(f.apply(i), value);
            pc_hashmap_custom_java.put(f.apply(i), value);
            pc_hashmap_linked_custom_ijava.put(f.apply(i), value);
            pc_hashmap_custom_ijava.put(f.apply(i), value);
            java_hashmap.put(f.apply(i), value);
            java_linked_hashmap.put(f.apply(i), value);
            over_hashmap_fx.put(f.apply(i), value);
            over_hashmap_java.put(f.apply(i), value);
            fastutils_openhashmap.put(f.apply(i), value);
            fastutils_linkedopenhashmap.put(f.apply(i), value);
            fastutils_opencustomhashmap_fx.put(f.apply(i), value);
            fastutils_openlinkedcustomhashmap_fx.put(f.apply(i), value);
            swissmap.put(f.apply(i), value);
        }
    }

    public void verifyValue(TupleII key, Integer value) {
        if(!pc_hashmap_linked.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_linked");
        if(!pc_hashmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap");
        if(!pc_hashmap_linked_custom_fx.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_linked_custom_fx");
        if(!pc_hashmap_custom_fx.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_custom_fx");
        if(!pc_hashmap_linked_custom_java.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_linked_custom_java");
        if(!pc_hashmap_custom_java.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_custom_java");
        if(!pc_hashmap_linked_custom_ijava.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_linked_custom_ijava");
        if(!pc_hashmap_custom_ijava.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: pc_hashmap_custom_ijava");
        if(!java_hashmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: java_hashmap");
        if(!java_linked_hashmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: java_linked_hashmap");
        if(!over_hashmap_fx.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: over_hashmap_fx");
        if(!over_hashmap_java.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: over_hashmap_java");
        if(!fastutils_openhashmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: fastutils_openhashmap");
        if(!fastutils_linkedopenhashmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: fastutils_linkedopenhashmap");
        if(!fastutils_opencustomhashmap_fx.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: fastutils_opencustomhashmap_fx");
        if(!fastutils_openlinkedcustomhashmap_fx.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: fastutils_openlinkedcustomhashmap_fx");
        if(!swissmap.get(key).equals(value)) throw new AssertionError("INCOHERENT GET: swissmap");
    }
}
