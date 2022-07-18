package me.jincrates.bookmanager.domain.reserve;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter @ToString
@Table(name = "reserve")
@Entity
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long id;

}
