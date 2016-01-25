package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.dto.UsOperationHistoryDto;
import jp.co.sraw.entity.UsOperationHistoryTbl;
import jp.co.sraw.entity.UsOperationHistoryTblPK;

@Repository
public interface UsOperationHistoryTblRepository extends JpaRepository<UsOperationHistoryTbl, UsOperationHistoryTblPK>, JpaSpecificationExecutor<UsOperationHistoryTbl> {

	@Modifying
	@Query(name = "UsOperationHistoryTbl.findAllByUserKey")
	public List<UsOperationHistoryDto> findAllByUserKey(@Param("operationUserKey") String operationUserKey);

}
