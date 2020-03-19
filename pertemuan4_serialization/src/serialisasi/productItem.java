package serialisasi;
import java.io.Serializable;

/**
 *
 * @author eli
 */
public class productItem implements Serializable {

    String nama;
    double harga;

    public productItem(String name, double price) {
        this.nama = name;
        this.harga = price;
    }

    @Override
    public String toString() {
        return "\r\nNama=" + nama + "\r\nHarga=" + harga + "\r\n";
    }
}
