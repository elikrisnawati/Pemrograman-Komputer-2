package crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Keong
 */
public class MD5Hash {

    public static String hash(String data){
        String hashed = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data.getBytes());
            byte[] byteData = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i]& 0xff)+
                        0x100, 16).substring(1));
            }
            hashed = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashed;
    }
    public static void main (String[] args){
        String plainText = "PHB2019#300";
        System.out.println("Plain Text\t: "+plainText);
        String enkripsi = hash(plainText);
        System.out.println("MD5 Hash\t: "+enkripsi);
    }
    
}
