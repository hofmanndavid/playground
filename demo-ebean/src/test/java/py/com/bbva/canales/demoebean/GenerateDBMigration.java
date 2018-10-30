package py.com.bbva.canales.demoebean;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;

public class GenerateDBMigration {

    public static void main(String[] args) throws Exception {

        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.H2);

        // generate the migration ddl and xml
        dbMigration.generateMigration();
    }
}