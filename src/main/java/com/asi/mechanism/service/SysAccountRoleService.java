package com.asi.mechanism.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysRoleMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRole;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRoleKey;
import com.asi.mechanism.service.dao.mybatis.model.SysRole;
import com.asi.mechanism.service.repository.SysAccountRoleRepository;

@Service
public class SysAccountRoleService {

	private Log log = LogFactory.getLog(SysAccountRoleService.class);

	@Autowired
	private SysAccountRoleRepository repository;

	// =====針對使用自訂SQL=====

	// ...

	// ==========

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int insert(final SysAccountRole model, SysAccountRoleMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(SysAccountRoleKey primaryKey, SysAccountRoleMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final SysAccountRole model, SysAccountRoleMapper mapper) throws Exception {
		return repository.update(model, mapper);
	}

	/**
	 * 
	 * @param pkModel
	 * @param newPkModel
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int updateModifyPk(SysAccountRole pkModel, SysAccountRole newPkModel, SysAccountRoleMapper mapper)
			throws Exception {
		return repository.updateModifyPk(pkModel, newPkModel, mapper);
	}

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int insert(final SysAccountRole model, SqlSession sqlSession) throws
	// Exception {
	// return repository.insert(model, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param primaryKey
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int deleteByKey(final String primaryKey, SqlSession sqlSession) throws
	// Exception {
	// return repository.deleteByKey(primaryKey, sqlSession);
	// }

	/**
	 * (注意!一個SqlSession不能重複開啟同一個Mapper,故此不建議使用)
	 * 
	 * @param model
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	// public int update(final SysAccountRole model, SqlSession sqlSession) throws
	// Exception {
	// return repository.update(model, sqlSession);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final SysAccountRole model) throws Exception {
		return repository.insert(model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<SysAccountRole> modelList) throws Exception {
		return repository.insertList(modelList);
	}

	// /**
	// *
	// * @param modelList
	// * @return Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜SysAccountRole＞)
	// * @throws Exception
	// */
	// public Map<String, Object> insertList(List<SysAccountRole> modelList)
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
	// *
	// * @param jobId
	// * @return
	// * @throws Exception
	// */
	// public SysAccountRole queryByJobId(final String jobId) throws Exception
	// {
	// return repository.queryByJobId(jobId);
	// }

	/**
	 * 
	 * @param SysAccountRole
	 * @return
	 * @throws Exception
	 */
	public List<SysAccountRole> queryBySysAccountRole(final SysAccountRole model) throws Exception {
		return repository.queryBySysAccountRole(model);
	}

	/**
	 * 
	 * @param SysAccountRole
	 * @return
	 * @throws Exception
	 */
	public List<SysAccountRole> queryBySysAccountRole(final SysAccountRole model, SqlSession sqlSession)
			throws Exception {
		return repository.queryBySysAccountRole(model, sqlSession);
	}

	/**
	 * 
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey) throws Exception {
		return repository.deleteByKey(primaryKey);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int update(final SysAccountRole model) throws Exception {
		return repository.update(model);
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<SysAccountRole> queryAll() throws Exception {
		return repository.queryAll();
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<SysAccountRole> queryAll(SqlSession sqlSession) throws Exception {
		return repository.queryAll(sqlSession);
	}

	// /**
	// *
	// * @return
	// * @throws Exception
	// */
	// public int deleteAll() throws Exception{
	// return repository.deleteAll();
	// }

	/// **
	// *
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	// public List<SysAccountRole> queryDistint{COL_NM}(SqlSession sqlSession)
	/// throws Exception {
	// return repository.queryDistint{COL_NM}(sqlSession);
	// }
	//
	/// **
	// *
	// * @param sqlSession
	// * @return
	// * @throws Exception
	// */
	// public List<SysAccountRole> queryDistint{COL_NM}() throws Exception {
	// return repository.queryDistint{COL_NM}();
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(SysAccountRole model) throws Exception {
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
	public long queryCount(SysAccountRole model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}