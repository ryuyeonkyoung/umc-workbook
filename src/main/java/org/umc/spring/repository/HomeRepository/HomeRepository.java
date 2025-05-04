package org.umc.spring.repository.HomeRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.umc.spring.domain.Member;

public interface HomeRepository extends JpaRepository<Member, Long>, HomeRepositoryCustom {
}