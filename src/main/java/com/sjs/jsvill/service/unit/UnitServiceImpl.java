package com.sjs.jsvill.service.unit;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Option;
import com.sjs.jsvill.entity.Unit;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.OptionRepository;
import com.sjs.jsvill.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor //의존성 자동 주입 -> repository가 자동 주입
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository; //반드시 final로 선언
    private final ContractRepository contractRepository;
    private final OptionRepository optionRepository;

    @Override
    public Long register(UnitDTO dto) {
        log.info("DTO-------------" );
        log.info(dto);
        Unit unit = dtoToEntity(dto);
        unitRepository.save(unit);
        return unit.getUnit_rowid();
    }

    @Override
    public UnitDTO get(Long unit_rowid) {
        List<Contract> contractList =  contractRepository.findByUnit(unit_rowid);
        //TODO 지금 계약중인 계약 하나 가져와서 -> contract_rowid
        long contract_rowid = contractList.stream().filter(c->c.getIsprogressing()).findFirst().get().getContract_rowid();
        log.info("contract_rowid : " + contract_rowid);
        List<Option> optionList = optionRepository.findByContract(contract_rowid);
        for (Option option : optionList) {
            System.out.println("option : " + option);
        }


        return new UnitDTO();
    }


//    @Override
//    public PageResultDTO<GroupDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//
//        Function<Object[], GroupDTO> fn = (en -> entityToDTO((Group)en[0]));
//
//        Page<Object[]> result = repository.getGroupWithAll(pageRequestDTO.getPageable(Sort.by("group_rowid").descending()));
//        
//        return
//    }
}