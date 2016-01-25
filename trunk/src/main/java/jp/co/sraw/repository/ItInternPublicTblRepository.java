package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.ItInternPublicTbl;
import jp.co.sraw.entity.ItInternPublicTblPK;

@Repository
public interface ItInternPublicTblRepository extends JpaRepository<ItInternPublicTbl, ItInternPublicTblPK>, JpaSpecificationExecutor<ItInternPublicTbl> {

	@Modifying
	@Query(name = "ItInternPublicTbl.delete")
	public int delete(@Param("internshipKey") String internshipKey);

}
