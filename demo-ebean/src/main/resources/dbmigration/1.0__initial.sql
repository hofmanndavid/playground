-- apply changes
create table address (
  id                            bigint auto_increment not null,
  address                       varchar(255),
  city                          varchar(255),
  constraint pk_address primary key (id)
);

create table contact (
  id                            bigint auto_increment not null,
  contact                       varchar(255),
  type                          varchar(5),
  customer_id                   bigint,
  constraint ck_contact_type check ( type in ('PHONE','EMAIL')),
  constraint pk_contact primary key (id)
);

create table customer (
  id                            bigint auto_increment not null,
  name                          varchar(255),
  doc_number                    varchar(255),
  status                        integer,
  registered                    date,
  billing_address_id            bigint,
  constraint ck_customer_status check ( status in (0,1)),
  constraint pk_customer primary key (id)
);

create table message (
  id                            bigint auto_increment not null,
  message                       varchar(255),
  fecha                         timestamp,
  customer_id                   bigint,
  constraint pk_message primary key (id)
);

create index ix_contact_customer_id on contact (customer_id);
alter table contact add constraint fk_contact_customer_id foreign key (customer_id) references customer (id) on delete restrict on update restrict;

create index ix_customer_billing_address_id on customer (billing_address_id);
alter table customer add constraint fk_customer_billing_address_id foreign key (billing_address_id) references address (id) on delete restrict on update restrict;

create index ix_message_customer_id on message (customer_id);
alter table message add constraint fk_message_customer_id foreign key (customer_id) references customer (id) on delete restrict on update restrict;

