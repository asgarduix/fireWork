package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreaty;
import com.asi.huanan.service.repository.FriTreatyRepository;
import com.asi.huanan.vo.Rin1102AQueryOneTreatyVOReq;
import com.asi.huanan.vo.Rin1102QueryTreatysVOReq;
import com.asi.huanan.vo.Rin1102VOResp;

@Service
public class FriTreatyService {

	private Log log = LogFactory.getLog(FriTreatyService.class);

	@Autowired
	private FriTreatyRepository repository;
	
	//=====針對使用自訂SQL====
	
	
	public List<FriTreaty> queryTreatyNoByTreatyYear(final String treatyYear) throws Exception{		
		return repository.queryTreatyNoByTreatyYear(treatyYear);
	}
	
	
	
	/**
	 * 
     * @param treatyYear
     * @param treatyNo
     * @return int
	 * @throws Exception
	 */
	public int deleteByYearNo(final String treatyYear, final String treatyNo, final FriTreatyMapper mapper) throws Exception {
		return repository.deleteByYearNo(treatyYear, treatyNo, mapper);
	}
	
	 /**
     * 
     * @param FriTreaty
     * @return
     * @throws Exception
     */
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model, FriTreatyMapper mapper)
            throws Exception
    {
        return repository.queryByFriTreaty(model, mapper);
    }
	
	//...
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<FriTreaty> queryOneTreaty(final Rin1102AQueryOneTreatyVOReq model, FriTreatyMapper mapper) throws Exception {
		return repository.queryOneTreaty(model, mapper);
	}
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<Rin1102VOResp> queryTreatys(final Rin1102QueryTreatysVOReq model) throws Exception {
		return repository.queryTreatys(model);
	}
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTreaty model, FriTreatyMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTreatyMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriTreaty model, FriTreatyMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriTreaty model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriTreaty model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreaty model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriTreaty> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriTreaty＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriTreaty> modelList)
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
    // public FriTreaty queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriTreaty
     * @return
     * @throws Exception
     */
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model)
            throws Exception
    {
        return repository.queryByFriTreaty(model);
    }

    /**
     * 
     * @param FriTreaty
     * @return
     * @throws Exception
     */
    public List<FriTreaty> queryByFriTreaty(final FriTreaty model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriTreaty(model, sqlSession);
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
    public int update(final FriTreaty model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreaty> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreaty> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<FriTreaty> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriTreaty> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriTreaty model) throws Exception {
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
	public long queryCount(FriTreaty model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}