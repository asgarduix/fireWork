package com.asi.mechanism.service.connector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 
 * 因介接舊程式碼，舊程式碼均無拋出例外的機制，故這邊均不拋出Exception，但會寫出log
 * 
 * @author biruh
 *
 */
@Configuration
public class DBConnection {

//	private Connection conn;

	private Logger log = LogManager.getLogger(DBConnection.class);

	@Autowired
	private MyBatisConnector mybatis;

	@Autowired
	public JdbcTemplate jdbcTemplate;

	/**
	 * 使用HashMap當回傳型態，不建議使用此method
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getResultList(ResultSet resultSet) {
		Logger log = LogManager.getLogger(DBConnection.class);

		if (resultSet == null) {
			log.debug("ResulSet is null");
			return null;
		}

		List<HashMap<String, String>> resList = new ArrayList<>();

		try {
			int colSize = resultSet.getMetaData().getColumnCount();

			while (resultSet.next()) {
				HashMap<String, String> map = new HashMap<>();

				for (int i = 1; i <= colSize; i++) {
					map.put(resultSet.getMetaData().getColumnName(i),
							resultSet.getString(resultSet.getMetaData().getColumnName(i)));
				}

				resList.add(map);
			}
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			// throw e;
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.debug(e.toString());
				Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			}
		}

		return resList;
	}

	/**
	 * 
	 * @param rs
	 * @throws SQLException
	 */
	private List<Map<String, String>> getResultListType2(ResultSet resultSet) {
		Logger log = LogManager.getLogger(DBConnection.class);

		if (resultSet == null) {
			log.debug("ResulSet is null");
			return null;
		}

		List<Map<String, String>> resList = new ArrayList<>();

		try {
			int colSize = resultSet.getMetaData().getColumnCount();

			while (resultSet.next()) {
				Map<String, String> map = new HashMap<>();// TODO 此部份僅因因應三商使用HashMap，轉移後須改用Map

				for (int i = 1; i <= colSize; i++) {
					map.put(resultSet.getMetaData().getColumnName(i),
							resultSet.getString(resultSet.getMetaData().getColumnName(i)));
				}

				resList.add(map);
			}
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			// throw e;
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.debug(e.toString());
				Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			}
		}

		return resList;

		// 測試用，可刪
		// List<SysUserPersonal> list = resList.stream().map(res -> {
		// Object userId = res.get("USER_ID");
		// SysUserPersonal bean = new SysUserPersonal();
		// bean.setUserId(String.valueOf(userId));
		// return bean;
		// }).collect(Collectors.toList());
		//
		// result = list;
		// resultSet.close();
	}

	/**
	 * 注意!使用此方法須要自行關閉(關閉需使用此class中的closeStatement(...))
	 * 
	 * @param preparedStatement
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(PreparedStatement preparedStatement, String sql) {
		// DBConnection connect = new DBConnection();//
		// Spring的Bean需要使用ioc的方式將連factory的物件取出，此程式碼僅提供界面參考
		log.debug("execute sql : " + sql);

		try {
			return preparedStatement.executeQuery(sql);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			return null;
		}
	}

	/**
	 * 注意!使用此方法須要自行關閉(關閉需使用此class中的closeStatement(...))
	 * 
	 * @param statement
	 * @param sql
	 * @return
	 */
	public ResultSet executeQuery(Statement statement, String sql) {
		// DBConnection connect = new DBConnection();//
		// Spring的Bean需要使用ioc的方式將連factory的物件取出，此程式碼僅提供界面參考
		log.debug("execute sql : " + sql);

		try {
			return statement.executeQuery(sql);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			return null;
		}
	}

