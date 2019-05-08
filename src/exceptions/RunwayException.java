package exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Runway Exceptions
 */
public class RunwayException extends GeneralException {

    // Exception codes
    public static final int INVALID_RUNWAY_EXISTS = 0;
    public static final int INVALID_RUNWAY_NOT_EXISTS = 1;
    public static final int RUNWAY_BUSY = 2;
    public static final int RUNWAY_CLEANING = 3;
    public static final int NUMBER = 4;
    public static final int STRING = 5;
    public static final int NO_PERTENECE = 6;
    public static final int NOT_FREE = 7;


    // Exception messages
    private final List<String> messages = Arrays.asList("< 001: Runway already exists >", "< 002: Runway does not exist >", "< 003: Runway is busy >", "< 004: Runway is not clean >", "< 005: Value must be a number >", "< 006: Value must be a String >", "< 007: Runway does not belong to the spaceport provided >", "< 008: Runway is not FREE >");

    public RunwayException(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }

}
