javaee-ebean-vaadin
=================
create user vtech with login;
alter role vtech with password 'vtech';
create database vtech owner vtech;

drop database vtech; create database vtech owner vtech;

data-source add --jndi-name=java:/VtechDS --name=VtechPool --connection-url=jdbc:postgresql:vtech --driver-name=postgres  --user-name=vtech --password=vtech
/subsystem=datasources/data-source=VtechPool/:write-attribute(name=max-pool-size, value=3)
