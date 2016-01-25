package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsLessonTbl;
import jp.co.sraw.entity.UsLessonTblPK;

@Repository
public interface UsLessonTblRepository extends JpaRepository<UsLessonTbl, UsLessonTblPK>, JpaSpecificationExecutor<UsLessonTbl> {

}
