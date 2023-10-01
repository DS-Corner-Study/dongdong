package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JdaMemberRepository implements MemberRepository{

    private final EntityManager em; //패키지 설치 시 엔티티 매니저 자동생성 -> 인젝션만 하면 됨

    public JdaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //jpa가 insert 쿼리 자동 생성 및 실행, id까지 셋팅하는 메서드
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //(타입, 식별자)
        return Optional.ofNullable(member);
    }

    //pk 외의 리스트는 쿼리 작성 필요
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> retult = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return retult.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                //jpa 쿼리 언어(엔티티(객체) 대상으로 질의, sql문으로 변환)
                .getResultList();
    }
}
