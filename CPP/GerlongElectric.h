#include <iostream>
#include <string>
#include <iomanip> // kontrol format output harga

// Kelas Produk merepresentasikan produk di Gerlong Electric
class Produk {
private:
    int id; // ID unik untuk setiap produk
    std::string nama; // Nama produk
    std::string jenis; // Jenis produk
    double harga; // Harga produk

public:
    // Konstruktor
    Produk(int _id, const std::string& _nama, const std::string& _jenis, double _harga)
        : id(_id), nama(_nama), jenis(_jenis), harga(_harga) {}

    ~Produk() {
        // Destructor tanpa pesan
    }

    // Getter dan Setter
    int getId() const { return id; }
    std::string getNama() const { return nama; }
    std::string getJenis() const { return jenis; }
    double getHarga() const { return harga; }

    void setNama(const std::string& newNama) { nama = newNama; }
    void setJenis(const std::string& newJenis) { jenis = newJenis; }
    
    // Mengembalikan true jika harga valid dan diubah, false jika tidak valid
    bool setHarga(double newHarga) {
        if (newHarga >= 0) {
            harga = newHarga;
            return true;
        }
        return false;
    }

    // Perubahan ada di fungsi display() ini
    void display() const {
        std::cout << "------------------------" << std::endl;
        std::cout << "ID: " << id << std::endl;
        std::cout << "Nama: " << nama << std::endl;
        std::cout << "Jenis: " << jenis << std::endl;
        // Gunakan iomanip untuk memformat harga
        std::cout << "Harga: " << std::fixed << std::setprecision(0) << harga << std::endl;
        std::cout << "------------------------" << std::endl;
    }
};
