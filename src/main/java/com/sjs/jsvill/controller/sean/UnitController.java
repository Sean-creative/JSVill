package com.sjs.jsvill.controller.sean;

import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.service.sean.unit.UnitService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/unit")
@Log4j2
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;

    @GetMapping("/register")
    public void register(int groupRowid, Model model) {
        log.info("groupRowid : " + groupRowid);
        model.addAttribute("groupRowid", groupRowid);
    }

    @PostMapping("/register")
    public String register(UnitDTO dto, RedirectAttributes redirectAttributes) {
        log.info("dto..." + dto);
        //TODO 로그인이 유지가 되면 컨트롤러에서 넣어줘도 상관없음
        Long gno = unitService.register(dto);
        log.info("result", gno);
        return "redirect:/group/list";
    }

    @GetMapping("/edit")
    public void edit(long unitRowid, Model model) {
        log.info("edit");
        model.addAttribute("result" , unitService.get(unitRowid));
    }

    @PostMapping("/edit")
    public String edit(UnitDTO unitDTO, RedirectAttributes redirectAttributes) {
        unitService.modify(unitDTO);
        return "redirect:/unit/read?unitRowid="+unitDTO.getUnitRowid();
    }

    @GetMapping("communityWrite")
    public void communityWrite() {log.info("communityWrite");}

    @PostMapping("/remove")
    public String remove(long unitRowid, RedirectAttributes redirectAttributes){
        unitService.remove(unitRowid);
        redirectAttributes.addFlashAttribute("msg", unitRowid);
        return "redirect:/group/list";
    }

    @GetMapping("/read")
    public void read(Long unitRowid, Model model){
        log.info("unitRowid: " + unitRowid);
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        Json.stringToJson(unitDTO);
        model.addAttribute("unitDTO", unitDTO);
    }

    @GetMapping("/previousContract")
    public void previousContract() {log.info("previousContract");}
}
