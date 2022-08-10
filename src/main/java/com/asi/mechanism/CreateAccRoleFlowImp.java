package com.asi.mechanism;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asi.mechanism.common.SysEnum;
import com.asi.mechanism.service.SysAccountRoleService;
import com.asi.mechanism.service.SysAccountService;
import com.asi.mechanism.service.SysUserPersonalService;
import com.asi.mechanism.service.connector.MyBatisConnector;
import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysAccountRoleMapper;
import com.asi.mechanism.service.dao.mybatis.mapper.SysUserPersonalMapper;
import com.asi.mechanism.service.dao.mybatis.model.SysAccount;
import com.asi.mechanism.service.dao.mybatis.model.SysAccountRole;
import com.asi.mechanism.service.dao.mybatis.model.SysUserPersonal;

@Component
public class CreateAccRoleFlowImp implements CreateAccRoleFlow {

	private static Logger log = LogManager.getLogger(CreateAccRoleFlowImp.class);

	@Autowired
	private SysAccountService sysAccountService;

	@Autowired
	private SysAccountRoleService sysAccountRoleService;

	@Autowired
	private SysUserPersonalService sysUserPersonalService;

	@Autowired
	private MyBatisConnector mybatis;

	@Override
	public boolean create(String newAkaId, String md5) {
		log.debug("create acc role data - start");

		try {
			// 檢查帳號、帳號角色表格是否已有該帳號資料
			SysAccount acc = new SysAccount();
			acc.setAkaId(newAkaId);
			List<SysAccount> accList4Check = this.sysAccountService.queryBySysAccount(acc);

			// 已有帳號資料即回覆正確即可
			if (accList4Check != null && accList4Check.size() > 0) {
				log.debug("account exist");
				return true;
			}

			// 新增資料流程
			SqlSession sqlSession = mybatis.createSqlSessionFactory().openSession();

			// 因為須正確關閉session，故再一層try-catch
			// 另此部份不需要關心寫入筆數的問題
			// 因為已在系統資料表中建立預設角色，
			// 故僅需要新增一筆資料「新的帳號資料並綁定預設角色」
			// 再新增至sys_account_role中即可
			try {
				SysAccountMapper sysAccMapper = sqlSession.getMapper(SysAccountMapper.class);
				SysAccountRoleMapper sysAccRoleMapper = sqlSession.getMapper(SysAccountRoleMapper.class);
				SysUserPersonalMapper sysUserPerMapper = sqlSession.getMapper(SysUserPersonalMapper.class);

				SysAccount accCond = new SysAccount();
				accCond.setAkaId(newAkaId);
				List<SysAccount> accList = sysAccountService.queryBySysAccount(accCond, sqlSession);

				// 確認無資料才寫入
				if (accList != null && accList.size() == 0) {
					SysAccount newAcc = new SysAccount();
					newAcc.setAkaId(newAkaId);
					newAcc.setUserName(newAkaId);
					newAcc.setMail(newAkaId);
					newAcc.setCipher(md5);
					newAcc.setCrtDate(new Date());

					int succVal = this.sysAccountService.insert(newAcc, sysAccMapper);
					log.debug("insert data to succVal : " + succVal);
				}

				// 這邊僅找帳號是否存在在帳號角色表中即可，即此帳號已有角色的意思
				SysAccountRole accRoleCond = new SysAccountRole();
				accRoleCond.setAkaId(newAkaId);
				List<SysAccountRole> accRoleList = sysAccountRoleService.queryBySysAccountRole(accRoleCond);

				if (accRoleList != null && accRoleList.size() == 0) {
					SysAccountRole newAccRole = new SysAccountRole();
					newAccRole.setAkaId(newAkaId);
					newAccRole.setUserRole(SysEnum.systemDefaultRole.context);
					int succVal2 = this.sysAccountRoleService.insert(newAccRole, sysAccRoleMapper);
					log.debug("insert data to succVal2 : " + succVal2);
				}

				// 這邊僅找帳號是否存在在個人設定表中即可，即此帳號的設定已存在的意思
				SysUserPersonal userPerCond = new SysUserPersonal();
				userPerCond.setAkaId(newAkaId);
				List<SysUserPersonal> userPerList = this.sysUserPersonalService.queryBySysUserPersonal(userPerCond);

				if (userPerList != null && userPerList.size() == 0) {
					SysUserPersonal newPer = new SysUserPersonal();
					newPer.setAkaId(newAkaId);
					newPer.setPageBg("colorful");
					int succVal3 = this.sysUserPersonalService.insert(newPer, sysUserPerMapper);
					log.debug("insert data to succVal3 : " + succVal3);
				}

				sqlSession.commit();
				log.debug("create acc role data - finish");
				return true;
			} catch (Exception e) {
				// 因和產生帳號基本資料有關，故這邊log4j的層級較低使之系統平常運作即可以攔截到
				sqlSession.rollback();
				log.debug("create acc role data - error");
				throw e;
			} finally {
				sqlSession.close();
			}

		} catch (Exception e) {
			// 因和產生帳號基本資料有關，故這邊log4j的層級較低使之系統平常運作即可以攔截到
			log.debug(e.toString());
			Arrays.asList(e.getStackTrace()).forEach(sub -> log.info(sub.toString()));
			return false;
		}

	}

}
