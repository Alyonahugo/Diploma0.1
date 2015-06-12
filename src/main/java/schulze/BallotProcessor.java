package schulze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import schulze.config.AdvancedConfiguration;

/**
 * Performs ballot registering, storing, processing etc according to
 * configuration
 * 
 * @author
 * 
 * @param <T>
 *          class of candidates to vote for
 */
class BallotProcessor<T> {

	private enum BallotOperation {
		ADD, REMOVE
	}

	private final AdvancedConfiguration configuration;
	private final List<T> registerdCandidates;
	private boolean firstBallotProcessed = false;

	/**
	 * List of all candidates taking part in voting. Can be larger than the list
	 * of registered candidates if dynamic adding of new candidates is allowed in
	 * configuration
	 */
	private final List<T> allCandidates;
	/**
	 * Matrix of distances between candidates
	 */
	private final Matrix matrixD;

	BallotProcessor(AdvancedConfiguration configuration, List<T> candidates) {
		this.configuration = configuration;
		this.registerdCandidates = candidates;
		this.allCandidates = new ArrayList<T>();
		this.matrixD = new Matrix();
	}

	/**
	 * Handles ballot according to configuration
	 * 
	 * @param ballot
	 * @return true if ballot was processed successfully
	 */
	boolean process(Ballot<T> ballot) {
		if (!firstBallotProcessed) {
			for (T candidate : registerdCandidates) {
				allCandidates.add(candidate);
				matrixD.handleCandidateAdded();
			}
			firstBallotProcessed = true;
		}
		if (configuration.isProcessBallotsOnTheFly()) {
			process(ballot, BallotOperation.ADD);
		}
		configuration.getStorage().store(ballot);
		return true;
	}

	/**
	 * Transforms values from ballot and puts them to matrix
	 * 
	 * @param ballot
	 * @param operation
	 */
	private void process(Ballot<T> ballot, BallotOperation operation) {
		final Map<T, Integer> votes = ballot.getVotes();
		final List<T> rankedCandidates = new ArrayList<T>();
		for (T candidate : votes.keySet()) {
			if (registerdCandidates.contains(candidate)) {
				rankedCandidates.add(candidate);
			} else {
				if (configuration.isAddingNewCandidatesAllowed()) {
					rankedCandidates.add(candidate);
					allCandidates.add(candidate);
					matrixD.handleCandidateAdded();
				}
			}
		}
		Collections.sort(rankedCandidates, new Comparator<T>() {
			public int compare(T o1, T o2) {
				return votes.get(o1).compareTo(votes.get(o2));
			}
		});
		for (int i = 0; i < rankedCandidates.size(); i++) {
			for (int j = i + 1; j < rankedCandidates.size(); j++) {
				int x = allCandidates.indexOf(rankedCandidates.get(i));
				int y = allCandidates.indexOf(rankedCandidates.get(j));
				switch (operation) {
					case ADD:
						matrixD.inc(x, y);
						break;
					case REMOVE:
						matrixD.dec(x, y);
						break;
				}
			}
		}
	}

	/**
	 * Removes ballot from storage and cancels its influence on the result
	 * 
	 * @param ballot
	 * @return true if ballot was removed successfully
	 */
	boolean cancel(Ballot<T> ballot) {
		if (configuration.isBallotWithdrawalAllowed()) {
			process(ballot, BallotOperation.REMOVE);
			configuration.getStorage().revoke(ballot);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Creates sorted set of winners. The fist one in the set has the most votes.
	 * The time of this operation depends on the amount of candidates, not on the
	 * amount of votes.
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	Set<T> calculateWinners() {
		if (!configuration.isProcessBallotsOnTheFly()) {
			for (Ballot<?> ballot : configuration.getStorage().getAll()) {
				process((Ballot<T>) ballot);
			}
		}
		final long[][] p = new long[allCandidates.size()][allCandidates.size()];
		for (int i = 0; i < allCandidates.size(); i++) {
			for (int j = 0; j < allCandidates.size(); j++) {
				if (i != j) {
					if (matrixD.get(i, j) > matrixD.get(j, i)) {
						p[i][j] = matrixD.get(i, j);
					} else {
						p[i][j] = 0;
					}
				}
			}
		}
		for (int i = 0; i < allCandidates.size(); i++) {
			for (int j = 0; j < allCandidates.size(); j++) {
				if (i != j) {
					for (int k = 0; k < allCandidates.size(); k++) {
						if (i != k && j != k) {
							p[j][k] = Math.max(p[j][k], Math.min(p[j][i], p[i][k]));
						}
					}
				}
			}
		}
		final TreeSet<T> sortedCandidates = new TreeSet<T>(new Comparator<T>() {
			public int compare(T o1, T o2) {
				return Long.compare(p[allCandidates.indexOf(o2)][allCandidates.indexOf(o1)],
				    p[allCandidates.indexOf(o1)][allCandidates.indexOf(o2)]);
			}
		});
		sortedCandidates.addAll(allCandidates);
		return sortedCandidates;
	}
}
