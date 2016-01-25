package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrSubjectUploadTbl;
import jp.co.sraw.entity.NrSubjectUploadTblPK;

@Repository
public interface NrSubjectUploadTblRepository extends JpaRepository<NrSubjectUploadTbl, NrSubjectUploadTblPK>, JpaSpecificationExecutor<NrSubjectUploadTbl> {

}
