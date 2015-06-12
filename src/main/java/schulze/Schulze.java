package schulze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import schulze.config.AdvancedConfiguration;
import schulze.config.Configuration;

/**
 * Voting system which uses Schulze algorithm. Check details of it at <a
 * href="http://en.wikipedia.org/wiki/Schulze_method">Wikipedia</a>
 * 
 * @author
 * 
 * @param <T>
 *          class of candidates to vote for
 */
public class Schulze<T> {

   public List<T> getRegisterdCandidates() {
        return registerdCandidates;
    }

	/**
	 * Represents internal phase of voting
	 */
	private enum Phase {
		REGISTERING_CANDIDATES, VOTING, VOTING_CLOSED
	}

	/**
	 * Stores configuration values. Can't be changed from outside of the class.
	 */
	private final AdvancedConfiguration configuration;

	/**
	 * List of preregistered candidates for voting. Depending on the configuration
	 * the voting system may omit any votes for candidates not present in this
	 * list
	 */
	private final List<T> registerdCandidates;

   

	/**
	 * Processes new ballots.
	 */
	private final BallotProcessor<T> processor;

	/**
	 * Current phase of voting
	 */
	private Phase phase;

	/**
	 * Short title or question describing the survey.
	 */
	private String title;

	/**
	 * Long description of the survey.
	 */
	private String description;

	/**
	 * Creates voting system with default configuration (see
	 * {@link schulze.config.Configuration})
	 */
	public Schulze() {
		this(new AdvancedConfiguration());
	}

	/**
	 * Creates voting system with given configuration. Note that configuration
	 * values are copied, i.e. any changes to it won't affect the system behavior
	 * 
	 * @param configuration
	 *          {@link schulze.config.Configuration} or
	 *          {@link schulze.config.AdvancedConfiguration}
	 */
	public Schulze(Configuration configuration) {
		if (configuration instanceof AdvancedConfiguration) {
			this.configuration = (AdvancedConfiguration) configuration;
		} else {
			this.configuration = new AdvancedConfiguration(configuration);
		}
		registerdCandidates = new ArrayList<T>();
		processor = new BallotProcessor<T>(this.configuration, registerdCandidates);
		phase = Phase.REGISTERING_CANDIDATES;
	}

	/**
	 * Registers candidate to be voted for. Does nothing if the phase is not
	 * {@link Schulze.Phase.REGISTERING_CANDIDATES}
	 * 
	 * @param candidate
	 * @return registered candidate
	 */
	public T addCandidate(T candidate) {
		if (phase == Phase.REGISTERING_CANDIDATES) {
			registerdCandidates.add(candidate);
		}
		return candidate;
	}

	/**
	 * Registers candidates to be voted for. Does nothing if the phase is not
	 * {@link Schulze.Phase.REGISTERING_CANDIDATES}
	 * 
	 * @param candidates
	 */
	public void addAllCandidates(List<T> candidates) {
		for (T candidate : candidates) {
			addCandidate(candidate);
		}
	}

	/**
	 * Passes ballot to ballot processor
	 * 
	 * @param ballot
	 * @return false if ballot was not processed
	 */
	public boolean registerBallot(Ballot<T> ballot) {
		if (phase != Phase.VOTING_CLOSED) {
			phase = Phase.VOTING;
			return processor.process(ballot);
		} else {
			return false;
		}
	}

	/**
	 * Unregisters ballot, i.e. its values won't influence the results
	 * 
	 * @param ballot
	 * @return false if unregistering failed
	 */
	public boolean unregisterBallot(Ballot<T> ballot) {
		return processor.cancel(ballot);
	}

	/**
	 * Stops accepting any new ballots
	 */
	public void stopVoting() {
		phase = Phase.VOTING_CLOSED;
	}

	/**
	 * Calculates and returns top candidate
	 * 
	 * @return
	 */
	public T getWinner() {
		return processor.calculateWinners().iterator().next();
	}

	/**
	 * Calculates and returns all candidate in ranked order
	 * 
	 * @return
	 */
	public Set<T> getWinners() {
		return processor.calculateWinners();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
