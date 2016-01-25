package jp.co.sraw.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.EvEventTbl;

@Repository
public interface EvEventTblRepository extends JpaRepository<EvEventTbl, String>, JpaSpecificationExecutor<EvEventTbl> {

	@Query("SELECT u FROM EvEventTbl u WHERE u.eventStartDate>= :currentDay and u.eventSendDate<= :currentDay and  u.publicFlag=:publicFlag and u.partyCode=:partyCode ORDER BY u.eventStartDate DESC")
	public List<EvEventTbl> findAdmin_Present(@Param("publicFlag") String publicFlag,
			@Param("partyCode") String partyCode, @Param("currentDay") Date currentDay);

	@Query("SELECT u FROM EvEventTbl u WHERE u.eventStartDate <:currentDay  and u.publicFlag=:publicFlag and u.partyCode=:partyCode ORDER BY u.eventStartDate DESC")
	public List<EvEventTbl> findAdmin_Past(@Param("publicFlag") String publicFlag, @Param("partyCode") String partyCode,
			@Param("currentDay") Date currentDay);

	@Modifying
	@Query(name = "EvEventTbl.delete")
	public int delete(@Param("eventKey") String eventKey, @Param("updDate") Timestamp updDate);

}
