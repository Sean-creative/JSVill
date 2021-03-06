package com.sjs.jsvill.service.sean.contract;

import com.sjs.jsvill.common.UserDuplicateCheck;
import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.entity.sean.Contract;
import com.sjs.jsvill.entity.sean.Unit;
import com.sjs.jsvill.entity.sub._ContractType;

import java.util.List;

public interface ContractService {
    void register(ContractDTO contractDTO);
//    List<GroupDTO> getList(Long member_rowid);
    void remove(Long contract_rowid);
      ContractDTO getDTO(Long contractRowid);
      Contract get(Long contractRowid);

//    void modify(GroupDTO groupDTO);

    String phoneCheck(List<UserDuplicateCheck> duplicateCheckList);

    default Contract dtoToEntity(ContractDTO contractDTO) {
        //계약을 등록 하기위해 뷰에서 받은 값을 엔티티로 바꿔보자
        // 1. tenant에 insert
        // 2. car를 등록했다면 car에 insert
        // 3. contract에 insert
        // 4. option에 insert

//        Unit unit = Unit.builder().unit_rowid(contractDTO.gettenantRowid()).build();
        Contract contract = Contract.builder()
                .unit(Unit.builder().unit_rowid(contractDTO.getUnitRowid()).build())
                ._contracttype(_ContractType.builder()._contracttype_rowid(contractDTO.getContractTypeRowid()).build())
                .startdate(contractDTO.getStartdate())
                .enddate(contractDTO.getEnddate())
                .deposit(contractDTO.getDeposit())
                .rentfee(contractDTO.getRentFee())
                .managementfees(contractDTO.getManagementFees())
                .paymentday(contractDTO.getPaymentDay())
                .build();
        return contract;
    }
}
