package ua.pp.darknsoft.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.pp.darknsoft.models.Client;
import ua.pp.darknsoft.services.ClientService;
import ua.pp.darknsoft.services.ClientServiceImpl;

import java.text.ParseException;
import java.util.List;

public class EncryptedPasswordUtils {
    // Encrypt Password with BCryptPasswordEncoder
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "admin"; //$2a$10$3XQXXF5WViv/Z2sED.a5I.BhlmSGRr1RvJ/3x47tSmBOx2LG4FSTq
        String encrytedPassword = encryptPassword(password);

        System.out.println("Encryted Password: " + encrytedPassword);

    }
}
