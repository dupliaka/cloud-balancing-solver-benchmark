package benchmark;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.optaplanner.core.api.solver.SolverFactory;

import domain.CloudBalance;

@BenchmarkMode(Mode.AverageTime)
@Fork(value = 2, warmups = 5)
@Warmup(iterations = 7)
@Measurement(iterations = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class BuildSolverBenchmark {

    private SolverFactory<CloudBalance> solverFactoryCs =
            SolverFactory.createFromXmlResource("solver/cloudBalancingSolverCSConfig.xml");
    private SolverFactory<CloudBalance> solverFactoryDrl =
            SolverFactory.createFromXmlResource("solver/cloudBalancingSolverConfig.xml");

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    public Object buildSolverFactoryCs(int iterations) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(iterations)
                .forEach(i -> solverFactoryCs.buildSolver());
        return new Object();
    }

    public Object buildSolverFactoryDrl( int iterations) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(iterations)
                .forEach(i -> solverFactoryDrl.buildSolver());
        return new Object();
    }
    @Benchmark
    @OperationsPerInvocation(10)
    public void buildSolverFactoryCsMeasure_10(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryCs(10));
    }

    @Benchmark
    @OperationsPerInvocation(10)
    public void buildSolverFactoryDrlMeasure_10(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryDrl(10));
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public void buildSolverFactoryCsMeasure_100(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryCs(100));
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public void buildSolverFactoryDrlMeasure_100(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryDrl(100));
    }

    @Benchmark
    @OperationsPerInvocation(500)
    public void buildSolverFactoryCsMeasure_500(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryCs(500));
    }

    @Benchmark
    @OperationsPerInvocation(500)
    public void buildSolverFactoryDrlMeasure_500(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryDrl(500));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void buildSolverFactoryCsMeasure_1000(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryCs(1000));
    }

    @Benchmark
    @OperationsPerInvocation(1000)
    public void buildSolverFactoryDrlMeasure_1000(Blackhole blackhole) {
        blackhole.consume( buildSolverFactoryDrl(1000));
    }

}
