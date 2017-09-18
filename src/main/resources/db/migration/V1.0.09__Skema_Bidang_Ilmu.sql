CREATE TABLE m_bidang_ilmu (
    id character varying(255) NOT NULL,
    keterangan character varying(255),
    kode character varying(255) NOT NULL,
    nama character varying(255) NOT NULL
);


ALTER TABLE ONLY m_bidang_ilmu
    ADD CONSTRAINT m_bidang_ilmu_pkey PRIMARY KEY (id);
