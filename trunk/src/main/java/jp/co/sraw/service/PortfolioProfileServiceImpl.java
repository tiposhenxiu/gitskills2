/*
* ファイル名：PortfolioServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.entity.UsCompetitionTbl;
import jp.co.sraw.entity.UsResultUploadTbl;
import jp.co.sraw.entity.UsUserTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.UsCompetitionTblRepository;
import jp.co.sraw.repository.UsResultUploadTblRepository;
import jp.co.sraw.repository.UsUserTblRepository;

/**
 * <B>PortfolioServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = true)
public class PortfolioProfileServiceImpl extends CommonService {

	@Autowired
	private UsUserTblRepository usUserTblRepository;

	@Autowired
	private UsCompetitionTblRepository usCompetitionTblRepository;

	@Autowired
	private UsResultUploadTblRepository usResultUploadTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(UserServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	// ユーザ情報テーブル
	public List<UsUserTbl> findAllUser() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<UsUserTbl> list = usUserTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	public UsUserTbl findOneUser(String userKey) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		UsUserTbl u = usUserTblRepository.findOne(userKey);

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return u;
	}

	// 成果物ファイル
	public List<UsResultUploadTbl> findAllUsResultUpload() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<UsResultUploadTbl> list = usResultUploadTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

	// コンペティションファイルVIEW
	public List<UsCompetitionTbl> findAllUsCompetition() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<UsCompetitionTbl> list = usCompetitionTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

}
