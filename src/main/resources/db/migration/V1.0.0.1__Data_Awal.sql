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
