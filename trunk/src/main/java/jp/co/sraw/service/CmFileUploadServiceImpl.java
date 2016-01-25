/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.common.UserInfo;
import jp.co.sraw.entity.CmFileUploadTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.CmFileUploadTblRepository;
import jp.co.sraw.util.DateUtil;

/**
 * <B>UserServiceクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class CmFileUploadServiceImpl extends CommonService {

	@Autowired
	private CmFileUploadTblRepository cmFileUploadTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(CmFileUploadServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public CmFileUploadTbl getOne(final String supportKey) {
		return cmFileUploadTblRepository.getOne(supportKey);
	}

	public CmFileUploadTbl findOne(String uploadKey, String userKey) {
		return cmFileUploadTblRepository.findByUploadKeyAndUserKey(uploadKey, userKey);
	}

	public CmFileUploadTbl findOne(String uploadKey) {
		return cmFileUploadTblRepository.findOne(uploadKey);
	}

	public boolean update(UserInfo userInfo, CmFileUploadTbl entity) {

		try {
			//
			entity.setUpdUserKey(userInfo.getLoginUserKey());
			entity.setUpdDate(DateUtil.getNowTimestamp());

			entity = cmFileUploadTblRepository.saveAndFlush(entity);

			if (entity != null) {
				logger.infoCode("I0002");
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

}
