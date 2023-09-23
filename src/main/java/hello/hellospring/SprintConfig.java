package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링 빈 등록하기
@Configuration
public class SprintConfig {

    // MemberService 에서 @Service 와 @Autowired 를 사용하지 않는 경우 직접 스프링빈으로 등록한다.
    @Bean
    public MemberService memberService() {
        // return new MemberService(); // 리파지터리도 필요.
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
