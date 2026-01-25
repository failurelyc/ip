package retupmoc;

/**
 * This class is thrown if an exception occurs due to bad user input.
 * This is a checked exception and explicit exception handling is required.
 */
public class RetupmocException extends Exception {

    /**
     * Constructions a new exception with a message.
     * @param s The exception message.
     */
    public RetupmocException(String s) {
        super(s);
    }

}
