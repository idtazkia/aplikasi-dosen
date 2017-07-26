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

-- UPDATE file pada kegiatan menjadi multiple
ALTER TABLE t_kegiatan_belajar_mengajar
    DROP COLUMN id_bukti_penugasan CASCADE;

ALTER TABLE t_kegiatan_belajar_mengajar
    DROP COLUMN id_bukti_kinerja CASCADE;

ALTER TABLE t_kegiatan_dosen
    DROP COLUMN id_bukti_penugasan CASCADE;

ALTER TABLE t_kegiatan_dosen
    DROP COLUMN id_bukti_kinerja CASCADE;

ALTER TABLE t_bukti_penugasan
    ADD COLUMN id_kegiatan_belajar_mengajar character varying(255) NOT NULL;

ALTER TABLE ONLY t_bukti_penugasan
    ADD CONSTRAINT fka2brvebpx0ct0i1fv6v2a9dq2 FOREIGN KEY (id_kegiatan_belajar_mengajar) REFERENCES t_kegiatan_belajar_mengajar(id);

ALTER TABLE t_bukti_kinerja
    ADD COLUMN id_kegiatan_belajar_mengajar character varying(255) NOT NULL;

ALTER TABLE ONLY t_bukti_kinerja
    ADD CONSTRAINT fk74lnkd78t2xg49y9xd07ct0vp FOREIGN KEY (id_kegiatan_belajar_mengajar) REFERENCES t_kegiatan_belajar_mengajar(id);

CREATE TABLE t_bukti_kinerja_kegiatan (
    id character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    url character varying(255) NOT NULL,
    id_kegiatan_dosen character varying(255) NOT NULL
);

CREATE TABLE t_bukti_penugasan_kegiatan (
    id character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    url character varying(255) NOT NULL,
    id_kegiatan_dosen character varying(255) NOT NULL
);

ALTER TABLE ONLY t_bukti_kinerja_kegiatan
    ADD CONSTRAINT t_bukti_kinerja_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_bukti_penugasan_kegiatan
    ADD CONSTRAINT t_bukti_penugasan_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_bukti_kinerja_kegiatan
    ADD CONSTRAINT fk176rp53h12q44hmlle3v9648 FOREIGN KEY (id_kegiatan_dosen) REFERENCES t_kegiatan_dosen(id);

ALTER TABLE ONLY t_bukti_penugasan_kegiatan
    ADD CONSTRAINT fkbdj8iish0huo6hlfy5kwn044j FOREIGN KEY (id_kegiatan_dosen) REFERENCES t_kegiatan_dosen(id);

-- TAMBAH tanggal mulai dan tanggal selesai pada kegiatan
ALTER TABLE t_kegiatan_belajar_mengajar
    ADD COLUMN tgl_mulai date;

ALTER TABLE t_kegiatan_belajar_mengajar
    ADD COLUMN tgl_selesai date;

ALTER TABLE t_kegiatan_dosen
    ADD COLUMN tgl_mulai date;

ALTER TABLE t_kegiatan_dosen
    ADD COLUMN tgl_selesai date;

