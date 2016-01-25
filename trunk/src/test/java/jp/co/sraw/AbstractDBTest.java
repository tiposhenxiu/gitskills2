package jp.co.sraw;

import java.io.FileInputStream;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

public abstract class AbstractDBTest extends DBTestCase {

	public static IDatabaseConnection connection;
	public static DataSource dataSource;
	protected String PROJECT_PATH = System.getProperty("user.dir");
	protected String TESTRESOURCES_PATH = PROJECT_PATH + "\\src\\test\\resources";

	protected String exceptfile = "";
	protected String clearfile = "";

	public AbstractDBTest(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
				"jdbc:postgresql://172.16.2.231:5432/pfolio");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "pfolio");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "pfolio");
	}

	protected DatabaseOperation getSetUpOperation() throws Exception {
		return DatabaseOperation.REFRESH;
	}

	protected DatabaseOperation getTearDownOperation() throws Exception {
		return DatabaseOperation.NONE;
	}

	protected void initData(String xmlPath) throws Exception {
		String path = TESTRESOURCES_PATH + xmlPath;
		IDatabaseConnection connection = this.getConnection();
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = builder.build(new FileInputStream(path));
		DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}

	protected void clearData() throws Exception {
		String path = TESTRESOURCES_PATH + clearfile;
		IDatabaseConnection connection = this.getConnection();
		FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = builder.build(new FileInputStream(path));
		DatabaseOperation.DELETE_ALL.execute(connection, dataSet);
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return this.getConnection().createDataSet();
	}
}
