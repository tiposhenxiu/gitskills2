/*
* ファイル名：MsRoleServiceImpl.java
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
import jp.co.sraw.entity.MsRoleTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsRoleTblRepository;

/**
* <B>MsRoleServiceクラス</B>
* <P>
* ロールサービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class MsRoleServiceImpl extends CommonService {

	@Autowired
	private MsRoleTblRepository msRoleTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MsRoleServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	public List<MsRoleTbl> findAll() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MsRoleTbl> list = msRoleTblRepository.findAll();

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return list;
	}

}
