package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.PageRequestDTO;
import com.sjs.jsvill.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
@Log4j2
@RequiredArgsConstructor
public class TestController2 {

    private final GuestbookService service; //final로 선언

    @GetMapping("/")
    public String index() {
        log.info("redirect:/guestbook/list..............");
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list.............." + pageRequestDTO);
        log.info("service.getList(pageRequestDTO).............." + service.getList(pageRequestDTO));
        model.addAttribute("result", service.getList(pageRequestDTO));
    }
//
//    @GetMapping("/register")
//    public void register(){
//        log.info("register get...");
//    }
//
//    @PostMapping("/register")
//    public String registerPost(GuestbookDTO dto, RedirectAttributes redirectAttributes) {
//        log.info("dto..." + dto);
//
//        //새로 추가된 엔티티의 번호
//        Long gno = service.register(dto);
//        redirectAttributes.addFlashAttribute("msg", gno);
//        return "redirect:/guestbook/list";
//    }
//
//    @GetMapping({"/read", "/modify"})
//    public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
//        log.info("gno: " + gno);
//        GuestbookDTO dto = service.read(gno);
//        model.addAttribute("dto", dto);
//    }
//
//    @PostMapping("/remove")
//    public String remove(long gno, RedirectAttributes redirectAttributes) {
//        log.info("gno : " + gno);
//        service.remove(gno);
//        redirectAttributes.addFlashAttribute("msg", gno);
//        return "redirect:/guestbook/list";
//    }
//
//    @PostMapping("/modify")
//    public String modify(GuestbookDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
//        log.info("post modify.........................");
//        log.info("dto : " + dto);
//
//        service.modify(dto);
//
//        redirectAttributes.addAttribute("page", requestDTO.getPage());
//        redirectAttributes.addAttribute("type", requestDTO.getType());
//        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
//        redirectAttributes.addAttribute("gno", dto.getGno());
//
//        return "redirect:/guestbook/read";
//    }
}