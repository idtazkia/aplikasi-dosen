create table dosen (
  id varchar(255),
  nama VARCHAR(255),
  primary key (id)
);

CREATE TABLE jenis_surat (
  id   VARCHAR(36),
  nama VARCHAR(255),
  keterangan VARCHAR(255),
  template VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE jabatan (
  id              VARCHAR(36),
  nama            VARCHAR(255),
  keterangan      VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE jenis_kegiatan (
  id              VARCHAR(36),
  kode            VARCHAR(255),
  nama            VARCHAR(255),
  bukti           VARCHAR(255),
  batas_maksimal   VARCHAR(255),
  angka_kredit     VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE surat_tugas (
  id              VARCHAR(36),
  no_surat        VARCHAR(255),
  id_jenis_surat  VARCHAR(36),
  id_dosen        VARCHAR(36),
  tanggal_mulai   DATETIME,
  tanggal_selesai DATETIME,
  PRIMARY KEY (id),
  FOREIGN KEY (id_jenis_surat) REFERENCES jenis_surat (id),
  FOREIGN KEY (id_dosen) REFERENCES dosen (id)
);

CREATE TABLE poin_kegiatan (
  id                  VARCHAR(36),
  id_jabatan          VARCHAR(36),
  id_jenis_kegiatan   VARCHAR(36),
  nilai_maksimum      DECIMAL(4,2),
  nilai               DECIMAL(4,2),
  PRIMARY KEY (id),
  FOREIGN KEY (id_jabatan) REFERENCES jabatan (id),
  FOREIGN KEY (id_jenis_kegiatan) REFERENCES jenis_kegiatan (id)
);

CREATE TABLE kategori_kegiatan (
  id              VARCHAR(36),
  nama            VARCHAR(255),
  keterangan      VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE kategori_bukti_kegiatan (
  id              VARCHAR(36),
  nama            VARCHAR(255),
  keterangan      VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE program_studi (
id          VARCHAR(36),
nama        VARCHAR(255),
keterangan  VARCHAR(255),
jenjang     VARCHAR(36),
PRIMARY KEY (id)
);

CREATE TABLE mata_kuliah(
  id              VARCHAR(36),
  kode            VARCHAR(255),
  nama            VARCHAR(255),
  program_studi   VARCHAR(255),
  konsentrasi     VARCHAR(255),
  sks             VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY(program_studi) REFERENCES program_studi(id) 
);
