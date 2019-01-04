/**
 * This class represents a simple Finite State Machine (FSM)
 */

public class FSM {

    private EnigmaPairings ep;
    private EnigmaSettings es;
    private StringBuilder sb = new StringBuilder();

    public FSM(EnigmaSettings settings, EnigmaPairings pairings) {
        this.ep = pairings;
        this.es = settings;
    }

    /**
     * Method which outputs a letter based on the enigma rules
     * @param s the input string seen
     */
    public void input(String s) throws InvalidCommandLineArgumentException, UnrecognisedCharacterException {
        char[] inputArray = s.toCharArray();

        String pair;
        String cipher;

        for (Character c : inputArray) {
            es.incrementRotor();
            pair = ep.findPair(c);
            cipher = es.cipher(pair);
            pair = ep.findPair(cipher.charAt(0));
            
            sb.append(pair);
        }
    }

    /**
     * Method which returns the ciphered string
     * @return the ciphered string
     */
    public String getOutput() {
        return sb.toString();
    }
}
