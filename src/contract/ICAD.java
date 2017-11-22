package contract;

public interface ICAD {
	
	void initCAD();
	boolean write(String trame);
	boolean updateData();
	void close();
}
