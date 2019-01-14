package Database;

public class ZadatakDAO {
	private String userDB;
	private String passwDB;
	private String host;
	
	public ZadatakDAO () {
		
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public void postaviZadGotov() {
		
	}
}
