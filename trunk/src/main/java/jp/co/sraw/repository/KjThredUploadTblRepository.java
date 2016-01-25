package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.KjThredUploadTbl;
import jp.co.sraw.entity.KjThredUploadTblPK;

@Repository
public interface KjThredUploadTblRepository extends JpaRepository<KjThredUploadTbl, KjThredUploadTblPK>, JpaSpecificationExecutor<KjThredUploadTbl> {

}
