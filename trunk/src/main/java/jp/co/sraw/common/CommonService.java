/*
* ファイル名：CommonService.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.common;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import jp.co.sraw.util.ObjectUtil;

/**
* <B>CommonServiceクラス</B>
* <P>
* Serviceのメソッドを提供する
*/
public abstract class CommonService {

	@Autowired
	protected MessageSource messageSource;

	@PostConstruct
	protected abstract void init();

	protected ObjectUtil objectUtil = new ObjectUtil();

	public Object setMapCopyValue(Object targetObject, Map sourceObject){
		ObjectUtil util=new ObjectUtil();
		return util.setMapCopyValue(targetObject,sourceObject);
	}

	public Object getObjectCopyValue(Object targetObject, Object sourceObject){
		ObjectUtil util=new ObjectUtil();
		return util.getObjectCopyValue(targetObject,sourceObject);
	}

}
