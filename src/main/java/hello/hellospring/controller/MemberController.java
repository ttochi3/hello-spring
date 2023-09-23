package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService(); // 이렇게 객체를 생성해도 되지만
    private final MemberService memberService; // 스프링 컨테이너에 하나만 등록하고 사용한다.

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    } //이렇게 지정하고 Autowired 하면 스프링 컨테이너가 생성될때 이 생성자가 호출되어 컨테이너에 연결시켜 준다.
    // 생성자를 통한 주입 Dependency Injection 이다.
    // 아니면 Field에다가 직접 Autowired 를 하는 방법도 있다. 필드 주입(Field Injection), 잘 사용하지 않는다.
    // @Autowired private MemberService memberService;
    // 아니면 Setter 주입이라고 해서
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }
    // 이는 public 으로 노출될 수 있다. 바뀐다면 이렇게 하지만 일단 실행되면 바뀌지 않는다.(잘못 바꾸면 문제가 생기므로)
}
//
