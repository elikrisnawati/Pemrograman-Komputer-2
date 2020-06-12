package crypto;

import static crypto.DESEncryptDecriptUsingFileKey.Decrypt;
import static crypto.DESEncryptDecriptUsingFileKey.Encript;
import static crypto.DESEncryptDecriptUsingFileKey.readKeyFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Keong
 */
public class AsymetricRSA {

    public static void GenerateKey(String privateKeyFile, String publicKeyFile) {
        ObjectOutputStream oosPublic, oosPrivate;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair key = keyGen.generateKeyPair();

            File publicKey = new File(publicKeyFile);
            File privateKey = new File(privateKeyFile);
            publicKey.createNewFile();
            privateKey.createNewFile();

            FileOutputStream fPublic = new FileOutputStream(publicKeyFile);
            oosPublic = new ObjectOutputStream(fPublic);
            oosPublic.writeObject(key.getPublic());
            oosPublic.close();

            FileOutputStream fPrivate = new FileOutputStream(privateKeyFile);
            oosPublic = new ObjectOutputStream(fPrivate);
            oosPublic.writeObject(key.getPrivate());
            oosPublic.close();
        } catch (IOException | NoSuchAlgorithmException e) {
        }
    }

    public static PublicKey getPublicKey(String keyFile) {
        PublicKey pbKey = null;
        try {
            FileInputStream fis = new FileInputStream(keyFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            pbKey = (PublicKey) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return pbKey;
    }

    public static PrivateKey getrivateKey(String keyFile) {
        PrivateKey pvKey = null;
        try {
            FileInputStream fis = new FileInputStream(keyFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            pvKey = (PrivateKey) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
        }
        return pvKey;
    }

    public static byte[] encrypt(String messag, PublicKey key) {
        byte[] cipherText = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(messag.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
        }
        return cipherText;
    }

    public static String decrypt(byte[] encrypted, PrivateKey key) {
        byte[] decrypted = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            decrypted = cipher.doFinal(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
        }
        return new String(decrypted);
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator;
        String privateFile = path + "PRIVATE.cer";
        String publicFile = path + "PUBLIC.cer";
        GenerateKey(privateFile, publicFile);

        String message = "Symetric Crypthography dengan file sebagai key";
        PublicKey pubKey = getPublicKey(publicFile);
        byte[] encrypted = encrypt(message, pubKey);

        PrivateKey privateKey = getrivateKey(privateFile);
        String plainText = decrypt(encrypted, privateKey);

        System.out.println("Message : " + message);
        System.out.println("encrypted : " + new String(encrypted));
        System.out.println("Ecrypted Hex : " + MyStringUtils.getHexString(encrypted));
        System.out.println("Decrypted :" + plainText);
    }

}
