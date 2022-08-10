package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriTreatyCommMapper;
import com.asi.huanan.service.dao.mybatis.model.FriTreatyComm;
import com.asi.huanan.service.repository.FriTreatyCommRepository;
import com.asi.huanan.vo.Rin1103DeleteTreatyVOReq;
import com.asi.huanan.vo.Rin1103InsertTreatyCommVOReq;
import com.asi.huanan.vo.Rin1103VOResp;
import com.asi.huanan.vo.Rin1103UpdateTreatyCommVOReq;

@Service
public class FriTreatyCommService {

	private Log log = LogFactory.getLog(FriTreatyCommService.class);

	@Autowired
	private FriTreatyCommRepository repository;
	
	//=====針對使用自訂SQL=====
	
	//...
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public short insertTreadyReturnSerial(final Rin1103InsertTreatyCommVOReq model) throws Exception {
		return repository.insertTreadyReturnSerial(model);
	}
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteTreadysByPkList(final List<Rin1103DeleteTreatyVOReq> model) throws Exception {
		return repository.deleteTreadysByPkList(model);
	}
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateByOldPk(final Rin1103UpdateTreatyCommVOReq model) throws Exception {
		return repository.updateByOldPk(model);
	}
	   /**
     * 
     * @param treatyYear
     * @param treatyNo
     * @return
     * @throws Exception
     */
    public List<Rin1103VOResp> queryTreatyList(final String treatyYear, final String treatyNo)
            throws Exception
    {
        return repository.queryTreatyList(treatyYear, treatyNo);
    }
    
    
    /**
     * 
     * @param treatyYear
     * @param treatyNo
     * @param commType
     * @return
     * @throws Exception
     */
    public List<FriTreatyComm> checkInsertUpLow(final String treatyYear, final String treatyNo, final String commType)
            throws Exception
    {
        return repository.checkInsertUpLow(treatyYear, treatyNo, commType);
    }
    
    /**
     * 
     * @param treatyYear
     * @param treatyNo
     * @param commType
     * @param serial
     * @return
     * @throws Exception
     */
    public List<FriTreatyComm> checkUpdateUpLow(final String treatyYear, final String treatyNo, final String commType, final short serial)
            throws Exception
    {
        return repository.checkUpdateUpLow(treatyYear, treatyNo, commType, serial);
    }
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final FriTreatyComm model, FriTreatyCommMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriTreatyCommMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriTreatyComm model, FriTreatyCommMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriTreatyComm model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriTreatyComm model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriTreatyComm model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriTreatyComm> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriTreatyComm＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriTreatyComm> modelList)
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
    // public FriTreatyComm queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriTreatyComm
     * @return
     * @throws Exception
     */
    public List<FriTreatyComm> queryByFriTreatyComm(final FriTreatyComm model)
            throws Exception
    {
        return repository.queryByFriTreatyComm(model);
    }

    /**
     * 
     * @param FriTreatyComm
     * @return
     * @throws Exception
     */
    public List<FriTreatyComm> queryByFriTreatyComm(final FriTreatyComm model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriTreatyComm(model, sqlSession);
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
    public int update(final FriTreatyComm model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreatyComm> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriTreatyComm> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<FriTreatyComm> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriTreatyComm> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriTreatyComm model) throws Exception {
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
	public long queryCount(FriTreatyComm model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}