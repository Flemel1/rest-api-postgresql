# Overview
* Java 17
* Spring Boot 3
* Postgresql Database
* Maven
* [Link Projek Frontend](https://github.com/Flemel1/user-admin-panel)


## Setup Projek
1. Clone projek ```git clone https://github.com/Flemel1/res-api-postgresql.git```
2. Clone projek frontend pada link diatas.
3. Buat database dengan nama `office`
4. (Optional) Import data di file initial-data.sql (Bisa inject data otomatis ketika aplikasi dijalankan tanpa harus import data melalui file initial-data.sql)
5. instal library projek ``mvn install``
6. Ganti username dan password sesuai user yang ada pada komputer `spring.datasource.username=postgres spring.datasource.password=postgres` (File application.properties)
7. Jalankan aplikasi
8. Akses API melalui `http://localhost:8080/api/users` & `http://localhost:8080/api/roles`