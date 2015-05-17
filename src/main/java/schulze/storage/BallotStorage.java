package schulze.storage;

import java.util.List;

import schulze.Ballot;

/**
 * Stores registered ballots
 * 
 * @author Yurii_Shylov
 * 
 */
public interface BallotStorage {

	/**
	 * Returns all stored ballots
	 * 
	 * @return
	 */
	List<Ballot<?>> getAll();

	/**
	 * Saves ballot in the storage
	 * 
	 * @param ballot
	 */
	void store(Ballot<?> ballot);

	/**
	 * Removes ballot from storage
	 * 
	 * @param ballot
	 */
	void revoke(Ballot<?> ballot);
}
