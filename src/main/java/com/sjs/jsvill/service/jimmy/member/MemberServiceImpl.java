package com.sjs.jsvill.service.jimmy.member;

import com.sjs.jsvill.dto.jimmy.member.MemberDTO;
import com.sjs.jsvill.entity.sean.Member;
import com.sjs.jsvill.repository.sean.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; //반드시 final로 선언

    @Override
    public Long register(MemberDTO dto) {
        //log.info("DTO-------------" );
        //log.info(dto);
        Member member = dtoToEntity(dto);
        Member returnMember = memberRepository.save(member);
        return returnMember.getMember_rowid();
    }



//    @Override
//    public PageResultDTO<MemberDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//        Function<Object[], MemberDTO> fn = (en -> entityToDTO((Member)en[0]));
//
//        Page<Object[]> result = repository.getMemberWithAll(pageRequestDTO.getPageable(Sort.by("member_rowid").descending()));
//        
//        return
//    }
}
