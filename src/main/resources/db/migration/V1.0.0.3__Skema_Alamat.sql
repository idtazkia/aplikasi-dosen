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


ALTER TABLE data_dosen ADD CONSTRAINT fkegnu35g4ojgslgih62so51we0r FOREIGN KEY (provinsi) REFERENCES provinsi (id);
ALTER TABLE data_dosen ADD CONSTRAINT fkegnu35g4ojslgihd63so51we0r FOREIGN KEY (kota) REFERENCES kota (id);
ALTER TABLE data_dosen ADD CONSTRAINT fkegnu35gs4ojslgih64so51we0r FOREIGN KEY (kecamatan) REFERENCES kecamatan (id);
