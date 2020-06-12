package crypto;

import static crypto.SHAHash.hash;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAHashOfFile {

    public static String hashFile(String file, String Algorithm) throws FileNotFoundException {
        String hashed = "";
        try {
            MessageDigest sha = MessageDigest.getInstance(Algorithm);
            FileInputStream fis = new FileInputStream(file);
//            sha.update(data.getBytes());
            byte[] dataBytes = new byte[1024];
            int nRead;
            while ((nRead = fis.read(dataBytes)) != -1) {                
                sha.update(dataBytes, 0 , nRead);
            }
            byte[] shaBytes = sha.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < shaBytes.length; i++) {
                sb.append(Integer.toString((shaBytes[i] & 0xff) + 
                        0x100, 16) .substring(1));
            }
            hashed = sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
        }
        return hashed;
    }
    public static void main(String[] args) {
        String file = System.getProperty("user.dir")+File.separator+"manifest.mf";
        System.out.println("File : "+file);
        System.out.println("SHA-1\t : "+hash(file, "SHA-1"));
        System.out.println("SHA-256\t : "+hash(file, "SHA-256"));
        System.out.println("SHA-512\t : "+hash(file, "SHA-512"));
    }
    
}
