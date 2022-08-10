package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacSlipriskMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacSlipriskKey;
import com.asi.huanan.service.repository.FriFacSlipriskRepository;
import com.asi.huanan.vo.rin1301.req.Rin1301HandleDataVOReq;

@Service
public class FriFacSlipriskService {

	private Log log = LogFactory.getLog(FriFacSlipriskService.class);

	@Autowired
	private FriFacSlipriskRepository repository;
	
	//=====針對使用自訂SQL=====
	
	//...
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacSlipriskKey model, FriFacSlipriskMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final FriFacSlipriskKey primaryKey, FriFacSlipriskMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriFacSlipriskKey model, FriFacSlipriskMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriFacSlipriskKey model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriFacSlipriskKey model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriFacSlipriskKey model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriFacSlipriskKey> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriFacSlipriskKey＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriFacSlipriskKey> modelList)
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
    // public FriFacSlipriskKey queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriFacSlipriskKey
     * @return
     * @throws Exception
     */
    public List<FriFacSlipriskKey> queryByFriFacSlipriskKey(final FriFacSlipriskKey model)
            throws Exception
    {
        return repository.queryByFriFacSlipriskKey(model);
    }
    
    
    
    /**
     * 
     * @param FriFacSlipriskKey
     * @return
     * @throws Exception
     */
    public List<FriFacSlipriskKey> queryByFriFacSlipriskKey(final FriFacSlipriskKey model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriFacSlipriskKey(model, sqlSession);
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
    public int update(final FriFacSlipriskKey model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriFacSlipriskKey> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriFacSlipriskKey> queryAll(SqlSession sqlSession) throws Exception{
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
    //public List<FriFacSlipriskKey> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
    //	return repository.queryDistint{COL_NM}(sqlSession);
    //}

    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
    //public List<FriFacSlipriskKey> queryDistint{COL_NM}() throws Exception {
    //	return repository.queryDistint{COL_NM}();
    //}
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriFacSlipriskKey model) throws Exception {
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
	public long queryCount(FriFacSlipriskKey model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
	
	
    public int insertByQueryPolicy(final Rin1301HandleDataVOReq record,FriFacSlipriskMapper mapper) throws Exception
    {
        return repository.insertByQueryPolicy(record,mapper);
    }
    
	public int deleteByFriFacSlipriskKey(final FriFacSlipriskKey model, FriFacSlipriskMapper mapper) throws Exception {
		return repository.deleteByFriFacSlipriskKey(model, mapper);
	}
}