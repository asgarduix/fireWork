package com.asi.huanan.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.BatchlogMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchlog;
import com.asi.huanan.service.repository.BatchlogRepository;

@Service
public class BatchlogService {

	private Log log = LogFactory.getLog(BatchlogService.class);

	@Autowired
	private BatchlogRepository repository;
	
	//=====針對使用自訂SQL=====
	
	public List<Batchlog> queryLogUseID(String taskid) throws Exception {
		return repository.queryLogUseID(taskid);
	}
	
	/**
	 * 再保臨分到期通知列印_排程執行batchlog table
	 * 
	 * @param modelLog
	 * @param mapper
	 * @return
	 */
	public int insertLog(Batchlog modelLog, BatchlogMapper mapper) {
		return repository.insertLog(modelLog, mapper);
	}
	
	/**
	 * 排程執行_batchlog table_start
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
//	public int updateLog_start(String taskid, Date date) throws Exception {
//		return repository.updateLog_start(taskid, date);
//	}
	
	/**
	 * 排程執行_batchlog table_start
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_start(String taskid, Date date) throws Exception {
		return repository.insertLog_start(taskid, date);
	}
	
	/**
	 * 排程執行_batchlog table_noData
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_noData(String taskid, Date date) throws Exception {
		return repository.insertLog_noData(taskid, date);
	}
	
	/**
	 * 排程執行_batchlog table_error
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_error(String taskid, Date date) throws Exception {
		return repository.insertLog_error(taskid, date);
	}
	
	/**
	 * 排程執行_batchlog table_end
	 * 
	 * @param taskid
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public int insertLog_end(String taskid, Date date) throws Exception {
		return repository.insertLog_end(taskid, date);
	}
	
	/**
	 * 作業細項說明_模糊查詢
	 * 
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Batchlog> queryByContent(String keyword, String taskid) throws Exception {
		return repository.queryByContent(keyword, taskid);
	}
	
	//...
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final Batchlog model, BatchlogMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, BatchlogMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final Batchlog model, BatchlogMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final Batchlog model, SqlSession sqlSession) throws Exception {
	//	return repository.insert(model, sqlSession);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws Exception {
	//	return repository.deleteByKey(primaryKey, sqlSession);
	//}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int update(final Batchlog model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Batchlog model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<Batchlog> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜Batchlog＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<Batchlog> modelList)
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
    //  * 
    //  * @param jobId
    //  * @return
    //  * @throws Exception
    //  */
    // public Batchlog queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param Batchlog
     * @return
     * @throws Exception
     */
    public List<Batchlog> queryByBatchlog(final Batchlog model)
            throws Exception
    {
        return repository.queryByBatchlog(model);
    }

    /**
     * 
     * @param Batchlog
     * @return
     * @throws Exception
     */
    public List<Batchlog> queryByBatchlog(final Batchlog model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByBatchlog(model, sqlSession);
    }
    
    

    /**
     * 
     * @param primaryKey
     * @return
     * @throws Exception
     */
    public int deleteByKey(final String primaryKey) throws Exception
    {
		return repository.deleteByKey(primaryKey);
    }

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int update(final Batchlog model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Batchlog> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Batchlog> queryAll(SqlSession sqlSession) throws Exception{
    	return repository.queryAll(sqlSession);
    }
    
	// /**
    // * 
    // * @return
    // * @throws Exception
    // */
    //public int deleteAll() throws Exception{
    //	return repository.deleteAll();
    //}
    
    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<Batchlog> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<Batchlog> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(Batchlog model) throws Exception {
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
	public long queryCount(Batchlog model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}