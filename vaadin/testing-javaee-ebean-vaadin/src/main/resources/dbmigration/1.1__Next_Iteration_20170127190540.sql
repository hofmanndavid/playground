-- apply changes
create table ciudad (
  id                            bigserial not null,
  nombre                        varchar(255),
  constraint pk_ciudad primary key (id)
);

create table direccion (
  id                            bigserial not null,
  calle                         varchar(255),
  nro                           varchar(255),
  persona_id                    bigint not null,
  ciudad_id                     bigint not null,
  constraint pk_direccion primary key (id)
);

create table persona (
  id                            bigserial not null,
  nombre                        varchar(255),
  apellido                      varchar(255),
  constraint pk_persona primary key (id)
);

alter table direccion add constraint fk_direccion_persona_id foreign key (persona_id) references persona (id) on delete restrict on update restrict;
create index ix_direccion_persona_id on direccion (persona_id);

alter table direccion add constraint fk_direccion_ciudad_id foreign key (ciudad_id) references ciudad (id) on delete restrict on update restrict;
create index ix_direccion_ciudad_id on direccion (ciudad_id);

