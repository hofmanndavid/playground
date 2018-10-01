-- apply changes
create table categoria (
  id                            bigserial not null,
  codigo                        varchar(255) not null,
  descripcion                   varchar(255),
  constraint uq_categoria_codigo unique (codigo),
  constraint pk_categoria primary key (id)
);

create table cliente (
  id                            bigserial not null,
  categoria_id                  bigint,
  fecha_nacimiento              date,
  nombre                        varchar(255),
  apellido                      varchar(255),
  ruc                           varchar(255),
  constraint pk_cliente primary key (id)
);

create table usuario (
  id                            bigserial not null,
  username                      varchar(255) not null,
  email                         varchar(255),
  password                      varchar(255) not null,
  full_name                     varchar(255),
  constraint uq_usuario_username unique (username),
  constraint pk_usuario primary key (id)
);

alter table cliente add constraint fk_cliente_categoria_id foreign key (categoria_id) references categoria (id) on delete restrict on update restrict;
create index ix_cliente_categoria_id on cliente (categoria_id);

