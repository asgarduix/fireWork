package com.asi.huanan.service.repository.cutomerize;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asi.huanan.service.dao.mybatis.mapper.customerize.CustomerizeMapper;
import com.asi.huanan.service.dao.mybatis.model.customerize.FricomJoinRicmpf1;
import com.asi.mechanism.service.connector.MyBatisConnector;

@Repository
public class CustomerizeRepository {

	@Autowired
	private MyBatisConnector mybatis;	

	
	public List<FricomJoinRicmpf1> queryOneReiner(String rinComId) throws Exception {

		List<FricomJoinRicmpf1> results = new ArrayList<>();

		SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

		try
        {
			CustomerizeMapper mapper = sqlSession.getMapper(CustomerizeMapper.class);
			results = mapper.queryOneReiner(rinComId);
			
        } catch(Exception e){
			sqlSession.rollback();
			throw e;
		}
        finally
        {
            sqlSession.close();
        }
        return results;
	}
}
