-- apply changes
create table usuario (
  id                            bigserial not null,
  full_name                     varchar(255),
  username                      varchar(255) not null,
  email                         varchar(255),
  password                      varchar(255) not null,
  role_admin                    boolean,
  role_impresor                 boolean,
  role_consulta                 boolean,
  constraint uq_usuario_username unique (username),
  constraint pk_usuario primary key (id)
);

