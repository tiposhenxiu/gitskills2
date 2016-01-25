package jp.co.sraw.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.MsCodeTbl;
import jp.co.sraw.entity.MsCodeTblPK;

@Repository
public interface MsCodeTblRepository extends JpaRepository<MsCodeTbl, MsCodeTblPK>, JpaSpecificationExecutor<MsCodeTbl> {

	@Modifying
	@Query(name="MsCodeTbl.delete")
	public int delete(String josuKbn, String josuCode, Timestamp updDate);
}
