package com.sjs.jsvill.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notice_rowid;

    @ManyToOne
    @JoinColumn(name = "member_rowid")
    private Member member_rowid;

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String contents;

    @Column(name = "viewcnt")
    private String viewcnt;

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;


}