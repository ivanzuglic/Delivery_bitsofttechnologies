package Database;

public class KlijentDAO {

	private String userDB;
	private String passwDB;
	private String host;
	
	public KlijentDAO () {
		this.userDB = "myuser";
		this.passwDB = "abc";
		this.host = "jdbc:mysql://localhost:3306/dostavljaona?useSSL=false&useLegacyDatetimeCode=false";
	}
	
	public dohvatiAktivnuNarudzbu (String korisnickoIme) {
		
		
	}
}
