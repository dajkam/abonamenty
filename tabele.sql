CREATE TABLE obywatel
(
    id       serial      PRIMARY KEY,
    pesel            varchar(11)   not null,
    nr_dowodu       varchar(5)   not null,
    imie varchar(32) not null,
    nazwisko varchar(32) not null,
    adres varchar(32) not null,
    kiedy_utworzono bigint not null,
    kiedy_zmodyfikowano bigint,
    czy_zarchiwizowano boolean,
    data_utworzenia date
);

