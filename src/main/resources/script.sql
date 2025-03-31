create table cidade(
                       cid_id bigserial,
                       cid_nome varchar(200) not null,
                       cid_uf varchar(2) not null,
                       constraint cid_id_pk primary key(cid_id),
                       constraint cid_nome_unique unique(cid_nome)
)

    insert into cidade (cid_nome, cid_uf)
values ('Cuiabá', 'MT')


create table endereco(
                         end_id bigserial,
                         end_tipo_logradouro varchar(50) not null,
                         end_logradouro varchar(200) not null,
                         end_numero varchar(100) not null,
                         end_bairro varchar(100) not null,
                         cid_id bigint not null,
                         constraint end_id_pk primary key(end_id),
                         constraint cid_id_fk foreign key(cid_id) references cidade(cid_id)
)

    insert into endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id)
values ('RUA', 'Major Gama', '161', 'Dom Aquino', 1)

create table unidade(
                        unid_id bigserial,
                        unid_nome varchar(200) not null,
                        unid_sigla varchar(50) not null,
                        constraint unid_id_pk primary key(unid_id),
                        constraint unid_nome_unique unique(unid_nome),
                        constraint unid_sigla_unique unique(unid_sigla)
)

    insert into unidade (unid_nome, unid_sigla)
values ('UNIDADE XPTO', 'XPTO')

create table unidade_endereco(
                                 unid_id bigint not null,
                                 end_id bigint not null,
                                 constraint unid_id_fk foreign key(unid_id) references unidade(unid_id),
                                 constraint end_id_fk foreign key(end_id) references endereco(end_id)
)

    insert into unidade_endereco (unid_id, end_id)
values (1,1)

create table pessoa(
                       pes_id bigserial,
                       pes_nome varchar(200) not null,
                       pes_data_nascimento date not null,
                       pes_sexo varchar(50) not null,
                       pes_mae varchar(200),
                       pes_pai varchar(200),
                       constraint pes_id_pk primary key(pes_id)
)

    insert into pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai)
values ('João Carlos Junior', '1990-10-10', 'MASCULINO', 'Maria Antonieta Senior', 'José Carambolas Senior')

create table foto_pessoa(
                            fp_id bigserial,
                            fp_data date not null,
                            fp_bucket varchar(50) not null,
                            fp_hash varchar(50) not null,
                            fp_url varchar(100),
                            pes_id bigint not null,
                            constraint fp_id_pk primary key(fp_id),
                            constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id)
)

    insert into foto_pessoa (fp_data, fp_bucket, fp_hash, fp_url, pes_id)
values (CURRENT_DATE, 'desafio-seletivo-seplag-bucket', '72d2d119-9e33-42f9-929c-bb7eb54062d0', 'http://localhost:9000/desafio-seletivo-seplag-bucket/72d2d119-9e33-42f9-929c-bb7eb54062d0', 1)

create table pessoa_endereco(
                                pes_id bigint not null,
                                end_id bigint not null,
                                constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id),
                                constraint end_id_fk foreign key(end_id) references endereco(end_id)
)

    insert into pessoa_endereco (pes_id, end_id)
values (1,1)

create table servidor_temporario(
                                    st_data_admissao date not null,
                                    st_data_demissao date not null,
                                    pes_id bigint not null,
                                    constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id)
)

create table servidor_efetivo(
                                 se_matricula varchar(20),
                                 pes_id bigint not null,
                                 constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id),
                                 constraint se_matricula_unique unique(se_matricula)
)

    insert into servidor_efetivo (se_matricula, pes_id)
values ('1000', 1)

create table lotacao(
                        lot_id bigserial,
                        lot_data_lotacao date,
                        lot_data_remocao date,
                        lot_portaria varchar(100),
                        pes_id bigint not null,
                        unid_id bigint not null,
                        constraint lot_id_pk primary key(lot_id),
                        constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id),
                        constraint unid_id_fk foreign key(unid_id) references unidade(unid_id)
)

    insert into lotacao (lot_data_lotacao, lot_portaria, pes_id, unid_id)
values (CURRENT_DATE, 'PORTARIA XPTO', 1, 1)

create table perfil(
                       pe_id bigserial,
                       authority varchar(100) not null,
                       constraint pe_id_pk primary key(pe_id)
)

    insert into perfil (authority)
values ('ADMIN')

