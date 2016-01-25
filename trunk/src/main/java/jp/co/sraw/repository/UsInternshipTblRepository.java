package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsInternshipTbl;
import jp.co.sraw.entity.UsInternshipTblPK;

@Repository
public interface UsInternshipTblRepository extends JpaRepository<UsInternshipTbl, UsInternshipTblPK>, JpaSpecificationExecutor<UsInternshipTbl> {

}
