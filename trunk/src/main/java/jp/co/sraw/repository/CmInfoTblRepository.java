package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmInfoTbl;

@Repository
public interface CmInfoTblRepository extends JpaRepository<CmInfoTbl, String>, JpaSpecificationExecutor<CmInfoTbl> {

	@Modifying
	@Query(name = "CmInfoTbl.findAllByPartyOrRoll")
	public List<CmInfoTbl> findAllByPartyOrRoll(@Param("partyCode") String partyCode, @Param("role") String role);

}
