package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.GyResearchKeywordView;

@Repository
public interface GyResearchKeywordViewRepository extends JpaRepository<GyResearchKeywordView, String>, JpaSpecificationExecutor<GyResearchKeywordView> {

}
