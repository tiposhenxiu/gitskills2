package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrAchievementReportTbl;

@Repository
public interface NrAchievementReportTblRepository extends JpaRepository<NrAchievementReportTbl, String>, JpaSpecificationExecutor<NrAchievementReportTbl> {

}
