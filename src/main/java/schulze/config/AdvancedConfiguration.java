package schulze.config;

/**
 * Stores very specific configuration of the library. Changing any of these
 * properties may lead to exception or incorrect calculations. Use at your own
 * risks.
 * 
 * @author Yurii_Shylov
 * 
 */
public class AdvancedConfiguration extends Configuration {
	public AdvancedConfiguration() {
		this(new Configuration());
	}

	public AdvancedConfiguration(Configuration configuration) {
		setAddingNewCandidatesAllowed(configuration.isAddingNewCandidatesAllowed());
		setBallotWithdrawalAllowed(configuration.isBallotWithdrawalAllowed());
		setProcessBallotsOnTheFly(configuration.isProcessBallotsOnTheFly());
		setStorage(configuration.getStorage());
	}
}