create table usuario(
                        us_id bigserial,
                        us_username varchar(200) not null,
                        us_password varchar(200) not null,
                        pes_id bigint not null,
                        constraint us_id_pk primary key(us_id),
                        constraint us_username_unique unique(us_username),
                        constraint pes_id_fk foreign key(pes_id) references pessoa(pes_id)
)

    insert into usuario (us_username, us_password, pes_id)
values ('joao_junior@gmail.com', '$2a$12$qxrsea9GumrThx2uhAzcEewY3CKeXPL/uGqPPc8fdwdBfdy6wfQI2', 1)

create table perfil_usuario(
                               pe_id bigint not null,
                               us_id bigint not null,
                               constraint pe_id_fk foreign key(pe_id) references perfil(pe_id),
                               constraint us_id_fk foreign key(us_id) references usuario(us_id)
)

    insert into perfil_usuario (pe_id, us_id)
values (1,1)

-- Inserindo 10 pessoas
insert into pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai)
values
    ('Naruto Uzumaki', '1989-10-10', 'MASCULINO', 'Kushina Uzumaki', 'Minato Namikaze'), -- Naruto
    ('Sasuke Uchiha', '1988-07-23', 'MASCULINO', 'Mikoto Uchiha', 'Fugaku Uchiha'), -- Sasuke
    ('Sakura Haruno', '1990-03-28', 'FEMININO', 'Kizashi Haruno', 'Mebuki Haruno'), -- Sakura
    ('Kakashi Hatake', '1982-09-15', 'MASCULINO', 'Sakumo Hatake', 'Kakashi Hatake'), -- Kakashi
    ('Hinata Hyuga', '1990-12-27', 'FEMININO', 'Hinata Hyuga', 'Hiashi Hyuga'), -- Hinata

    ('Goku Son', '1980-04-16', 'MASCULINO', 'Chi-Chi', 'Bardock'), -- Goku
    ('Vegeta', '1980-08-12', 'MASCULINO', 'Bulma Briefs', 'King Vegeta'), -- Vegeta
    ('Piccolo', 'unknown', 'MASCULINO', 'Namekian', 'Namekian'), -- Piccolo
    ('Bulma Briefs', '1985-08-18', 'FEMININO', 'Mrs. Briefs', 'Dr. Briefs'), -- Bulma
    ('Krillin', '1985-10-29', 'MASCULINO', 'Karin', 'Krillin Senior'); -- Krillin

-- Inserindo 5 servidores temporários
insert into servidor_temporario (st_data_admissao, st_data_demissao, pes_id)
values
    ('2025-01-01', '2025-12-31', 1), -- Naruto como servidor temporário
    ('2025-01-01', '2025-12-31', 2), -- Sasuke como servidor temporário
    ('2025-01-01', '2025-12-31', 3), -- Sakura como servidor temporário
    ('2025-01-01', '2025-12-31', 4), -- Kakashi como servidor temporário
    ('2025-01-01', '2025-12-31', 5); -- Hinata como servidor temporário

-- Inserindo 5 servidores efetivos
insert into servidor_efetivo (se_matricula, pes_id)
values
    ('1001', 6), -- Goku como servidor efetivo
    ('1002', 7), -- Vegeta como servidor efetivo
    ('1003', 8), -- Piccolo como servidor efetivo
    ('1004', 9), -- Bulma como servidor efetivo
    ('1005', 10); -- Krillin como servidor efetivo

-- Inserindo fotos para as novas pessoas
insert into foto_pessoa (fp_data, fp_bucket, fp_hash, fp_url, pes_id)
values
    (CURRENT_DATE, 'anime-fotos', 'naruto_hash', 'http://localhost:9000/anime-fotos/naruto', 1),
    (CURRENT_DATE, 'anime-fotos', 'sasuke_hash', 'http://localhost:9000/anime-fotos/sasuke', 2),
    (CURRENT_DATE, 'anime-fotos', 'sakura_hash', 'http://localhost:9000/anime-fotos/sakura', 3),
    (CURRENT_DATE, 'anime-fotos', 'kakashi_hash', 'http://localhost:9000/anime-fotos/kakashi', 4),
    (CURRENT_DATE, 'anime-fotos', 'hinata_hash', 'http://localhost:9000/anime-fotos/hinata', 5),
    (CURRENT_DATE, 'anime-fotos', 'goku_hash', 'http://localhost:9000/anime-fotos/goku', 6),
    (CURRENT_DATE, 'anime-fotos', 'vegeta_hash', 'http://localhost:9000/anime-fotos/vegeta', 7),
    (CURRENT_DATE, 'anime-fotos', 'piccolo_hash', 'http://localhost:9000/anime-fotos/piccolo', 8),
    (CURRENT_DATE, 'anime-fotos', 'bulma_hash', 'http://localhost:9000/anime-fotos/bulma', 9),
    (CURRENT_DATE, 'anime-fotos', 'krillin_hash', 'http://localhost:9000/anime-fotos/krillin', 10);

