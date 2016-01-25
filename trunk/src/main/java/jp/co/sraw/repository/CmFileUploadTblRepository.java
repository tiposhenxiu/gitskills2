package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmFileUploadTbl;

@Repository
public interface CmFileUploadTblRepository extends JpaRepository<CmFileUploadTbl, String>, JpaSpecificationExecutor<CmFileUploadTbl> {
	public CmFileUploadTbl findByUploadKeyAndUserKey(String uploadKey, String userKey);
}
