package jAccess.client;

public class UserClient {
	
	// Variabeln deklarieren
	private String kuerzel;

	private boolean isOnline() {
		return false;
	}

	
	// Getter und Setter Methoden
	public String getKuerzel() {
		return kuerzel;
	}

	public void setKuerzel(String kuerzel) {
		this.kuerzel = kuerzel;
	}

}
