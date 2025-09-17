<?php
// PHP Sesi
// session_start() harus dipanggil di awal skrip, sebelum output HTML apapun.
// Fungsinya untuk memulai atau melanjutkan sesi yang sudah ada. Sesi ini
// digunakan untuk menyimpan data produk di $_SESSION['products'] karena kita
// tidak menggunakan database.
session_start();

// --- Class Produk ---
// Ini adalah "cetakan" (blueprint) untuk objek produk.
class Produk {
    // Atribut/properti kelas.
    // Dideklarasikan sebagai 'private' untuk menerapkan ENKAPSULASI.
    // Artinya, atribut ini hanya bisa diakses dari dalam kelas itu sendiri
    // melalui metode (getter/setter) yang kita sediakan.
    private $id;
    private $nama;
    private $jenis;
    private $harga;
    private $gambar; // Atribut tambahan untuk menyimpan path file gambar

    // Metode Konstruktor (__construct)
    // Metode ini otomatis dipanggil saat sebuah objek baru dibuat dengan 'new Produk(...)'.
    // Fungsinya untuk menginisialisasi nilai awal dari atribut objek.
    public function __construct($id, $nama, $jenis, $harga, $gambar) {
        $this->id = $id; // $this merujuk pada objek saat ini.
        $this->nama = $nama;
        $this->jenis = $jenis;
        $this->harga = $harga;
        $this->gambar = $gambar;
    }

    // Metode Getter
    // Metode publik ini memungkinkan kita "mengambil" nilai dari atribut privat.
    public function getId() { return $this->id; }
    public function getNama() { return $this->nama; }
    public function getJenis() { return $this->jenis; }
    public function getHarga() { return $this->harga; }
    public function getGambar() { return $this->gambar; }
    
    // Metode Setter
    // Metode publik ini memungkinkan kita "mengatur" atau mengubah nilai atribut privat.
    public function setNama($nama) { $this->nama = $nama; }
    public function setJenis($jenis) { $this->jenis = $jenis; }
    public function setGambar($gambar) { $this->gambar = $gambar; }

    // Setter dengan Validasi
    // Di sini, kita menambahkan logika validasi: harga tidak boleh negatif.
    // Metode ini mengembalikan 'true' jika berhasil, dan 'false' jika gagal.
    public function setHarga($harga) {
        if ($harga >= 0) {
            $this->harga = $harga;
            return true;
        }
        return false;
    }
}

// --- Logika Aplikasi ---

// Inisialisasi Data Awal:
// Blok ini memeriksa apakah array produk di dalam sesi sudah ada.
// Jika belum, artinya ini adalah kunjungan pertama pengguna, jadi kita
// inisialisasi dengan data hardcode yang diminta.
if (!isset($_SESSION['products'])) {
    $_SESSION['products'] = []; // Buat array kosong di sesi.
    $nextId = 1;
    
    // Hardcode produk awal
    $_SESSION['products'][] = new Produk($nextId++, "Laptop Gaming", "Komputer", 15000000.0, "img/laptop.jpg");
    $_SESSION['products'][] = new Produk($nextId++, "Smartphone Android", "Handphone", 4500000.0, "img/smartphone.jpg");
    $_SESSION['products'][] = new Produk($nextId++, "Headphone Nirkabel", "Aksesoris", 1200000.0, "img/headphone.jpg");
    echo "Data produk awal berhasil dimuat.";
}

// Mencari ID produk terakhir:
// Ini penting agar setiap produk baru memiliki ID unik yang berurutan.
// Jika ada produk di sesi, ambil ID terakhir dan tambahkan 1. Jika tidak, mulai dari 1.
$nextId = count($_SESSION['products']) > 0 ? end($_SESSION['products'])->getId() + 1 : 1;

// Proses Permintaan POST:
// Blok ini dijalankan hanya saat form HTML dikirim (metode POST).
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $action = $_POST['action'] ?? ''; // Ambil nilai 'action' dari form

    // Logika untuk Tambah Produk (CRUD: Create)
    if ($action === 'tambah') {
        $nama = $_POST['nama'];
        $jenis = $_POST['jenis'];
        $harga = (float)$_POST['harga'];
        
        // Menangani unggahan file:
        // basename() mengambil hanya nama file dari path.
        $gambar = 'img/' . basename($_FILES['gambar']['name']); 
        
        // move_uploaded_file() memindahkan file dari direktori sementara
        // ke direktori tujuan (folder img).
        if (move_uploaded_file($_FILES['gambar']['tmp_name'], $gambar)) {
            $newProduct = new Produk($nextId, $nama, $jenis, $harga, $gambar);
            $_SESSION['products'][] = $newProduct; // Simpan objek baru ke sesi.
            header('Location: TP1.php'); // Redirect untuk mencegah form dikirim ulang saat refresh.
            exit;
        } else {
            echo "Gagal mengunggah gambar.";
        }
    }

    // Logika untuk Ubah Produk (CRUD: Update)
    if ($action === 'ubah') {
        $id = (int)$_POST['id']; // Ambil ID produk yang akan diubah.
        foreach ($_SESSION['products'] as $product) { // Cari produk di dalam sesi.
            if ($product->getId() === $id) {
                // Gunakan setter untuk mengubah data produk.
                $product->setNama($_POST['nama']);
                $product->setJenis($_POST['jenis']);
                $product->setHarga((float)$_POST['harga']);
                
                // Jika ada gambar baru yang diunggah, update path-nya.
                if (!empty($_FILES['gambar']['name'])) {
                    $newGambar = 'img/' . basename($_FILES['gambar']['name']);
                    if (move_uploaded_file($_FILES['gambar']['tmp_name'], $newGambar)) {
                        $product->setGambar($newGambar);
                    }
                }
                header('Location: TP1.php'); // Redirect
                exit;
            }
        }
    }
}

