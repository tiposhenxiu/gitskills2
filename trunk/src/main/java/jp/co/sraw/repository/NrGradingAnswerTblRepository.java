package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrGradingAnswerTbl;
import jp.co.sraw.entity.NrGradingAnswerTblPK;

@Repository
public interface NrGradingAnswerTblRepository extends JpaRepository<NrGradingAnswerTbl, NrGradingAnswerTblPK>, JpaSpecificationExecutor<NrGradingAnswerTbl> {

}
