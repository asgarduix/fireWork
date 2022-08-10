package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriFacBrokerMapper;
import com.asi.huanan.service.dao.mybatis.model.FriFacBroker;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1303Query3;
import com.asi.huanan.service.repository.FriFacBrokerRepository;

@Service
public class FriFacBrokerService {

	private Log log = LogFactory.getLog(FriFacBrokerService.class);

	@Autowired
	private FriFacBrokerRepository repository;
	
	//=====針對使用自訂SQL=====
	
	//...
	/**
	 * @param slipNo
	 * @param rinComId
	 * @return
	 * @throws Exception
	 */
	public List<Rin1303Query3> queryRin1303Print3(String slipNo, String rinComId, FriFacBrokerMapper mapper) throws Exception {
		return repository.queryRin1303Print3(slipNo, rinComId, mapper);
	}
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriFacBroker model, FriFacBrokerMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriFacBrokerMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}
	
	public int deleteByFriFacBroker(final FriFacBroker model, FriFacBrokerMapper mapper) throws Exception {
		return repository.deleteByFriFacBroker(model, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriFacBroker model, FriFacBrokerMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriFacBroker model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriFacBroker model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriFacBroker model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriFacBroker> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriFacBroker＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriFacBroker> modelList)
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
    // public FriFacBroker queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriFacBroker
     * @return
     * @throws Exception
     */
    public List<FriFacBroker> queryByFriFacBroker(final FriFacBroker model)
            throws Exception
    {
        return repository.queryByFriFacBroker(model);
    }

    /**
     * 
     * @param FriFacBroker
     * @return
     * @throws Exception
     */
    public List<FriFacBroker> queryByFriFacBroker(final FriFacBroker model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriFacBroker(model, sqlSession);
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
    public int update(final FriFacBroker model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriFacBroker> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriFacBroker> queryAll(SqlSession sqlSession) throws Exception{
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
    
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriFacBroker> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriFacBroker> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriFacBroker model) throws Exception {
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
	public long queryCount(FriFacBroker model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}