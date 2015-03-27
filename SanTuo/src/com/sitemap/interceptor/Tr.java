package com.sitemap.interceptor;
import java.sql.Connection;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.DbKit;

/**
 * Example: @Before(Tx.class)
 * 方法中有try 捕获异常后一定要 throw 才能回滚
 */
public class Tr implements Interceptor {
	protected int getTransactionLevel() {
		return DbKit.getTransactionLevel();
	}

	public void intercept(ActionInvocation invocation) {
		if (DbKit.isExistsThreadLocalConnection())
			throw new ActiveRecordException(
					"Nested transaction can not be supported. You can't execute transaction inside another transaction.");
		Connection conn = null;
		Boolean autoCommit = null;
		try {
			conn = DbKit.getDataSource().getConnection();
			autoCommit = conn.getAutoCommit();
			DbKit.setThreadLocalConnection(conn);
			conn.setTransactionIsolation(getTransactionLevel()); // conn.setTransactionIsolation(transactionLevel);
			conn.setAutoCommit(false);
			invocation.invoke();
			conn.commit();
		} catch (Exception e) {
			if (conn != null)
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					if (autoCommit != null)
						conn.setAutoCommit(autoCommit);
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace(); 
			} finally {
				DbKit.removeThreadLocalConnection(); // prevent memory leak
			}
		}
	}
}
/**
 * Reentrance transaction, nested transaction in other words. JFinal decide not
 * to support nested transaction. The code below is help to support nested
 * transact in the future. private void reentryTx() { Connection oldConn =
 * DbKit.getThreadLocalConnection()); // Get connection from threadLocal
 * directly Connection conn = null; try { conn =
 * DbKit.getDataSource().getConnection(); DbKit.setThreadLocalConnection(conn);
 * conn.setTransactionIsolation(getTransactionLevel()); //
 * conn.setTransactionIsolation(transactionLevel); conn.setAutoCommit(false); //
 * here is service code conn.commit(); } catch (Exception e) { if (conn != null)
 * try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();} throw
 * new ActiveRecordException(e); } finally { try { if (conn != null) {
 * conn.setAutoCommit(true); conn.close(); } } catch (Exception e) {
 * e.printStackTrace(); // can not throw exception here, otherwise the more
 * important exception in catch block can not be throw. } finally { if (oldConn
 * != null) DbKit.setThreadLocalConnection(oldConn); else
 * DbKit.removeThreadLocalConnection(); // prevent memory leak } } }
 */
