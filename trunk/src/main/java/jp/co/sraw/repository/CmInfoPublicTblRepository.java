package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmInfoPublicTbl;
import jp.co.sraw.entity.CmInfoPublicTblPK;

@Repository
public interface CmInfoPublicTblRepository extends JpaRepository<CmInfoPublicTbl, CmInfoPublicTblPK>, JpaSpecificationExecutor<CmInfoPublicTbl> {

}
