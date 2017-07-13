-- CREATE SKEMA TABLE satuan_hasil_kegiatan

CREATE TABLE satuan_hasil_kegiatan (
    id character varying(255) NOT NULL,
    kode character varying(255) NOT NULL,
    nama character varying(255) NOT NULL
);

ALTER TABLE ONLY satuan_hasil_kegiatan
    ADD CONSTRAINT m_satuan_hasil_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY satuan_hasil_kegiatan
    ADD CONSTRAINT uk_cs41kuj5tp7gcykq1fxrx8u9m UNIQUE (kode);

-- ALTER TABLE KEGIATAN_BELAJAR_MENGAJAR

ALTER TABLE t_kegiatan_belajar_mengajar
    ADD COLUMN id_satuan_hasil_kegiatan character varying(255) NOT NULL;

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT fkg28frw8tqmvojc8cyjn1jvcjr FOREIGN KEY (id_satuan_hasil_kegiatan) REFERENCES satuan_hasil_kegiatan(id);

ALTER TABLE t_kegiatan_belajar_mengajar
    RENAME COLUMN jumlah_mahasiswa TO volume;

-- data satuan hasil kegiatan

INSERT INTO jenis_surat (id, kode, nama) VALUES 
('1', 'kls', 'Kelas'),
('2', 'mhs', 'Mahasiswa');