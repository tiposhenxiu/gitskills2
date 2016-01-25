package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.MsRoleTbl;

@Repository
public interface MsRoleTblRepository extends JpaRepository<MsRoleTbl, String>, JpaSpecificationExecutor<MsRoleTbl> {

}
