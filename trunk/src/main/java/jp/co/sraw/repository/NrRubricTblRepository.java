package jp.co.sraw.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrRubricTbl;

@Repository
public interface NrRubricTblRepository extends JpaRepository<NrRubricTbl, String>, JpaSpecificationExecutor<NrRubricTbl> {
	public List<NrRubricTbl> findAllByOrderByRubricName();
	public NrRubricTbl findByRubricKeyAndUpdDate(String rkey, Timestamp dt);

}
