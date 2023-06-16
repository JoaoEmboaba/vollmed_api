CREATE TABLE pacientes (
    id serial not null,
    nome varchar(55) not null,
    email varchar(55) not null unique,
    telefone varchar(55) not null,
    cpf varchar(55) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero bigint not null,
    uf char(2) not null,
    cidade varchar(100) not null,

    primary key(id)
)