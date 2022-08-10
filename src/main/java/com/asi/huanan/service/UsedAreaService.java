package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.UsedAreaMapper;
import com.asi.huanan.service.dao.mybatis.model.UsedArea;
import com.asi.huanan.service.repository.UsedAreaRepository;
import com.asi.huanan.vo.DeleteUsedArea;
import com.asi.huanan.vo.Rin1203Vo;

@Service
public class UsedAreaService {

	private Log log = LogFactory.getLog(UsedAreaService.class);

	@Autowired
	private UsedAreaRepository repository;
	
	//=====針對使用自訂SQL=====
	/**
	 * Rin1203_同險設定，檢核地段是否上鎖
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public List<UsedArea> queryAreaCodeIsLock(final UsedArea model,UsedAreaMapper mapper) throws Exception{
		return repository.queryAreaCodeIsLock(model,mapper);
	}
	
	/**
	 * Rin1203_同險設定，地段解鎖
	 * @param modelList
	 * @return
	 * @throws Exception
	 */
	public int deleteUsedAreaIn(final List<DeleteUsedArea> modelList) throws Exception{
		return repository.deleteUsedAreaIn(modelList);
	}
	//=====針對使用自訂SQL結束=====
	/**
     * 
     * @param area_code
     * @return
     * @throws Exception
     */
    public int deleteOneAreaCode(final String area_code) throws Exception
    {
		return repository.deleteByKey(area_code);
    }
	
	//==========
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final UsedArea model, UsedAreaMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, UsedAreaMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final UsedArea model, UsedAreaMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final UsedArea model, SqlSession sqlSession) throws Exception {
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
	//public int update(final UsedArea model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final UsedArea model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<UsedArea> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜UsedArea＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<UsedArea> modelList)
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
    // public UsedArea queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param UsedArea
     * @return
     * @throws Exception
     */
    public List<UsedArea> queryByUsedArea(final UsedArea model)
            throws Exception
    {
        return repository.queryByUsedArea(model);
    }

    /**
     * 
     * @param UsedArea
     * @return
     * @throws Exception
     */
    public List<UsedArea> queryByUsedArea(final UsedArea model, UsedAreaMapper mapper)
            throws Exception
    {
        return repository.queryByUsedArea(model, mapper);
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
    public int update(final UsedArea model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<UsedArea> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<UsedArea> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<UsedArea> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<UsedArea> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(UsedArea model) throws Exception {
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
	public long queryCount(UsedArea model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}

	
	
	
	
}