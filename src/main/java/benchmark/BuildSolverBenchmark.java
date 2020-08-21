package benchmark;

import java.io.IOException;
import java.util.stream.Stream;

import domain.CloudBalance;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.optaplanner.core.api.solver.SolverFactory;

@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
public class BuildSolverBenchmark {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    public void buildSolverFactoryCs(SolverFactoryConfigured solverFactoryConfigured) {
        Stream
                .iterate(0, i -> i + 1)
                .limit(solverFactoryConfigured.iterations)
                .forEach(i -> solverFactoryConfigured.solverFactoryCs.buildSolver());
    }

    @Benchmark
    public void buildSolverFactoryDrl(SolverFactoryConfigured solverFactoryConfigured) {
        Stream.
                iterate(0, i -> i + 1)
                .limit(solverFactoryConfigured.iterations)
                .forEach(i -> solverFactoryConfigured.solverFactoryDrl.buildSolver());
    }

    @State(Scope.Benchmark)
    public static class SolverFactoryConfigured {

        public SolverFactory<CloudBalance> solverFactoryCs;
        public SolverFactory<CloudBalance> solverFactoryDrl;

        @Param({"10", "100", "500", "1000"})
        public int iterations;

        @Setup
        public void setupFactory() {
            solverFactoryCs = SolverFactory.createFromXmlResource("solver/cloudBalancingSolverCSConfig.xml");
            solverFactoryDrl = SolverFactory.createFromXmlResource("solver/cloudBalancingSolverConfig.xml");
        }
    }
}
