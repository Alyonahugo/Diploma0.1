package schulze.storage;

import java.util.ArrayList;
import java.util.List;

import schulze.Ballot;

/**
 * Stores ballots in memory.
 * 
 * @author Yurii_Shylov
 * 
 */
public class MemoryBallotStorage implements BallotStorage {

	private List<Ballot<?>> storage = new ArrayList<Ballot<?>>();

	public void store(Ballot<?> ballot) {
		storage.add(ballot);
	}

	public void revoke(Ballot<?> ballot) {
		storage.remove(ballot);
	}

	public List<Ballot<?>> getAll() {
		return storage;
	}
}
