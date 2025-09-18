import java.util.ArrayList; // Library untuk menggunakan ArrayList (list dinamis)
import java.util.Scanner;   // Library untuk membaca input dari keyboard

public class GerlongElectric { // Nama kelas utama

    private static ArrayList<Produk> daftarProduk = new ArrayList<>(); // List produk yang menyimpan semua data produk
    private static int nextId = 1; // Counter ID produk agar selalu unik
    private static Scanner scanner = new Scanner(System.in); // Scanner untuk input user

    // Titik masuk program
    public static void main(String[] args) {
        // Hardcode produk awal
        daftarProduk.add(new Produk(nextId++, "Laptop Gaming", "Komputer", 15000000.0));
        daftarProduk.add(new Produk(nextId++, "Smartphone Android", "Handphone", 4500000.0));
        daftarProduk.add(new Produk(nextId++, "Headphone Nirkabel", "Aksesoris", 1200000.0));
        System.out.println("Data produk awal berhasil dimuat.");

        int pilihan;

        // Perulangan menu sampai user memilih keluar
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan Anda: ");
            
            try {
                pilihan = scanner.nextInt(); // Baca input pilihan
                scanner.nextLine(); // Buang newline biar input berikutnya aman
                
                // Jalankan fungsi sesuai pilihan user
                switch (pilihan) {
                    case 1:
                        tambahProduk(); // Create
                        break;
                    case 2:
                        tampilkanSemuaProduk(); // Read
                        break;
                    case 3:
                        updateProduk(); // Update
                        break;
                    case 4:
                        hapusProduk(); // Delete
                        break;
                    case 5:
                        cariProduk(); // Search
                        break;
                    case 6:
                        System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            } catch (java.util.InputMismatchException e) { 
                // Menangani jika input bukan angka
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine(); // Bersihkan input buffer
                pilihan = 0; // Set pilihan ke 0 biar loop lanjut
            }
        } while (pilihan != 6); // Ulangi sampai user pilih keluar
        
        scanner.close(); // Tutup scanner setelah selesai
    }

    // Menu utama
    private static void tampilkanMenu() {
        System.out.println("\n=== Menu Manajemen GerlongElectric ===");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Ubah Produk");
        System.out.println("4. Hapus Produk");
        System.out.println("5. Cari Produk");
        System.out.println("6. Keluar");
    }

    // Tambah produk baru
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
            
            if (harga < 0) { // Validasi harga
                System.out.println("Harga tidak boleh negatif. Produk tidak ditambahkan.");
            } else {
                Produk newProduk = new Produk(nextId++, nama, jenis, harga);
                daftarProduk.add(newProduk);
                System.out.println("Produk berhasil ditambahkan dengan ID " + newProduk.getId() + ".");
            }
        } catch (java.util.InputMismatchException e) {
            // Menangani input harga yang bukan angka
            System.out.println("Input harga tidak valid. Produk tidak ditambahkan.");
            scanner.nextLine();
        }
    }

    // Tampilkan semua produk
    private static void tampilkanSemuaProduk() {
        if (daftarProduk.isEmpty()) {
            System.out.println("\nTidak ada produk yang tersedia.");
        } else {
            System.out.println("\n=== Daftar Semua Produk ===");
            for (Produk produk : daftarProduk) {
                produk.display(); // Panggil metode display milik Produk
            }
        }
    }

    // Update produk berdasarkan ID
    private static void updateProduk() {
        System.out.println("\n--- Ubah Produk ---");
        System.out.print("Masukkan ID Produk yang ingin diubah: ");
        
        try {
            int idToUpdate = scanner.nextInt();
            scanner.nextLine();
            
            Produk produkToUpdate = cariProdukById(idToUpdate); // Cari produk dulu
            if (produkToUpdate != null) {
                // Input data baru
                System.out.print("Masukkan Nama baru: ");
                String newNama = scanner.nextLine();
                System.out.print("Masukkan Jenis baru: ");
                String newJenis = scanner.nextLine();
                System.out.print("Masukkan Harga baru: ");
                double newHarga = scanner.nextDouble();
                scanner.nextLine();

                // Update data produk
                produkToUpdate.setNama(newNama);
                produkToUpdate.setJenis(newJenis);
                if (produkToUpdate.setHarga(newHarga)) {
                    System.out.println("Produk dengan ID " + idToUpdate + " berhasil diubah.");
                } else {
                    System.out.println("Gagal: Harga tidak boleh negatif. Perubahan dibatalkan.");
                }
            } else {
                System.out.println("Produk dengan ID " + idToUpdate + " tidak ditemukan.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input ID tidak valid. Silakan masukkan angka.");
            scanner.nextLine();
        }
    }

    // Hapus produk berdasarkan ID
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
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input ID tidak valid. Silakan masukkan angka.");
            scanner.nextLine();
        }
    }
    
    // Cari produk berdasarkan ID
    private static void cariProduk() {
        System.out.println("\n--- Cari Produk ---");
        System.out.print("Masukkan ID Produk yang ingin dicari: ");
        
        try {
            int idToFind = scanner.nextInt();
            scanner.nextLine();
            
            Produk foundProduk = cariProdukById(idToFind);
            if (foundProduk != null) {
                System.out.println("\nProduk ditemukan:");
                foundProduk.display();
            } else {
                System.out.println("Produk dengan ID " + idToFind + " tidak ditemukan.");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input ID tidak valid. Silakan masukkan angka.");
            scanner.nextLine();
        }
    }
    
    // Fungsi pembantu untuk mencari produk di list berdasarkan ID
    private static Produk cariProdukById(int id) {
        for (Produk produk : daftarProduk) {
            if (produk.getId() == id) {
                return produk; // Kalau ketemu, return objek produk
            }
        }
        return null; // Kalau tidak ketemu, return null
    }
}
