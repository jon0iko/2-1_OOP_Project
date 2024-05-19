package org.example.salon;

public class UserInputException extends Exception {
    public UserInputException(String message) {
        super(message);
    }
}

class UsernameNotFoundException extends UserInputException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}

class PhoneNumberException extends UserInputException {
    public PhoneNumberException(String message) {
        super(message);
    }
}

class EmailFormatException extends UserInputException {
    public EmailFormatException(String message) {
        super(message);
    }
}
class BlankEmailField extends EmailFormatException {
    public BlankEmailField(String message) {
        super(message);
    }
}

class AbsenceofGmailSuffix extends EmailFormatException {
    public AbsenceofGmailSuffix(String message) {
        super(message);
    }
}

class NotProperlyFormatedEmailPrefix extends EmailFormatException {
    public NotProperlyFormatedEmailPrefix(String message) {
        super(message);
    }
}

class PasswordFormatException extends UserInputException {
    public PasswordFormatException(String message) {
        super(message);
    }
}


