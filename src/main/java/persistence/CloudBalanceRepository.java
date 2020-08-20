package persistence;

import java.io.File;

import domain.CloudBalance;
import domain.CloudComputer;
import domain.CloudProcess;
import org.optaplanner.persistence.xstream.impl.domain.solution.XStreamSolutionFileIO;

public class CloudBalanceRepository {

    public static CloudBalance load(File inputSolutionFile) {
        XStreamSolutionFileIO<CloudBalance> solutionFileIO = new XStreamSolutionFileIO<>(CloudBalance.class,
                                                                                         CloudProcess.class, CloudComputer.class);
        return solutionFileIO.read(inputSolutionFile);
    }
}