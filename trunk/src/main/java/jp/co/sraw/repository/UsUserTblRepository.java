package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.UsUserTbl;

@Repository
public interface UsUserTblRepository extends JpaRepository<UsUserTbl, String>, JpaSpecificationExecutor<UsUserTbl> {


}
