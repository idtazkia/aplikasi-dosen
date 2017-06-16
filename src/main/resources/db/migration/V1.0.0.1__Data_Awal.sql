INSERT INTO `dosen` VALUES ('5d9b24ff-21d9-44d6-8781-d6b5254c6bd2', 'Dosen 1');
INSERT INTO `dosen` VALUES ('47a1b8e8-fc1f-4082-8dea-6fa7a4899bbd', 'Dosen 2');

INSERT INTO `jenis_surat` VALUES ('6a20627f-65db-4a40-b92f-c575daaef370', 'SK Mengajar', 'SK Tugas Mengajar Mata Kuliah', 'Template Jenis Surat 1');
INSERT INTO `jenis_surat` VALUES ('af6d73bb-cf8e-4c47-be1c-9a93bc1f1e21', 'SK Bimbingan', 'SK Tugas Membimbing Skripsi', 'Template Jenis Surat 2');

INSERT INTO `surat_tugas` VALUES ('4fd3cf54-77dc-4523-9bab-36387987d99e', '123', '6a20627f-65db-4a40-b92f-c575daaef370',
                                  '5d9b24ff-21d9-44d6-8781-d6b5254c6bd2', '2017-04-14', '2017-04-15');
INSERT INTO `surat_tugas` VALUES ('5118e180-7055-4fc7-8394-e287b405840c', '321', 'af6d73bb-cf8e-4c47-be1c-9a93bc1f1e21',
                                  '47a1b8e8-fc1f-4082-8dea-6fa7a4899bbd', '2017-04-16', '2017-04-17');

INSERT INTO `jabatan` VALUES ('1', 'Asisten Ahli', 'Asisten ahli');
INSERT INTO `jabatan` VALUES ('2', 'Lektor', 'Lektor');
INSERT INTO `jabatan` VALUES ('3', 'Lektor Kepala', 'Lektor Kepala');
INSERT INTO `jabatan` VALUES ('4', 'Profesor', 'Profesor');
INSERT INTO `jabatan` VALUES ('5', 'Dokter', 'Dokter');

INSERT INTO `kategori_kegiatan` VALUES ('1', 'Pendidikan', 'Pendidikan');
INSERT INTO `kategori_kegiatan` VALUES ('2', 'Penelitian', 'Penelitian');
INSERT INTO `kategori_kegiatan` VALUES ('3', 'Pengabdian Masyarakat', 'Pengabdian Masyarakat');
INSERT INTO `kategori_kegiatan` VALUES ('4', 'Penunjang', 'Penunjang');

INSERT INTO `kategori_bukti_kegiatan` VALUES ('1', 'Bukti Pendidikan', 'Bukti Pendidikan');
INSERT INTO `kategori_bukti_kegiatan` VALUES ('2', 'Bukti Penelitian', 'Bukti Penelitian');
INSERT INTO `kategori_bukti_kegiatan` VALUES ('3', 'Bukti Pengabdian Masyarakat', 'Bukti Pengabdian Masyarakat');

INSERT INTO `program_studi` VALUES ('1454326a-7c20-452b-bbe1-88dddf786b64', 'Muhammad Fadlan', 'Teknik Informatika', 'S1');

