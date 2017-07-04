
CREATE TABLE IF NOT EXISTS t_bukti_penugasan (
    id varchar(36),
    nama varchar(255) NOT NULL,
    url varchar(255) NOT NULL,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS t_bukti_kinerja (
    id varchar(36),
    nama varchar(255) NOT NULL,
    url varchar(255) NOT NULL,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS t_kegiatan_belajar_mengajar (
    id varchar(36),
    keterangan varchar(255),
    periode varchar(255) NOT NULL,
    semester varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    jumlah_mahasiswa int(5),
    id_mata_kuliah varchar(255) NOT NULL,
    id_dosen varchar(255) NOT NULL,
    id_bukti_kinerja varchar(255),
    id_bukti_penugasan varchar(255),
    primary key (id),
    FOREIGN KEY (id_mata_kuliah) REFERENCES mata_kuliah (id),
    FOREIGN KEY (id_dosen) REFERENCES dosen (id),
    FOREIGN KEY (id_bukti_kinerja) REFERENCES t_bukti_kinerja (id),
    FOREIGN KEY (id_bukti_penugasan) REFERENCES t_bukti_penugasan (id)
);

CREATE TABLE IF NOT EXISTS t_kegiatan_dosen (
    id varchar(36),
    keterangan varchar(255),
    periode varchar(255) NOT NULL,
    semester varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    sks int(5),
    id_jenis_kegiatan varchar(255) NOT NULL,
    id_kategori_kegiatan varchar(255) NOT NULL,
    id_dosen varchar(255) NOT NULL,
    id_bukti_kinerja varchar(255),
    id_bukti_penugasan varchar(255),
    primary key (id),
    FOREIGN KEY (id_jenis_kegiatan) REFERENCES jenis_kegiatan (id),
    FOREIGN KEY (id_kategori_kegiatan) REFERENCES kategori_kegiatan (id),
    FOREIGN KEY (id_dosen) REFERENCES dosen (id),
    FOREIGN KEY (id_bukti_kinerja) REFERENCES t_bukti_kinerja (id),
    FOREIGN KEY (id_bukti_penugasan) REFERENCES t_bukti_penugasan (id)
);


