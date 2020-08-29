package benchmark;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.optaplanner.core.api.solver.SolverFactory;

import domain.CloudBalance;

@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3)
@Measurement(iterations = 10)
@Fork(value = 2, warmups = 7,jvmArgs = { "-Xms20g", "-Xmx20g"})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class BuildSolverBenchmark {

    @Param({ "1", "2", "5", "10", "100", "1000" })
    int iterations;

    SolverFactory<CloudBalance> solverFactoryCs;
    SolverFactory<CloudBalance> solverFactoryDrl;

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Setup
    public void setupFactory() {

        solverFactoryCs = SolverFactory.createFromXmlResource("solver/cloudBalancingSolverCSConfig.xml");
        solverFactoryDrl = SolverFactory.createFromXmlResource("solver/cloudBalancingSolverConfig.xml");
    }

    @Benchmark
    public Object buildSolverFactoryCsMeasure(BuildSolverBenchmark buildSolverBenchmark, Blackhole blackhole) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(buildSolverBenchmark.iterations)
                .forEach(i -> blackhole.consume(buildSolverBenchmark.solverFactoryCs.buildSolver()));
        return new Object();
    }

    @Benchmark
    public Object buildSolverFactoryDrlMeasure(BuildSolverBenchmark buildSolverBenchmark,Blackhole blackhole) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(buildSolverBenchmark.iterations)
                .forEach(i -> blackhole.consume(buildSolverBenchmark.solverFactoryDrl.buildSolver()));
        return new Object();
    }
}
