package jp.co.sraw.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsMessageBoxTbl;

@Repository
public interface UsMessageBoxTblRepository extends JpaRepository<UsMessageBoxTbl, String>, JpaSpecificationExecutor<UsMessageBoxTbl> {

	@Modifying
	@Query(name = "UsMessageBoxTbl.delete")
	public int delete(@Param("messageKey") String messageKey, @Param("updDate") Timestamp updDate);


}
