package crypto;

import com.sun.corba.se.spi.activation.NoSuchEndPoint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import org.omg.CORBA.DynAnyPackage.Invalid;


public class DESEncryptDecrypt {

    public static void doEncryptDecrypt() {
        try {
            String data = " Dasar Symmetric Cryptography";
            byte[] plain = data.getBytes("UTF8");
//            membuat DES priv key
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            keygen.init(56);
            Key key = keygen.generateKey();
//            membuat DES cipher => print ke proveder
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//            Proses encrypt
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cipherText = cipher.doFinal(plain);
//            proses dec
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] originText = cipher.doFinal(cipherText);
            System.out.println("Plain Text : " + data);
            System.out.println("Enkripsi : " + new String(cipherText, "UTF8"));
            System.out.println("Enkripsi Heksa : " + MyStringUtils.getHexString(cipherText));
            System.out.println("Dekripsi :" + new String(originText, "UTF8"));
        } catch (UnsupportedEncodingException | InvalidKeyException
                | NoSuchAlgorithmException | BadPaddingException
                | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
    }

    public static void main(String[] args) {
        doEncryptDecrypt();
    }
}
