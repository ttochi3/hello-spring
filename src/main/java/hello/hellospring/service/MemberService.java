package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService { // MemberService 를 포커스하고 Ctrl + Shift + T 하면 테스트를 바로 생성시킬 수 있다.

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); // 이렇게 하면 독립적인 클래스로 각각 생성된다.

    private final MemberRepository memberRepository;

    // 외부에서 리파지터리를 주입하도록 한다.Dependency Injection

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // // 같은 이름이 있는 중복 회원을 찾아서 있으면 안된다.
        // // 1: memberRepository.findByName(member.getName()); // 여기까지 작성하고 Ctrl + Alt + V 하면
        // // 2: Optional<Member> byName = memberRepository.findByName(member.getName()); // 이렇게 작성된다.
        // Optional<Member> result = memberRepository.findByName(member.getName()); // 결과값 변수만 변경한다.
        // // Optional 을 하지 않으면 조건검색시 null이면 isNull 로 판단하지만 Optional로 감싸면 사용할 수 있는 메소드가 많다.
        // result.ifPresent(m -> {
        //     throw new IllegalStateException("이미 존재하는 회원입니다.");
        // });
        // 아니면,
        // memberRepository.findByName(member.getName())
        //                 .ifPresent(m -> {
        //                     throw new IllegalStateException("이미 존재하는 회원입니다.");
        //                 });
        // 로 해도 된다, 하지만 이런 프로세스는 독립적인 메소드로 먼들어서 호출한다.
        // 바로 위 memberRepository.~ 부터 ~ });까지 선택하고 Ctrl + Shift + Alt + T 하고난 다음 Extract Method 를 선택, 메소드명만 설정하면 새로운 메소드로 독립, 추가된다.
        // 그런 다음에 메소드를 호출한다.
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
