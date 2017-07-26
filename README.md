# Aplikasi Dosen #

[![Build Status](https://travis-ci.org/idtazkia/aplikasi-dosen.svg?branch=master)](https://travis-ci.org/idtazkia/aplikasi-dosen)
[![Coverage Report](https://codecov.io/gh/idtazkia/aplikasi-dosen/branch/master/graph/badge.svg)](https://codecov.io/gh/idtazkia/aplikasi-dosen)

Menjadi dosen di Indonesia tidak mudah. Selain harus membuat materi ajar, menghadapi kelakuan mahasiswa, juga harus membuat laporan untuk Kementerian Pendidikan dan Kebudayaan.

Aplikasi ini bertujuan untuk meringankan pekerjaan dosen yang berkaitan dengan pelaporan untuk Kemdikbud. Harapannya, dengan adanya aplikasi ini, dosen akan lebih mudah dalam:

* mengumpulkan data-data yang berkaitan dengan poin kepangkatan
* menghasilkan laporan yang sesuai dengan format yang diminta Kemdikbud
* mengetahui poin kepangkatan saat ini dan berapa kekurangannya untuk mendapatkan kenaikan pangkat
* dan manfaat lain yang akan ditambahkan seiring dengan perkembangan aplikasi

## Roadmap Pengembangan Aplikasi ##

Berikut adalah rencana pengembangan aplikasi ini:

* Versi 1.0.0

    * Entri data pribadi dosen
    * Entri berbagai surat tugas
    * Cetak surat tugas
    * Upload scan surat tugas yang sudah ditandatangani dan distempel
    * Menghitung poin kepangkatan dari surat tugas
    * Mencetak laporan semesteran untuk Kemdikbud

## Teknologi, Framework, Tools, Platform ##

Untuk bisa melakukan build dan menjalankan aplikasi, ada beberapa software yang harus dipasang, diantaranya:

* Java 8
* Maven 3.3.9
* PostgreSQL 9.5 / H2 Database
* Spring Boot 1.5.2
* Heroku

Cara instalasi Java dan Maven bisa dibaca dengan klik [tautan ini](http://software.endy.muhardin.com/java/persiapan-coding-java/).

## Cara Menjalankan Aplikasi ##

Saat ini kami belum menyediakan binary release, karena masih dalam tahap pengembangan. Bila ingin mengetes aplikasinya, Anda harus [mengunduh kode program dalam format zip](https://github.com/idtazkia/aplikasi-dosen/archive/master.zip) atau melakukan `git clone`. Cara instalasi Git dan mengambil kode program dari Github bisa dibaca pada [tautan ini](http://software.endy.muhardin.com/aplikasi/instalasi-git-di-windows/).

Setelah kode program didapatkan, buka terminal / command prompt, kemudian:

* masuk ke folder kode program

        cd aplikasi-dosen

* jalankan aplikasi

        mvn clean spring-boot:run

* browse aplikasi ke [http://localhost:10000/](http://localhost:10000/)

Versi development dari aplikasi ini dideploy secara otomatis ke Heroku dan bisa diakses di [https://aplikasi-dosen.herokuapp.com/](https://aplikasi-dosen.herokuapp.com/)

## Lisensi ##

Aplikasi ini dirilis secara open-source dengan lisensi Apache License versi 2.0, yang [secara garis besar artinya sebagai berikut](https://tldrlegal.com/license/apache-license-2.0-(apache-2.0)):

* Anda wajib :

    * mencantumkan pemilik hak cipta aslinya (yaitu kami para penulisnya)
    * menyertakan lisensi Apache License versi 2.0 setiap kali membagikan aplikasi ini
    * menjelaskan perubahan yang dilakukan terhadap aplikasi ini

* Anda boleh :

    * menggunakannya untuk keperluan komersil/bisnis
    * membagikannya kepada siapa saja
    * memodifikasi sesuai kebutuhan
    * menyimpan modifikasinya untuk diri sendiri (tidak harus dirilis open-source juga)

* Anda tidak bisa :

    * menyalahkan pembuatnya (yaitu kami) bila terjadi kerusakan/kerugian apapun
    * menggunakan merek dagang pembuatnya (yaitu kami) untuk mempromosikan kegiatan Anda

## Kontribusi ##

Kami mengharapkan kontribusi dari rekan-rekan dalam berbagai bentuk, diantaranya:

* ikut serta coding
* menggunakan, mengetes, dan melaporkan kalau ada bug/error/usulan perbaikan
* membuat dokumentasi
* dan bantuan lain dalam bentuk apapun

Bila rekan-rekan ingin berkontribusi, caranya sangat mudah.

* Kontribusi menulis kode program : [fork repo ini](https://github.com/idtazkia/aplikasi-dosen#fork-destination-box), lakukan apa yang ingin Anda lakukan, kemudian submit Pull Request (PR). Kami akan review dan merge PR yang berkualitas bagus. Bila ada kekurangan, kami akan [pandu untuk memperbaikinya](https://github.com/idtazkia/aplikasi-dosen/pull/1). Untuk lebih jelasnya bisa nonton [video tutorial tentang workflow Pull Request ini](https://www.youtube.com/watch?v=gDqT_Wvt3VQ)

* Melaporkan bug/error/usulan : cukup [buat issue baru](https://github.com/idtazkia/aplikasi-dosen/issues/new).

* Membuat dokumentasi : caranya sama dengan kontribusi kode program. Fork, tulis, submit PR.

* Bantuan lain : silahkan japri ke [endy@tazkia.ac.id](mailto:endy@tazkia.ac.id)

## Media Komunikasi ##

Pergerakan project ini bisa dipantau melalui :

* [Trello](https://trello.com/b/lGBRvvPM/aplikasi-dosen)
* [Travis CI](https://travis-ci.org/idtazkia/aplikasi-dosen)
* [Live Demo di Heroku](https://aplikasi-dosen.herokuapp.com/)

Diskusi dan koordinasi bisa dilakukan melalui:

* [Grup Telegram](https://t.me/AplikasiDosen)

## Sponsor ##

* [Sekolah Tinggi Ekonomi Islam Tazkia](http://www.tazkia.ac.id)

## Tim Pengembang ##

* [Endy Muhardin](https://software.endy.muhardin.com/about) - [endy@tazkia.ac.id](mailto:endy@tazkia.ac.id)
* Adi Mulya Suprayogi - [suprayogi@tazkia.ac.id](mailto:suprayogi@tazkia.ac.id)
* Nurul Adhom - [adhom@tazkia.ac.id](mailto:adhom@tazkia.ac.id)
