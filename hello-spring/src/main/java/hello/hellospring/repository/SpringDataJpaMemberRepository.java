package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    //인터페이스 상속 (JpaRepository<T, 식별자 타입>)
    //JpaRepository: 구현체 자동 생성(스프링 빈 자동 등록)

    @Override
    Optional<Member> findByName(String name);
}
