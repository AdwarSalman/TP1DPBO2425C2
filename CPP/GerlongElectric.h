#include <iostream>   // library standar untuk input-output (cout, cin)
#include <string>     // library untuk tipe data string
#include <iomanip>    // library untuk formatting output (setprecision, fixed)

// ===============================
// Kelas Produk
// Merepresentasikan 1 produk elektronik di toko Gerlong Electric
// Memuat atribut: id, nama, jenis, harga
// ===============================
class Produk {
private:
    int id;              // ID unik tiap produk
    std::string nama;    // Nama produk (contoh: "Laptop Gaming")
    std::string jenis;   // Jenis produk (contoh: "Komputer")
    double harga;        // Harga produk (contoh: 15000000)

public:
    // Konstruktor: otomatis dipanggil saat objek dibuat
    // Inisialisasi atribut dengan nilai dari parameter
    Produk(int _id, const std::string& _nama, const std::string& _jenis, double _harga)
        : id(_id), nama(_nama), jenis(_jenis), harga(_harga) {}

    // Destructor: dipanggil saat objek dihancurkan
    // Kosong karena tidak ada alokasi dinamis (placeholder saja)
    ~Produk() {}

    // Getter → membaca data privat dari luar class
    int getId() const { return id; }
    std::string getNama() const { return nama; }
    std::string getJenis() const { return jenis; }
    double getHarga() const { return harga; }

    // Setter → mengubah data privat dari luar class
    void setNama(const std::string& newNama) { nama = newNama; }
    void setJenis(const std::string& newJenis) { jenis = newJenis; }

    // Setter harga dengan validasi
    // Mengembalikan true jika harga >= 0, false jika tidak valid
    bool setHarga(double newHarga) {
        if (newHarga >= 0) {
            harga = newHarga;
            return true;
        }
        return false;
    }

    // Menampilkan detail produk ke layar (menggunakan formatting iomanip)
    void display() const {
        std::cout << "------------------------" << std::endl;
        std::cout << "ID: " << id << std::endl;
        std::cout << "Nama: " << nama << std::endl;
        std::cout << "Jenis: " << jenis << std::endl;
        std::cout << "Harga: " << std::fixed << std::setprecision(0) << harga << std::endl;
        std::cout << "------------------------" << std::endl;
    }
};
