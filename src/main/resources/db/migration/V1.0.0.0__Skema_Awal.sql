CREATE TABLE c_password_reset_token (
    id character varying(255) NOT NULL,
    expiry_date timestamp without time zone,
    token character varying(255) NOT NULL,
    id_user character varying(255) NOT NULL
);

CREATE TABLE c_security_permission (
    id character varying(255) NOT NULL,
    permission_label character varying(255) NOT NULL,
    permission_value character varying(255) NOT NULL
);

CREATE TABLE c_security_role (
    id character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL
);

CREATE TABLE c_security_role_permission (
    id_role character varying(255) NOT NULL,
    id_permission character varying(255) NOT NULL
);

CREATE TABLE c_security_user (
    id character varying(255) NOT NULL,
    active boolean NOT NULL,
    username character varying(255) NOT NULL,
    id_role character varying(255) NOT NULL
);

CREATE TABLE c_security_user_password (
    id_user character varying(36) NOT NULL,
    password character varying(255) NOT NULL
);

CREATE TABLE dosen (
id character varying(255) NOT NULL,
  alamat character varying(255) NOT NULL,
  alamat_pt character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  golongan character varying(255) NOT NULL,
  jenis character varying(255) NOT NULL,
  karpeg character varying(255) NOT NULL,
  kode_bidang_ilmu character varying(255) NOT NULL,
  nama character varying(255) NOT NULL,
  nama_bidang_ilmu character varying(255) NOT NULL,
  nama_pt character varying(255) NOT NULL,
  nidn character varying(255) NOT NULL,
  nip character varying(255) NOT NULL,
  no_telp character varying(255) NOT NULL,
  lulusan_s1 character varying(255),
  lulusan_s2 character varying(255),
  lulusan_s3 character varying(255),
  tempat_lahir character varying(255) NOT NULL,
  tanggal_lahir date NOT NULL,
  asesor_1 character varying(255),
  asesor_2 character varying(255),
  id_jabatan character varying(255) NOT NULL,
  id_kecamatan character varying(255) NOT NULL,
  id_kota character varying(255) NOT NULL,
  id_program_studi character varying(255) NOT NULL,
  id_provinsi character varying(255) NOT NULL,
  id_user character varying(255) NOT NULL
);

CREATE TABLE jabatan (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL
);

CREATE TABLE jenis_kegiatan (
    id character varying(255) NOT NULL,
    angka_kredit character varying(255),
    batas_maksimal character varying(255),
    bukti character varying(255),
    nama character varying(255) NOT NULL,
    id_kategori_kegiatan character varying(255) NOT NULL
);

CREATE TABLE jenis_surat (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL,
    template character varying(255)
);

CREATE TABLE kategori_bukti_kegiatan (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL
);

CREATE TABLE jenis_bukti_kegiatan (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL
);

CREATE TABLE kategori_kegiatan (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL
);

CREATE TABLE kecamatan (
    id character varying(255) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL,
    id_kota character varying(255) NOT NULL
);

CREATE TABLE kota (
    id character varying(255) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL,
    id_provinsi character varying(255) NOT NULL
);

CREATE TABLE provinsi (
    id character varying(255) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL
);

CREATE TABLE mata_kuliah (
    id character varying(255) NOT NULL,
    kode character varying(255) NOT NULL,
    konsentrasi character varying(255),
    nama character varying(255),
    sks character varying(255),
    program_studi character varying(255) NOT NULL
);

CREATE TABLE poin_kegiatan (
    id character varying(255) NOT NULL,
    nilai numeric(19,2) NOT NULL,
    nilai_maksimum numeric(19,2) NOT NULL,
    id_jabatan character varying(255) NOT NULL,
    id_jenis_kegiatan character varying(255) NOT NULL,
    CONSTRAINT poin_kegiatan_nilai_check CHECK (((nilai <= (100)::numeric) AND (nilai >= (0)::numeric))),
    CONSTRAINT poin_kegiatan_nilai_maksimum_check CHECK (((nilai_maksimum <= (100)::numeric) AND (nilai_maksimum >= (0)::numeric)))
);

