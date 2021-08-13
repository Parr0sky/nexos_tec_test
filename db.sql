create table cargo
(
    id    serial
        constraint cargo_pk
            primary key,
    cargo varchar(30)
);

alter table cargo
    owner to postgres;

create table usuario
(
    nombre        varchar(100) not null,
    edad          integer,
    cargo_id      integer
        constraint usuario_cargo_id_fk
            references cargo,
    fecha_ingreso date,
    id            serial
        constraint usuario_pk
            primary key
);

alter table usuario
    owner to postgres;

create table mercancia
(
    nombre              varchar(100) not null,
    cantidad            integer,
    fecha_ingreso       date,
    usuario_creacion_id integer
        constraint mercancia_usuario_id_fk
            references usuario,
    id                  serial
        constraint mercancia_pk
            primary key,
    modificacion_id     integer
);

alter table mercancia
    owner to postgres;

create unique index mercancia_nombre_uindex
    on mercancia (nombre);

create unique index usuario_nombre_uindex
    on usuario (nombre);

create table modificacion
(
    id                      serial
        constraint table_name_pk
            primary key,
    usuario_modificacion_id integer
        constraint table_name_usuario_id_fk
            references usuario,
    fecha                   date,
    mercancia_id            integer
        constraint modificacion_mercancia_id_fk
            references mercancia
);

alter table modificacion
    owner to postgres;

alter table mercancia
    add constraint mercancia_modificacion_id_fk
        foreign key (modificacion_id) references modificacion;

create unique index cargo_cargo_uindex
    on cargo (cargo);

create unique index cargo_id_uindex
    on cargo (id);

