public class Produk {

    // Atribut privat untuk enkapsulasi
    private int id;         // ID unik untuk produk
    private String nama;    // Nama produk
    private String jenis;   // Jenis produk (kategori)
    private double harga;   // Harga produk

    // Konstruktor → dipanggil saat objek dibuat
    public Produk(int id, String nama, String jenis, double harga) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
    }
    
    // Tidak ada destructor di Java.
    // Java punya Garbage Collector yang otomatis menghapus objek yang tidak dipakai.

    // Getter methods (akses atribut privat)
    public int getId() { return id; }
    public String getNama() { return nama; }
    public String getJenis() { return jenis; }
    public double getHarga() { return harga; }
    
    // Setter methods (mengubah atribut privat)
    public void setNama(String nama) { this.nama = nama; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    // Setter harga dengan validasi (tidak boleh negatif)
    public boolean setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
            return true;
        } else {
            return false;
        }
    }

    // Metode untuk menampilkan detail produk
    public void display() {
        System.out.println("------------------------");
        System.out.println("ID: " + this.id);
        System.out.println("Nama: " + this.nama);
        System.out.println("Jenis: " + this.jenis);
        System.out.println("Harga: " + this.harga);
        System.out.println("------------------------");
    }
}
