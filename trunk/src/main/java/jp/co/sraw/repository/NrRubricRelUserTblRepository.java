package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrRubricRelUserTbl;
import jp.co.sraw.entity.NrRubricRelUserTblPK;

@Repository
public interface NrRubricRelUserTblRepository extends JpaRepository<NrRubricRelUserTbl, NrRubricRelUserTblPK>, JpaSpecificationExecutor<NrRubricRelUserTbl> {

}
