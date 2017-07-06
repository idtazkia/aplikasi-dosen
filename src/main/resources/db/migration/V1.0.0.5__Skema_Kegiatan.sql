CREATE TABLE IF NOT EXISTS t_kegiatan_belajar_mengajar (
    id varchar(36),
    keterangan varchar(255),
    periode varchar(255) NOT NULL,
    semester varchar(255) NOT NULL,
    status varchar(255) NOT NULL,
    jumlah_mahasiswa int(5),
    id_mata_kuliah varchar(255) NOT NULL,
    id_bukti_kinerja varchar(255),
    id_bukti_penugasan varchar(255),
    primary key (id)
);

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

