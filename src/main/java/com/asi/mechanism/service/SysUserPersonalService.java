package com.asi.mechanism.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.mechanism.service.dao.mybatis.mapper.SysUserPersonalMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysUserPersonal;
import com.asi.mechanism.service.repository.SysUserPersonalRepository;

@Service
public class SysUserPersonalService {

	private Log log = LogFactory.getLog(SysUserPersonalService.class);

	@Autowired
	private SysUserPersonalRepository repository;
	
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
	public int insert(final SysUserPersonal model, SysUserPersonalMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, SysUserPersonalMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final SysUserPersonal model, SysUserPersonalMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final SysUserPersonal model, SqlSession sqlSession) throws Exception {
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
	//public int update(final SysUserPersonal model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final SysUserPersonal model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<SysUserPersonal> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜SysUserPersonal＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<SysUserPersonal> modelList)
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
    // public SysUserPersonal queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param SysUserPersonal
     * @return
     * @throws Exception
     */
    public List<SysUserPersonal> queryBySysUserPersonal(final SysUserPersonal model)
            throws Exception
    {
        return repository.queryBySysUserPersonal(model);
    }

    /**
     * 
     * @param SysUserPersonal
     * @return
     * @throws Exception
     */
    public List<SysUserPersonal> queryBySysUserPersonal(final SysUserPersonal model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryBySysUserPersonal(model, sqlSession);
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
    public int update(final SysUserPersonal model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<SysUserPersonal> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<SysUserPersonal> queryAll(SqlSession sqlSession) throws Exception{
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
    
   ///**
   // * 
   // * @param sqlSession
   // * @return
   // * @throws Exception
   // */
   //public List<SysUserPersonal> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
   //	return repository.queryDistint{COL_NM}(sqlSession);
   //}
   //
   ///**
   // * 
   // * @param sqlSession
   // * @return
   // * @throws Exception
   // */
   //public List<SysUserPersonal> queryDistint{COL_NM}() throws Exception {
   //	return repository.queryDistint{COL_NM}();
   //}
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(SysUserPersonal model) throws Exception {
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
	public long queryCount(SysUserPersonal model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}