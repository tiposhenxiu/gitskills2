package jp.co.sraw.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmScheduleTbl;

@Repository
public interface CmScheduleTblRepository extends JpaRepository<CmScheduleTbl, String>, JpaSpecificationExecutor<CmScheduleTbl> {

	@Modifying
	@Query(name="CmScheduleTbl.delete")
	public int delete(String refKey, String dataKbn);

	@Modifying
	@Query(name = "CmScheduleTbl.findAllByPartyOrRoll")
	public List<CmScheduleTbl> findAllByPartyOrRoll(@Param("startDate") Date startDate,
													@Param("endDate") Date endDate,
													@Param("partyCode") String partyCode,
													@Param("role") String role);

}
