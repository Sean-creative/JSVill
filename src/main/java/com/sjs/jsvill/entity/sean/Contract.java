package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.dto.sean.CarDTO;
import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.dto.sean.OptionDTO;
import com.sjs.jsvill.dto.sean.TenantDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import com.sjs.jsvill.entity.sub._ContractType;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contract")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"unit", "_contracttype"})
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contract_rowid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "_contracttype_rowid")
    private _ContractType _contracttype;

    @Column(length = 100, nullable = false)
    private String startdate;

    @Column(length = 100, nullable = false)
    private String enddate;

    @Column(nullable = false)
    private Long deposit;

    @Column(nullable = false)
    private Long rentfee;

    @Column(nullable = false)
    private Long managementfees;

    @Column(length = 100, nullable = false)
    private Long paymentday;


    public void changeDeposit(Long deposit) {
        this.deposit = deposit;
    }

    public void changeRentfee(Long rentfee) {
        this.rentfee = rentfee;
    }

    public void changeManagemnetfees(Long managementfees) {
        this.managementfees = managementfees;
    }

    public void changePaymentday(Long paymentday) {
        this.paymentday = paymentday;
    }

    public Long dDayOperator(String s_date) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow = now.format(formatter);

        Date format1;
        Date format2;
        try {
            format1 = new SimpleDateFormat("yyyy-MM-dd").parse(s_date);
            format2 = new SimpleDateFormat("yyyy-MM-dd").parse(formatedNow);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        long diffSec = (format1.getTime() - format2.getTime() ) / 1000;
        long diffDays = diffSec / (24 * 60 * 60); //????????? ??????
        return diffDays;
    }


    //????????? ????????? ?????? ??????????????? ???????????????
    public static ContractDTO entityToDTO(Contract contarct, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList, OptionDTO optionDTO) {
        ContractDTO contractDTO = ContractDTO.builder()
                .contractRowid(contarct.getContract_rowid())
                .unitRowid(contarct.getUnit().getUnit_rowid())
                .ContractTypeRowid(contarct.get_contracttype().get_contracttype_rowid())
                .startdate(contarct.getStartdate())
                .enddate(contarct.getEnddate())
                .deposit(contarct.getDeposit())
                .rentFee(contarct.getRentfee())
                .managementFees(contarct.getManagementfees())
                .paymentDay(contarct.getPaymentday())
                .carDTOList(carDTOList)
                .tenantDTOList(tenantDTOList)
                .optionDTO(optionDTO)
                .dDay(contarct.dDayOperator(contarct.enddate))
                .build();
        return contractDTO;
    }
//
//    //contract ???????????? ???
//    default List<ContractDTO> entitiesToDTO(List<Contarct> contarctList) {
//        List<ContractDTO> contractDTOList = new ArrayList<>();
//        if(!contarctList.isEmpty()) {
//            contractDTOList = contarctList.stream().map(contract -> ContractDTO.builder()
//                    .tenantRowid(contract.getUnit().getUnit_rowid())
//                    ._contracttypeRowid(contract.getContractType().get_contracttype_rowid())
//                    .title(contract.getTitle())
//                    .startdate(contract.getStartdate())
//                    .enddate(contract.getEnddate())
//                    .isprogressing(contract.getIsprogressing())
//                    .deposit(contract.getDeposit())
//                    .rentfee(contract.getRentfee())
//                    .managementfees(contract.getManagementfees())
//                    .paymentday(contract.getPaymentday())
//                    .build()
//            ).collect(Collectors.toList());
//        }
//
//        return contractDTOList;
//    }
}