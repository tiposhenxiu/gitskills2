package jp.co.sraw.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrLessonCourseTbl;
import jp.co.sraw.entity.NrLessonCourseTblPK;
@Repository
public interface NrLessonCourseTblRepository extends JpaRepository<NrLessonCourseTbl, NrLessonCourseTblPK>, JpaSpecificationExecutor<NrLessonCourseTbl> {

	public NrLessonCourseTbl findByIdLessonKeyAndIdUserKeyAndUpdDate(String lessonKey, String userKey, Timestamp dt);
}
