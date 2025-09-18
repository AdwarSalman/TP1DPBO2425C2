import java.util.ArrayList; // ArrayList → list dinamis mirip vector di C++
import java.util.Scanner;   // Scanner → input user

public class GerlongElectric {

    // Array of list produk (database sementara)
    private static ArrayList<Produk> daftarProduk = new ArrayList<>();
    private static int nextId = 1; // ID auto increment
    private static Scanner scanner = new Scanner(System.in);

    // Titik masuk program
    public static void main(String[] args) {
        // Tambah produk awal (hardcode)
        daftarProduk.add(new Produk(nextId++, "Laptop Gaming", "Komputer", 15000000.0));
        daftarProduk.add(new Produk(nextId++, "Smartphone Android", "Handphone", 4500000.0));
        daftarProduk.add(new Produk(nextId++, "Headphone Nirkabel", "Aksesoris", 1200000.0));
        System.out.println("Data produk awal berhasil dimuat.");

        int pilihan;

        // Loop menu utama
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan Anda: ");
            
            try {
                pilihan = scanner.nextInt(); 
                scanner.nextLine(); // buang newline supaya input string berikutnya aman
                
                switch (pilihan) {
                    case 1: tambahProduk(); break; // CREATE
                    case 2: tampilkanSemuaProduk(); break; // READ
                    case 3: updateProduk(); break; // UPDATE
                    case 4: hapusProduk(); break; // DELETE
                    case 5: cariProduk(); break;  // SEARCH
                    case 6: System.out.println("Terima kasih telah menggunakan aplikasi ini!"); break;
                    default: System.out.println("Pilihan tidak valid."); break;
                }
            } catch (java.util.InputMismatchException e) {
                // Jika input bukan angka
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine(); // bersihkan buffer
                pilihan = 0;
            }
        } while (pilihan != 6);

        scanner.close(); // tutup scanner saat keluar
    }

    // Menampilkan menu utama
    private static void tampilkanMenu() {
        System.out.println("\n=== Menu Manajemen GerlongElectric ===");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Ubah Produk");
        System.out.println("4. Hapus Produk");
        System.out.println("5. Cari Produk");
        System.out.println("6. Keluar");
    }

    // CREATE: Tambah produk
    private static void tambahProduk() {
        System.out.println("\n--- Tambah Produk Baru ---");
        System.out.print("Masukkan Nama Produk: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Jenis Produk: ");
        String jenis = scanner.nextLine();
        System.out.print("Masukkan Harga: ");
        
        try {
            double harga = scanner.nextDouble();
            scanner.nextLine();

            if (harga < 0) {
                System.out.println("Harga tidak boleh negatif. Produk tidak ditambahkan.");
            } else {
                Produk newProduk = new Produk(nextId++, nama, jenis, harga);
                daftarProduk.add(newProduk);
                System.out.println("Produk berhasil ditambahkan dengan ID " + newProduk.getId() + ".");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input harga tidak valid. Produk tidak ditambahkan.");
            scanner.nextLine();
        }
    }

    // READ: tampilkan semua produk
    private static void tampilkanSemuaProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("\nTidak ada produk yang tersedia.");
        } else {
            System.out.println("\n=== Daftar Semua Produk ===");
            for (Produk produk : daftarProduk) {
                produk.display();
            }
        }
    }

    // UPDATE: ubah produk berdasarkan ID
    private static void updateProduk() {
        System.out.println("\n--- Ubah Produk ---");
        System.out.print("Masukkan ID Produk yang ingin diubah: ");
        
        try {
            int idToUpdate = scanner.nextInt();
            scanner.nextLine();

            Produk produkToUpdate = cariProdukById(idToUpdate);
            if (produkToUpdate != null) {
                System.out.print("Masukkan Nama baru: ");
                String newNama = scanner.nextLine();
                System.out.print("Masukkan Jenis baru: ");
                String newJenis = scanner.nextLine();
                System.out.print("Masukkan Harga baru: ");
                double newHarga = scanner.nextDouble();
                scanner.nextLine();

                produkToUpdate.setNama(newNama);
                produkToUpdate.setJenis(newJenis);
                if (produkToUpdate.setHarga(newHarga)) {
                    System.out.println("Produk dengan ID " + idToUpdate + " berhasil diubah.");
                } else {
                    System.out.println("Harga tidak boleh negatif. Perubahan dibatalkan.");
                }
            } else {
                System.out.println("Produk dengan ID " + idToUpdate + " tidak ditemukan.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input ID tidak valid. Masukkan angka.");
            scanner.nextLine();
        }
    }

    // DELETE: hapus produk
    private static void hapusProduk() {
        System.out.println("\n--- Hapus Produk ---");
        System.out.print("Masukkan ID Produk yang ingin dihapus: ");
        
        try {
            int idToDelete = scanner.nextInt();
            scanner.nextLine();

            Produk produkToDelete = cariProdukById(idToDelete);
            if (produkToDelete != null) {
                daftarProduk.remove(produkToDelete);
                System.out.println("Produk dengan ID " + idToDelete + " berhasil dihapus.");
            } else {
                System.out.println("Produk dengan ID " + idToDelete + " tidak ditemukan.");
            }
        } catch (java.util.InputMismatchEx
