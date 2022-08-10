package com.asi.huanan.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.huanan.service.dao.mybatis.mapper.FriComMapper;
import com.asi.huanan.service.dao.mybatis.model.FriCom;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1101FricomJoinRicmpf1;
import com.asi.huanan.service.repository.FriComRepository;
import com.asi.huanan.vo.Rin1102AChkRinIdUsableVOReq;
import com.asi.huanan.vo.Rin1102ApopVOResp;
import com.asi.huanan.vo.rin1301.req.Rin1301FacRinserVoReq;


@Service
public class FriComService {

	private Log log = LogFactory.getLog(FriComService.class);

	@Autowired
	private FriComRepository repository;
	
	//=====針對使用自訂SQL=====
	
	//...
	/**
	 * @param rinComId 
     * @return 
	 * @throws Exception
	 */
	public String queryOneReinser(String rinComId) throws Exception {
		
		return repository.queryOneReinser(rinComId);
	}
	
	
	/**
	 * @param rinComId 
     * @return 
	 * @throws Exception
	 */
	public String chkEnode(String txtrin_com_id1) throws Exception {
		
		return repository.chkEnode(txtrin_com_id1);
	}
	
	/**
	 * @param model 
     * @return 
	 * @throws Exception
	 */
	public String chkRinQua(Rin1102AChkRinIdUsableVOReq model, FriComMapper mapper) throws Exception {
		String msg = "";
		String result = "";
		msg = repository.chkRinQua(model, mapper);
		
		if(!"適格".equals(msg)) {
			if("另案簽報名單".equals(msg) || "觀察名單".equals(msg)) {
				result = model.getType().equals("reinder")?"請留意，此再保人為"+msg:"請留意，此經紀人為"+msg;
			}else {
				result = model.getType().equals("reinder")?"此再保人"+msg+"，不可選擇!":"此經紀人"+msg+"，不可選擇!";
			}
		}		
		
		return result;
	}
	
	public String chkRinQua2(Rin1301FacRinserVoReq req) throws Exception {
		var model=new Rin1102AChkRinIdUsableVOReq();
		model.setRinComId(req.getRinComId());
		model.setTreatyBgn(req.getTreatyDBgn());
		return repository.chkRinQua2(model);
	}
	
	/**
	 * @param model 
     * @return 
	 * @throws Exception
	 */
	public int chkRemark(Rin1102AChkRinIdUsableVOReq model, FriComMapper mapper) throws Exception {	
		return repository.chkRemark(model, mapper);
	}
	
	/**
	 * 
     * @return 
	 * @throws Exception
	 */
	public List<Rin1102ApopVOResp> queryAllFriCom() throws Exception {
		return repository.queryAllFriCom();
	}
	
	/**
     * 
     * @param rinComId
     * @return
     * @throws Exception
     */
    public List<Rin1101FricomJoinRicmpf1> queryReiners(final String rinComId)
            throws Exception
    {
        return repository.queryReiners(rinComId);
    }
    
    
	/**
     * 
     * @param rinComId
     * @return
     * @throws Exception
     */
    public List<FriCom> autoTenRcid(final String rinComId)
            throws Exception
    {
        return repository.autoTenRcid(rinComId);
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
	public int insert(final FriCom model, FriComMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, FriComMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final FriCom model, FriComMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}
	
	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	//public int insert(final FriCom model, SqlSession sqlSession) throws Exception {
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
	//public int update(final FriCom model, SqlSession sqlSession) throws Exception {
	//	return repository.update(model, sqlSession);
	//}

    /**
     * 
     * @param model
     * @return
     * @throws Exception
     */
    public int insert(final FriCom model) throws Exception
    {
        return repository.insert(model);
    }
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<FriCom> modelList)
			throws Exception {
		return repository.insertList(modelList);
	}

    // /**
    // *
    // * @param modelList
    // * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜FriCom＞)
    // * @throws Exception
    // */
    // public Map<String, Object> insertList(List<FriCom> modelList)
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
    // public FriCom queryByJobId(final String jobId) throws Exception
    // {
    //     return repository.queryByJobId(jobId);
    // }

    /**
     * 
     * @param FriCom
     * @return
     * @throws Exception
     */
    public List<FriCom> queryByFriCom(final FriCom model)
            throws Exception
    {
        return repository.queryByFriCom(model);
    }

    /**
     * 
     * @param FriCom
     * @return
     * @throws Exception
     */
    public List<FriCom> queryByFriCom(final FriCom model, SqlSession sqlSession)
            throws Exception
    {
        return repository.queryByFriCom(model, sqlSession);
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
    public int update(final FriCom model) throws Exception
    {
        return repository.update(model);
    }
	
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriCom> queryAll() throws Exception{
    	return repository.queryAll();
    }
    
	/**
    * 
    * @param sqlSession
    * @return
    * @throws Exception
    */
    public List<FriCom> queryAll(SqlSession sqlSession) throws Exception{
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
//    public List<FriCom> queryDistint{COL_NM}(SqlSession sqlSession) throws Exception {
//    	return repository.queryDistint{COL_NM}(sqlSession);
//    }
//
//    /**
//     * 
//     * @param sqlSession
//     * @return
//     * @throws Exception
//     */
//    public List<FriCom> queryDistint{COL_NM}() throws Exception {
//    	return repository.queryDistint{COL_NM}();
//    }
    
    /**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(FriCom model) throws Exception {
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
	public long queryCount(FriCom model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}