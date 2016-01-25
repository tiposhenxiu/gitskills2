package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrLessonPublicTbl;
import jp.co.sraw.entity.NrLessonPublicTblPK;

@Repository
public interface NrLessonPublicTblRepository extends JpaRepository<NrLessonPublicTbl, NrLessonPublicTblPK>, JpaSpecificationExecutor<NrLessonPublicTbl> {

}
