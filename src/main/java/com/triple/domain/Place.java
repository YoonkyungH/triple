package com.triple.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString(of = {"id", "name", "placeUUID"})
@EntityListeners(AuditingEntityListener.class)
public class Place extends BaseTimeEntity{

    @Id @GeneratedValue
    private Long id;

    private String name;

    private String placeUUID;

    public Place(String name) {
        this.name = name;
    }

}
