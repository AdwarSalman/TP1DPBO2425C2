import os

class Produk:
    """
    Kelas Produk ini merepresentasikan sebuah produk elektronik.
    Menggunakan konsep OOP seperti enkapsulasi dan getter/setter.
    """
    def __init__(self, id, nama, jenis, harga):
        # Konstruktor: Metode yang dipanggil saat objek baru dibuat.
        # Atribut diawali dengan underscore (_) untuk menandakan "privat".
        # enkapsulasi di Python.
        self._id = id
        self._nama = nama
        self._jenis = jenis
        self._harga = harga

    # Getter untuk id. Menggunakan @property decorator.
    @property
    def id(self):
        return self._id

    # Getter dan Setter untuk nama.
    @property
    def nama(self):
        return self._nama

    @nama.setter
    def nama(self, new_nama):
        self._nama = new_nama

    # Getter dan Setter untuk jenis.
    @property
    def jenis(self):
        return self._jenis

    @jenis.setter
    def jenis(self, new_jenis):
        self._jenis = new_jenis

    # Getter dan Setter untuk harga, dengan validasi.
    @property
    def harga(self):
        return self._harga

    @harga.setter
    def harga(self, new_harga):
        if new_harga >= 0:
            self._harga = new_harga
        else:
            # Menggunakan ValueError untuk menghentikan operasi jika harga negatif.
            raise ValueError("Harga tidak boleh negatif.")

    # Metode __str__ ini otomatis dipanggil saat objek dicetak dengan print().
    def __str__(self):
        return f"""
------------------------
ID: {self._id}
Nama: {self._nama}
Jenis: {self._jenis}
Harga: {self._harga}
------------------------
"""

# --- Logika Program Utama ---

# Menggunakan list sebagai "array/list of objects"
daftar_produk = []
next_id = 1

def tampilkan_menu():
    """Fungsi ini menampilkan menu utama program."""
    print("\n=== Menu Manajemen GerlongElectric ===")
    print("1. Tambah Produk")
    print("2. Tampilkan Semua Produk")
    print("3. Ubah Produk")
    print("4. Hapus Produk")
    print("5. Cari Produk")
    print("6. Keluar")

def tambah_produk():
    """Fungsi CRUD: Menambah produk baru ke daftar."""
    global next_id
    print("\n--- Tambah Produk Baru ---")
    nama = input("Masukkan Nama Produk: ")
    jenis = input("Masukkan Jenis Produk: ")
    try:
        harga = float(input("Masukkan Harga: "))
        if harga < 0:
            print("Harga tidak boleh negatif. Produk tidak ditambahkan.")
            return
        
        # Buat objek Produk baru dan tambahkan ke list.
        new_produk = Produk(next_id, nama, jenis, harga)
        daftar_produk.append(new_produk)
        next_id += 1
        print(f"Produk berhasil ditambahkan dengan ID {new_produk.id}.")
    except ValueError:
        print("Input harga tidak valid. Produk tidak ditambahkan.")

def tampilkan_semua_produk():
    """Fungsi CRUD: Menampilkan semua produk dari daftar."""
    if not daftar_produk:
        print("\nTidak ada produk yang tersedia.")
        return
    
    print("\n=== Daftar Semua Produk ===")
    for produk in daftar_produk:
        print(produk)

def update_produk():
    """Fungsi CRUD: Mengubah data produk berdasarkan ID."""
    print("\n--- Ubah Produk ---")
    try:
        id_to_update = int(input("Masukkan ID Produk yang ingin diubah: "))
        
        produk_ditemukan = False
        for produk in daftar_produk:
            if produk.id == id_to_update:
                print("Produk ditemukan. Masukkan data baru:")
                produk.nama = input("Masukkan Nama baru: ")
                produk.jenis = input("Masukkan Jenis baru: ")
                try:
                    new_harga = float(input("Masukkan Harga baru: "))
                    produk.harga = new_harga
                    print(f"Produk dengan ID {id_to_update} berhasil diubah.")
                except ValueError as e:
                    print(f"Gagal: {e}. Perubahan dibatalkan.")
                
                produk_ditemukan = True
                break
        
        if not produk_ditemukan:
            print(f"Produk dengan ID {id_to_update} tidak ditemukan.")
    except ValueError:
        print("Input ID tidak valid. Silakan masukkan angka.")

def hapus_produk():
    """Fungsi CRUD: Menghapus produk dari daftar berdasarkan ID."""
    print("\n--- Hapus Produk ---")
    try:
        id_to_delete = int(input("Masukkan ID Produk yang ingin dihapus: "))
        
        produk_dihapus = False
        for produk in daftar_produk:
            if produk.id == id_to_delete:
                daftar_produk.remove(produk)
                print(f"Produk dengan ID {id_to_delete} berhasil dihapus.")
                produk_dihapus = True
                break
        
        if not produk_dihapus:
            print(f"Produk dengan ID {id_to_delete} tidak ditemukan.")
    except ValueError:
        print("Input ID tidak valid. Silakan masukkan angka.")

def cari_produk():
    """Fungsi: Mencari satu produk spesifik berdasarkan ID."""
    print("\n--- Cari Produk ---")
    try:
        id_to_find = int(input("Masukkan ID Produk yang ingin dicari: "))
        
        produk_ditemukan = False
        for produk in daftar_produk:
            if produk.id == id_to_find:
                print("\nProduk ditemukan:")
                print(produk)
                produk_ditemukan = True
                break
        
        if not produk_ditemukan:
            print(f"Produk dengan ID {id_to_find} tidak ditemukan.")
    except ValueError:
        print("Input ID tidak valid. Silakan masukkan angka.")

def main():
    """Fungsi utama yang menjalankan loop menu program."""
    # Hardcode produk awal
    global next_id
    daftar_produk.append(Produk(next_id, "Laptop Gaming", "Komputer", 15000000.0))
    next_id += 1
    daftar_produk.append(Produk(next_id, "Smartphone Android", "Handphone", 4500000.0))
    next_id += 1
    daftar_produk.append(Produk(next_id, "Headphone Nirkabel", "Aksesoris", 1200000.0))
    next_id += 1
    print("Data produk awal berhasil dimuat.")

    while True:
        tampilkan_menu()
        pilihan = input("Masukkan pilihan Anda: ")
        
        if pilihan == '1':
            tambah_produk()
        elif pilihan == '2':
            tampilkan_semua_produk()
        elif pilihan == '3':
            update_produk()
        elif pilihan == '4':
            hapus_produk()
        elif pilihan == '5':
            cari_produk()
        elif pilihan == '6':
            print("Terima kasih telah menggunakan aplikasi ini!")
            break
        else:
            print("Pilihan tidak valid. Silakan coba lagi.")

if __name__ == "__main__":
    main()