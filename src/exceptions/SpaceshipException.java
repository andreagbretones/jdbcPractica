package exceptions;

import java.util.Arrays;
import java.util.List;

public class SpaceshipException extends GeneralException {

    // Exception codes
    public static final int INVALID_SPACESHIP_NAME = 0;
    public static final int INVALID_SPACESHIP_NOT_EXISTS = 1;
    public static final int SPACESHIP_BROKEN = 2;
    public static final int SPACESHIP_LANDED = 3;
    public static final int SPACESHIP_FLYING = 4;
    public static final int WRONG_STATUS = 5;
    public static final int NOT_FLYING = 6;


    // Exception messages
    private final List<String> messages = Arrays.asList("< 001: Spaceship already exists >", "< 002: Spaceship does not exist >", "< 003: Spaceship is BROKEN >", "< 004: Spaceship is LANDED >", "< 005: Spaceship is FLYING >", "< 006: WRONG status. Flights need maintenance every 15 flights. Finish maintenance before flying", "< 007: Spaceship is not FLYING >");

    public SpaceshipException(int code) {
        super(code);
    }

    @Override
    public String getMessage() {
        return messages.get(getCode());
    }

}

