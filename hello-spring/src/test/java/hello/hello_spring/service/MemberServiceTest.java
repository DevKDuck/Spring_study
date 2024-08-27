package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach(){
        memoryMemberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
            Member member = new Member();
            member.setName("hello");
        //when
            Long saveId = memberService.join(member);
        //then
            Member findMember = memberService.findOne(saveId).get();
            Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when

        //assertThrows 를 이용하여 테스트
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try - catch를 이용하여 테스트

        try{
            memberService.join(member2);
            fail();
        }
        catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        */

        //then
    }

    @Test
    void findOne() {
    }
}