package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.KjGroupCommonFileTbl;
import jp.co.sraw.entity.KjGroupCommonFileTblPK;

@Repository
public interface KjGroupCommonFileTblRepository extends JpaRepository<KjGroupCommonFileTbl, KjGroupCommonFileTblPK>, JpaSpecificationExecutor<KjGroupCommonFileTbl> {

}
