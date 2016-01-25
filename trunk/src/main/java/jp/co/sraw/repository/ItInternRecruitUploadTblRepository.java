package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.ItInternRecruitUploadTbl;
import jp.co.sraw.entity.ItInternRecruitUploadTblPK;

@Repository
public interface ItInternRecruitUploadTblRepository extends JpaRepository<ItInternRecruitUploadTbl, ItInternRecruitUploadTblPK>, JpaSpecificationExecutor<ItInternRecruitUploadTbl> {

}
