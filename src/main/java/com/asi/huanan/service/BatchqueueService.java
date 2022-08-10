package com.asi.huanan.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.BatchqueueMapper;
import com.asi.huanan.service.dao.mybatis.model.Batchqueue;
import com.asi.huanan.service.repository.BatchqueueRepository;

@Service
public class BatchqueueService {

	private Log log = LogFactory.getLog(BatchqueueService.class);

	@Autowired
	private BatchqueueRepository repository;

	//=====針對使用自訂SQL=====

	/**
	 * 再保臨分到期通知列印_立即執行
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertBatchFriFacQueue(final Batchqueue model) throws Exception {
		return repository.insertBatchFriFacQueue(model);
	}

	/**
	 * 再保臨分到期通知列印_排程執行
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertBatchFriFacQueue(final Batchqueue model, BatchqueueMapper mapper) {
		return repository.insertBatchFriFacQueue(model, mapper);
	}

	/**
	 * 產生taskid_立即執行
	 *
	 * @return
	 */
	public String genTaskID() throws Exception {
		return repository.genTaskID();
	}

	/**
	 * 產生taskid_排程執行
	 *
	 * @param mapper
	 * @return
	 */
	public String genTaskID(BatchqueueMapper mapper) {
		return repository.genTaskID(mapper);
	}

	/**
	 * 排程查batchqueue的ProcessStatus
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Batchqueue> queryProcessStatus() throws Exception {
		return repository.queryProcessStatus();
	}

	// selectSubmitTime
	public Date selectSubmitTime(String taskid, Date date) throws Exception {
		return repository.selectSubmitTime(taskid, date);
	}

	//立即執行，修改batchqueue processstatus狀態_endtime
	public int updateProcessStatus0(String taskid, Date endtime) throws Exception {
		return repository.updateProcessStatus0(taskid, endtime);
	}

	/**
	 * 修改batchqueue processstatus狀態_starttime
	 *
	 * @param taskid
	 * @param processstatus
	 * @param starttime
	 * @return
	 * @throws Exception
	 */
	public int updateProcessStatus(String taskid, byte processstatus, Date starttime) throws Exception {
		return repository.updateProcessStatus(taskid, processstatus, starttime);
	}

	// starttime/endtime
	public int updateProcessStatus(String taskid, byte processstatus, Date starttime, Date endtime, String batchreprotaccesspath) throws Exception {
		return repository.updateProcessStatus(taskid, processstatus, starttime, endtime, batchreprotaccesspath);
	}

	// 修改batchqueue processstatus狀態_endtime，執行完成
	public int updateProcessStatus2(String taskid, byte processstatus, Date endtime, String batchreprotaccesspath) throws Exception {
		return repository.updateProcessStatus2(taskid, processstatus, endtime, batchreprotaccesspath);
	}

	// 修改batchqueue processstatus狀態，執行失敗
	public int updateProcessStatus4(String taskid, byte processstatus, Date endtime) throws Exception {
		return repository.updateProcessStatus4(taskid, processstatus, endtime);
	}

	//批次作業監視器_強制中止
	public int updateBatchqueueByTerminate(String taskid, Date date, byte processstatus) throws Exception {
		return repository.updateBatchqueueByTerminate(taskid, date, processstatus);
	}

	//批次作業監視器_開啟報表
	public List<Batchqueue> queryBatchReprotAccessPath(String taskid) throws Exception {
		return repository.queryBatchReprotAccessPath(taskid);
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
	public int insert(final Batchqueue model, BatchqueueMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 *
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, BatchqueueMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 *
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final Batchqueue model, BatchqueueMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final Batchqueue model, SqlSession sqlSession) throws Exception {
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
	//public int update(final Batchqueue model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     *
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Batchqueue model) throws Exception
    {
        return repository.insert(model);
    }

	/**
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<Batchqueue> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜Batchqueue＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<Batchqueue> modelList)
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
    // public Batchqueue queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     *
     * @param Batchqueue
     * @return
     * @throws Exception
     */
    public List<Batchqueue> queryByBatchqueue(final Batchqueue model)
            throws Exception
    {
        return repository.queryByBatchqueue(model);
    }

    /**
     *
     * @param Batchqueue
     * @return
     * @throws Exception
     */
    public List<Batchqueue> queryByBatchqueue(final Batchqueue model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByBatchqueue(model, sqlSession);
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
    public int update(final Batchqueue model) throws Exception
    {
        return repository.update(model);
    }

	/**
    *
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Batchqueue> queryAll() throws Exception{
    	return repository.queryAll();
    }

	/**
    *
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Batchqueue> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<Batchqueue> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }

    /**
     *
     * @param sqlSession
     * @return
     * @throws Exception
     */
//    public List<Batchqueue> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }

    /**
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(Batchqueue model) throws Exception {
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
	public long queryCount(Batchqueue model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

	public int updatePathByTaskId(String taskId, String filePath) throws Exception {
		return repository.updatePathByTaskId(taskId, filePath);
	}
}