//	/**
//	 * 使用jdbcTemplate取得java sql statement api(可供舊型系統使用, 可自行開啟關閉連線)
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public ResultSet exeQuery(String sql) throws Exception {
//		log.debug("execute sql : " + sql);
//		// SqlSession sqlSession = mybatis.createSqlSessionFactorySec().openSession();
//
//		ResultSet rs = null;
//
//		try {
//			jdbcTemplate.setDataSource(mybatis.dataSourceSec());
//			rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(sql);
//		} catch (Exception e) {
//			// sqlSession.rollback();
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
//			throw e;
//		} finally {
//			// sqlSession.close();
//		}
//
//		return rs;
//	}
//
//	/**
//	 * 使用jdbcTemplate取得java sql statement api(可供舊型系統使用, 可自行開啟關閉連線)
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public ResultSet exeQueryType2(String sql) throws Exception {
//		log.debug("execute sql : " + sql);
//		// SqlSession sqlSession = mybatis.createSqlSessionFactorySec().openSession();
//
//		ResultSet rs = null;
//
//		try {
//			jdbcTemplate.setDataSource(mybatis.dataSourceSec());
//			rs = jdbcTemplate.getDataSource().getConnection().createStatement().executeQuery(sql);
//		} catch (Exception e) {
//			// sqlSession.rollback();
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
//			throw e;
//		} finally {
//			// sqlSession.close();
//		}
//
//		return rs;
//	}
//
//	/**
//	 * 建立一statement供執行SQL語法
//	 * 
//	 * @param sql sql語法會設定到preparedstatement中
//	 * @return Statement 連結未成功回傳null,成功的連線回傳Statement
//	 */
//	public PreparedStatement createPreparedStatement(String sql) {
//		log.debug("execute sql(createPreparedStatement) : " + sql);
//		// SqlSession sqlSession = null;
//
//		try {
//			// sqlSession = mybatis.createSqlSessionFactorySec().openSession();
//			jdbcTemplate.setDataSource(mybatis.dataSourceSec());
//			PreparedStatement stat = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
//			return stat;
//		} catch (Exception e) {
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
//			// sqlSession.rollback();
//			// throw e;
//		} finally {
//			// sqlSession.close();
//		}
//
//		return null;
//	}
//
//	/**
//	 * 開啟連線, 回傳Statement物件
//	 * 
//	 * 注意!使用此方法須要自行關閉(關閉需使用此class中的closeStatement(...))
//	 * 
//	 * @return Statement 連結未成功回傳null,成功的連線回傳Statement
//	 */
//	public Statement createStatement() {
//		try {
//			// mybatis.createSqlSessionFactorySec().openSession();
//			jdbcTemplate.setDataSource(mybatis.dataSourceSec());
//			return jdbcTemplate.getDataSource().getConnection().createStatement();
//		} catch (Exception e) {
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
//			// sqlSession.rollback();
//			// throw e;
//			return null;
//		}
//	}

//	/**
//	 * 開啟連線, 回傳Statement物件
//	 * 
//	 * 注意!使用此方法須要自行關閉(關閉需使用此class中的closeStatement(...))
//	 * 
//	 * @return Statement 連結未成功回傳null,成功的連線回傳Statement
//	 */
//	public Statement createLiteStatement() {
//		try {
//			mybatis.createSqlSessionFactorySec().openSession();
//			jdbcTemplate.setDataSource(mybatis.dataSourceSec());
//			return jdbcTemplate.getDataSource().getConnection().createStatement();
//		} catch (Exception e) {
//			log.debug(e.toString());
//			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
//			// sqlSession.rollback();
//			// throw e;
//			return null;
//		}
//	}

	/**
	 * 垃圾程式碼，直接使用PreparedStatement即可，不建議使用此method
	 * 
	 * TODO 廢棄使用
	 * 
	 * @param ps
	 * @param index
	 * @param value
	 */
	public void setPreparedStatementString(PreparedStatement ps, int index, String value) {
		try {
//			if (ps.getConnection().isClosed() == true) {
//				log.debug("check connection fail, pls check your connection");
//				return;
//			}

			ps.setString(index, value);
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}
	}

	/**
	 * 垃圾程式碼，直接使用PreparedStatement即可，不建議使用此method
	 * 
	 * 此method需要在外部先開啟連線和須自行關閉連線
	 * 
	 * TODO 廢棄使用
	 * 
	 * @param stmt 需要先設定SQL語法到此物件
	 * @return
	 */
	public ResultSet executeQuery(PreparedStatement stmt) {
		try {
			return stmt.executeQuery();
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
			return null;
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean closeStatement(PreparedStatement stmt) {
		try {
			stmt.close();
			stmt.getConnection().close();
			return true;
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}

		return false;
	}

	public boolean closeStatement(Statement stmt) {
		try {
			stmt.close();
			stmt.getConnection().close();
			return true;
		} catch (SQLException e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.debug(sub.toString()));
		}

		return false;
	}
}