-- Relacionando pessoas com seus endereços (assumindo endereços fictícios de exemplo)
insert into pessoa_endereco (pes_id, end_id)
values
    (1, 1), -- Naruto em Konoha
    (2, 1), -- Sasuke em Konoha
    (3, 1), -- Sakura em Konoha
    (4, 1), -- Kakashi em Konoha
    (5, 1), -- Hinata em Konoha
    (6, 2), -- Goku em Bulma City
    (7, 2), -- Vegeta em Bulma City
    (8, 2), -- Piccolo em Bulma City
    (9, 2), -- Bulma em Bulma City
    (10, 2); -- Krillin em Bulma City

-- Inserindo unidades fictícias
insert into unidade (unid_nome, unid_sigla)
values
    ('Ninja Academy', 'NA'), -- Unidade fictícia de Konoha
    ('Capsule Corp', 'CC'); -- Unidade fictícia da Bulma

-- Relacionando unidades com endereços
insert into unidade_endereco (unid_id, end_id)
values
    (1, 1), -- Ninja Academy em Konoha
    (2, 2); -- Capsule Corp em Bulma City

-- Inserindo lotação de servidores
insert into lotacao (lot_data_lotacao, lot_portaria, pes_id, unid_id)
values
    (CURRENT_DATE, 'PORTARIA NARUTO', 1, 1), -- Naruto na Ninja Academy
    (CURRENT_DATE, 'PORTARIA SASUKE', 2, 1), -- Sasuke na Ninja Academy
    (CURRENT_DATE, 'PORTARIA SAKURA', 3, 1), -- Sakura na Ninja Academy
    (CURRENT_DATE, 'PORTARIA KAKASHI', 4, 1), -- Kakashi na Ninja Academy
    (CURRENT_DATE, 'PORTARIA HINATA', 5, 1), -- Hinata na Ninja Academy
    (CURRENT_DATE, 'PORTARIA GOKU', 6, 2), -- Goku na Capsule Corp
    (CURRENT_DATE, 'PORTARIA VEGETA', 7, 2), -- Vegeta na Capsule Corp
    (CURRENT_DATE, 'PORTARIA PICCOLO', 8, 2), -- Piccolo na Capsule Corp
    (CURRENT_DATE, 'PORTARIA BULMA', 9, 2), -- Bulma na Capsule Corp
    (CURRENT_DATE, 'PORTARIA KRILLIN', 10, 2); -- Krillin na Capsule Corp

-- Inserindo usuários fictícios
insert into usuario (us_username, us_password, pes_id)
values
    ('naruto.uzumaki@konohasystem.com', '$2a$12$abcdhash123456', 1),
    ('sasuke.uchiha@konohasystem.com', '$2a$12$abcdhash123456', 2),
    ('sakura.haruno@konohasystem.com', '$2a$12$abcdhash123456', 3),
    ('kakashi.hatake@konohasystem.com', '$2a$12$abcdhash123456', 4),
    ('hinata.hyuga@konohasystem.com', '$2a$12$abcdhash123456', 5),
    ('goku.son@bulmacity.com', '$2a$12$abcdhash987654', 6),
    ('vegeta@bulmacity.com', '$2a$12$abcdhash987654', 7),
    ('piccolo@bulmacity.com', '$2a$12$abcdhash987654', 8),
    ('bulma.briefs@bulmacity.com', '$2a$12$abcdhash987654', 9),
    ('krillin@bulmacity.com', '$2a$12$abcdhash987654', 10);

-- Relacionando perfil com usuários
insert into perfil_usuario (pe_id, us_id)
values
    (1, 1), -- Naruto com perfil ADMIN
    (1, 2), -- Sasuke com perfil ADMIN
    (1, 3), -- Sakura com perfil ADMIN
    (1, 4), -- Kakashi com perfil ADMIN
    (1, 5), -- Hinata com perfil ADMIN
    (1, 6), -- Goku com perfil ADMIN
    (1, 7), -- Vegeta com perfil ADMIN
    (1, 8), -- Piccolo com perfil ADMIN
    (1, 9), -- Bulma com perfil ADMIN
    (1, 10); -- Krillin com perfil ADMIN
