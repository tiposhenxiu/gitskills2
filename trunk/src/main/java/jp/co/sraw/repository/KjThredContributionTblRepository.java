package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.KjThredContributionTbl;
import jp.co.sraw.entity.KjThredContributionTblPK;

@Repository
public interface KjThredContributionTblRepository extends JpaRepository<KjThredContributionTbl, KjThredContributionTblPK>, JpaSpecificationExecutor<KjThredContributionTbl> {

}
