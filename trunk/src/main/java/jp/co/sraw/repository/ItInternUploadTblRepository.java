package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.dto.ItInternUploadDto;
import jp.co.sraw.entity.ItInternUploadTbl;
import jp.co.sraw.entity.ItInternUploadTblPK;

@Repository
public interface ItInternUploadTblRepository extends JpaRepository<ItInternUploadTbl, ItInternUploadTblPK>, JpaSpecificationExecutor<ItInternUploadTbl> {

	@Modifying
	@Query(name = "ItInternUploadTbl.delete")
	public int delete(@Param("internshipKey") String internshipKey);

	@Modifying
	@Query(name = "ItInternUploadTbl.findAllFileName")
	public List<ItInternUploadDto> findAllFileName(@Param("internshipKey") String internshipKey);

}