CREATE TABLE m_fakultas(
  id character varying(255) NOT NULL,
  keterangan character varying(255),
  nama character varying(255) NOT NULL
);

CREATE TABLE program_studi (
    id character varying(255) NOT NULL,
    jenjang character varying(255) NOT NULL,
    keterangan character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    id_fakultas character varying(255)
);

CREATE TABLE surat_tugas (
    id character varying(255) NOT NULL,
    no_surat character varying(255) NOT NULL,
    tanggal_mulai timestamp without time zone NOT NULL,
    tanggal_selesai timestamp without time zone NOT NULL,
    id_jenis_surat character varying(255) NOT NULL,
    id_dosen character varying(255) NOT NULL
);

CREATE TABLE t_bukti_kinerja (
    id character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    url character varying(255) NOT NULL
);

CREATE TABLE t_bukti_penugasan (
    id character varying(255) NOT NULL,
    nama character varying(255) NOT NULL,
    url character varying(255) NOT NULL
);

CREATE TABLE t_kegiatan_belajar_mengajar (
    id character varying(255) NOT NULL,
    jumlah_mahasiswa integer,
    keterangan character varying(255),
    periode character varying(255) NOT NULL,
    semester character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    id_bukti_kinerja character varying(255),
    id_bukti_penugasan character varying(255),
    id_dosen character varying(255) NOT NULL,
    id_mata_kuliah character varying(255) NOT NULL
);

CREATE TABLE t_kegiatan_dosen (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    periode character varying(255) NOT NULL,
    semester character varying(255) NOT NULL,
    sks integer NOT NULL,
    status character varying(255) NOT NULL,
    id_bukti_kinerja character varying(255),
    id_bukti_penugasan character varying(255),
    id_dosen character varying(255) NOT NULL,
    id_jenis_kegiatan character varying(255) NOT NULL,
    id_kategori_kegiatan character varying(255) NOT NULL
);

ALTER TABLE ONLY c_password_reset_token
    ADD CONSTRAINT c_password_reset_token_pkey PRIMARY KEY (id);

ALTER TABLE ONLY c_security_permission
    ADD CONSTRAINT c_security_permission_pkey PRIMARY KEY (id);

ALTER TABLE ONLY c_security_role_permission
    ADD CONSTRAINT c_security_role_permission_pkey PRIMARY KEY (id_role, id_permission);

ALTER TABLE ONLY c_security_role
    ADD CONSTRAINT c_security_role_pkey PRIMARY KEY (id);

ALTER TABLE ONLY c_security_user_password
    ADD CONSTRAINT c_security_user_password_pkey PRIMARY KEY (id_user);

