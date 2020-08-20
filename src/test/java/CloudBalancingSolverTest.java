import java.io.File;

import domain.CloudBalance;
import event.DebugSolverEventListener;
import org.junit.Test;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import persistence.CloudBalanceRepository;

/**
 * CloudBalancingSolverTest
 */
public class CloudBalancingSolverTest {

    @Test
    public void testSolver() {
        //Create the solver from our solver configuration.
        SolverFactory<CloudBalance> solverFactory = SolverFactory.createFromXmlResource("solver/cloudBalancingSolverConfig.xml");
        Solver<CloudBalance> solver = solverFactory.buildSolver();
        solver.addEventListener(new DebugSolverEventListener());

        //Loud a problem into the PlanningSolution
        File inputFile = new File("data/cloudbalancing/unsolved/100computers-300processes.xml");
        CloudBalance cloudBalance = CloudBalanceRepository.load(inputFile);

        //Run the solver.
        solver.solve(cloudBalance);
    }
}
