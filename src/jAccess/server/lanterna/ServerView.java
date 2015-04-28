package jAccess.server.lanterna;

import java.nio.charset.Charset;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.terminal.Terminal;

/**
 * Einfaches Google Lanterna Terminal für die Darstellung des Servers
 * @author Nico Fehr, ICT Berufsbildungscenter AG, nico.fehr@bbcag.ch
 * @version 1.0
 */
public class ServerView {

	private static Terminal t;

	public static void main(String[] args) {

		Key taste = null;

		t = TerminalFacade.createTerminal(System.in, System.out, Charset.forName("UTF8"));
		t.enterPrivateMode();

		t.setCursorVisible(true);

		moveCursor();
	}

	/**
	 * Funktion um den Lanterna Cursor mit den Pfeilen zu bewegen
	 * Lassen sich auch mit den Tasten AWSD bewegen.
	 */
	public static void moveCursor() {
		int xLocation = 0;
		int yLocation = 0;
		while (true) {
			Key key = t.readInput();
			if (key != null) {
				if (key.getKind() == Key.Kind.ArrowLeft) {
					t.moveCursor(xLocation - 1, yLocation);
					xLocation--;
				} else if (key.getKind() == Key.Kind.ArrowRight) {
					t.moveCursor(xLocation + 1, yLocation);
					xLocation++;
				} else if (key.getKind() == Key.Kind.ArrowUp) {
					t.moveCursor(xLocation, yLocation - 1);
					yLocation--;
				} else if (key.getKind() == Key.Kind.ArrowDown) {
					t.moveCursor(xLocation, yLocation + 1);
					yLocation++;
				}
			}
		}
	}
	
	/**
	 * Funktion um einen ganzen String auf dem Lanterna Terminal auszugeben
	 * @param zeichenkette
	 */
	private static void putString(String zeichenkette) {
        // Zähle die Positionen von 0 bis zur Länge der Zeichenkette
        for (int position = 0; position < zeichenkette.length(); position++) {
            // Gib das Zeichen an der aktuellen Position aus
            t.putCharacter(zeichenkette.charAt(position));
        }
    }

}
