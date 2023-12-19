package hexlet.code.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;


/**
 * Uses the PBKDF2 algorithm.
 */
public class PasswordHasher {

    /**
     * this method create crypto-safely array of bytes
     * @return bytes for hashing
     */
    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        var salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * The third parameter (65536) is effectively the strength parameter.
     * It indicates how many iterations that this algorithm run for, increasing the time it takes to produce the hash.
     * @param password
     * @return hash bytes
     */
    public static byte[] getHash(String password) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), getSalt(), 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
