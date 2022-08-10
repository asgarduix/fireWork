package com.asi.mechanism.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asi.mechanism.service.dao.mybatis.mapper.SysFuncUiFieldRoleMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysFuncUiFieldRole;
import com.asi.mechanism.service.repository.SysFuncUiFieldRoleRepository;

@Service
public class SysFuncUiFieldRoleService {

	private Log log = LogFactory.getLog(SysFuncUiFieldRoleService.class);

	@Autowired
	private SysFuncUiFieldRoleRepository repository;

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
	public int insert(final SysFuncUiFieldRole model, SysFuncUiFieldRoleMapper mapper) throws Exception {
		return repository.insert(model, mapper);
	}

	/**
	 * 
	 * @param primaryKey
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int deleteByKey(final String primaryKey, SysFuncUiFieldRoleMapper mapper) throws Exception {
		return repository.deleteByKey(primaryKey, mapper);
	}

	/**
	 * 
	 * @param model
	 * @param mapper
	 * @return
	 * @throws Exception
	 */
	public int update(final SysFuncUiFieldRole model, SysFuncUiFieldRoleMapper mapper) throws Exception {
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
	public int updateModifyPk(SysFuncUiFieldRole pkModel, SysFuncUiFieldRole newPkModel,
			SysFuncUiFieldRoleMapper mapper) throws Exception {
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
	// public int insert(final SysFuncUiFieldRole model, SqlSession sqlSession)
	// throws Exception {
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
	// public int update(final SysFuncUiFieldRole model, SqlSession sqlSession)
	// throws Exception {
	// return repository.update(model, sqlSession);
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insert(final SysFuncUiFieldRole model) throws Exception {
		return repository.insert(model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int insertList(final List<SysFuncUiFieldRole> modelList) throws Exception {
		return repository.insertList(modelList);
	}

	// /**
	// *
	// * @param modelList
	// * @return
	// Key:SUCC_VAL(成功筆數,int),ERR_OBJ_LIST(失敗model,List＜SysFuncUiFieldRole＞)
	// * @throws Exception
	// */
	// public Map<String, Object> insertList(List<SysFuncUiFieldRole> modelList)
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
	// public SysFuncUiFieldRole queryByJobId(final String jobId) throws Exception
	// {
	// return repository.queryByJobId(jobId);
	// }

	/**
	 * 
	 * @param SysFuncUiFieldRole
	 * @return
	 * @throws Exception
	 */
	public List<SysFuncUiFieldRole> queryBySysFuncUiFieldRole(final SysFuncUiFieldRole model) throws Exception {
		return repository.queryBySysFuncUiFieldRole(model);
	}

	/**
	 * 
	 * @param SysFuncUiFieldRole
	 * @return
	 * @throws Exception
	 */
	public List<SysFuncUiFieldRole> queryBySysFuncUiFieldRole(final SysFuncUiFieldRole model, SqlSession sqlSession)
			throws Exception {
		return repository.queryBySysFuncUiFieldRole(model, sqlSession);
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
	public int update(final SysFuncUiFieldRole model) throws Exception {
		return repository.update(model);
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<SysFuncUiFieldRole> queryAll() throws Exception {
		return repository.queryAll();
	}

	/**
	 * 
	 * @param sqlSession
	 * @return
	 * @throws Exception
	 */
	public List<SysFuncUiFieldRole> queryAll(SqlSession sqlSession) throws Exception {
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
	// public List<SysFuncUiFieldRole> queryDistint{COL_NM}(SqlSession sqlSession)
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
	// public List<SysFuncUiFieldRole> queryDistint{COL_NM}() throws Exception {
	// return repository.queryDistint{COL_NM}();
	// }

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public long queryCount(SysFuncUiFieldRole model) throws Exception {
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
	public long queryCount(SysFuncUiFieldRole model, SqlSession sqlSession) throws Exception {
		return repository.queryCount(model, sqlSession);
	}
}