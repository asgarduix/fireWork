package com.asi.huanan.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.BatchparameterMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchparameter;
import com.asi.huanan.service.repository.BatchparameterRepository;

@Service
public class BatchparameterService {

	private Log log = LogFactory.getLog(BatchparameterService.class);

	@Autowired
	private BatchparameterRepository repository;

	// =====針對使用自訂SQL=====
	
	/**
	 * 再保臨分到期通知列印_排程執行_batchparameter table
	 * 
	 * @param modelParam
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertBatchFriFacParam1(Batchparameter modelParam, BatchparameterMapper mapper) {
		return repository.insertBatchFriFacParam1(modelParam, mapper);
	}

	/**
	 * 再保臨分到期通知列印_排程執行_batchparameter table
	 * 
	 * @param modelParam
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insertBatchFriFacParam2(Batchparameter modelParam, BatchparameterMapper mapper) {
		return repository.insertBatchFriFacParam2(modelParam, mapper);
	}

	/**
	 * 排程使用，找DateValue起日
	 * 
	 * @param taskid
	 * @return
	 * @throws Exception
	 */
	public String queryDateValue1(String taskid) throws Exception {
		return repository.queryDateValue1(taskid);
	}

	/**
	 * 排程使用，找DateValue訖日
	 * 
	 * @param taskid
	 * @return
	 * @throws Exception
	 */
	public String queryDateValue2(String taskid) throws Exception {
		return repository.queryDateValue2(taskid);
	}
	
	// ...

	// ==========

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final Batchparameter model, BatchparameterMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, BatchparameterMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final Batchparameter model, BatchparameterMapper mapper) throws Exception {
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
	// public int insert(final Batchparameter model, SqlSession sqlSession) throws
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
	// public int update(final Batchparameter model, SqlSession sqlSession) throws
	// Exception {
	// return repository.update(model, sqlSession);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final Batchparameter model) throws Exception {
		return repository.insert(model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<Batchparameter> modelList) throws Exception {
		return repository.insertList(modelList);
	}

	// /**
	// *
	// * @param modelList
	// * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜Batchparameter＞)
	// * @throws Exception
	// */
	// public Map<String, Object> insertList(List<Batchparameter> modelList)
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
	// public Batchparameter queryByJobId(final String jobId) throws Exception
	// {
	// return repository.queryByJobId(jobId);
	// }

	/**
	 * 
	 * @param Batchparameter
	 * @return
	 * @throws Exception
	 */
	public List<Batchparameter> queryByBatchparameter(final Batchparameter model) throws Exception {
		return repository.queryByBatchparameter(model);
	}

	/**
	 * 
	 * @param Batchparameter
	 * @return
	 * @throws Exception
	 */
	public List<Batchparameter> queryByBatchparameter(final Batchparameter model, SqlSession sqlSession)
			throws Exception {
		return repository.queryByBatchparameter(model, sqlSession);
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
	public int update(final Batchparameter model) throws Exception {
		return repository.update(model);
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<Batchparameter> queryAll() throws Exception {
		return repository.queryAll();
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<Batchparameter> queryAll(SqlSession sqlSession) throws Exception {
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
//    public List<Batchparameter> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
//    public List<Batchparameter> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(Batchparameter model) throws Exception {
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
	public long queryCount(Batchparameter model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}