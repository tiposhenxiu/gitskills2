package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsResultUploadTbl;
import jp.co.sraw.entity.UsResultUploadTblPK;

@Repository
public interface UsResultUploadTblRepository extends JpaRepository<UsResultUploadTbl, UsResultUploadTblPK>, JpaSpecificationExecutor<UsResultUploadTbl> {

}
