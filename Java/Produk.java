public class Produk {

    // Atribut privat untuk menerapkan enkapsulasi
    private int id;
    private String nama;
    private String jenis;
    private double harga;

    // Konstruktor
    public Produk(int id, String nama, String jenis, double harga) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
        this.harga = harga;
    }
    
    // Tidak perlu Destruktor di Java. Java memiliki Garbage Collector.

    // Getter methods
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public double getHarga() {
        return harga;
    }
    
    // Setter methods
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    // Setter dengan validasi
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