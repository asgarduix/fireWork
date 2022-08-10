package com.asi.huanan.service.repository.cutomerize;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.customerize.Rin1304Mapper;
import com.asi.huanan.service.dao.mybatis.model.customerize.Rin1304QueryTree;
import com.asi.huanan.vo.Rin1304QueryTreeVoReq;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class Rin1304Repository {

	private Log log = LogFactory.getLog(Rin1304Repository.class);

	@Autowired
	private MyBatisConnector mybatis;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryTree(Rin1304QueryTreeVoReq vo, int layer) throws Exception {
		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();
		List<? extends Rin1304QueryTree> results = new ArrayList<>();

		try {
			Rin1304Mapper mapper = sqlSession.getMapper(Rin1304Mapper.class);

			if (layer > 3 || layer < 0) {
				log.debug("illeagal layer no : " + layer);
				throw new Exception("illeagal layer no : " + layer);
			}

			switch (layer) {
			case 0:
				results = mapper.queryTreeLayer0(vo);
				break;
			case 1:
				results = mapper.queryTreeLayer1(vo);
				break;
			case 2:
				results = mapper.queryTreeLayer2(vo);
				break;
			case 3:
				results = mapper.queryTreeLayer3(vo);
				break;
			default:
				break;
			}

		} catch (Exception e) {
			sqlSession.rollback();
			throw e;
		} finally {
			sqlSession.close();
		}

		return (T) results;
	}

}
