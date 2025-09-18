# Proyek Tugas Praktikum 1: GerlongElectric

Proyek ini adalah implementasi sistem manajemen data produk toko elektronik **GerlongElectric** menggunakan paradigma Object-Oriented Programming (OOP). 
Implementasi tersedia dalam empat bahasa: C++, Java, Python, dan PHP (web). 
Setiap implementasi menyediakan fungsi dasar manajemen produk: tambah (Create), tampil (Read), ubah (Update), hapus (Delete), dan cari (Search).

## Janji
Saya Muhammad Adwar Salman dengan 2401539
mengerjakan tugas praktikum 1 dalam mata kuliah Desain Pemrograman Berbasis Objek (DPBO)
untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan
seperti yang telah di spesifikasikan Aamiin.

## Desain Umum
Desain inti mengikuti pola OOP: ada kelas `Produk` yang menjadi blueprint untuk setiap item produk. Atribut utama adalah `id`, `nama`, `jenis`, `harga` (pada implementasi PHP juga ada atribut `gambar`). 
Semua atribut disembunyikan (encapsulation) dan hanya diakses/diubah melalui getter dan setter; setter harga dilengkapi validasi agar tidak menerima nilai negatif. 
Kumpulan objek disimpan dalam struktur koleksi sesuai bahasa: `std::vector` di C++, `ArrayList` di Java, `list` di Python, dan array dalam `$_SESSION` di PHP. 
Alur program pada dasarnya sama: user menambah item → program membuat objek Produk baru (dengan id unik), user bisa melihat semua item, mengubah berdasarkan ID, menghapus berdasarkan ID, dan mencari item tertentu.

---

## Implementasi — Penjelasan Per Bahasa (lebih detail)

### C++
File utama: `GerlongElectric.h` (definisi class `Produk`) dan `main.cpp` (menu + fungsi CRUD).
Kelas `Produk` berisi atribut privat `id`, `nama`, `jenis`, `harga`. Konstruktor menginisialisasi semua atribut, destructor dibiarkan default karena tidak ada alokasi dinamis. Getter bersifat `const` dan mengembalikan nilai, setter untuk `nama`/`jenis` mengubah string, setter `setHarga(double)` memeriksa `>= 0` dan mengembalikan `bool` (true saat sukses). Method `display()` menggunakan `<iomanip>` untuk memformat harga (mis. `std::fixed` + `std::setprecision(0)`).

Di `main.cpp` data produk disimpan dalam `std::vector<Produk> daftarProduk;` dan variabel `int nextId` dipakai untuk auto-increment ID. Penambahan produk menggunakan `push_back(Produk(...))`. Penghapusan dilakukan dengan iterator (`erase(it)`), sedangkan pencarian/ubah dilakukan dengan loop dan pencocokan `produk.getId()`. Ada helper `clearInputBuffer()` yang mengecek `std::cin.fail()` dan membersihkan buffer menggunakan `ignore()` — ini penting karena program mengombinasikan `operator>>` dan `std::getline()`.


### Java

File utama: Produk.java dan GerlongElectric.java.
Produk di Java punya atribut privat id, nama, jenis, harga. Konstruktor menerima semua atribut. Java tidak memiliki destructor manual; garbage collector mengurus memori. Getter dan setter konvensional ada; setHarga(double) melakukan validasi (mengembalikan boolean). Method display() menulis detail ke System.out.

GerlongElectric.java menyimpan produk di private static ArrayList<Produk> daftarProduk. ID unik dikelola lewat private static int nextId. Input menggunakan Scanner dan ada penanganan InputMismatchException untuk input yang tidak valid (mis. user mengetik huruf saat diminta angka). CRUD diimplementasikan dengan metode: tambahProduk(), tampilkanSemuaProduk(), updateProduk(), hapusProduk(), cariProduk(). Fungsi helper cariProdukById(int id) mengembalikan Produk atau null.

### Python

File: GerlongElectric.py.
Produk di Python memakai konvensi atribut privat _id, _nama, _jenis, _harga. Getter & setter diimplementasikan dengan @property dan nama setter yang sesuai. Setter harga menaikkan ValueError jika harga negatif sehingga caller bisa try/except dan menolak perubahan saat input salah. __str__ mengembalikan representasi string yang rapi sehingga cukup print(produk) untuk menampilkan.

Data disimpan di daftar_produk (list biasa) dan next_id integer untuk auto-increment. Fungsi CRUD terpisah (tambah_produk, tampilkan_semua_produk, update_produk, hapus_produk, cari_produk). main() menambahkan beberapa produk default lalu menjalankan loop menu berbasis input().

### PHP (Web)

File: TP1.php (menggabungkan class Produk, session storage, form HTML untuk CRUD, dan tampilan).
Kelas Produk pada implementasi PHP berisi id, nama, jenis, harga, dan gambar. Constructor menginisialisasi atribut, getter/setter disediakan. Server-side penyimpanan memakai $_SESSION sehingga data bertahan antar refresh selama session aktif. Pada awal, skrip menginisialisasi $_SESSION['products'] (array) dan $_SESSION['next_id'] untuk auto-increment.

Interface web: ada form untuk tambah dan ubah (menggunakan multipart/form-data untuk unggah gambar), serta tombol/hyperlink untuk menghapus atau mengedit item. Upload file disimpan ke folder img/ menggunakan move_uploaded_file() dan nama file default dibuat dengan basename($_FILES['gambar']['name']).


## Dokumentasi

[![Dokumentasi Video](https://raw.githubusercontent.com/AdwarSalman/TP1DPBO2425C2/main/assets/thumbnail.jpg)](https://drive.google.com/file/d/1GEDXRm8phPBnjrASQlGQsj70ejX9TvJK/view?usp=sharing)

