package com.overminddl1.hash_map;

import com.overminddl1.hash_map.hashers.FxHasher;
import com.overminddl1.hash_map.hashers.Hasher;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import speiger.src.collections.utils.HashUtil;


@State(Scope.Benchmark)
public class Hashers {
    private FxHasher hasher = new FxHasher();

    private int idx = 0;

    @Setup
    public void setup() {
        this.idx = 0;
    }

    @Benchmark
    public void hasher_java(Blackhole bh) {
        bh.consume(new TupleII(idx, idx+1).hashCode());
        idx += 1;
    }

    @Benchmark
    public void hasher_fx(Blackhole bh) {
        TupleII o = new TupleII(idx, idx+1);
        int hash = hasher.init();
        hash = hasher.write(hash, o.f0());
        hash = hasher.write(hash, o.f1());
        bh.consume(hasher.finish(hash));
        idx += 1;
    }

    @Benchmark
    public void hasher_pc_mix_java(Blackhole bh) {
        bh.consume(HashUtil.mix(new TupleII(idx, idx+1).hashCode()));
        idx += 1;
    }

    @Benchmark
    public void hasher_pc_mix_fx(Blackhole bh) {
        TupleII o = new TupleII(idx, idx+1);
        int hash = hasher.init();
        hash = hasher.write(hash, o.f0());
        hash = hasher.write(hash, o.f1());
        bh.consume(HashUtil.mix((int)hasher.finish(hash)));
        idx += 1;
    }
}
