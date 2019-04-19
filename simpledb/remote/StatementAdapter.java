package remote;

import java.sql.*;

/**
 * This class implements all of the methods of the Statement interface, by
 * throwing an exception for each one. Subclasses (such as SimpleStatement) can
 * override those methods that it want to implement.
 * 
 * @author Edward Sciore
 */
public abstract class StatementAdapter implements Statement {
	public void addBatch(String sql) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void cancel() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void clearBatch() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void clearWarnings() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void close() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean execute(String sql) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int[] executeBatch() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int executeUpdate(String sql) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public Connection getConnection() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getFetchDirection() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getFetchSize() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getMaxFieldSize() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getMaxRows() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean getMoreResults() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean getMoreResults(int current) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getQueryTimeout() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public ResultSet getResultSet() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getResultSetConcurrency() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getResultSetHoldability() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getResultSetType() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public int getUpdateCount() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public SQLWarning getWarnings() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean isClosed() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean isPoolable() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setCursorName(String name) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setEscapeProcessing(boolean enable) {
	}

	public void setFetchDirection(int direction) {
	}

	public void setFetchSize(int rows) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setMaxFieldSize(int max) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setMaxRows(int max) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setPoolable(boolean poolable) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public void closeOnCompletion() throws SQLException {
		throw new SQLException("operation not implemented");
	}

	public boolean isCloseOnCompletion() throws SQLException {
		throw new SQLException("operation not implemented");
	}
}