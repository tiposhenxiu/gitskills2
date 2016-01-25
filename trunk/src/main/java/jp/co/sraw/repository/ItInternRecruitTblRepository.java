package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.ItInternRecruitTbl;
import jp.co.sraw.entity.ItInternRecruitTblPK;

@Repository
public interface ItInternRecruitTblRepository extends JpaRepository<ItInternRecruitTbl, ItInternRecruitTblPK>, JpaSpecificationExecutor<ItInternRecruitTbl> {

}
