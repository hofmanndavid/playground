-- apply changes
create table appuser (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  password                      varchar(255),
  roles                         varchar(255),
  constraint pk_appuser primary key (id)
);

