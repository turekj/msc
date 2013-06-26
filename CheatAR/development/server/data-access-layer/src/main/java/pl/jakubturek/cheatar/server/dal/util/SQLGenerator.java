package pl.jakubturek.cheatar.server.dal.util;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class SQLGenerator
{
    private final Configuration configuration = new Configuration().configure();
    private String filename;

    public void generateSQL(String filename)
    {
        this.filename = filename;

        exportSQL();
    }

    private void exportSQL()
    {
        SchemaExport schemaExport = createSchemaExport();
        schemaExport.create(true, false);
    }

    private SchemaExport createSchemaExport()
    {
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setOutputFile(filename);

        return schemaExport;
    }
}
