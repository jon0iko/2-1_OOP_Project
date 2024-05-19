package org.example.salon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidator {


    public static void validateSignUpInfo(String username, String fullName, String phoneNo, String email, String password, String confirmPassword) throws UserInputException {
        if (!isValidUsername(username)) {
            throw new UsernameNotFoundException("Invalid username format.");
        }

        if (!isValidFullname(fullName)) {
            throw new UserInputException("Invalid full name format.");
        }

        if (!isValidPhoneNumber(phoneNo)) {
            throw new PhoneNumberException("Invalid phone number format.");
        }

        if (!isValidEmail(email)) {
            throw new EmailFormatException("Invalid email format.");
        }

        if (!isValidPassword(password)) {
            throw new PasswordFormatException("Password must be at least 8 characters long");
        }

        if (!passwordMatch(password, confirmPassword)) {
            throw new UserInputException("Passwords do not match.");
        }
    }

    public static boolean isValidFullname(String username) {
        String regex = "^[a-zA-Z\\s]+$";
        return username.matches(regex);
    }

    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        return username.matches(regex);
    }

    public static boolean isValidPassword(String password) {
        return password.length()==8;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        //starts with +880

        String regex = "^\\+?[0-9]+$";
        return phoneNumber.matches(regex);
    }

    public static boolean isValidEmail(String email) throws BlankEmailField {
        // String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        // return email.matches(regex);
        try {
            if (email.isEmpty()) {
                throw new BlankEmailField("Email address field is blank.");
            }

            if (!email.endsWith("@gmail.com")) {
                throw new AbsenceofGmailSuffix("Email address does not have @gmail.com suffix.");
            }

            int atIndex = email.indexOf('@');
            if (atIndex == -1 || atIndex == 0 || atIndex == email.length() - 1) {
                throw new NotProperlyFormatedEmailPrefix("Email address prefix is not properly formatted.");
            }

            String prefix = email.substring(0, atIndex);
            return true;
        } catch (Exception e) {
            BlankEmailField bef = new BlankEmailField("Invalid type of email address.");
            bef.initCause(e);
            throw bef;
        }
    }

    public static boolean passwordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}

