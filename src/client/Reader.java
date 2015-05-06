package client;

import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.smartcardio.*;

import server.CardIntf;

/**
 * Ein Programm das Die UID von NFC und Mifare Karten/Tags einlesen kann und
 * diese als Client an einen Server weitersendet.
 * 
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 * 
 */
public class Reader {

	// Uid des Tags und ID des Angestellten
	private static String uid;

	/**
	 * Hauptprogramm: Führt Kartenleseprogramm aus.
	 * 
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {

		// Überprüft angeschlossene Reader
		try {
			if (TerminalFactory.getDefault().terminals().list().size() == 0) {
				// Wenn keine Reader angeschlossen
				System.err.println("Kein Reader angeschlossen!");
			}
			System.out.println("Reader gestartet");
		} catch (CardException e1) {
			e1.printStackTrace();
		}

		// CardTerminal
		CardTerminal terminal = null;
		try {
			// Nimmt ersten Reader des Terminals
			terminal = TerminalFactory.getDefault().terminals().list().get(0);
		} catch (CardException e1) {
			e1.printStackTrace();
		}

		try {
			while (true) {
				// Wartet bis Karte sofort hingehalten wird
				if (terminal.waitForCardPresent(0)) {
					// Karte wird verbunden
					// connect(protokoll) * = Alle Protokolle (T=1(neuere
					// Geräte/Tags) & T=0(ältere Geräte/Tags))
					Card card = terminal.connect("*");

					// Befehl der an den Tag gesendet wird um die Uid
					// auszulesen:
					// FFCA0000
					byte[] command = new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00 };

					CardChannel cc = card.getBasicChannel();

					// Die Uid wird gesetzt mit der Antwort der Karte
					setUid(send(command, cc));

					// Uid wird Client über Uid Instanz gesendet
					try {
						// Uid wird noch einmal ausgegeben
						System.out.println(getUid());

						newTag(getUid());
					} catch (MalformedURLException | NotBoundException e) {
						e.printStackTrace();
					}
					// Terminal diconnected Karte
					terminal.waitForCardAbsent(0);
				}
			}
		} catch (CardException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Wenn ein neuer Tag erkannt wird, sendet diese Funktion die Uid als
	 * {@code send} dem Server zu.
	 * 
	 * @param send
	 * @throws MalformedURLException
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private static void newTag(String uid) throws MalformedURLException, RemoteException, NotBoundException {
		CardIntf serverobj = (CardIntf) Naming.lookup("//localhost/CardServer");
		try {
			serverobj.receiveUid(uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to insert Time!");
			e.printStackTrace();
		}
		System.err.println(serverobj.getResponse());
	}

	/**
	 * Wandelt den Hexadezimalen Wert der Antwort der Karte in einen String um.
	 * 
	 * @param cmd
	 * @param channel
	 * @return Gibt Uid als fomratierten String zurück.
	 */
	public static String send(byte[] cmd, CardChannel channel) {

		String res = "";

		byte[] baResp = new byte[258];
		ByteBuffer bufCmd = ByteBuffer.wrap(cmd);
		ByteBuffer bufResp = ByteBuffer.wrap(baResp);

		// output = The length of the received response APDU
		int output = 0;

		try {
			output = channel.transmit(bufCmd, bufResp);
		} catch (CardException ex) {
			ex.printStackTrace();
		}

		for (int i = 0; i < output - 2; i++) {
			res += String.format("%02X", baResp[i]);
			// Resultat ist als Hex Integer formatiert.
		}

		return res;
	}

	// Getter und Setter Methoden
	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		Reader.uid = uid;
	}
}