/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sraw.dto.ViewDto;
import jp.co.sraw.entity.ViewTbl;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.ViewRepository;

/**
 * <B>EventViewServiceImplクラス</B>
 * <P>
 * ユーザーサービスのメソッドを提供する
 */
@Service
@Transactional(readOnly = false)
public class EventViewServiceImpl<T extends ViewTbl, D extends ViewDto, R extends ViewRepository>
		extends ViewServiceImpl {

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(EventViewServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

}
