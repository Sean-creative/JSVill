package com.sjs.jsvill.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="movein")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoveIn extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movein_rowid;

    @ManyToOne
    @JoinColumn(name = "unit_rowid")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "contract_rowid")
    private Contract contract;

    @Column(nullable = false)
    private String note;
}