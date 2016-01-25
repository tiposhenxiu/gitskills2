/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.portfolio.form.ProfileForm;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.UsUserTblRepository;
import jp.co.sraw.util.DateUtil;

/**
* <B>UserServiceクラス</B>
* <P>
* ユーザーサービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends CommonService {

	@Autowired
	private UsUserTblRepository usUserTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(UserServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<UsUserTbl> findAll() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<UsUserTbl> list = usUserTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	public UsUserTbl findOne(String userKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		UsUserTbl u = usUserTblRepository.findOne(userKey);

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return u;
	}

	/**
	 * 更新
	 *
	 * @param userKey
	 * @param updDate
	 * @return
	 */
	public boolean update(String userKey, ProfileForm form, UserInfo userInfo) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		boolean resultFlg = false;

		// 取得条件：ユーザキー
		Specification<UsUserTbl> whereUserKey =
			new Specification<UsUserTbl>() {
				@Override
				public Predicate toPredicate(Root<UsUserTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("userKey"), userKey);
				}
			};

		// 取得条件：更新日付
		Specification<UsUserTbl> whereUpdDate =
			new Specification<UsUserTbl>() {
				@Override
				public Predicate toPredicate(Root<UsUserTbl> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					return cb.equal(root.get("updDate"), form.getUpdDate());
				}
			};

		UsUserTbl entity = usUserTblRepository.findOne(Specifications.where(whereUserKey).and(whereUpdDate));

		if (entity != null) {
			//データ更新
			entity.setUserPublicFlag(form.getUserPublicFlag());
			entity.setDegree(form.getDegree());
			entity.setUserFamilyName(form.getUserFamilyName());
			entity.setUserMiddleName(form.getUserMiddleName());
			entity.setUserName(form.getUserName());
			entity.setUserFamilyNameKn(form.getUserFamilyNameKn());
			entity.setUserMiddleNameKn(form.getUserMiddleNameKn());
			entity.setUserNameKn(form.getUserNameKn());
			entity.setUserFamilyNameEn(form.getUserFamilyNameEn());
			entity.setUserMiddleNameEn(form.getUserMiddleNameEn());
			entity.setUserNameEn(form.getUserNameEn());
			entity.setSex(form.getSex());
			entity.setResearchSubject(form.getResearchSubject());
			entity.setStudentId(form.getStudentId());
			entity.setCountry(form.getCountry());
			entity.setFreeInput1(form.getFreeInput1());
			entity.setFreeInput2(form.getFreeInput2());
			entity.setUploadKey(form.getUploadKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			usUserTblRepository.saveAndFlush(entity);
			resultFlg = true;
		}

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return resultFlg;
	}

}
