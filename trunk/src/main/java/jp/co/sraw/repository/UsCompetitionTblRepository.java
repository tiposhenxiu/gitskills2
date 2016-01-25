package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsCompetitionTbl;
import jp.co.sraw.entity.UsCompetitionTblPK;

@Repository
public interface UsCompetitionTblRepository extends JpaRepository<UsCompetitionTbl, UsCompetitionTblPK>, JpaSpecificationExecutor<UsCompetitionTbl> {

}
