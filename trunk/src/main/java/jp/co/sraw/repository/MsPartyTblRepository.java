package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.MsPartyTbl;

@Repository
public interface MsPartyTblRepository extends JpaRepository<MsPartyTbl, String>, JpaSpecificationExecutor<MsPartyTbl> {

}
