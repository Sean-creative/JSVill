package com.sjs.jsvill.entity.sean;

import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.entity.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="unit")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "group")
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unit_rowid;

    @ManyToOne( fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "group_rowid", nullable = false)
    private Group group;

    @Column(length = 100, nullable = false)
    private String addr2; // 호수

    @Column(nullable = false)
    private String memo; // 메모 (선택적)

    public void changeAddr2(String addr2) { this.addr2 = addr2; }
    public void changeMemo(String memo) { this.memo = memo; }

    //단순히 호실만 다룰 때
    public static UnitDTO entityToDTO(Unit unit) {
        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .memo(unit.getMemo())
                .build();
        return unitDTO;
    }

    //여기에 재료를 쏟아 부으면 너무 과부하가 걸릴까 걱정 -> 역할을 분리하고자 unit빼고는 DTO를 받는게 낫겠다.
    public static  UnitDTO entityToDTOWithContract(Unit unit, List<ContractDTO> contractDTOList) {
        Group group = unit.getGroup();
        List<String> optionList = new ArrayList<>();
        if(!contractDTOList.isEmpty()){
            optionList = contractDTOList.get(0).getOptionDTO().getOptionList();
        }

        UnitDTO unitDTO = UnitDTO.builder()
                .unitRowid(unit.getUnit_rowid())
                .groupRowid(unit.getGroup().getGroup_rowid())
                .addr2(unit.getAddr2())
                .memo(unit.getMemo())
                .groupTitle(group.getTitle())
                .groupAddr(group.getAddr1())
                .contractDTOList(contractDTOList)
                .optionList(optionList)
                .build();
        return unitDTO;
    }
}