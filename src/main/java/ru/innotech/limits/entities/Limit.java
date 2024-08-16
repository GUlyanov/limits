package ru.innotech.limits.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "limits")
public class Limit {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(name = "userid")
    private Long userId;
    @Column(name = "value")
    private BigDecimal value;

    public Limit(Long userId, BigDecimal value) {
        this.userId = userId;
        this.value = value;
    }


}
