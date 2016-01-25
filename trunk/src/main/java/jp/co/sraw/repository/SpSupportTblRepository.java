package jp.co.sraw.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.SpSupportTbl;

@Repository
public interface SpSupportTblRepository
		extends JpaRepository<SpSupportTbl, String>, JpaSpecificationExecutor<SpSupportTbl> {

	@Modifying
	@Query(name = "SpSupportTbl.delete")
	public int delete(@Param("supportKey") String supportKey, @Param("updDate") Timestamp updDate);

}
