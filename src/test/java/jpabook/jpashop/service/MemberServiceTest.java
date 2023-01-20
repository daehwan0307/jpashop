package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입성공")
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("hwan");
        //when
        Long savedId = memberService.join(member);
        //then
        Assertions.assertEquals(member,memberRepository.findOne(savedId));

    }

    @Test
    @DisplayName("회원가입실패 - 중복회원")
    public void 중복_회원_예외() throws Exception{
        //given
        Member member = new Member();
        member.setName("hwan");

        Member member1 = new Member();
        member1.setName("hwan");

        //when
        memberService.join(member);
        try {
            memberService.join(member1); //이때 같은 이름" hwan"으로 가입하기때문에 예외 발생해야한다.
        }catch (IllegalStateException e){
            return;
        }
        //then
        Assertions.fail("예외가 발생해야 한다.");
    }

}