package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriPolicyAdditionMapper;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatySharesMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyShares;
import com.asi.huanan.service.repository.FriTreatySharesRepository;
import com.asi.huanan.vo.Rin1205VORespSub;
import com.asi.huanan.vo.Rin1205UpdateShareAmtPremVOReq;

@Service
public class FriTreatySharesService {

	private Log log = LogFactory.getLog(FriTreatySharesService.class);

	@Autowired
	private FriTreatySharesRepository repository;
	
	//=====針對使用自訂SQL=====
	public int deletefriTreatyShares(String policyNo, String endorseNo, FriTreatySharesMapper mapper) {
		int count=0;
		count = mapper.deleteFriTreatyShares(policyNo,endorseNo);
		return count;
		
	}
	
	
	
	//=====針對使用自訂SQL結束=====
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateShareAmtPrem(final Rin1205UpdateShareAmtPremVOReq model, FriTreatySharesMapper mapper) throws Exception {
		return repository.updateShareAmtPrem(model, mapper);
	}
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<Rin1205VORespSub> getShareResult(final FriTreatyShares model, FriTreatySharesMapper mapper) throws Exception {
		return repository.getShareResult(model, mapper);
	}
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<FriTreatyShares> getRetainResult(final FriTreatyShares model, FriTreatySharesMapper mapper) throws Exception {
		return repository.getRetainResult(model, mapper);
	}
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTreatyShares model, FriTreatySharesMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTreatySharesMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriTreatyShares model, FriTreatySharesMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriTreatyShares model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriTreatyShares model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyShares model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriTreatyShares> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriTreatyShares＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriTreatyShares> modelList)
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
    // public FriTreatyShares queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriTreatyShares
     * @return
     * @throws Exception
     */
    public List<FriTreatyShares> queryByFriTreatyShares(final FriTreatyShares model)
            throws Exception
    {
        return repository.queryByFriTreatyShares(model);
    }

    /**
     * 
     * @param FriTreatyShares
     * @return
     * @throws Exception
     */
    public List<FriTreatyShares> queryByFriTreatyShares(final FriTreatyShares model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriTreatyShares(model, sqlSession);
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
    public int update(final FriTreatyShares model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreatyShares> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreatyShares> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<FriTreatyShares> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriTreatyShares> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriTreatyShares model) throws Exception {
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
	public long queryCount(FriTreatyShares model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

	
}