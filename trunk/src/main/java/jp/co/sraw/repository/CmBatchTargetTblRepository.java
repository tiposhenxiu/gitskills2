package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmBatchTargetTbl;
import jp.co.sraw.entity.CmBatchTargetTblPK;

@Repository
public interface CmBatchTargetTblRepository extends JpaRepository<CmBatchTargetTbl, CmBatchTargetTblPK>, JpaSpecificationExecutor<CmBatchTargetTbl> {

}
