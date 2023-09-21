package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // http://localhost:8080/hello
    // resources/static에 존재하는 thymeleaf 템플릿 페이지를 보여준다.(data부분에 값 전달)
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","spring!!");
        return "hello";
    }

    // http://localhost:8080/hello-mvc?name=spring!!
    // resources/templates 에 존재하는 hello-template.html 라는 thymeleaf 템플릿 페이지에 값을 입력, 결과를 보여준다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name", required=false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // http://localhost:8080/hello-string?name=spring!!
    // HTML Template 페이지를 거치지 않고 브라우저에 값(No HTML)을 바로 보낸다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // http://localhost:8080/hello-api?name=Spring!!! >> {"name":"Spring!!!"}
    // 객체를 반환하는 경우 값이 API(JSON) 방식으로 반환. Spring은 기본적으로 JSON 으로 반환한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; //작성후 Alt+Insert > Generate > Getter & Setter 자동작성.

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
