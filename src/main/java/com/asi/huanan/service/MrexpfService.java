package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.MrexpfMapper;
import com.asi.huanan.service.dao.mybatis.model.Mrexpf;
import com.asi.huanan.service.repository.MrexpfRepository;

@Service
public class MrexpfService {

	private Log log = LogFactory.getLog(MrexpfService.class);

	@Autowired
	private MrexpfRepository repository;
	
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
	public int insert(final Mrexpf model, MrexpfMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, MrexpfMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final Mrexpf model, MrexpfMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final Mrexpf model, SqlSession sqlSession) throws Exception {
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
	//public int update(final Mrexpf model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final Mrexpf model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<Mrexpf> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜Mrexpf＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<Mrexpf> modelList)
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
    // public Mrexpf queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param Mrexpf
     * @return
     * @throws Exception
     */
    public List<Mrexpf> queryByMrexpf(final Mrexpf model)
            throws Exception
    {
        return repository.queryByMrexpf(model);
    }

    /**
     * 
     * @param Mrexpf
     * @return
     * @throws Exception
     */
    public List<Mrexpf> queryByMrexpf(final Mrexpf model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByMrexpf(model, sqlSession);
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
    public int update(final Mrexpf model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Mrexpf> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<Mrexpf> queryAll(SqlSession sqlSession) throws Exception{
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
    //public List<Mrexpf> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
    //	return repository.queryDistint{COL_NM}(sqlSession);
    //}

    /**
     * 
     * @param sqlSession
     * @return
     * @throws Exception
     */
    //public List<Mrexpf> queryDistint{COL_NM}() throws Exception {
    //	return repository.queryDistint{COL_NM}();
    //}
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(Mrexpf model) throws Exception {
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
	public long queryCount(Mrexpf model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
	
	public List<Mrexpf> queryByCrtdat() throws Exception{
		return repository.queryByCrtdat();
	}

	public List<Mrexpf> queryCurrency(String currency) throws Exception {
		return repository.queryCurrency(currency);
	}
}