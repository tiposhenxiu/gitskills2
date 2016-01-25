package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.EvEventRelSubjectTbl;
import jp.co.sraw.entity.EvEventRelSubjectTblPK;

@Repository
public interface EvEventRelSubjectTblRepository extends JpaRepository<EvEventRelSubjectTbl, EvEventRelSubjectTblPK>, JpaSpecificationExecutor<EvEventRelSubjectTbl> {

}
