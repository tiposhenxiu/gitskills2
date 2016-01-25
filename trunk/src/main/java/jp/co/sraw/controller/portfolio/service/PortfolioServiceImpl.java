/*
* ファイル名：PortfolioServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.controller.portfolio.service;

import java.sql.Timestamp;
import java.util.ArrayList;
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

import jp.co.sraw.common.CommonConst;
import jp.co.sraw.common.CommonForm;
import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.controller.portfolio.form.PortfolioForm;
import jp.co.sraw.entity.GyCommonTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.GyRepository;
import jp.co.sraw.service.UserServiceImpl;
import jp.co.sraw.util.DateUtil;
import jp.co.sraw.util.StringUtil;

/**
 * <B>PortfolioServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@SuppressWarnings("rawtypes")
@Service
@Transactional(readOnly = true)
public abstract class PortfolioServiceImpl<T extends GyCommonTbl, F extends PortfolioForm, R extends GyRepository>
		extends CommonService {

	@Autowired
	private R repository;

	@Autowired
	private UserServiceImpl userServiceImpl;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(PortfolioServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<F> getDtoList(List<T> list) {
		List<F> dtoList = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			F form = getPortfolioForm(list.get(i));
			form.setViewType(CommonForm.VIEW_TYPE_FORM);
			dtoList.add(form);
		}
		return dtoList;
	}

	public abstract F getPortfolioForm(T t);

	@SuppressWarnings("unchecked")
	public T getPortfolioTbl(F f, T t) {
		return (T) objectUtil.getObjectCopyValue(t, f);
	}

	public List<F> findAllDto(UserInfo userInfo, F form) {
		logger.infoCode("I0001");
		List<T> list = findAll(userInfo, form);
		logger.infoCode("I0002");
		return getDtoList(list);
	}

	public List<F> findAllProfileView(UserInfo userInfo, String[] publicFlags) {
		logger.infoCode("I0001");

		//
		Specification<T> whereUserKey = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("usUserTbl").get("userKey"), userInfo.getTargetUserKey());
			}
		};

		// 取得条件：
		Specification<T> wherePublicFlags = publicFlags == null ? null : new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				for (int i = 0; i < publicFlags.length; i++) {
					String keyword = publicFlags[i];
					if (i == 0) {
						predicate = cb.and(predicate, cb.equal(root.get("publicFlag"), keyword));
					} else {
						predicate = cb.or(predicate, cb.equal(root.get("publicFlag"), keyword));
					}
				}
				return predicate;
			}
		};

		List<T> list = (List<T>) repository
				.findAll((Specification<T>) Specifications.where(whereUserKey).and(wherePublicFlags));

		logger.infoCode("I0002");
		return getDtoList(list);
	}

	public List<T> findAll(UserInfo userInfo, F form) {
		logger.infoCode("I0001");

		// 必須
		Specification<T> whereUserKey = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("usUserTbl").get("userKey"), userInfo.getTargetUserKey());
			}
		};
		// 言語
		Specification<T> whereLanguage = StringUtil.isNull(form.getLanguage()) ? null : new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("paperLanguage"), form.getLanguage());
			}
		};

		List<T> list = (List<T>) repository
				.findAll((Specification<T>) Specifications.where(whereUserKey).and(whereLanguage));

		logger.infoCode("I0002");
		return list;
	}

	@Transactional
	public boolean delete(UserInfo userInfo, F form) {
		logger.infoCode("I0001");
		try {
			if (form.getPageMode().equals(CommonConst.PAGE_MODE_DELETE)) {
				T entity = findOne(form.getGyosekiKey(), userInfo.getTargetUserKey(), form.getUpdDateAsTimestamp());
				if (entity == null) {
					String objInfo = "key=" + form.getGyosekiKey() + " updDate=" + form.getUpdDate();
					logger.errorCode("E1009", "entity donot find " + objInfo); // E1009=削除に失敗しました。{0}
					return false;
				}
				repository.delete(entity.getGyosekiKey());
				repository.flush();
				logger.infoCode("I0002");
				return true;
			}
		} catch (Exception e) {
			logger.errorCode("E1009", e); // E1009=削除に失敗しました。{0}
		}
		return false;
	}

	@Transactional
	public boolean updateAll(UserInfo userInfo, F form) {
		logger.infoCode("I0001");
		try {

			List<T> list = findAll(userInfo, form);

			for (int i = 0; i < list.size(); i++) {
				T entity = list.get(i);

				entity.setPublicFlag(form.getPublicFlag());

				UsUserTbl usUserTbl = new UsUserTbl();
				usUserTbl.setUserKey(userInfo.getTargetUserKey());
				entity.setUsUserTbl(usUserTbl);
				entity.setUpdUserKey(userInfo.getLoginUserKey());
				entity.setUpdDate(DateUtil.getNowTimestamp());

				repository.save(entity);
			}
			repository.flush();
			logger.infoCode("I0002");
			return true;
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	@Transactional
	public boolean update(UserInfo userInfo, F form) {
		logger.infoCode("I0001");
		try {
			T entity = (T) form.getNewTbl();

			if (form.getPageMode().equals(CommonConst.PAGE_MODE_EDIT)) {
				entity = findOne(form.getGyosekiKey(), userInfo.getTargetUserKey(), form.getUpdDateAsTimestamp());
				if (entity == null) {
					throw new Exception();
				}
			}

			entity = (T) getPortfolioTbl(form, entity);

			UsUserTbl usUserTbl = new UsUserTbl();
			usUserTbl.setUserKey(userInfo.getTargetUserKey());
			entity.setUsUserTbl(usUserTbl);
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());

			entity = (T) repository.saveAndFlush(entity);

			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	@Transactional
	public boolean importData(UserInfo userInfo, List<F> list) {
		logger.infoCode("I0001");
		try {

			for (int i = 0; i < list.size(); i++) {
				F form = list.get(i);

				T entity = (T) form.getNewTbl();
				entity = this.getPortfolioTbl(form, entity);

				entity.setPublicFlag(form.getPublicFlag());

				UsUserTbl usUserTbl = new UsUserTbl();
				usUserTbl.setUserKey(userInfo.getTargetUserKey());
				entity.setUsUserTbl(usUserTbl);
				entity.setUpdUserKey(userInfo.getLoginUserKey());
				entity.setUpdDate(DateUtil.getNowTimestamp());

				repository.save(entity);
			}

			repository.flush();
			logger.infoCode("I0002");
			return true;
		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

	/**
	 * 支援制度supportKey指定取得
	 *
	 * @param supportKey
	 * @return
	 */
	public F getOne(final String paperKey) {
		return getPortfolioForm((T) repository.getOne(paperKey));
	}

	public F findOne(UserInfo userInfo, F form) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		if (form.getGyosekiKey() == null && form.getUpdDate() == null) {
			return null;
		}

		T gyPaperTbl = findOne(form.getGyosekiKey(), userInfo.getTargetUserKey(), form.getUpdDateAsTimestamp());
		if (gyPaperTbl == null) {
			return null;
		}
		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return getPortfolioForm(gyPaperTbl);
	}

	public T findOne(String gyosekiKey, String userKey, Timestamp updDate) {
		// 必須
		Specification<T> whereGyosekiKey = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("gyosekiKey"), gyosekiKey);
			}
		};
		// 必須
		Specification<T> whereUserKey = new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("usUserTbl").get("userKey"), userKey);
			}
		};
		// 更新日
		Specification<T> whereUpdDate = updDate == null ? null : new Specification<T>() {
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("updDate"), updDate);
			}
		};
		return (T) repository
				.findOne((Specification<T>) Specifications.where(whereGyosekiKey).and(whereUserKey).and(whereUpdDate));
	}

}
