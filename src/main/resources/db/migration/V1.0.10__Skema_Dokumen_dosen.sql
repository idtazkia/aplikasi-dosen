CREATE TABLE t_dosen_document_profile (
    id character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    status character varying(255) NOT NULL,
    id_dosen_profile character varying(255) NOT NULL,
    id_jenis_dokumen_profile character varying(255) NOT NULL
);

CREATE TABLE m_jenis_pengajuan_dokumen_profile (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    nama character varying(255) NOT NULL,
    required boolean NOT NULL
);

ALTER TABLE ONLY m_jenis_pengajuan_dokumen_profile
    ADD CONSTRAINT m_jenis_pengajuan_dokumen_profile_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_dosen_document_profile
    ADD CONSTRAINT t_dosen_document_profile_pkey PRIMARY KEY (id);

ALTER TABLE ONLY t_dosen_document_profile
    ADD CONSTRAINT ukil3yiws3klf5dvkvmudv31f77 UNIQUE (id_dosen_profile, id_jenis_dokumen_profile);

ALTER TABLE ONLY t_dosen_document_profile
    ADD CONSTRAINT fklvedhs8r7hf95wk74idbu56nw FOREIGN KEY (id_dosen_profile) REFERENCES dosen(id);

ALTER TABLE ONLY t_dosen_document_profile
    ADD CONSTRAINT fknkdw6k2s9bodjeq29uxk2d0dy FOREIGN KEY (id_jenis_dokumen_profile) REFERENCES m_jenis_pengajuan_dokumen_profile(id);
