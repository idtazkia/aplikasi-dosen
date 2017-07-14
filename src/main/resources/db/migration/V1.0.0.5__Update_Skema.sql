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

-- ALTER TABLE KEGIATAN
ALTER TABLE t_kegiatan_dosen
    ADD COLUMN id_satuan_hasil_kegiatan character varying(255) NOT NULL;

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fkg28frw8tqmvojc8cyjn1jvcjr FOREIGN KEY (id_satuan_hasil_kegiatan) REFERENCES satuan_hasil_kegiatan(id);

ALTER TABLE t_kegiatan_dosen
    ADD COLUMN volume integer;

-- data satuan hasil kegiatan

INSERT INTO satuan_hasil_kegiatan (id, kode, nama) VALUES 
('1', 'kls', 'Kelas'),
('2', 'mhs', 'Mahasiswa');

-- ALTER TABLE KBM, ADD COLUMN SKS AND SKS KEGIATAN
ALTER TABLE t_kegiatan_belajar_mengajar
    ADD COLUMN sks_mata_kuliah integer NOT NULL;

ALTER TABLE t_kegiatan_belajar_mengajar
    ADD COLUMN sks_kegiatan integer NOT NULL;

-- FIXING kolom sks master mata kuliah

ALTER TABLE mata_kuliah 
    ALTER COLUMN sks TYPE integer USING (sks::integer);