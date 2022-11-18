package yeamy.sql;

/**
 * Implement this interface if you want to do something after the row read.
 */
public interface DsObserver {

    /**
     * this method will be invoke after the row read (every field is ready)
     */
	void onDsFinish();
}
