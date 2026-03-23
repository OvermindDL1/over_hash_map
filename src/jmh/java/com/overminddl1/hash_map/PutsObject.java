package com.overminddl1.hash_map;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


@State(Scope.Thread)
public class PutsObject {
    private static final Integer value = 42;
    private final int COUNT = 1;
    private final Maps maps = new Maps(10_000_000);
    private int idx = 0;

    @Setup(Level.Iteration)
    public void setup() {
        maps.clear();
        this.idx = 0;
    }

//    @Benchmark
//    public void put_pc_hashmap_linked(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_linked_custom_fx(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_fx.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_custom_fx(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_fx.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_linked_custom_java(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_java.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_custom_java(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_java.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_linked_custom_ijava(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_ijava.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }
//
//    @Benchmark
//    public void put_pc_hashmap_custom_ijava(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_ijava.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }

    @Benchmark
    public void put_java_hashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.java_hashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

//    @Benchmark
//    public void put_java_linked_hashmap(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.java_linked_hashmap.put(new TupleII(idx, idx + 1), value));
//            idx += 1;
//        }
//    }

    @Benchmark
    public void put_over_hashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.over_hashmap_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_over_hashmap_java(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.over_hashmap_java.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    @Benchmark
    public void put_fastutils_openhashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.fastutils_openhashmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    // @Benchmark
    // public void put_fastutils_linkedopenhashmap(Blackhole bh) {
    //     for(int i=0; i<COUNT; i++) {
    //     bh.consume(maps.fastutils_linkedopenhashmap.put(new TupleII(idx, idx+1), value));
    //     idx += 1;
    //     }
    // }

    @Benchmark
    public void put_fastutils_opencustomhashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.fastutils_opencustomhashmap_fx.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }

    // @Benchmark
    // public void put_fastutils_openlinkedcustomhashmap_fx(Blackhole bh) {
    //     for(int i=0; i<COUNT; i++) {
    //     bh.consume(maps.fastutils_openlinkedcustomhashmap_fx.put(new TupleII(idx, idx+1), value));
    //     idx += 1;
    //     }
    // }

    @Benchmark
    public void put_swissmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.swissmap.put(new TupleII(idx, idx + 1), value));
            idx += 1;
        }
    }
}
