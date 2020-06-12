package crypto;

import static crypto.MD5Hash.hash;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
/**
 *
 * @author Keong
 */
public class MD5HashOfFile {

    public static String hashFile(String file) throws FileNotFoundException, IOException {
        String hashed = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(file);
            byte[] byteData = new byte[1024];
            int nRead;
            while ((nRead = fis.read(byteData)) != -1) {                
                md.update(byteData, 0 , nRead);
            }
            byte[] mdBytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mdBytes.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 
                        0x100, 16) .substring(1));
            }
            hashed = sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
        }
        return hashed;
    }
    public static void main(String[] args) {
        String file = System.getProperty("user.dir") +
                File.separator + "manifest.mf";
        System.out.println("File\t\t: "+file);
        System.out.println("MD5 checksum\t: "+hash(file));
    }
    
}
