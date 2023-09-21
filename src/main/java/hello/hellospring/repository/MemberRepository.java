package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // 조회시 없을 경우 null을 반환하는데 이를 대비 Optional을 감싸서 반환한다.(java8 부터)
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
