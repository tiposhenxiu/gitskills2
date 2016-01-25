package jp.co.sraw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jp.co.sraw.dto.InternshipApplicantDto;
import jp.co.sraw.entity.ItInternView;

@Repository
public interface ItInternViewRepository extends JpaRepository<ItInternView, String>, JpaSpecificationExecutor<ItInternView> {

	@Query(name = "ItInternView.findAllInternshipForApplication")
	List<InternshipApplicantDto> findAllInternshipForApplication(@Param("userKey") String userKey);

}
