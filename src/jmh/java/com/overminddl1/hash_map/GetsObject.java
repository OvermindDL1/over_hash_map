package com.overminddl1.hash_map;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;


@State(Scope.Benchmark)
public class GetsObject {
    private static final Integer value = 42;
    private final int COUNT = 1;
    private final int KEY_COUNT = 1_000;
    private final Maps maps = new Maps(0);
    private int idx = 0;
    private TupleII tKey = new TupleII(0, 0);

    public GetsObject() {
        maps.fill(KEY_COUNT, (int i) -> new TupleII(i, i + 1), value);
        maps.verifyValue(new TupleII(0, 1), value);
        maps.verifyValue(new TupleII(42, 43), value);

    }

    @Setup(Level.Iteration)
    public void setup() {
        this.idx = 0;
    }

    private TupleII key() {
        this.tKey.f0 = this.idx;
        this.tKey.f1 = this.idx + 1;
        this.idx = (this.idx + 1) % KEY_COUNT;
        return this.tKey;
    }

//    @Benchmark
//    public void get_pc_hashmap_linked(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_linked_custom_fx(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_fx.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_custom_fx(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_fx.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_linked_custom_java(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_java.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_custom_java(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_java.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_linked_custom_ijava(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_linked_custom_ijava.get(this.key()));
//        }
//    }
//
//    @Benchmark
//    public void get_pc_hashmap_custom_ijava(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//            bh.consume(maps.pc_hashmap_custom_ijava.get(this.key()));
//        }
//    }

    @Benchmark
    public void get_java_hashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.java_hashmap.get(this.key()));
        }
    }

//    @Benchmark
//    public void get_java_linked_hashmap(Blackhole bh) {
//        for (int i = 0; i < COUNT; i++) {
//        bh.consume(maps.java_linked_hashmap.get(this.key()));
//    }

    @Benchmark
    public void get_over_hashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.over_hashmap_fx.get(this.key()));
        }
    }

    @Benchmark
    public void get_over_hashmap_java(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.over_hashmap_java.get(this.key()));
        }
    }

    @Benchmark
    public void get_fastutils_openhashmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.fastutils_openhashmap.get(this.key()));
        }
    }

    // @Benchmark
    // public void get_fastutils_linkedopenhashmap(Blackhole bh) {
    //     for (int i = 0; i < COUNT; i++) {
    //         bh.consume(maps.fastutils_linkedopenhashmap.get(this.key()));
    //     }
    // }

    @Benchmark
    public void get_fastutils_opencustomhashmap_fx(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.fastutils_opencustomhashmap_fx.get(this.key()));
        }
    }

    // @Benchmark
    // public void get_fastutils_openlinkedcustomhashmap_fx(Blackhole bh) {
    //     for (int i = 0; i < COUNT; i++) {
    //         bh.consume(maps.fastutils_openlinkedcustomhashmap_fx.get(this.key()));
    //     }
    // }

    @Benchmark
    public void get_swissmap(Blackhole bh) {
        for (int i = 0; i < COUNT; i++) {
            bh.consume(maps.swissmap.get(this.key()));
        }
    }
}
