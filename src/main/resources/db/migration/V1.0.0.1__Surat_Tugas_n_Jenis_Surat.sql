ALTER TABLE dosen
  ADD COLUMN nama VARCHAR(255);

CREATE TABLE jenis_surat (
  id   VARCHAR(36),
  nama VARCHAR(255),
  keterangan VARCHAR(255),
  template VARCHAR(255),
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