/*
* ファイル名：UserServiceImpl.java
*
* <MODIFICATION HISTORY>
*   (Rev.)     (Date)       (ID/NAME)   (Comment)
*   Rev 1.00   2015/12/01   toishigawa  新規作成
*/
package jp.co.sraw.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import jp.co.sraw.common.CommonService;
import jp.co.sraw.dto.MenuDto;
import jp.co.sraw.logger.LoggerWrapper;
import jp.co.sraw.logger.LoggerWrapperFactory;
import jp.co.sraw.repository.MsMenuTblRepository;

/**
* <B>UserServiceクラス</B>
* <P>
* ユーザーサービスのメソッドを提供する
*/
@Service
@Transactional(readOnly = true)
public class MenuServiceImpl extends CommonService {

	@Autowired
	private MsMenuTblRepository msMenuTblRepository;

	private static final LoggerWrapper logger = LoggerWrapperFactory.getLogger(MenuServiceImpl.class);

	@PostConstruct
	protected void init() {
		logger.setMessageSource(messageSource);
	}

	/**
	 * メニュー一覧取得
	 *
	 * @return
	 */
	public List<MenuDto> findAllRoleMenu(String roleCode) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MenuDto> l = getMenuList(msMenuTblRepository.findAllRoleMenu(roleCode));

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return l;
	}

	/**
	 * メニュー一覧取得
	 *
	 * @return
	 */
	public List<MenuDto> findAllMenu() {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		List<MenuDto> l = getMenuList(msMenuTblRepository.findAllMenu());

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return l;
	}

	/**
	 * URI一覧取得
	 *
	 * @return
	 */
	public Set<String> findAllRoleUri(String roleCode) {
		logger.infoCode("I0001"); // I0001=メソッド開始:{0}

		Set<String> s = new HashSet<String>();

		List<MenuDto> l = msMenuTblRepository.findAllRoleMenu(roleCode);
		for (MenuDto m : l) {
			String uri = m.getActionUrl();
			if (uri != null && !uri.endsWith("/")) {
				uri = uri + "/";
			}
			s.add(uri);
		}

		logger.infoCode("I0002"); // I0002=メソッド終了:{0}
		return s;
	}

	/**
	 * 表示用のメニュー一覧を生成
	 *
	 * @param menuList
	 * @return
	 */
	private List<MenuDto> getMenuList(List<MenuDto> menuList) {

		List<MenuDto> l = new ArrayList<MenuDto>();

		Integer tmpKey = null;
		MenuDto e = new MenuDto();
		for (MenuDto m : menuList) {
			Integer i = m.getOyaMenuId();
			if (i != null && i.equals(tmpKey)) {
				// 子メニュー
				MenuDto sub = new MenuDto();
				sub.setMenuId(m.getMenuId());
				sub.setMenuNameEn(m.getMenuNameEn());
				sub.setMenuName(m.getMenuName());
				sub.setMenuNameAbbrEn(m.getMenuNameAbbrEn());
				sub.setMenuNameAbbr(m.getMenuNameAbbr());
				sub.setMenuLevel(m.getMenuLevel());
				sub.setActionUrl(m.getActionUrl());
				sub.setMenuDispOrder(m.getMenuDispOrder());
				e.getSubMenuList().add(sub);
			} else {
				if (tmpKey != null) {
					l.add(e); //直前までを追加
				}
				tmpKey = i;
				// 新しく生成
				e = new MenuDto();
				e.setSubMenuList(new ArrayList<MenuDto>());
				e.setMenuId(m.getOyaMenuId());
				e.setMenuNameEn(m.getOyaMenuNameEn());
				e.setMenuName(m.getOyaMenuName());
				e.setMenuNameAbbrEn(m.getOyaMenuNameAbbrEn());
				e.setMenuNameAbbr(m.getOyaMenuNameAbbr());
				e.setMenuLevel(m.getOyaMenuLevel());
				e.setMenuDispOrder(m.getOyaMenuDispOrder());
				// 子メニュー
				MenuDto sub = new MenuDto();
				sub.setMenuId(m.getMenuId());
				sub.setMenuNameEn(m.getMenuNameEn());
				sub.setMenuName(m.getMenuName());
				sub.setMenuNameAbbrEn(m.getMenuNameAbbrEn());
				sub.setMenuNameAbbr(m.getMenuNameAbbr());
				sub.setMenuLevel(m.getMenuLevel());
				sub.setActionUrl(m.getActionUrl());
				sub.setMenuDispOrder(m.getMenuDispOrder());
				e.getSubMenuList().add(sub);

			}
		}
		if (!CollectionUtils.isEmpty(menuList)) {
			l.add(e); // 最後に追加
		}

		return l;
	}
}
