package com.sjs.jsvill.repository;

import com.sjs.jsvill.entity.Group;
import com.sjs.jsvill.entity.Member;
import com.sjs.jsvill.entity._MemberType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    // DB에 들어가는 테스트........
    // Member Entity를 만들어서 데이터를 넣는다.
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testRegister() {
        _MemberType mt = _MemberType.builder()._membertype_rowid(10L).build();
        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member member = Member.builder()
                    .name("name" + i)
                    ._memberType(mt)
                    .build();
            System.out.println(memberRepository.save(member));
        });
    }
}
