alter table if exists cliente drop constraint if exists fk_cliente_categoria_id;
drop index if exists ix_cliente_categoria_id;

drop table if exists categoria cascade;

drop table if exists cliente cascade;

drop table if exists usuario cascade;

