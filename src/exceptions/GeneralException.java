package exceptions;

public abstract class GeneralException extends Exception {

    private final int code;


    public GeneralException(int code) {
        this.code = code;
    }

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public int getCode() {
        return code;
    }

}
