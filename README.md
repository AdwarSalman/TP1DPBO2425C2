Proyek Tugas Praktikum 1: GerlongElectric
Proyek ini merupakan implementasi sistem manajemen data produk toko elektronik "GerlongElectric" menggunakan konsep Object-Oriented Programming (OOP) dalam berbagai bahasa pemrograman.

Janji
Saya bersumpah bahwa saya mengerjakan tugas ini sendiri dan tidak mencontek/memberi contek.

Penjelasan Desain dan Kode
Desain Berbasis Objek (OOP)
Proyek ini dirancang dengan pendekatan berorientasi objek. Semua data produk diwakili oleh sebuah kelas bernama Produk. Kelas ini berfungsi sebagai cetakan untuk setiap objek produk yang dibuat dalam program.

Struktur Data dan Enkapsulasi
Atribut: Kelas Produk memiliki lima atribut utama: id, nama, jenis, harga, dan gambar. Atribut ini dibuat privat untuk menerapkan enkapsulasi, menjaga integritas data.

Getter dan Setter: Akses dan modifikasi atribut hanya bisa dilakukan melalui metode getter (untuk mengambil nilai) dan setter (untuk mengubah nilai). Untuk atribut harga, setter memiliki validasi untuk memastikan nilai yang dimasukkan selalu positif.

Koleksi Objek: Semua objek produk disimpan dalam sebuah list of objects (std::vector di C++, ArrayList di Java, list di Python, dan $_SESSION di PHP) agar mudah dikelola.

Alur Program (CRUD)
Program ini mengimplementasikan operasi dasar manajemen data CRUD (Create, Read, Update, Delete) dan satu operasi tambahan, yaitu Cari (Search).

Create (Tambah): Pengguna dapat menambah produk baru, dan program akan membuat objek Produk baru yang unik.

Read (Tampilkan): Program dapat menampilkan semua produk yang tersimpan dalam daftar.

Update (Ubah): Pengguna dapat mengubah data produk yang sudah ada dengan mencari berdasarkan ID.

Delete (Hapus): Pengguna dapat menghapus produk dari daftar berdasarkan ID.

Cari: Program dapat mencari dan menampilkan satu produk spesifik.

Implementasi
Logika program ini diimplementasikan dalam empat bahasa berbeda, dengan antarmuka berbasis CLI (untuk C++, Java, Python) dan antarmuka web (untuk PHP).

Dokumentasi
Berikut adalah tangkapan layar yang menunjukkan program berjalan dan berfungsi dengan baik.

C++
(masukkan gambar screenshot program C++ Anda di sini)

Java
(masukkan gambar screenshot program Java Anda di sini)

Python
(masukkan gambar screenshot program Python Anda di sini)

PHP (Tampilan Web)
(masukkan gambar screenshot tampilan web PHP Anda di sini)
