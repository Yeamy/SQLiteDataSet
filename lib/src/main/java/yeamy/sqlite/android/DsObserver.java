package yeamy.sqlite.android;

/**
 * Implement this interface if you want to do something after the row read.
 */
public interface DsObserver {

	/**
	 * This method will be invoked after the row read (every field is ready)
	 */
	void onDsFinish();
}
