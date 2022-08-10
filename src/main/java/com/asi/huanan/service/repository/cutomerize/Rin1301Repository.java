package com.asi.huanan.service.repository.cutomerize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.controller.api.Rin1301Controller;
import com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1301Mapper;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryMainData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryPolicyData;
import com.asi.huanan.service.dao.mybatis.model.customerize.rin1301.Rin1301QueryRinserData;
import com.asi.huanan.vo.rin1301.req.Rin1301AQueryParamVoReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryMainDataVOReq;
import com.asi.huanan.vo.rin1301.req.Rin1301QueryPolicyDetailVOReq;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class Rin1301Repository {
	private static Logger log = LogManager.getLogger(Rin1301Controller.class);
	
	@Autowired
	private MyBatisConnector mybatis;
	
	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為保單號、合約編號、知會/更正號擇一
	 * @param model
	 * @return
	 * @throws Exception
	 * @author chewei_hu	2021/12/08
	 */
	public List<Rin1301QueryMainData> queryFacPolicy(Rin1301QueryMainDataVOReq model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryMainData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryFacPolicy(model);
		} catch (Exception e) {
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).stream().forEach(sube -> log.debug(sube.toString()));
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	/**
	 * 臨分資料維護主頁查詢<br>
	 * 查詢條件為未列印及暫緩或不轉之臨分期間(時間區間)
	 * @param model
	 * @return
	 * @throws Exception
	 * @author chewei_hu	2021/12/08
	 */
	public List<Rin1301QueryMainData> queryFacPrintAccount(Rin1301QueryMainDataVOReq model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryMainData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryFacPrintAccount(model);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	public List<Rin1301QueryPolicyData> queryPolicyDetailBySameRiskCode(Rin1301AQueryParamVoReq model) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetailBySameRiskCode(model);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	public List<Rin1301QueryPolicyData> checkPolEndorseByOtherCessionUse(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.checkPolEndorseByOtherCessionUse(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	/**
	 * 臨分資料維護內頁之「保單基本資料」明細1查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail1(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail1(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail1ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail1ByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail1WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail1WithLogSeqByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	/**
	 * 臨分資料維護內頁之「保單基本資料」明細2查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail2(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail2(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail2ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail2ByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail2WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail2WithLogSeqByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	/**
	 * 臨分資料維護內頁之「保單基本資料」明細3查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail3(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail3(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail3ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail3ByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail3WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail3WithLogSeqByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	/**
	 * 臨分資料維護內頁之「保單基本資料」明細4查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail4(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail4(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail4ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail4ByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail4WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail4WithLogSeqByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	
	
	
	/**
	 * 臨分資料維護內頁之「保單基本資料」明細5查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail5(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail5(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	
	/**
	 * 臨分資料維護內頁之「分保計算及說明」計算前資料<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryPolicyData> queryPolicyDetail10(List<Rin1301QueryPolicyDetailVOReq> voList) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail10(voList);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail10ByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail10ByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	public List<Rin1301QueryPolicyData> queryPolicyDetail10WithLogSeqByEdit(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryPolicyData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryPolicyDetail10WithLogSeqByEdit(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	

	
	/**
	 * 臨分資料維護內頁之「再保人資料」再保人明細查詢<br>
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1301QueryRinserData> queryRinserDetail(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryRinserData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryRinserDetail(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
	public List<Rin1301QueryRinserData> queryRinserDetailForLogSeq(Rin1301QueryPolicyDetailVOReq vo) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<Rin1301QueryRinserData> results = new ArrayList<>();
		
		try {
			Rin1301Mapper mapper = sqlSession.getMapper(Rin1301Mapper.class);
			results = mapper.queryRinserDetailForLogSeq(vo);
		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}
		return results;
	}
	
	
}
