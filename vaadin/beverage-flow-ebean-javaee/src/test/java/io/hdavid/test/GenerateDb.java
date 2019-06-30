package io.hdavid.test;
import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

public class GenerateDb {

    public static void main(String[]  args) throws Exception {

        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.H2);

        // generate the migration ddl and xml
        dbMigration.generateMigration();
    }
}