package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sraw.dto.MenuDto;
import jp.co.sraw.entity.MsMenuTbl;

@Repository
public interface MsMenuTblRepository extends JpaRepository<MsMenuTbl, Integer>, JpaSpecificationExecutor<MsMenuTbl> {

	/**
	 * 全メニュー一覧取得
	 * @return
	 */
	@Query(name="MsMenuTbl.findAllMenu")
	public List<MenuDto> findAllMenu();


	/**
	 * ロール対応メニュー一覧取得
	 * @return
	 */
	@Query(name="MsMenuTbl.findAllRoleMenu")
	public List<MenuDto> findAllRoleMenu(String roleCode);

}