package contract;

public interface ICAD extends Runnable {
	
	void init();
	boolean write(String trame);
	boolean updateData();
	void close();
}
