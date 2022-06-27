package dcom.spring.redisexample.domain.member.repository;

import dcom.spring.redisexample.domain.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @EntityGraph(attributePaths = "team")
    List<Member> findAll();
}
