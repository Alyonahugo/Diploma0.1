package schulze;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores votes for each candidate. Doesn't do any preprocessing, i.e. it is
 * possible to vote even for unregistered candidate. All
 * unregistered/duplicated/etc candidates are handled by
 * {@link BallotProcessor}
 * */
public class Ballot<T> {
	private final Map<T, Integer> ranks = new HashMap<T, Integer>();
	/**
	 * External data associated with ballot. Can be id or IP or any other
	 * identifier. Used in comparing the ballots
	 */
	private Object data;

	public Ballot() {
		data = ranks;
	}

	/**
	 * Constructs ballot using identifier of who has created this ballot
	 * 
	 * @param data
	 *          any object representing the voter
	 */
	public Ballot(Object data) {
		this();
		this.data = data;
	}

	/**
	 * Puts candidate's rank into internal storage
	 * 
	 * @param candidate
	 *          Candidate to vote for
	 * @param rank
	 *          candidate's rank, the lower - the better
	 * @return this ballot for method chaining
	 */
	public Ballot<T> vote(T candidate, int rank) {
		ranks.put(candidate, rank);
		return this;
	}

	/**
	 * Returns votes as a map of pairs {Candidate, rank}
	 * 
	 * @return
	 */
	public Map<T, Integer> getVotes() {
		return ranks;
	}

	@Override
	public int hashCode() {
		return data.hashCode();
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean equals(Object obj) {
		if (obj instanceof Ballot) {
			Ballot ballotObj = (Ballot) obj;
			return data.equals(ballotObj.data);
		}
		return super.equals(obj);
	}
}
