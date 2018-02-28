-- data-source add --jndi-name=java:jboss/datasources/demoDS --name=DemoPool --connection-url=jdbc:postgresql:vaadindemo --driver-name=postgres  --user-name=vaadindemo --password=vaadindemo
-- /subsystem=datasources/data-source=DemoPool/:write-attribute(name=max-pool-size, value=5)

-- create user vaadindemo with login;
-- alter role vaadindemo with password 'vaadindemo';
-- create database vaadindemo owner vaadindemo;