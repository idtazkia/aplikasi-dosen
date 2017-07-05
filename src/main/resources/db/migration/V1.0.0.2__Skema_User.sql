CREATE TABLE IF NOT EXISTS c_security_permission (
  id varchar(255) NOT NULL,
  permission_label varchar(255) NOT NULL,
  permission_value varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS c_security_role (
  id varchar(255) NOT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS c_security_role_permission (
  id_role varchar(255) NOT NULL,
  id_permission varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS c_security_user (
    id varchar(36),
    username varchar(255) NOT NULL,
    active boolean NOT NULL,
    id_role varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS c_security_user_password (
    id_user varchar(36) NOT NULL,
    password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS c_password_reset_token (
  id varchar(255) not null,
  token varchar(255) not null,
  id_user varchar (36) not null,
  expiry_date timestamp not null,
    primary key (id),
    unique (token),
    foreign key (id_user) references c_security_user (id)
);

ALTER TABLE dosen
   ADD COLUMN id_user varchar(255);

-- ALTER TABLE dosen
--   ADD CONSTRAINT FK9x3oscn4lr5ahn1vep9userr FOREIGN KEY (id_user) REFERENCES c_security_user (id);
-- 
-- ALTER TABLE c_security_permission
--   ADD PRIMARY KEY (id),
--   ADD UNIQUE KEY UK_k4suda9cvcsoikdgquscypmt6 (permission_value);
-- 
-- ALTER TABLE c_security_role
--   ADD PRIMARY KEY (id),
--   ADD UNIQUE KEY UK_hliaoojt6u3a11d8svttju10l (name);
-- 
-- ALTER TABLE c_security_role_permission
--   ADD PRIMARY KEY (id_role,id_permission);
-- 
-- ALTER TABLE c_security_user
--   ADD PRIMARY KEY (id),
--   ADD UNIQUE KEY UK_at8if7a9lnl90wxllb9divpdf (username);
-- 
-- ALTER TABLE c_security_user_password
--   ADD PRIMARY KEY (id_user);
-- 
-- ALTER TABLE c_security_role_permission
--   ADD CONSTRAINT FKg9os4isbs19ssfahravousxes FOREIGN KEY (id_role) REFERENCES c_security_role (id),
--   ADD CONSTRAINT FKnqcv2qdac1phe20qqnyi6n1n FOREIGN KEY (id_permission) REFERENCES c_security_permission (id);
-- 
-- ALTER TABLE c_security_user
--   ADD CONSTRAINT FKe5ychpyk27l8vj47v36mrn0s1 FOREIGN KEY (id_role) REFERENCES c_security_role (id);
-- 
-- ALTER TABLE c_security_user_password
--   ADD CONSTRAINT FKe5ychpyk27l8vj47v36passwd FOREIGN KEY (id_user) REFERENCES c_security_user (id);

INSERT INTO c_security_permission (id, permission_label, permission_value) VALUES
('KEGIATAN', 'Halaman Kegiatan', 'ROLE_KEGIATAN'),
('KEGIATAN_ALL', 'All Kegiatan Dosen', 'ROLE_KEGIATAN_ALL'),
('MASTER_INSTITUSI', 'Halaman Institusi', 'ROLE_MASTER_INSTITUSI'),
('MASTER_JENIS_SURAT', 'Halaman Jenis Surat', 'ROLE_MASTER_JENIS_SURAT'),
('MASTER_JABATAN', 'Halaman Jabatan', 'ROLE_MASTER_JABATAN'),
('MASTER_PROGRAM_STUDI', 'Halaman Program Studi', 'ROLE_MASTER_PROGRAM_STUDI'),
('MASTER_MATA_KULIAH', 'Halaman Mata Kuliah', 'ROLE_MASTER_MATA_KULIAH'),
('MASTER_SURAT_TUGAS', 'Halaman Surat Tugas', 'ROLE_MASTER_SURAT_TUGAS'),
('MASTER_KATEGORI_KEGIATAN', 'Halaman Kategori Kegiatan', 'ROLE_MASTER_KATEGORI_KEGIATAN'),
('MASTER_JENIS_KEGIATAN', 'Halaman Jenis Kegiatan', 'ROLE_MASTER_JENIS_KEGIATAN'),
('MASTER_KATEGORI_BUKTI_KEGIATAN', 'Halaman Kategori Bukti Kegiatan', 'ROLE_MASTER_KATEGORI_BUKTI_KEGIATAN'),
('MASTER_JENIS_BUKTI_KEGIATAN', 'Halaman Jenis Bukti Kegiatan', 'ROLE_MASTER_JENIS_BUKTI_KEGIATAN'),
('MASTER_POIN_KEGIATAN', 'Halaman Poin Kegiatan', 'ROLE_MASTER_POIN_KEGIATAN'),
('USER_LOGGED_IN', 'Get User Loggin Information', 'ROLE_USER_LOGGED_IN');

INSERT INTO c_security_role (id, description, name) VALUES
('ADMINISTRATOR', 'Application Administrator', 'Administrator'),
('DOSEN', 'Dosen', 'Dosen');

INSERT INTO c_security_role_permission (id_role, id_permission) VALUES
('ADMINISTRATOR', 'USER_LOGGED_IN'),
('ADMINISTRATOR', 'KEGIATAN'),
('ADMINISTRATOR', 'KEGIATAN_ALL'),
('ADMINISTRATOR', 'MASTER_INSTITUSI'),
('ADMINISTRATOR', 'MASTER_JENIS_SURAT'),
('ADMINISTRATOR', 'MASTER_JABATAN'),
('ADMINISTRATOR', 'MASTER_PROGRAM_STUDI'),
('ADMINISTRATOR', 'MASTER_MATA_KULIAH'),
('ADMINISTRATOR', 'MASTER_SURAT_TUGAS'),
('ADMINISTRATOR', 'MASTER_KATEGORI_KEGIATAN'),
('ADMINISTRATOR', 'MASTER_JENIS_KEGIATAN'),
('ADMINISTRATOR', 'MASTER_KATEGORI_BUKTI_KEGIATAN'),
('ADMINISTRATOR', 'MASTER_JENIS_BUKTI_KEGIATAN'),
('ADMINISTRATOR', 'MASTER_POIN_KEGIATAN'),

('DOSEN', 'KEGIATAN'),
('DOSEN', 'USER_LOGGED_IN');

INSERT INTO c_security_user (id, active, username, id_role) VALUES
('admin', true,'admin', 'ADMINISTRATOR'),
('1', true,'vaansaa@gmail.com', 'DOSEN');

INSERT INTO c_security_user_password (id_user, password) VALUES
('admin', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6'),
('1', '$2a$08$LS3sz9Ft014MNaIGCEyt4u6VflkslOW/xosyRbinIF9.uaVLpEhB6');
