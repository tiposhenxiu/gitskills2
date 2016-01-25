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
import jp.co.sraw.dto.OperateInfoDto;
import jp.co.sraw.entity.UsOperationHistoryTbl;
import jp.co.sraw.entity.UsOperationHistoryTblPK;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.UsOperationHistoryTblRepository;
import jp.co.sraw.util.StringUtil;

/**
 * <B>OperationHistoryServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = false)
public class OperationHistoryServiceImpl extends CommonService {

	@Autowired
	private UsOperationHistoryTblRepository usOperationHistoryTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(OperationHistoryServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * 新規追加、更新
	 *
	 * @param form
	 * @param userInfo
	 * @return
	 */
	@Transactional
	public boolean insert(OperateInfoDto dto) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}
		try {
			boolean error = false;
			if (StringUtil.isNull(dto.getOperationUserKey())) {
				logger.errorCode("E0015");
				error = true;
			}
			if (StringUtil.isNull(dto.getOerationFuncId())) {
				logger.errorCode("E1016");
				error = true;
			}
			if (StringUtil.isNull(dto.getOperationActionId())) {
				logger.errorCode("E1017");
				error = true;
			}
			if (StringUtil.isNull(dto.getUpdUserKey())) {
				logger.errorCode("E1018");
				error = true;
			}
			if (error) {
				throw new Exception();
			}

			UsOperationHistoryTbl entity = new UsOperationHistoryTbl();

			UsOperationHistoryTblPK pk = new UsOperationHistoryTblPK();
			pk.setOperationActionId(dto.getOperationActionId());
			pk.setOperationDate(dto.getOperationDate());
			pk.setOperationFuncId(dto.getOerationFuncId());
			pk.setOperationUserKey(dto.getOperationUserKey());

			entity.setId(pk);
			entity.setUpdDate(dto.getUpdDate());
			entity.setUpdUserKey(dto.getUpdUserKey());

			entity = usOperationHistoryTblRepository.saveAndFlush(entity);

			if (entity != null) {
				logger.infoCode("I0002"); // I0002=メソッド終了:{0}
				return true;
			}

		} catch (Exception e) {
			logger.errorCode("E1007", e); // E1007=登録に失敗しました。{0}
		}
		return false;
	}

}
