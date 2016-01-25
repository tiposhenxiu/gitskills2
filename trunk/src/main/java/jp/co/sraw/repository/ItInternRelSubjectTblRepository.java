package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.ItInternRelSubjectTbl;
import jp.co.sraw.entity.ItInternRelSubjectTblPK;

@Repository
public interface ItInternRelSubjectTblRepository extends JpaRepository<ItInternRelSubjectTbl, ItInternRelSubjectTblPK>, JpaSpecificationExecutor<ItInternRelSubjectTbl> {

}
