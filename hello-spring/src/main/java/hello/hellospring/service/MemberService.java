package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // 객체 생성 후 사용 시 다른 저장소 객체 사용하게 됨
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 생성자, 인자로 저장소 받음 (같은 저장소 객체 공유) (의존성 주입(Dependency Injection)(DI))
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){ // 회원가입
        // 같은 이름 증복 회원 X
        validateBuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateBuplicateMember(Member member) {
/*        Optional<Member> result = memberRepository.findByName(member.getName());
            result.ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });*/
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
