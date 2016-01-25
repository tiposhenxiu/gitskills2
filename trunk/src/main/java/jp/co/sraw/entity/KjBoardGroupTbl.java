package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import jp.co.sraw.common.CommonConst;



/**
 * The persistent class for the kj_board_group_tbl database table.
 * 
 */
@Entity
@Table(name="kj_board_group_tbl")
@NamedQuery(name="KjBoardGroupTbl.findAll", query="SELECT k FROM KjBoardGroupTbl k")
public class KjBoardGroupTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name="KJ_BOARD_GROUP_TBL_BOARDGROUPKEY_GENERATOR", strategy="jp.co.sraw.generator.SmartIdSequenceGenerator",
	parameters={
			@Parameter(name="sequence", value="KJ_BOARD_GROUP_SEQ"),
			@Parameter(name="allocationSize", value="1"),
			@Parameter(name="format", value=CommonConst.SEQUENCE_FORMAT)
	})
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="KJ_BOARD_GROUP_TBL_BOARDGROUPKEY_GENERATOR")
	@Column(name="board_group_key")
	private String boardGroupKey;

	@Column(name="admin_user_key")
	private String adminUserKey;

	@Column(name="board_name")
	private String boardName;

	@Column(name="common_flag")
	private String commonFlag;

	private String outline;

	@Column(name="public_flag")
	private String publicFlag;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	//bi-directional many-to-one association to KjBoardRelUser
	@OneToMany(mappedBy="kjBoardGroupTbl")
	private List<KjBoardRelUser> kjBoardRelUsers;

	//bi-directional many-to-one association to KjThredTbl
	@OneToMany(mappedBy="kjBoardGroupTbl")
	private List<KjThredTbl> kjThredTbls;

	public KjBoardGroupTbl() {
	}

	public String getBoardGroupKey() {
		return this.boardGroupKey;
	}

	public void setBoardGroupKey(String boardGroupKey) {
		this.boardGroupKey = boardGroupKey;
	}

	public String getAdminUserKey() {
		return this.adminUserKey;
	}

	public void setAdminUserKey(String adminUserKey) {
		this.adminUserKey = adminUserKey;
	}

	public String getBoardName() {
		return this.boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getCommonFlag() {
		return this.commonFlag;
	}

	public void setCommonFlag(String commonFlag) {
		this.commonFlag = commonFlag;
	}

	public String getOutline() {
		return this.outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public String getPublicFlag() {
		return this.publicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		this.publicFlag = publicFlag;
	}

	public Timestamp getUpdDate() {
		return this.updDate;
	}

	public void setUpdDate(Timestamp updDate) {
		this.updDate = updDate;
	}

	public String getUpdUserKey() {
		return this.updUserKey;
	}

	public void setUpdUserKey(String updUserKey) {
		this.updUserKey = updUserKey;
	}

	public List<KjBoardRelUser> getKjBoardRelUsers() {
		return this.kjBoardRelUsers;
	}

	public void setKjBoardRelUsers(List<KjBoardRelUser> kjBoardRelUsers) {
		this.kjBoardRelUsers = kjBoardRelUsers;
	}

	public KjBoardRelUser addKjBoardRelUser(KjBoardRelUser kjBoardRelUser) {
		getKjBoardRelUsers().add(kjBoardRelUser);
		kjBoardRelUser.setKjBoardGroupTbl(this);

		return kjBoardRelUser;
	}

	public KjBoardRelUser removeKjBoardRelUser(KjBoardRelUser kjBoardRelUser) {
		getKjBoardRelUsers().remove(kjBoardRelUser);
		kjBoardRelUser.setKjBoardGroupTbl(null);

		return kjBoardRelUser;
	}

	public List<KjThredTbl> getKjThredTbls() {
		return this.kjThredTbls;
	}

	public void setKjThredTbls(List<KjThredTbl> kjThredTbls) {
		this.kjThredTbls = kjThredTbls;
	}

	public KjThredTbl addKjThredTbl(KjThredTbl kjThredTbl) {
		getKjThredTbls().add(kjThredTbl);
		kjThredTbl.setKjBoardGroupTbl(this);

		return kjThredTbl;
	}

	public KjThredTbl removeKjThredTbl(KjThredTbl kjThredTbl) {
		getKjThredTbls().remove(kjThredTbl);
		kjThredTbl.setKjBoardGroupTbl(null);

		return kjThredTbl;
	}

}