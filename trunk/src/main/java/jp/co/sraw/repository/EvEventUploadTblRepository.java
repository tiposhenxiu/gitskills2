package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.EvEventUploadTbl;
import jp.co.sraw.entity.EvEventUploadTblPK;

@Repository
public interface EvEventUploadTblRepository extends JpaRepository<EvEventUploadTbl, EvEventUploadTblPK>, JpaSpecificationExecutor<EvEventUploadTbl> {

}
