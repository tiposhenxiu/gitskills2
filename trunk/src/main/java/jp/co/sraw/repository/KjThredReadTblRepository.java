package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.KjThredReadTbl;
import jp.co.sraw.entity.KjThredReadTblPK;

@Repository
public interface KjThredReadTblRepository extends JpaRepository<KjThredReadTbl, KjThredReadTblPK>, JpaSpecificationExecutor<KjThredReadTbl> {

}
