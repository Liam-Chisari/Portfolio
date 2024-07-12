import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CipherUtil {
    private static final String ALGORITHM = "AES"; // Feel free to change the algorithm
    private static SecretKey secretKey;

    static {
        // Initialize the secret key. In production use a KeyGenerator
        byte[] decodedKey = Base64.getDecoder().decode("your_secret_key_here"); // Replace with your secret key
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM); 
    }

    public static void encryptFile(Path inputFile, Path outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] fileContent = Files.readAllBytes(inputFile);
        byte[] encryptedContent = cipher.doFinal(fileContent);
        Files.write(outputFile, encryptedContent);
    }

    public static void decryptFile(Path inputFile, Path outputFile) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] fileContent = Files.readAllBytes(inputFile);
        byte[] decryptedContent = cipher.doFinal(fileContent);
        Files.write(outputFile, decryptedContent);
    }
}
