package jp.co.sraw.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.ItInternTbl;

@Repository
public interface ItInternTblRepository extends JpaRepository<ItInternTbl, String>, JpaSpecificationExecutor<ItInternTbl> {

	@Modifying
	@Query(name = "ItInternTbl.delete")
	public int delete(@Param("internshipKey") String internshipKey, @Param("updDate") Timestamp updDate);

}