ALTER TABLE ONLY c_security_user
    ADD CONSTRAINT c_security_user_pkey PRIMARY KEY (id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT dosen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jabatan
    ADD CONSTRAINT jabatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jenis_kegiatan
    ADD CONSTRAINT jenis_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY jenis_surat
    ADD CONSTRAINT jenis_surat_pkey PRIMARY KEY (id);

ALTER TABLE ONLY kategori_bukti_kegiatan
    ADD CONSTRAINT kategori_bukti_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY kategori_kegiatan
    ADD CONSTRAINT kategori_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY kecamatan
    ADD CONSTRAINT kecamatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY kota
    ADD CONSTRAINT kota_pkey PRIMARY KEY (id);

ALTER TABLE ONLY mata_kuliah
    ADD CONSTRAINT mata_kuliah_pkey PRIMARY KEY (id);

ALTER TABLE ONLY poin_kegiatan
    ADD CONSTRAINT poin_kegiatan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY m_fakultas
    ADD CONSTRAINT m_fakultas_pkey PRIMARY KEY (id);

ALTER TABLE ONLY program_studi
    ADD CONSTRAINT program_studi_pkey PRIMARY KEY (id);

ALTER TABLE ONLY provinsi
    ADD CONSTRAINT provinsi_pkey PRIMARY KEY (id);

ALTER TABLE ONLY surat_tugas
    ADD CONSTRAINT surat_tugas_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_bukti_kinerja
    ADD CONSTRAINT t_bukti_kinerja_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_bukti_penugasan
    ADD CONSTRAINT t_bukti_penugasan_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT t_kegiatan_belajar_mengajar_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT t_kegiatan_dosen_pkey PRIMARY KEY (id);

ALTER TABLE ONLY kecamatan
    ADD CONSTRAINT uk_9l5531pt3rreqe61ywh5kpt7 UNIQUE (kode);

ALTER TABLE ONLY c_security_user
    ADD CONSTRAINT uk_at8if7a9lnl90wxllb9divpdf UNIQUE (username);

ALTER TABLE ONLY kota
    ADD CONSTRAINT uk_bmkxy9rqvvudv7f8ut7gakceu UNIQUE (kode);

ALTER TABLE ONLY c_password_reset_token
    ADD CONSTRAINT uk_feu70fo876metrg5g46eh21nf UNIQUE (token);

ALTER TABLE ONLY provinsi
    ADD CONSTRAINT uk_fiyvt9gp82mjrujlxi5tvk2n8 UNIQUE (kode);

ALTER TABLE ONLY c_security_role
    ADD CONSTRAINT uk_hliaoojt6u3a11d8svttju10l UNIQUE (name);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT uk_jqvwxko6fkojn60qeqn7np7hg UNIQUE (email);

ALTER TABLE ONLY c_security_permission
    ADD CONSTRAINT uk_k4suda9cvcsoikdgquscypmt6 UNIQUE (permission_value);

ALTER TABLE ONLY c_password_reset_token
    ADD CONSTRAINT uk_tf9agbefkf39bqj62wprw2vo0 UNIQUE (id_user);

ALTER TABLE ONLY program_studi
    ADD CONSTRAINT fksukwm0lnhl9g0vigm5unme7l8 FOREIGN KEY (id_fakultas) REFERENCES m_fakultas (id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fk195t5s845u7kfimjkranyg97v FOREIGN KEY (id_dosen) REFERENCES dosen(id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fk1cc9fat2hc1hno64ui620e7fu FOREIGN KEY (id_jenis_kegiatan) REFERENCES jenis_kegiatan(id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fk56vi6x7iew4f6oefyuihnfvl8 FOREIGN KEY (id_bukti_kinerja) REFERENCES t_bukti_kinerja(id);

ALTER TABLE ONLY kota
    ADD CONSTRAINT fk6gfr8sod7jv1vrq6a28uwpfoq FOREIGN KEY (id_provinsi) REFERENCES provinsi(id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fk73p7xgo2t0hgl08yo1otv860a FOREIGN KEY (id_kategori_kegiatan) REFERENCES kategori_kegiatan(id);

ALTER TABLE ONLY c_security_user_password
    ADD CONSTRAINT fk80arji7i1u0styufcy8b91it5 FOREIGN KEY (id_user) REFERENCES c_security_user(id);

ALTER TABLE ONLY surat_tugas
    ADD CONSTRAINT fk82cwg35aphch84b166h63ta32 FOREIGN KEY (id_dosen) REFERENCES dosen(id);

ALTER TABLE ONLY mata_kuliah
    ADD CONSTRAINT fk8i6tjrnh9cm5g5l28ja5yofro FOREIGN KEY (program_studi) REFERENCES program_studi(id);

ALTER TABLE ONLY poin_kegiatan
    ADD CONSTRAINT fk97viusy9bph6is3q0v61r1ftf FOREIGN KEY (id_jenis_kegiatan) REFERENCES jenis_kegiatan(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fk9enaks8ia1gkxp4ftstctyabc FOREIGN KEY (id_kota) REFERENCES kota(id);

ALTER TABLE ONLY kecamatan
    ADD CONSTRAINT fkbhnomh901bg3qqkogs1l0gad7 FOREIGN KEY (id_kota) REFERENCES kota(id);

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT fkckwjsm9290iu1ytbi8xhlyyn1 FOREIGN KEY (id_dosen) REFERENCES dosen(id);

ALTER TABLE ONLY c_security_user
    ADD CONSTRAINT fke5ychpyk27l8vj47v36mrn0s1 FOREIGN KEY (id_role) REFERENCES c_security_role(id);

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT fkebrrv9w21o76cyucop4mswqdu FOREIGN KEY (id_bukti_penugasan) REFERENCES t_bukti_penugasan(id);

ALTER TABLE ONLY c_security_role_permission
    ADD CONSTRAINT fkg9os4isbs19ssfahravousxes FOREIGN KEY (id_role) REFERENCES c_security_role(id);

ALTER TABLE ONLY jenis_kegiatan
    ADD CONSTRAINT fkgn9cuur2ngmdfa036rx54vm74 FOREIGN KEY (id_kategori_kegiatan) REFERENCES kategori_kegiatan(id);

ALTER TABLE ONLY t_kegiatan_dosen
    ADD CONSTRAINT fkiitnyy23oytc4w525uyx8ycjh FOREIGN KEY (id_bukti_penugasan) REFERENCES t_bukti_penugasan(id);

ALTER TABLE ONLY surat_tugas
    ADD CONSTRAINT fkjvxxcdgmdykb3je5ldqy987ro FOREIGN KEY (id_jenis_surat) REFERENCES jenis_surat(id);

ALTER TABLE ONLY poin_kegiatan
    ADD CONSTRAINT fklc3hsiwuyqmi4u3qtietmbn63 FOREIGN KEY (id_jabatan) REFERENCES jabatan(id);

ALTER TABLE ONLY c_password_reset_token
    ADD CONSTRAINT fkm4d87sx4kfcxn34v23i75vh91 FOREIGN KEY (id_user) REFERENCES c_security_user(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkmk31jjakwfd5tk5rycltwwjem FOREIGN KEY (id_kecamatan) REFERENCES kecamatan(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fknj0cck97cis98qbp4cq92xj07 FOREIGN KEY (id_provinsi) REFERENCES provinsi(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkls0hin3tdsn1kdoprm9aoj698 FOREIGN KEY (asesor_1)
         REFERENCES dosen (id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkdfhg5c3ne76cntu32rp2bw9l4 FOREIGN KEY (asesor_2)
         REFERENCES dosen (id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkl9dhq2udgvjdgkhh5lgqpse6u FOREIGN KEY (id_program_studi)
         REFERENCES program_studi (id);

ALTER TABLE ONLY c_security_role_permission
    ADD CONSTRAINT fknqcv2qdac1phe20qqnyi6n1n FOREIGN KEY (id_permission) REFERENCES c_security_permission(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkq2kubmxq6xcbj3ssbjka7p9ul FOREIGN KEY (id_jabatan) REFERENCES jabatan(id);

ALTER TABLE ONLY dosen
    ADD CONSTRAINT fkr029jw3cx56xxfonp0er3o4rx FOREIGN KEY (id_user) REFERENCES c_security_user(id);

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT fktflaytuvu52x374l9xx5aacjd FOREIGN KEY (id_bukti_kinerja) REFERENCES t_bukti_kinerja(id);

ALTER TABLE ONLY t_kegiatan_belajar_mengajar
    ADD CONSTRAINT fktfna2ov8bswjnencn29n9tur5 FOREIGN KEY (id_mata_kuliah) REFERENCES mata_kuliah(id);

