package jp.co.sraw.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrSubjectAnswerBkupTbl;
import jp.co.sraw.entity.NrSubjectAnswerBkupTblPK;

@Repository
public interface NrSubjectAnswerBkupTblRepository
		extends JpaRepository<NrSubjectAnswerBkupTbl, NrSubjectAnswerBkupTblPK>,
		JpaSpecificationExecutor<NrSubjectAnswerBkupTbl> {

	public List<NrSubjectAnswerBkupTbl> findByIdUserKeyAndIdRubricKey(String userKey, String rubricKey);

	public List<NrSubjectAnswerBkupTbl> findByIdUserKeyAndIdRubricKeyAndIdSaveDateOrderByUpdDateDesc(String userKey,
			String rubricKey, Date savedDate);

	public NrSubjectAnswerBkupTbl findByIdUserKeyAndIdRubricKeyAndIdSubjectCodeAndIdSaveDate(String userKey,
			String rubricKey, String abilityCode, Date savedDate);

	@Modifying
	@Query(name = "NrSubjectAnswerBkupTbl.backup")
	public int backup(Date savedDate);
}
