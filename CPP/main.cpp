#include <iostream>     // library standar untuk input-output
#include <vector>       // STL vector sebagai array dinamis
#include <limits>       // untuk membersihkan buffer input
#include "GerlongElectric.h" // header custom berisi class Produk

// Deklarasi fungsi-fungsi CRUD + helper
void tambahProduk(std::vector<Produk>& daftarProduk, int& nextId);
void tampilkanSemuaProduk(const std::vector<Produk>& daftarProduk);
void updateProduk(std::vector<Produk>& daftarProduk);
void hapusProduk(std::vector<Produk>& daftarProduk);
void cariProduk(const std::vector<Produk>& daftarProduk);
void clearInputBuffer();

int main() {
    // Vector untuk menyimpan daftar produk
    std::vector<Produk> daftarProduk;
    int nextId = 1; // ID auto increment

    // Tambahkan produk default (hardcode)
    daftarProduk.push_back(Produk(nextId++, "Laptop Gaming", "Komputer", 15000000.0));
    daftarProduk.push_back(Produk(nextId++, "Smartphone Android", "Handphone", 4500000.0));
    daftarProduk.push_back(Produk(nextId++, "Headphone Nirkabel", "Aksesoris", 1200000.0));
    std::cout << "Data produk awal berhasil dimuat." << std::endl;

    int pilihan; // untuk input menu

    // Menu utama
    do {
        std::cout << "\n=== Menu Manajemen GerlongElectric ===" << std::endl;
        std::cout << "1. Tambah Produk" << std::endl;
        std::cout << "2. Tampilkan Semua Produk" << std::endl;
        std::cout << "3. Ubah Produk" << std::endl;
        std::cout << "4. Hapus Produk" << std::endl;
        std::cout << "5. Cari Produk" << std::endl;
        std::cout << "6. Keluar" << std::endl;
        std::cout << "Masukkan pilihan Anda: ";
        std::cin >> pilihan;

        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

        switch (pilihan) {
            case 1: tambahProduk(daftarProduk, nextId); break;
            case 2: tampilkanSemuaProduk(daftarProduk); break;
            case 3: updateProduk(daftarProduk); break;
            case 4: hapusProduk(daftarProduk); break;
            case 5: cariProduk(daftarProduk); break;
            case 6: std::cout << "Terima kasih telah menggunakan aplikasi ini!" << std::endl; break;
            default: std::cout << "Pilihan tidak valid. Silakan coba lagi." << std::endl; break;
        }
    } while (pilihan != 6);

    return 0;
}

// Fungsi untuk membersihkan buffer input setelah error
void clearInputBuffer() {
    if (std::cin.fail()) {
        std::cin.clear();
        std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');
    }
}

// CREATE: Tambah produk baru
void tambahProduk(std::vector<Produk>& daftarProduk, int& nextId) {
    std::string nama, jenis;
    double harga;

    std::cout << "\n--- Tambah Produk Baru ---" << std::endl;
    std::cout << "Masukkan Nama Produk: ";
    std::getline(std::cin, nama);
    std::cout << "Masukkan Jenis Produk: ";
    std::getline(std::cin, jenis);
    std::cout << "Masukkan Harga: ";
    std::cin >> harga;

    if (std::cin.fail() || harga < 0) {
        std::cout << "Input harga tidak valid. Produk tidak ditambahkan." << std::endl;
        clearInputBuffer();
        return;
    }
    clearInputBuffer();

    Produk newProduk(nextId++, nama, jenis, harga);
    daftarProduk.push_back(newProduk);

    std::cout << "Produk berhasil ditambahkan dengan ID " << newProduk.getId() << "." << std::endl;
}

// READ: Tampilkan semua produk
void tampilkanSemuaProduk(const std::vector<Produk>& daftarProduk) {
    if (daftarProduk.empty()) {
        std::cout << "\nTidak ada produk yang tersedia." << std::endl;
        return;
    }

    std::cout << "\n=== Daftar Semua Produk ===" << std::endl;
    for (const auto& produk : daftarProduk) {
        produk.display();
    }
}

// UPDATE: Ubah produk berdasarkan ID
void updateProduk(std::vector<Produk>& daftarProduk) {
    int idToUpdate;
    std::cout << "\n--- Ubah Produk ---" << std::endl;
    std::cout << "Masukkan ID Produk yang ingin diubah: ";
    std::cin >> idToUpdate;

    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

    for (auto& produk : daftarProduk) {
        if (produk.getId() == idToUpdate) {
            std::string newNama, newJenis;
            double newHarga;

            std::cout << "Masukkan Nama baru: ";
            std::getline(std::cin, newNama);

            std::cout << "Masukkan Jenis baru: ";
            std::getline(std::cin, newJenis);

            std::cout << "Masukkan Harga baru: ";
            std::cin >> newHarga;

            if (std::cin.fail()) {
                std::cout << "Input harga tidak valid. Perubahan dibatalkan." << std::endl;
                clearInputBuffer();
                return;
            }
            clearInputBuffer();

            if (produk.setHarga(newHarga)) {
                produk.setNama(newNama);
                produk.setJenis(newJenis);
                std::cout << "Produk dengan ID " << idToUpdate << " berhasil diubah." << std::endl;
            } else {
                std::cout << "Harga tidak boleh negatif. Perubahan dibatalkan." << std::endl;
            }
            return;
        }
    }
    std::cout << "Produk dengan ID " << idToUpdate << " tidak ditemukan." << std::endl;
}


// DELETE: Hapus produk
void hapusProduk(std::vector<Produk>& daftarProduk) {
    int idToDelete;
    std::cout << "\n--- Hapus Produk ---" << std::endl;
    std::cout << "Masukkan ID Produk yang ingin dihapus: ";
    std::cin >> idToDelete;
    clearInputBuffer();

    for (auto it = daftarProduk.begin(); it != daftarProduk.end(); ++it) {
        if (it->getId() == idToDelete) {
            daftarProduk.erase(it);
            std::cout << "Produk dengan ID " << idToDelete << " berhasil dihapus." << std::endl;
            return;
        }
    }
    std::cout << "Produk dengan ID " << idToDelete << " tidak ditemukan." << std::endl;
}

// SEARCH: Cari produk
void cariProduk(const std::vector<Produk>& daftarProduk) {
    int idToFind;
    std::cout << "\n--- Cari Produk ---" << std::endl;
    std::cout << "Masukkan ID Produk yang ingin dicari: ";
    std::cin >> idToFind;
    clearInputBuffer();

    for (const auto& produk : daftarProduk) {
        if (produk.getId() == idToFind) {
            std::cout << "\nProduk ditemukan:" << std::endl;
            produk.display();
            return;
        }
    }
    std::cout << "Produk dengan ID " << idToFind << " tidak ditemukan." << std::endl;
}
