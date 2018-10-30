-- apply changes
create table customer (
  id                            bigint auto_increment not null,
  full_name                     varchar(255),
  document_number               varchar(255),
  constraint pk_customer primary key (id)
);

create table interaction (
  id                            bigint auto_increment not null,
  op_name                       varchar(256),
  customer_name                 varchar(256),
  customer_document_number      varchar(256),
  fecha                         timestamp,
  id_canal                      varchar(256),
  constraint pk_interaction primary key (id)
);

create table message (
  id                            bigint auto_increment not null,
  fecha                         timestamp,
  sent_by_client                boolean,
  message                       varchar(10000),
  aditional                     varchar(10000),
  op_name                       varchar(256),
  interaction_id                bigint,
  constraint pk_message primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  email                         varchar(255) not null,
  password                      varchar(255),
  name                          varchar(255),
  roles                         varchar(255),
  constraint uq_user_email unique (email),
  constraint pk_user primary key (id)
);

create index ix_message_interaction_id on message (interaction_id);
alter table message add constraint fk_message_interaction_id foreign key (interaction_id) references interaction (id) on delete restrict on update restrict;

