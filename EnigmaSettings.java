import java.util.LinkedList;
import java.util.Queue;

import javax.xml.stream.events.Characters;

/**
 * This class represents the rotor settings of the enigma machine
 */
public class EnigmaSettings {


    private String alphabet =               "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    private String firstRotorSettings =     "EKMFLGDQVZNTOWYHXUSPAIBRCJ"; //rotor I
    private String secondRotorSettings =    "AJDKSIRUXBLHWTMCQGZNPYFVOE"; //rotor II
    private String thirdRotorSettings =     "BDFHJLCPRTXVZNYEIWGAKMUSQO"; //rotor III
    private String reflectorString =        "YRUHQSLDPXNGOKMIEBFZCWVJAT"; //reflector B
    private int firstRotorNotch = 17;
    private int secondRotorNotch = 5;
    private int thirdRotorNotch = 21;
    private int firstRotor = 0;
    private int secondRotor = 0;
    private int thirdRotor = 0;

    /**
     * Constructor for EnigmaSettings class
     * @param firstRotor represents the slow rotor in an enigma machine
     * @param secondRotor represents the not-fast-not-slow rotor in an enimga machine
     * @param thirdRotor represents the fast rotor in an enigma machine
     */
    public EnigmaSettings(int firstRotor, int secondRotor, int thirdRotor) throws InvalidFileException {

        if (firstRotor > 26 || secondRotor > 26 || thirdRotor > 26 || firstRotor < 0 || secondRotor < 0 || thirdRotor < 0) {
            throw new InvalidFileException();
        } else {
            this.firstRotor = firstRotor;
            this.secondRotor = secondRotor;
            this.thirdRotor = thirdRotor;
            setRotors();
        }
    }

    /**
     * Empty constructor for EnigmaSettings class
     */
    public EnigmaSettings() {}

    /**
     * This method takes a string and ciphers it using the rotors
     * @param s the character to be ciphered
     * @return the ciphered character
     */
    public String cipher(String s) throws UnrecognisedCharacterException {

        int index;
        char c;
        try {
            s = s.toUpperCase();
            index = alphabet.indexOf(s.charAt(0));
            c = thirdRotorSettings.charAt(index);
            index = alphabet.indexOf(c);
            c = secondRotorSettings.charAt(index);
            index = alphabet.indexOf(c);
            c = firstRotorSettings.charAt(index);
            index = alphabet.indexOf(c);
            c = reflectorString.charAt(index);
            index = firstRotorSettings.indexOf(c);
            c = alphabet.charAt(index);
            index = secondRotorSettings.indexOf(c);
            c = alphabet.charAt(index);
            index = thirdRotorSettings.indexOf(c);
        } catch (StringIndexOutOfBoundsException e) {
            throw new UnrecognisedCharacterException();
        }


        return Character.toString(alphabet.charAt(index));
    }

    /**
     * Method which increments the rotor
     */
    public void incrementRotor() {
        //third rotor increments every time
        thirdRotor++;
        thirdRotorSettings = rotorToChange(1, thirdRotorSettings.toCharArray());
        //change second rotor when third rotor transitions from V - W
        if (thirdRotor - thirdRotorNotch == 0) {
            secondRotor++;
            secondRotorSettings = rotorToChange(1, secondRotorSettings.toCharArray());
            if (secondRotor - secondRotorNotch == 0) {
                firstRotor++;
                firstRotorSettings = rotorToChange(1, firstRotorSettings.toCharArray());
                if (firstRotor - firstRotorNotch == 0) {
                    //nothing happens when first rotor reaches its notch
                }
            }
        }

        validateNumbers();
    }

    /**
     * Method which validates numbers so that no number is above 26
     */
    private void validateNumbers() {
        if (thirdRotor == 26) {
            thirdRotor = 0;
        }

        if (secondRotor == 26) {
            secondRotor = 0;
        }

        if (firstRotor == 26) {
            firstRotor = 0;
        }
    }

    /**
     * Method which configures the rotors
     */
    private void setRotors() {
        firstRotorSettings = rotorToChange(firstRotor, firstRotorSettings.toCharArray());
        secondRotorSettings = rotorToChange(secondRotor, secondRotorSettings.toCharArray());
        thirdRotorSettings = rotorToChange(thirdRotor, thirdRotorSettings.toCharArray());
    }

    /**
     * Method which reverses the rotor by the set amount
     * @param rotor the rotor to change
     */
    private String rotorToChange(int rotor, char[] array) {
        Object head;
        Queue queue = new LinkedList<Character>();

        for (int i = 0; i < array.length; i++){
            queue.add(array[i]);
        }

        for (int i = 0; i < rotor; i++) {
            head = queue.remove();
            queue.add(head);
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            sb.append(queue.remove());
        }

        return sb.toString();

    }
}
