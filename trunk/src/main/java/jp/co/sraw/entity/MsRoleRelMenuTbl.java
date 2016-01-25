package jp.co.sraw.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the ms_role_rel_menu_tbl database table.
 * 
 */
@Entity
@Table(name="ms_role_rel_menu_tbl")
@NamedQuery(name="MsRoleRelMenuTbl.findAll", query="SELECT m FROM MsRoleRelMenuTbl m")
public class MsRoleRelMenuTbl implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MsRoleRelMenuTblPK id;

	private String permission;

	@Column(name="upd_date")
	private Timestamp updDate;

	@Column(name="upd_user_key")
	private String updUserKey;

	public MsRoleRelMenuTbl() {
	}

	public MsRoleRelMenuTblPK getId() {
		return this.id;
	}

	public void setId(MsRoleRelMenuTblPK id) {
		this.id = id;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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

}