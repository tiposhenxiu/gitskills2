package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsPrmovieUploadTbl;
import jp.co.sraw.entity.UsPrmovieUploadTblPK;

@Repository
public interface UsPrmovieUploadTblRepository extends JpaRepository<UsPrmovieUploadTbl, UsPrmovieUploadTblPK>, JpaSpecificationExecutor<UsPrmovieUploadTbl> {

}
