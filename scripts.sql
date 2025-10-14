CREATE TABLE autor(
                      id uuid not null primary key,
                      nome varchar(100) not null,
                      data_nascimento date not null,
                      nacionalidade varchar(50) not null
)

CREATE TABLE livro (
                       id uuid not null primary key,
                       isdn varchar(20) not null,
                       titulo varchar(150) not null,
                       data_publicaco date not null,
                       genero 	varchar(30) not null,
                       preco numeric(18, 2),
                       id_autor uuid not null references autor(id),
                       constraint chk_genero check (genero in ('FICCAO', 'FANTASIA', 'MISTERIO', 'ROMANCE', 'BIOGRAFIA', 'CIENCIA'))
)

select * from autor;
select * from livro;