INSERT INTO `jenis_kegiatan` VALUES ('1', 'I.A.1.a', 'Mengikuti pendidikan formal dan memperoleh gelar/sebutan/ijazah : Doktor / sederajat', 'Bukti tugas/izin belajar dan pindai ijazah asli', '1/periode penilaian', '200');
INSERT INTO `jenis_kegiatan` VALUES ('2', 'I.A.1.b', 'Mengikuti pendidikan formal dan memperoleh gelar/sebutan/ijazah : Magister/sederajat', 'Bukti tugas/izin belajar dan pindai ijazah asli', '1/periode penilaian', '150');
INSERT INTO `jenis_kegiatan` VALUES ('3', 'I.A.2 ', 'Mengikuti diklat prajabatan golongan III', 'Bukti tugas/izin belajar dan pindai ijazah asli', '1/periode penilaian', '3');
INSERT INTO `jenis_kegiatan` VALUES ('4', 'II.A.1.a ', 'Asisten Ahli untuk : beban mengajar 10 sks pertama ', 'Pindai SK penugasan asli dan bukti kinerja', '5', '0,5');
INSERT INTO `jenis_kegiatan` VALUES ('5', 'II.A.1.b ', 'Asisten Ahli untuk : beban mengajar 2 sks berikutnya', 'Pindai SK penugasan asli dan bukti kinerja', '0,5', '0,25');
INSERT INTO `jenis_kegiatan` VALUES ('6', 'II.A.2.a', 'Lektor/Lektor Kepala/Profesor untuk : beban mengajar 10 sks pertama', 'Pindai SK penugasan asli dan bukti kinerja', '10/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('7', 'II.A.2.b ', 'Lektor/Lektor Kepala/Profesor untuk : beban mengajar 2 sks berikutnya ', 'Pindai SK penugasan asli dan bukti kinerja', '1/semester', '0,5');
INSERT INTO `jenis_kegiatan` VALUES ('8','II.A.3.a ', 'Kegiatan pelaksanaan pendidikan untuk pendidikan dokter klinis - Melakukan pengajaran untuk peserta   pendidikan dokter melalui tindakan medik spesialistik', 'Pindai SK Penugasan dan bukti kinerja', '11/semester', '4');
INSERT INTO `jenis_kegiatan` VALUES ('9', 'II.A.3.b ', 'Kegiatan pelaksanaan pendidikan untuk pendidikan dokter klinis - Melakukan pengajaran   Konsultasi spesialis  kepada peserta   pendidikan dokter ', 'Pindai SK Penugasan dan bukti kinerja', '11/semester', '2');
INSERT INTO `jenis_kegiatan` VALUES ('10', 'II.A.3.c ', 'Kegiatan pelaksanaan pendidikan untuk pendidikan dokter klinis - Melakukan pemeriksaan luar dengan pembimbingan terhadap peserta   pendidikan dokter ', 'Pindai SK Penugasan dan bukti kinerja', '11/semester', '2');
INSERT INTO `jenis_kegiatan` VALUES ('11', 'II.A.3.d ', 'Kegiatan pelaksanaan pendidikan untuk pendidikan dokter klinis - Melakukan pemeriksaan dalam dengan pembimbingan terhadap peserta   pendidikan dokter ', 'Pindai SK Penugasan dan bukti kinerja', '11/semester', '3');
INSERT INTO `jenis_kegiatan` VALUES ('12', 'II.A.3.e ', 'Kegiatan pelaksanaan pendidikan untuk pendidikan dokter klinis - Menjadi saksi ahli dengan  pembimbingan terhadap peserta pendidikan dokter ', 'Pindai SK Penugasan dan bukti kinerja', '11/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('13', 'II.B ', 'Membimbing seminar mahasiswa (setiap mahasiswa)', 'Pindai SK penugasan asli dan bukti kinerja', '', '1');
INSERT INTO `jenis_kegiatan` VALUES ('14', 'II.C ', 'Membimbing KKN, Praktik Kerja Nyata, Praktik Kerja Lapangan (setiap semester) ', 'Pindai SK penugasan asli dan bukti kinerja', '', '1');
INSERT INTO `jenis_kegiatan` VALUES ('15', 'II.D.1.a', 'Pembimbing Utama per orang (setiap mahasiswa) - Disertasi', 'Pindai lembar pengesahan dan bukti kinerja', '4 lulusan/semester', '8');
INSERT INTO `jenis_kegiatan` VALUES ('16', 'II.D.1.b', 'Pembimbing Utama per orang (setiap mahasiswa) - Tesis', 'Pindai lembar pengesahan dan bukti kinerja', '6 lulusan/semester', '3');
INSERT INTO `jenis_kegiatan` VALUES ('17', 'II.D.1.c ', 'Pembimbing Utama per orang (setiap mahasiswa) - Skripsi', 'Pindai lembar pengesahan dan bukti kinerja', '8 lulusan/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('18','II.D.1.d', 'Pembimbing Utama per orang (setiap mahasiswa) - Laporan akhir studi', 'Pindai lembar pengesahan dan bukti kinerja', '10 lulusan/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('19', 'II.D.2.a', 'Pembimbing Pendamping/Pembantu per orang (setiap mahasiswa) : Disertasi', 'Pindai lembar pengesahan dan bukti kinerja', '4 lulusan/semester', '6');
INSERT INTO `jenis_kegiatan` VALUES ('20', 'II.D.2.b', 'Pembimbing Pendamping/Pembantu per orang (setiap mahasiswa) : Tesis ', 'Pindai lembar pengesahan dan bukti kinerja', '6 lulusan/semester', '2');
INSERT INTO `jenis_kegiatan` VALUES ('21', 'II.D.2.c', 'Pembimbing Pendamping/Pembantu per orang (setiap mahasiswa) : Skripsi ', 'Pindai lembar pengesahan dan bukti kinerja', '8 lulusan/semester', '0,5');
INSERT INTO `jenis_kegiatan` VALUES ('22', 'II.D.2.d', 'Pembimbing Pendamping/Pembantu per orang (setiap mahasiswa) : Laporan akhir studi ', 'Pindai lembar pengesahan dan bukti kinerja', '10 lulusan/semester', '0,5');
INSERT INTO `jenis_kegiatan` VALUES ('23', 'II.E.1 ', 'Bertugas sebagai penguji pada ujian akhir/Profesi*** (setiap mahasiswa) : Ketua penguji', 'Pindai SK penugasan, bukti kinerja dan undangan', '4 lulusan/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('24', 'II.E.2', 'Bertugas sebagai penguji pada ujian akhir/Profesi*** (setiap mahasiswa) : Anggota penguji ', 'Pindai SK penugasan, bukti kinerja dan undangan', '8 lulusan/semester', '0,5');
INSERT INTO `jenis_kegiatan` VALUES ('25', 'II.F ', 'Membina kegiatan mahasiswa di bidang akademik dan kemahasiswaan, termasuk dalam kegiatan ini adalah membimbing mahasiswa menghasilkan produk saintifik (setiap semester)', 'Pindai SK penugasan, dan bukti kinerja', '2 kegiatan/semester', '1');
INSERT INTO `jenis_kegiatan` VALUES ('26', 'II.G ', 'Mengembangkan program kuliah yang mempunyai nilai kebaharuan metode atau substansi (setiap produk) ', 'File produk', '1 mata kuliah/semester', '2');
INSERT INTO `jenis_kegiatan` VALUES ('27', 'II.H.1', 'Mengembangkan bahan pengajaran/ bahan kuliah yang mempunyai nilai kebaharuan (setiap produk), Buku ajar ', 'File produk', '1 buku/tahun', '20');
INSERT INTO `jenis_kegiatan` VALUES ('28', 'II.H.2 ', 'Mengembangkan bahan pengajaran/ bahan kuliah yang mempunyai nilai kebaharuan (setiap produk), Diktat,Modul, Petunjuk praktikum,      Model, Alat bantu, Audio visual, Naskah tutorial, Job sheet praktikum terkait dengan mata kuliah yang diampu ', 'File produk', '1 Produk/semester', '5');
INSERT INTO `jenis_kegiatan` VALUES ('29', 'II.I ', 'Menyampaikan orasi ilmiah di tingkat perguruan tinggi ', 'File produk', '2 orasi/semester', '5');
INSERT INTO `jenis_kegiatan` VALUES ('30', 'II.J.1 ', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Rektor ', 'Pindai SK Jabatan', '1 Jabatan/semester', '6');
INSERT INTO `jenis_kegiatan` VALUES ('31', 'II.J.2', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Wakil rektor/dekan/direktur program pasca sarjana/ketua lembaga ', 'Pindai SK Jabatan', '1 Jabatan/semester', '5');
INSERT INTO `jenis_kegiatan` VALUES ('32', 'II.J.3', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Ketua sekolah tinggi/pembantu dekan/asisten direktur program pasca sarjana/direktur politeknik/koordinator kopertis ', 'Pindai SK Jabatan', '1 Jabatan/semester', '4');
INSERT INTO `jenis_kegiatan` VALUES ('33', 'II.J.4', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Pembantu ketua sekolah tinggi/pembantu direktur politeknik', 'Pindai SK Jabatan', '1 Jabatan/semester', '4');
INSERT INTO `jenis_kegiatan` VALUES ('34', 'II.J.5', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Direktur akademi ', 'Pindai SK Jabatan', '1 Jabatan/semester', '4');
INSERT INTO `jenis_kegiatan` VALUES ('35', 'II.J.6', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Pembantu direktur politeknik, ketua jurusan/ bagian pada universitas/ institut/sekolah tinggi', 'Pindai SK Jabatan', '1 Jabatan/semester', '3');
INSERT INTO `jenis_kegiatan` VALUES ('36', 'II.J.7', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Pembantu direktur akademi/ketua jurusan/ketua prodi pada universitas/politeknik/akademi, sekretaris jurusan/bagian pada universitas/institut/sekolah tinggi ', 'Pindai SK Jabatan', '1 Jabatan/semester', '3');
INSERT INTO `jenis_kegiatan` VALUES ('37', 'II.J.8', 'Menduduki jabatan pimpinan perguruan tinggi (setiap semester) : Sekretaris jurusan pada politeknik/akademi dan kepala laboratorium (bengkel) universitas/institut/sekolah tinggi/politeknik/akademi ', 'Pindai SK Jabatan', '1 Jabatan/semester', '3');
INSERT INTO `jenis_kegiatan` VALUES ('38', 'II.K.1 ', 'Membimbing dosen yang mempunyai jabatan akademik lebih rendah setiap semester (bagi dosen Lektor Kepala ke atas) : Pembimbing pencangkokan ', 'Pindai SK Penugasan, dan bukti kinerja', '1 orang', '2');
INSERT INTO `jenis_kegiatan` VALUES ('39', 'II.K.2 ', 'Membimbing dosen yang mempunyai jabatan akademik lebih rendah setiap semester (bagi dosen Lektor Kepala ke atas) : Reguler', 'Pindai SK Penugasan, dan bukti kinerja', '1 orang', '1');
INSERT INTO `jenis_kegiatan` VALUES ('40', 'II.L.1', 'Melaksanakan kegiatan detasering dan pencangkokan di luar institusi tempat bekerja setiap semester (bagi dosen Lektor kepala ke atas) : Detasering', 'Pindai SK Penugasan, dan bukti kinerja', '1 orang', '5');
INSERT INTO `jenis_kegiatan` VALUES ('41', 'II.L.2', 'Melaksanakan kegiatan detasering dan pencangkokan di luar institusi tempat bekerja setiap semester (bagi dosen Lektor kepala ke atas) : Pencangkokan', 'Pindai SK Penugasan, dan bukti kinerja', '1 orang', '4');
INSERT INTO `jenis_kegiatan` VALUES ('42', 'II.M.1', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya lebih dari 960 jam', 'Pindai sertifikat asli', '', '15');
INSERT INTO `jenis_kegiatan` VALUES ('43', 'II.M.2', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara  641- 960 jam', 'Pindai sertifikat asli', '', '9');
INSERT INTO `jenis_kegiatan` VALUES ('44', 'II.M.3', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara 481- 640 jam ', 'Pindai sertifikat asli', '', '6');
INSERT INTO `jenis_kegiatan` VALUES ('45', 'II.M.4', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara 161- 480 jam ', 'Pindai sertifikat asli', '', '3');
INSERT INTO `jenis_kegiatan` VALUES ('46', 'II.M.5', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara   81- 160 jam', 'Pindai sertifikat asli', '', '2');
INSERT INTO `jenis_kegiatan` VALUES ('47', 'II.M.6', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara 30 - 80 jam ', 'Pindai sertifikat asli', '', '1');
INSERT INTO `jenis_kegiatan` VALUES ('48', 'II.M.7', 'Melaksanakan pengembangan diri untuk meningkatkan kompetensi : Lamanya antara 10 - 30 jam ', 'Pindai sertifikat asli', '', '0,5');

