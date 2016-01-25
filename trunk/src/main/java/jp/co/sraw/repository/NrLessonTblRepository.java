package jp.co.sraw.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrLessonTbl;

@Repository
public interface NrLessonTblRepository extends JpaRepository<NrLessonTbl, String>, JpaSpecificationExecutor<NrLessonTbl> {
//	@Modifying
//	@Query(name = "NrLessonTbl.searchNrLessonTblForLessonCode")
//	public List<NrLessonTbl> searchNrLessonTblForLessonCode(@Param("rubricKey") String rubricKey
//			, @Param("subjectCode") String subjectCode
//			, @Param("courseStatus") String courseStatus);
}
