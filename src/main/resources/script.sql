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