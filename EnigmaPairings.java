import java.util.ArrayList;

/**
 * This class represents the pairings of the enigma machine
 */
public class EnigmaPairings {


    private Pair enigmaPair;
    private ArrayList<Pair> listOfPairs = new ArrayList<Pair>();

    public EnigmaPairings() {}

    /**
     * Method which adds pairs to the list of pairs
     * @param letter1 the first letter of the pair
     * @param letter2 the second letter of the pair
     */
    public void addPair(String letter1, String letter2) throws InvalidFileException {
        enigmaPair = new Pair(letter1, letter2);
        boolean inList = false;
        
        for (Pair p : listOfPairs) {
            if (p.getFirstLetter().equals(enigmaPair.getFirstLetter()) || p.getSecondLetter().equals(enigmaPair.getFirstLetter()) || p.getSecondLetter().equals(enigmaPair.getSecondLetter())) {
                throw new InvalidFileException();
            }
        }

        if (!inList) {
            listOfPairs.add(enigmaPair);
        }
    }

    /**
     * Method which finds the pair of character c
     * @param c the character to find the pair for
     * @return the pair of the character or the character itself if it does not have a pair
     */
    public String findPair(Character c) {
        String element = c.toString().toUpperCase();

        for (Pair p : listOfPairs) {
            if (element.equals(p.getFirstLetter())) {
                return p.getSecondLetter();
            }
        }

        for (Pair p : listOfPairs) {
            if (element.equals(p.getSecondLetter())) {
                return p.getFirstLetter();
            }
        }

        return element;
    }
    
    /**
     * Class used for enigma pairings
     */
    class Pair {

        private String letter1;
        private String letter2;

        /**
         * Consctutor for enigma pairings
         * @param letter1 the first letter of the pair
         * @param letter2 the second letter of the pair
         */
        public Pair(String letter1, String letter2) throws InvalidFileException {
            if (letter1.length() == 1 || letter2.length() == 1) {
                if (!letter1.equals(letter2)) {
                    this.letter1 = letter1;
                    this.letter2 = letter2;
                } else {
                    throw new InvalidFileException();
                }
            } else {
                throw new InvalidFileException();
            }
        }

        /**
         * Method which returns the first letter of the pair
         * @return the first letter of the pair
         */
        public String getFirstLetter() {
            return letter1;
        }

        /**
         * Method which returns the second letter of the pair
         * @return the second letter of the pair
         */
        public String getSecondLetter() { 
            return letter2;
        }
    }
}
