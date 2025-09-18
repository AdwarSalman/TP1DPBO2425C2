<?php
// ===============================
// Kelas Produk
// ===============================
class Produk {
    private $id;     // ID unik tiap produk
    private $nama;   // Nama produk
    private $jenis;  // Jenis produk
    private $harga;  // Harga produk

    // Constructor → inisialisasi atribut
    public function __construct($id, $nama, $jenis, $harga) {
        $this->id = $id;
        $this->nama = $nama;
        $this->jenis = $jenis;
        $this->harga = $harga;
    }

    // Getter
    public function getId() { return $this->id; }
    public function getNama() { return $this->nama; }
    public function getJenis() { return $this->jenis; }
    public function getHarga() { return $this->harga; }

    // Setter
    public function setNama($nama) { $this->nama = $nama; }
    public function setJenis($jenis) { $this->jenis = $jenis; }
    
    // Setter harga dengan validasi
    public function setHarga($harga) {
        if ($harga >= 0) {
            $this->harga = $harga;
            return true;
        }
        return false;
    }

    // Tampilkan data produk (dipanggil saat render HTML)
    public function display() {
        echo "<div class='produk'>";
        echo "<p><b>ID:</b> " . $this->id . "</p>";
        echo "<p><b>Nama:</b> " . $this->nama . "</p>";
        echo "<p><b>Jenis:</b> " . $this->jenis . "</p>";
        echo "<p><b>Harga:</b> " . $this->harga . "</p>";
        echo "</div><hr>";
    }
}

// ===============================
// Inisialisasi daftar produk
// ===============================
session_start();
if (!isset($_SESSION['daftarProduk'])) {
    $_SESSION['daftarProduk'] = [];
    $_SESSION['nextId'] = 1;

    // Tambahkan produk default
    $_SESSION['daftarProduk'][] = new Produk($_SESSION['nextId']++, "Laptop Gaming", "Komputer", 15000000);
    $_SESSION['daftarProduk'][] = new Produk($_SESSION['nextId']++, "Smartphone Android", "Handphone", 4500000);
    $_SESSION['daftarProduk'][] = new Produk($_SESSION['nextId']++, "Headphone Nirkabel", "Aksesoris", 1200000);
}

// ===============================
// Handler untuk aksi CRUD
// ===============================
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $aksi = $_POST['aksi'];

    // CREATE
    if ($aksi === 'tambah') {
        $nama = $_POST['nama'];
        $jenis = $_POST['jenis'];
        $harga = (float) $_POST['harga'];
        if ($harga >= 0) {
            $_SESSION['daftarProduk'][] = new Produk($_SESSION['nextId']++, $nama, $jenis, $harga);
        }
    }

    // UPDATE
    elseif ($aksi === 'ubah') {
        $id = (int) $_POST['id'];
        foreach ($_SESSION['daftarProduk'] as $produk) {
            if ($produk->getId() === $id) {
                $produk->setNama($_POST['nama']);
                $produk->setJenis($_POST['jenis']);
                $produk->setHarga((float) $_POST['harga']);
                break;
            }
        }
    }

    // DELETE
    elseif ($aksi === 'hapus') {
        $id = (int) $_POST['id'];
        foreach ($_SESSION['daftarProduk'] as $index => $produk) {
            if ($produk->getId() === $id) {
                unset($_SESSION['daftarProduk'][$index]);
                $_SESSION['daftarProduk'] = array_values($_SESSION['daftarProduk']); // reindex
                break;
            }
        }
    }

    // SEARCH tidak eksplisit, dilakukan lewat tampilan (form ID)
}
?>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>GerlongElectric - PHP CRUD</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .produk { padding: 10px; background: #f4f4f4; border-radius: 8px; margin-bottom: 10px; }
        form { margin-bottom: 20px; padding: 10px; background: #eaeaea; border-radius: 8px; }
        input, button { margin: 5px; padding: 5px; }
    </style>
</head>
<body>
    <h1>Manajemen Produk GerlongElectric</h1>

    <!-- Form tambah produk -->
    <form method="POST">
        <h3>Tambah Produk</h3>
        <input type="hidden" name="aksi" value="tambah">
        <input type="text" name="nama" placeholder="Nama Produk" required>
        <input type="text" name="jenis" placeholder="Jenis Produk" required>
        <input type="number" name="harga" placeholder="Harga" required>
        <button type="submit">Tambah</button>
    </form>

    <!-- Form ubah produk -->
    <form method="POST">
        <h3>Ubah Produk</h3>
        <input type="hidden" name="aksi" value="ubah">
        <input type="number" name="id" placeholder="ID Produk" required>
        <input type="text" name="nama" placeholder="Nama Baru" required>
        <input type="text" name="jenis" placeholder="Jenis Baru" required>
        <input type="number" name="harga" placeholder="Harga Baru" required>
        <button type="submit">Ubah</button>
    </form>

    <!-- Form hapus produk -->
    <form method="POST">
        <h3>Hapus Produk</h3>
        <input type="hidden" name="aksi" value="hapus">
        <input type="number" name="id" placeholder="ID Produk" required>
        <button type="submit">Hapus</button>
    </form>

    <h2>Daftar Produk</h2>
    <?php
    // Tampilkan semua produk dengan memanggil method display()
    foreach ($_SESSION['daftarProduk'] as $produk) {
        $produk->display();
    }
    ?>
</body>
</html>
