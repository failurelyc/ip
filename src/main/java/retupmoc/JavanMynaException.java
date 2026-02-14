package retupmoc;

/**
 * This class is thrown if an exception occurs due to bad user input.
 * This is a checked exception and explicit exception handling is required.
 */
public class JavanMynaException extends Exception {

    /**
     * Constructions a new exception with a message.
     * @param s The exception message.
     */
    public JavanMynaException(String s) {
        super(s);
    }

}
