CREATE TABLE provinsi (
    id character varying(36) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL
);

CREATE TABLE kota (
    id character varying(36) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL,
    id_provinsi character varying(36) NOT NULL
);

CREATE TABLE kecamatan (
    id character varying(36) NOT NULL,
    kode character varying(10) NOT NULL,
    nama character varying(225) NOT NULL,
    id_kota character varying(36) NOT NULL
);

-- ALTER TABLE ONLY provinsi
--     ADD CONSTRAINT provinsi_pkey PRIMARY KEY (id);
-- 
-- ALTER TABLE ONLY kota
--     ADD CONSTRAINT kota_pkey PRIMARY KEY (id);
-- 
-- ALTER TABLE ONLY kecamatan
--     ADD CONSTRAINT kecamatan_pkey PRIMARY KEY (id);
-- 
-- ALTER TABLE ONLY kota
--     ADD CONSTRAINT fk6gfr8sod7jv1vrq6a28uwpfoq FOREIGN KEY (id_provinsi) REFERENCES provinsi(id);
-- 
-- ALTER TABLE ONLY kecamatan
--     ADD CONSTRAINT fkbhnomh901bg3qqkogs1l0gad7 FOREIGN KEY (id_kota) REFERENCES kota(id);