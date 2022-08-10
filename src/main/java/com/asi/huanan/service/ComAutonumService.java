package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.ComAutonumMapper;
import com.asi.huanan.service.dao.mybatis.model.ComAutonum;
import com.asi.huanan.service.dao.mybatis.model.ComAutonumKey;
import com.asi.huanan.service.repository.ComAutonumRepository;
import com.asi.huanan.vo.QueryByNumbeCodeVo;

@Service
public class ComAutonumService {

	private Log log = LogFactory.getLog(ComAutonumService.class);

	@Autowired
	private ComAutonumRepository repository;
	
	//=====針對使用自訂SQL=====
	
	/**
	 * Rin1203_同險設定，產生同險代號按鈕，取得流水號
	 * @param numberCode
	 * @return
	 * @throws Exception
	 */
	public int getRiskNoSerial(final String  numberCode,ComAutonumMapper mapper) throws Exception {
		return repository.getRiskNoSerial(numberCode,mapper);
		
	}

	/**
	 * Rin1203_同險設定，產生同險代號按鈕，流水號+1
	 * @param numberCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int plusComAutonumSerial(final String numberCode,final ComAutonumMapper mapper) throws Exception{
		return repository.plusComAutonumSerial(numberCode,mapper);
		
	}

	/**
	 * Rin1203_同險設定，產生同險代號按鈕，流水號-1
	 * @param numberCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int minusComAutonumSerial(final String numberCode,final ComAutonumMapper mapper) throws Exception{
		return repository.minusComAutonumSerial(numberCode,mapper);
		
	}

	/**
	 * Rin1203_同險設定，產生同險代號按鈕，刪除一筆流水號紀錄
	 * @param numberCode
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteOneComAutonumList(String numberCode,final ComAutonumMapper mapper)throws Exception {
		return  repository.deleteOneComAutonumList(numberCode,mapper);
		
	}

	/**
	 * Rin1203_同險設定，產生同險代號按鈕，新增一筆流水號紀錄
	 * @param numberCode
	 * @param mapper
	 * @return
	 */
	public int insertOneListByNumberCode(String numberCode, ComAutonumMapper mapper) {
		return  repository.insertOneListByNumberCode(numberCode,mapper);
		
	}
	
	//=====針對使用自訂SQL結束=====
	
	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final ComAutonum model, ComAutonumMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, ComAutonumMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final ComAutonum model, ComAutonumMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final ComAutonum model, SqlSession sqlSession) throws Exception {
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
	//public int update(final ComAutonum model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final ComAutonum model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<ComAutonum> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜ComAutonum＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<ComAutonum> modelList)
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
    // public ComAutonum queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param ComAutonum
     * @return
     * @throws Exception
     */
    public List<ComAutonum> queryByComAutonum(final ComAutonum model)
            throws Exception
    {
        return repository.queryByComAutonum(model);
    }

    /**
     * 
     * @param ComAutonum
     * @return
     * @throws Exception
     */
    public List<ComAutonum> queryByComAutonum(final ComAutonum model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByComAutonum(model, sqlSession);
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
    public int update(final ComAutonum model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<ComAutonum> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<ComAutonum> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<ComAutonum> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<ComAutonum> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(ComAutonum model) throws Exception {
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
	public long queryCount(ComAutonum model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
	
	
	public Integer selectMaxSerial(ComAutonumMapper mapper,ComAutonum model) throws Exception{
		return repository.selectMaxSerial(mapper, model);
	}

	

	
}