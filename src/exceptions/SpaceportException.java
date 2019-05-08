package exceptions;

import java.util.Arrays;
import java.util.List;

/**
 * Spaceport Exceptions
 */
public class SpaceportException extends GeneralException {

    // Exception codes
    public static final int INVALID_SPACEPORT_NAME = 0;
    public static final int SPACEPORT_FULL = 1;
    public static final int SPACEPORT_NOT_EXIST = 2;

    // Exception messages
    private final List<String> messages = Arrays.asList("< 001: Spaceport already exists >", "< 002: The Spaceport is FULL >", "< 003: Spaceport not exists >");

    public SpaceportException(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }

}
