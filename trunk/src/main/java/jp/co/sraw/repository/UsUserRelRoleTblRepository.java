package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.dto.UserMenuDto;
import jp.co.sraw.dto.UserRoleDto;
import jp.co.sraw.entity.UsUserRelRoleTbl;
import jp.co.sraw.entity.UsUserRelRoleTblPK;

@Repository
public interface UsUserRelRoleTblRepository extends JpaRepository<UsUserRelRoleTbl, UsUserRelRoleTblPK>, JpaSpecificationExecutor<UsUserRelRoleTbl> {

	public List<UsUserRelRoleTbl> findAllById_UserKeyOrderById_RoleCode(String userKey);

	/**
	 * ユーザーロールコード取得(ROLE_CODEとROLEを取得)
	 * ※@JoinColumn設定していないテーブルの値を取得する場合はinner joinのみ使用となる。
	 * @return
	 */
	@Query(name="UsUserRelRoleTbl.findAllUserRole")
	public List<UserRoleDto> findAllUserRole(@Param("userKey") String userKey);

	/**
	 * ユーザーロールコード取得(ROLE_CODEとROLEを取得)
	 * ※@JoinColumn設定していないテーブルの値を取得する場合はinner joinのみ使用となる。
	 * @return
	 */
	@Query(name="UsUserRelRoleTbl.findUserRole")
	public UserRoleDto findUserRole(@Param("userKey") String userKey, @Param("roleCode") String roleCode);

	/**
	 * メニューのアクセス権を取得(MENU_ID, ACTION_URL, PERMISSIONを取得)
	 * ※@JoinColumn設定していないテーブルの値を取得する場合はinner joinのみ使用となる。
	 * @return
	 */
	@Query(name="UsUserRelRoleTbl.findAllUserMenu")
	public List<UserMenuDto> findAllUserMenu(@Param("userKey") String userKey, @Param("roleCode") String roleCode);
}
