package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrSubjectAnswerTbl;
import jp.co.sraw.entity.NrSubjectAnswerTblPK;

@Repository
public interface NrSubjectAnswerTblRepository
		extends JpaRepository<NrSubjectAnswerTbl, NrSubjectAnswerTblPK>, JpaSpecificationExecutor<NrSubjectAnswerTbl> {
	public List<NrSubjectAnswerTbl> findByIdUserKeyAndIdRubricKeyOrderByUpdDateDesc(String userKey, String rubricKey);

	public NrSubjectAnswerTbl findByIdUserKeyAndIdRubricKeyAndIdSubjectCode(String userKey, String rubricKey,
			String abilityCode);
}
