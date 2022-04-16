package me.jincrates.work.domain.annual;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import me.jincrates.work.domain.BaseTimeEntity;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate // 변경한 필드만 대응
@Entity
public class AnnualUsed extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "used_id")
    private Long id;

    @Column(nullable = false)
    private Long baseYear;

    @Column(nullable = false)
    private String member;  //사원키

    @Column(nullable = false)
    private double used;   //사용일수

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date usedFromDate;  //사용 시작일

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date usedToDate;  //사용 종료일

    @Column(nullable = false)
    private String reason;  //사유

    public void change(String reason, double used, Date usedFromDate, Date usedToDate) {
        this.reason = reason;
        this.used = used;
        this.usedFromDate = usedFromDate;
        this.usedToDate = usedToDate;
    }
}
