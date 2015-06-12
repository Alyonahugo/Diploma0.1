package schulze.config;

import schulze.storage.BallotStorage;
import schulze.storage.MemoryBallotStorage;

/**
 * Stores common configurable values.
 * 
 * @author
 */
public class Configuration {
	private BallotStorage storage = new MemoryBallotStorage();
	private boolean ballotWithdrawalAllowed = false;
	private boolean addingNewCandidatesAllowed = true;
	private boolean processBallotsOnTheFly = true;

	public BallotStorage getStorage() {
		return storage;
	}

	public void setStorage(BallotStorage storage) {
		this.storage = storage;
	}

	public boolean isBallotWithdrawalAllowed() {
		return ballotWithdrawalAllowed;
	}

	public void setBallotWithdrawalAllowed(boolean ballotWithdrawalAllowed) {
		this.ballotWithdrawalAllowed = ballotWithdrawalAllowed;
	}

	public boolean isAddingNewCandidatesAllowed() {
		return addingNewCandidatesAllowed;
	}

	public void setAddingNewCandidatesAllowed(boolean addingNewCandidatesAllowed) {
		this.addingNewCandidatesAllowed = addingNewCandidatesAllowed;
	}

	public boolean isProcessBallotsOnTheFly() {
		return processBallotsOnTheFly;
	}

	public void setProcessBallotsOnTheFly(boolean processBallotsOnTheFly) {
		this.processBallotsOnTheFly = processBallotsOnTheFly;
	}
}
