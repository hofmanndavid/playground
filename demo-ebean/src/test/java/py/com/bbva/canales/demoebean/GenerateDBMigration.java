package py.com.bbva.canales.demoebean;

import io.ebean.annotation.Platform;
import io.ebean.dbmigration.DbMigration;
import org.junit.Test;

public class GenerateDBMigration {

    @Test
    public void main() throws Exception {

        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.H2);

        // generate the migration ddl and xml
        dbMigration.generateMigration();
    }
}