INSERT INTO c_security_permission (id, permission_label, permission_value) VALUES
('PENGAJUAN', 'Transaksi Pengajuan Dosen', 'ROLE_PENGAJUAN'),
('PENGAJUAN_ALL', 'All Pengajuan Dosen', 'ROLE_PENGAJUAN_ALL'),
('MASTER_JENIS_DOKUMEN_PENGAJUAN', 'Halaman Jenis Pengajuan', 'ROLE_MASTER_JENIS_DOKUMEN_PENGAJUAN');

INSERT INTO c_security_role_permission (id_role, id_permission) VALUES
('ADMINISTRATOR', 'PENGAJUAN'),
('ADMINISTRATOR', 'PENGAJUAN_ALL'),
('ADMINISTRATOR', 'MASTER_JENIS_DOKUMEN_PENGAJUAN'),

('DOSEN', 'PENGAJUAN');