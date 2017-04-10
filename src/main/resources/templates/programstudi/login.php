<?php
include 'koneksi.php';

$Nama = $_POST['nama'];
$Keterangan = $_POST['keterangan'];
$Jenjang = $_POST['jenjang'];
$query = mysql_query("select * from admin where nama='$dhika' keterangan='$dhika' and 'jenjang='$S1");
$cek = mysql_num_rows($query);
echo $cek;
?>