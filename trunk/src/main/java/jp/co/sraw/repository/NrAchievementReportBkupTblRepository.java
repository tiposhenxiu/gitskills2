package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrAchievementReportBkupTbl;
import jp.co.sraw.entity.NrAchievementReportBkupTblPK;

@Repository
public interface NrAchievementReportBkupTblRepository extends JpaRepository<NrAchievementReportBkupTbl, NrAchievementReportBkupTblPK>, JpaSpecificationExecutor<NrAchievementReportBkupTbl> {

}
