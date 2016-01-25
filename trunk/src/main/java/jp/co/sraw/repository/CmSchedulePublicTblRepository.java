package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.CmSchedulePublicTbl;
import jp.co.sraw.entity.CmSchedulePublicTblPK;

@Repository
public interface CmSchedulePublicTblRepository extends JpaRepository<CmSchedulePublicTbl, CmSchedulePublicTblPK>, JpaSpecificationExecutor<CmSchedulePublicTbl> {

	@Modifying
	@Query(name="CmSchedulePublicTbl.delete")
	public int delete(String refKey);

}
