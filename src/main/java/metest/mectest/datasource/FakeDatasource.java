package metest.mectest.datasource;

import metest.mectest.MECTest;
import myessentials.config.ConfigTemplate;
import myessentials.datasource.DatasourceSQL;
import myessentials.datasource.Schema;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FakeDatasource extends DatasourceSQL {

    public FakeDatasource(Logger log, ConfigTemplate config, Schema schema) {
        super(log, config, schema);
    }

    @Override
    public boolean loadAll() {
        return loadBlocks();
    }

    public boolean loadBlocks() {
        try {
            PreparedStatement s = prepare("SELECT * FROM BlockTypes", false);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {
                //LOG.info(rs.getString("name") + " | " + rs.getString(1));
                DatasourceTest.instance.blockNames.add(rs.getString("name"));
            }
            return true;
        } catch (SQLException ex) {
            MECTest.instance.LOG.error("Failed to load BlockTypes");
            MECTest.instance.LOG.error(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public boolean deleteBlocks() {
        try {
            PreparedStatement s = prepare("DELETE FROM BlockTypes", false);
            s.execute();
            return true;
        } catch (SQLException ex) {
            MECTest.instance.LOG.error("Failed to delete all BlockTypes");
            MECTest.instance.LOG.error(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    public boolean saveBlock(String name, int lightValue, float hardness) {
        try {
            PreparedStatement s = prepare("INSERT INTO BlockTypes VALUES(?, ?, ?)", false);
            s.setString(1, name);
            s.setInt(2, lightValue);
            s.setFloat(3, hardness);
            s.execute();
            return true;
        } catch (SQLException ex) {
            MECTest.instance.LOG.error("Failed to save BlockType");
            MECTest.instance.LOG.error(ExceptionUtils.getStackTrace(ex));
            return false;
        }
    }

    @Override
    public boolean checkAll() {
        return false;
    }
}
