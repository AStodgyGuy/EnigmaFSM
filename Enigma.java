import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * This class the main implementation for the fsa interpreter
 */

public class Enigma {

	/**
	 * Main method
	 * @param args - inputs
	 */
	public static void main(String args[]) {
		//args length is not valid
		if (args.length != 3) {
			System.out.println("Usage: java Enigma <enigmasettings.txt> <letterpairings.txt> <word to encode>");
		} else {
            runEnigma(args);
        }
    }
    
    /**
     * Method which runs the enimga FSM
     * @param args the arguments from main
     */
    public static void runEnigma(String args[]) {
        SettingsReader sr = new SettingsReader();

        try {
            EnigmaSettings settings = sr.readSettings(args[0]);
            EnigmaPairings pairings = sr.readPairings(args[1]);
            FSM fsm = new FSM(settings, pairings);

            fsm.input(args[2]);

            System.out.println(fsm.getOutput());
                        
        } catch (InvalidCommandLineArgumentException e) {
            System.out.println("Usage: java Enigma <enigmasettings.txt> <letterpairings.txt> <word to encode>");
        } catch (InvalidFileException e) {
            System.out.println("Invalid enigmasettings or letterpairings file");
        } catch (UnrecognisedCharacterException e) {
            System.out.println("Unrecognised input, this program only understands letters between A-Z");
        } catch (IOException e) {
            System.out.println("Invalid enigmasettings or letterpairings file");
        }

    }
}
