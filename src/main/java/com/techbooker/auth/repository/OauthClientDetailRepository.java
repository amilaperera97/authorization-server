package com.techbooker.auth.repository;

import com.techbooker.auth.model.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailRepository extends JpaRepository<OauthClientDetail,Long> {
}
