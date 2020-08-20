package event;

import domain.CloudBalance;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.solver.event.BestSolutionChangedEvent;
import org.optaplanner.core.api.solver.event.SolverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebugSolverEventListener implements SolverEventListener<CloudBalance> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DebugSolverEventListener.class);

	@Override
	public void bestSolutionChanged(BestSolutionChangedEvent<CloudBalance> event) {
	    HardSoftScore score = (HardSoftScore) event.getNewBestScore();
		LOGGER.debug("New best solution found: (" + score.getHardScore() +"hard/" + score.getSoftScore() + "soft)");
	}
}