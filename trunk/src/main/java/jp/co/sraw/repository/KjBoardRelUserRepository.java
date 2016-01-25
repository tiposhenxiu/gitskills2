package jp.co.sraw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import jp.co.sraw.entity.KjBoardRelUser;
import jp.co.sraw.entity.KjBoardRelUserPK;

@Repository
public interface KjBoardRelUserRepository extends JpaRepository<KjBoardRelUser, KjBoardRelUserPK>, JpaSpecificationExecutor<KjBoardRelUser> {

}