import java.util.ArrayList; // Library untuk menggunakan ArrayList (list dinamis)
import java.util.Scanner;   // Library untuk membaca input dari keyboard

public class GerlongElectric { // Nama kelas dan file sudah diperbarui

    private static ArrayList<Produk> daftarProduk = new ArrayList<>();
    private static int nextId = 1;
    private static Scanner scanner = new Scanner(System.in);

    // Metode utama, titik masuk program.
    public static void main(String[] args) {
        // Hardcode produk awal
        daftarProduk.add(new Produk(nextId++, "Laptop Gaming", "Komputer", 15000000.0));
        daftarProduk.add(new Produk(nextId++, "Smartphone Android", "Handphone", 4500000.0));
        daftarProduk.add(new Produk(nextId++, "Headphone Nirkabel", "Aksesoris", 1200000.0));
        System.out.println("Data produk awal berhasil dimuat.");

        int pilihan;

        // Loop do-while untuk menampilkan menu berulang kali
        do {
            tampilkanMenu();
            System.out.print("Masukkan pilihan Anda: ");
            
            // Menggunakan try-catch untuk menangani input yang tidak valid
            try {
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Membuang karakter newline
                
                // Switch-case untuk menjalankan fungsi berdasarkan pilihan pengguna
                switch (pilihan) {
                    case 1:
                        tambahProduk();
                        break;
                    case 2:
                        tampilkanSemuaProduk();
                        break;
                    case 3:
                        updateProduk();
                        break;
                    case 4:
                        hapusProduk();
                        break;
                    case 5:
                        cariProduk();
                        break;
                    case 6:
                        System.out.println("Terima kasih telah menggunakan aplikasi ini!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
                scanner.nextLine(); // Membersihkan baris yang salah
                pilihan = 0; // Mengatur pilihan ke 0 agar loop berlanjut
            }
        } while (pilihan != 6);
        
        scanner.close(); // Menutup objek scanner
    }

    // Metode untuk menampilkan menu program.
    private static void tampilkanMenu() {
        System.out.println("\n=== Menu Manajemen GerlongElectric ===");
        System.out.println("1. Tambah Produk");
        System.out.println("2. Tampilkan Semua Produk");
        System.out.println("3. Ubah Produk");
        System.out.println("4. Hapus Produk");
        System.out.println("5. Cari Produk");
        System.out.println("6. Keluar");
    }

    // Fungsi "Tambah Produk" (Create)
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

    // Fungsi "Tampilkan Semua Produk" (Read)
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

    // Fungsi "Ubah Produk" (Update)
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

    // Fungsi "Hapus Produk" (Delete)
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
    
    // Fungsi "Cari Produk"
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
    
    // Metode pembantu untuk mencari produk di dalam ArrayList berdasarkan ID.
    private static Produk cariProdukById(int id) {
        for (Produk produk : daftarProduk) {
            if (produk.getId() == id) {
                return produk;
            }
        }
        return null;
    }
}