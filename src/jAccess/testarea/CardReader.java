package jAccess.testarea;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;

import javax.smartcardio.*;

public class CardReader {
	
	private static CardClient client = new CardClient();
	
	public static void main(String[] args) throws RemoteException {
		
		// Überprüft angeschlossene Reader
		try {
			if (TerminalFactory.getDefault().terminals().list().size() == 0) {
				System.err.println("Kein Reader angeschlossen!");
			}
		} catch (CardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// CardTerminal
		CardTerminal terminal = null;
		try {
			terminal = TerminalFactory.getDefault().terminals().list().get(0);
		} catch (CardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			while (true) {
				if (terminal.waitForCardPresent(0)) {
					Card card = terminal.connect("*");
					ATR atr = card.getATR();
					
					byte[] command = new byte[] { (byte) 0xFF,
							(byte) 0xCA, (byte) 0x00, (byte) 0x00 };
					
					CardChannel cc = card.getBasicChannel();
					
					// Uid wird Client über Uid Instanz gesendet
					client.setUid(send(command, cc));
					// Uid wird noch einmal ausgegeben
					System.out.println(client.getUid());
					
					// Terminal diconnected Karte
					terminal.waitForCardAbsent(0);
				}
			}
		} catch (CardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			// The result is formatted as a hexadecimal integer
		}
		
		return res;
	}
}