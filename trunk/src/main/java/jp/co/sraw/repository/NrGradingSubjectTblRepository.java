package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrGradingSubjectTbl;
import jp.co.sraw.entity.NrGradingSubjectTblPK;

@Repository
public interface NrGradingSubjectTblRepository extends JpaRepository<NrGradingSubjectTbl, NrGradingSubjectTblPK>, JpaSpecificationExecutor<NrGradingSubjectTbl> {

}
