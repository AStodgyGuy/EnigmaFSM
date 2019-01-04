import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class which reads the settings files for the enigma machine
 */
public class SettingsReader {

    public SettingsReader() {}

    /**
     * Method which reads the settings file for the rotors
     * @param pathToFile the location of the settings file
     * @return the EnigmaSettings object which represents the rotor settings
     */
    public EnigmaSettings readSettings(String pathToFile) throws InvalidFileException, IOException {
        EnigmaSettings es = new EnigmaSettings();
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        String line = br.readLine();
        while (line != null) {
            String[] array = line.split(" ");
            es = new EnigmaSettings(convertStringToInt(array[0]), convertStringToInt(array[1]), convertStringToInt(array[2]));
            line = br.readLine();       
        }

        return es;
    }

    /**
     * Method which reads the pairs of letters that are connected
     * @param pathToFile the location of the pairings file
     * @return the EnigmaPairings object which represents the pairs of letters
     */
    public EnigmaPairings readPairings(String pathToFile) throws InvalidFileException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(pathToFile));
        EnigmaPairings ep = new EnigmaPairings();
        String line = br.readLine();
        
        while (line != null) {
            String[] array = line.split(" ");
            if (array.length > 2) {
                throw new InvalidFileException();
            } else {
                ep.addPair(array[0], array[1]);
            }
            line = br.readLine();
        }
        
        return ep;
    }

    /**
     * Method which converts String to integer
     * @param stringToConvert the string that needs converting
     * @return the converted string
     */
    private int convertStringToInt(String stringToConvert) throws InvalidFileException {
        int convertedInteger = -1;

        try {
            convertedInteger = Integer.parseInt(stringToConvert);
        } catch (NumberFormatException e) {
            throw new InvalidFileException();
        }

        return convertedInteger;
    }
}