// Proses Permintaan Hapus (CRUD: Delete)
// Blok ini berjalan saat link 'Hapus' diklik (metode GET).
if (isset($_GET['action']) && $_GET['action'] === 'hapus') {
    $id = (int)$_GET['id'];
    foreach ($_SESSION['products'] as $key => $product) {
        if ($product->getId() === $id) {
            unset($_SESSION['products'][$key]); // Hapus elemen dari array.
            $_SESSION['products'] = array_values($_SESSION['products']); // Reset indeks array agar tidak bolong.
            header('Location: TP1.php'); // Redirect
            exit;
        }
    }
}

// Logika untuk Mode Ubah
// Blok ini memeriksa apakah ada parameter 'edit' di URL untuk mengisi form Ubah.
$editProduct = null;
if (isset($_GET['action']) && $_GET['action'] === 'edit') {
    $editId = (int)$_GET['id'];
    foreach ($_SESSION['products'] as $product) {
        if ($product->getId() === $editId) {
            $editProduct = $product;
            break;
        }
    }
}
?>

<!DOCTYPE html>
<html lang="id">
<head>
    <meta charset="UTF-8">
    <title>GerlongElectric - Manajemen Produk</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1, h2 { color: #333; }
        .container { max-width: 900px; margin: auto; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
        img { max-width: 100px; height: auto; }
        form { margin-bottom: 20px; padding: 20px; border: 1px solid #ccc; background-color: #f9f9f9; }
        input[type="text"], input[type="number"], input[type="file"] { width: calc(100% - 22px); padding: 10px; margin: 5px 0; }
        input[type="submit"] { background-color: #4CAF50; color: white; padding: 10px 15px; border: none; cursor: pointer; }
        .btn-update { background-color: #2196F3; }
        .btn-delete { background-color: #f44336; }
        .btn-update, .btn-delete { color: white; text-decoration: none; padding: 5px 10px; border-radius: 3px; }
    </style>
</head>
<body>
<div class="container">
    <h1>Manajemen Produk GerlongElectric</h1>

    <h2><?php echo $editProduct ? 'Ubah Produk' : 'Tambah Produk'; ?></h2>
    <form method="POST" enctype="multipart/form-data">
        <input type="hidden" name="action" value="<?php echo $editProduct ? 'ubah' : 'tambah'; ?>">
        <?php if ($editProduct): ?>
            <input type="hidden" name="id" value="<?php echo $editProduct->getId(); ?>">
        <?php endif; ?>
        
        Nama Produk:<br>
        <input type="text" name="nama" value="<?php echo $editProduct ? htmlspecialchars($editProduct->getNama()) : ''; ?>" required><br>
        
        Jenis Produk:<br>
        <input type="text" name="jenis" value="<?php echo $editProduct ? htmlspecialchars($editProduct->getJenis()) : ''; ?>" required><br>
        
        Harga:<br>
        <input type="number" name="harga" value="<?php echo $editProduct ? htmlspecialchars($editProduct->getHarga()) : ''; ?>" required><br>

        Gambar:<br>
        <input type="file" name="gambar" <?php echo $editProduct ? '' : 'required'; ?>><br>
        <?php if ($editProduct): ?>
            <small>Kosongkan jika tidak ingin mengubah gambar. Gambar saat ini: <img src="<?php echo htmlspecialchars($editProduct->getGambar()); ?>" alt="Gambar Produk" style="width:50px; height:auto;"></small><br>
        <?php endif; ?>

        <input type="submit" value="<?php echo $editProduct ? 'Ubah Produk' : 'Tambah Produk'; ?>">
    </form>

    <h2>Daftar Produk</h2>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nama</th>
                <th>Jenis</th>
                <th>Harga</th>
                <th>Gambar</th>
                <th>Aksi</th>
            </tr>
        </thead>
        <tbody>
            <?php if (empty($_SESSION['products'])): ?>
                <tr><td colspan="6">Tidak ada produk yang tersedia.</td></tr>
            <?php else: ?>
                <?php foreach ($_SESSION['products'] as $product): ?>
                <tr>
                    <td><?php echo htmlspecialchars($product->getId()); ?></td>
                    <td><?php echo htmlspecialchars($product->getNama()); ?></td>
                    <td><?php echo htmlspecialchars($product->getJenis()); ?></td>
                    <td>Rp <?php echo number_format($product->getHarga(), 0, ',', '.'); ?></td>
                    <td><img src="<?php echo htmlspecialchars($product->getGambar()); ?>" alt="Gambar Produk"></td>
                    <td>
                        <a href="?action=edit&id=<?php echo $product->getId(); ?>" class="btn-update">Ubah</a>
                        <a href="?action=hapus&id=<?php echo $product->getId(); ?>" class="btn-delete" onclick="return confirm('Yakin ingin menghapus produk ini?');">Hapus</a>
                    </td>
                </tr>
                <?php endforeach; ?>
            <?php endif; ?>
        </tbody>
    </table>
</div>
</body>
</html>