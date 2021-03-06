package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.dto.sean.CarDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "car")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"tenant"})
public class Car extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long car_rowid;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tenant_rowid")
    private Tenant tenant;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 64, nullable = false, unique = true)
    private String number;

    //    default ContractDTO entityToDTO(Contract contarct, List<CarDTO> carDTOList, List<TenantDTO> tenantDTOList) {
//        ContractDTO contractDTO = ContractDTO.builder()
//                .contractRowid(contarct.getContract_rowid())
//                .unitRowid(contarct.getUnit().getUnit_rowid())
//                ._contractTypeRowid(contarct.get_contracttype().get_contracttype_rowid())
//                .startDate(contarct.getStartdate())
//                .endDate(contarct.getEnddate())
//                .deposit(contarct.getDeposit())
//                .rentFee(contarct.getRentfee())
//                .managementFees(contarct.getManagementfees())
//                .paymentDay(contarct.getPaymentday())
//
//                .carDTOList(carDTOList)
//                .tenantDTOList(tenantDTOList)
//                .build();
//        return contractDTO;
//    }
//

    public static CarDTO entityToDTO(Car car, String phone) {
        return CarDTO.builder()
                .title(car.getTitle())
                .number(car.getNumber())
                .tenantRowid(car.getTenant().getTenant_rowid())
                .build();
    }

    public static List<CarDTO> entitiesToDTO(List<Car> carList) {
        List<CarDTO> carDTOList = new ArrayList<>();
        if (!carList.isEmpty()) {
            carDTOList = carList.stream().map(car -> CarDTO.builder()
                    .title(car.getTitle())
                    .number(car.getNumber())
                    .build()
            ).collect(Collectors.toList());
        }
        return carDTOList;
    }

}