package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private DataSource dataSource; //dataSource 빈 생성
//
//    @Autowired //생성자 하나면 생략 가능
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource; // 등록
//    }

//    private EntityManager em; //dataSource 빈 생성
//
//    @Autowired //생성자 하나면 생략 가능
//    public SpringConfig(EntityManager em){
//        this.em = em; // 등록
//    }

    private final MemberRepository memberRepository;

    @Autowired //생성자 하나면 생략 가능
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository());
//    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    } //이중관계 연결

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource); // 데이터베이스 교체
//        return new JdbcTemplateMemberRepository(dataSource); // 데이터베이스 교체
//        return new JdaMemberRepository(em); // 데이터베이스 교체
//    }
}
