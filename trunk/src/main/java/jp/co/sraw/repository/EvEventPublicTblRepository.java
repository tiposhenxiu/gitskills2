package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.EvEventPublicTbl;
import jp.co.sraw.entity.EvEventPublicTblPK;

@Repository
public interface EvEventPublicTblRepository extends JpaRepository<EvEventPublicTbl, EvEventPublicTblPK>, JpaSpecificationExecutor<EvEventPublicTbl> {

//	@Modifying
//	@Query(name = "EvEventPublicTbl.delete")
//	public int delete(@Param("eventKey") String eventKey, @Param("updDate") Timestamp updDate);

}
