package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.NrRubricLense;
import jp.co.sraw.entity.NrRubricLensePK;

@Repository
public interface NrRubricLenseRepository extends JpaRepository<NrRubricLense, NrRubricLensePK>, JpaSpecificationExecutor<NrRubricLense> {

}
