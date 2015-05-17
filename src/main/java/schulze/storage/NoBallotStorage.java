package schulze.storage;

import java.util.Collections;
import java.util.List;

import schulze.Ballot;

/**
 * Doesn't store ballots at all
 * 
 * @author Yurii_Shylov
 * 
 */
public class NoBallotStorage implements BallotStorage {

	public void store(Ballot<?> ballot) {
	}

	public void revoke(Ballot<?> ballot) {
	}

	public List<Ballot<?>> getAll() {
		return Collections.emptyList();
	}
}
