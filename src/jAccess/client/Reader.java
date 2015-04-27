package jAccess.client;

import jAccess.server.CardIntf;

import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.smartcardio.*;

public class Reader {
	
	// Uid des Tags und ID des Angestellten
	private static String uid;

	public static void main(String[] args) throws RemoteException {
 
		// Überprüft angeschlossene Reader
		try {
			if (TerminalFactory.getDefault().terminals().list().size() == 0) {
				// Wenn keine Reader angeschlossen
				System.err.println("Kein Reader angeschlossen!");
			}
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
					// connect(protokoll) * = Alle Protokolle (T=1(neuere Geräte/Tags) & T=0(ältere Geräte/Tags))
					Card card = terminal.connect("*");

					// Befehl der an den Tag gesendet wird um die Uid auszulesen:
					// FFCA0000
					byte[] command = new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00 };

					CardChannel cc = card.getBasicChannel();

					// Die Uid wird gesetzt mit der Antwort der Karte
					setUid(send(command, cc));
					
					// Uid wird Client über Uid Instanz gesendet
					try {
						newTag(getUid());
					} catch (MalformedURLException | NotBoundException e) {
						e.printStackTrace();
					}
					// Uid wird noch einmal ausgegeben
					System.out.println(getUid());

					// Terminal diconnected Karte
					terminal.waitForCardAbsent(0);
				}
			}
		} catch (CardException e) {
			e.printStackTrace();
		}
	}

	private static void newTag(String send) throws MalformedURLException, RemoteException, NotBoundException {
		CardIntf serverobj = (CardIntf) Naming.lookup("//localhost/CardServer");
		serverobj.receiveUid(send);
	}

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

	public static String getUid() {
		return uid;
	}

	public static void setUid(String uid) {
		Reader.uid = uid;
	}
}
