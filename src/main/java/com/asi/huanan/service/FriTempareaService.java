package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriTempareaMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTemparea;
import com.asi.huanan.service.repository.FriTempareaRepository;
import com.asi.huanan.vo.Rin1203Vo;

@Service
public class FriTempareaService {

	private Log log = LogFactory.getLog(FriTempareaService.class);

	@Autowired
	private FriTempareaRepository repository;

	// =====針對使用自訂SQL=====

	/**
	 * Rin1203_同險設定，查詢兩年內資料
	 * 
	 * @param areaCode
	 * @param riskFlag
	 * @return
	 * @throws Exception
	 */
	public List<Rin1203Vo> queryTemparea(final String areaCode, final String riskFlag) throws Exception {
		return repository.queryTemparea(areaCode, riskFlag);
	}

	/**
	 * Rin1203_同險設定，當狀態為n時，變成未完成地段
	 * 
	 * @param procCount
	 * @param areaCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateTemparea(int procCount, String areaCode, FriTempareaMapper mapper) throws Exception {
		return repository.updateTemparea(procCount, areaCode, mapper);

	}

	// =====針對使用自訂SQL結束=====

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTemparea model, FriTempareaMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTempareaMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriTemparea model, FriTempareaMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int insert(final FriTemparea model, SqlSession sqlSession) throws
	// Exception {
	// return repository.insert(model, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws
	// Exception {
	// return repository.deleteByKey(primaryKey, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int update(final FriTemparea model, SqlSession sqlSession) throws
	// Exception {
	// return repository.update(model, sqlSession);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTemparea model) throws Exception {
		return repository.insert(model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriTemparea> modelList) throws Exception {
		return repository.insertList(modelList);
	}

	// /**
	// *
	// * @param modelList
	// * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriTemparea＞)
	// * @throws Exception
	// */
	// public Map<String, Object> insertList(List<FriTemparea> modelList)
	// throws Exception
	// {
	// return repository.insertList(modelList);
	// }

	// /**
	// *
	// * @param nmArray
	// * @param paraNmArray
	// * @param moduleNm
	// * @return
	// * @throws Exception
	// */
	// public Map<String, String> queryAllReturnMap(String[] paraNmArray,
	// String moduleNm) throws Exception
	// {
	// return repository.queryAllReturnMap(paraNmArray, moduleNm);
	// }

	// /**
	// *
	// * @param jobId
	// * @return
	// * @throws Exception
	// */
	// public FriTemparea queryByJobId(final String jobId) throws Exception
	// {
	// return repository.queryByJobId(jobId);
	// }

	/**
	 * 
	 * @param FriTemparea
	 * @return
	 * @throws Exception
	 */
	public List<FriTemparea> queryByFriTemparea(final FriTemparea model) throws Exception {
		return repository.queryByFriTemparea(model);
	}

	/**
	 * 
	 * @param FriTemparea
	 * @return
	 * @throws Exception
	 */
	public List<FriTemparea> queryByFriTemparea(final FriTemparea model, SqlSession sqlSession) throws Exception {
		return repository.queryByFriTemparea(model, sqlSession);
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey) throws Exception {
		return repository.deleteByKey(primaryKey);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(final FriTemparea model) throws Exception {
		return repository.update(model);
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<FriTemparea> queryAll() throws Exception {
		return repository.queryAll();
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<FriTemparea> queryAll(SqlSession sqlSession) throws Exception {
		return repository.queryAll(sqlSession);
	}

	// /**
	// *
	// * @return
	// * @throws Exception
	// */
	// public int deleteAll() throws Exception{
	// return repository.deleteAll();
	// }

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
//    public List<FriTemparea> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
//    public List<FriTemparea> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriTemparea model) throws Exception {
		return repository.queryCount(model);
	}

	/**
	 * 請注意須在使用前須完成交易
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriTemparea model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

}