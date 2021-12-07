package com.carpco.footballstats.adapter.persistence.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "person")
@Table(indexes = @Index(columnList = "job"))
@OptimisticLocking(type = OptimisticLockType.DIRTY)
@DynamicUpdate
@SelectBeforeUpdate
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PersonEntity {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  private LocalDate bornDate;
  @ManyToOne
  @JoinColumn(name = "country_id")
  private CountryEntity country;
  @Enumerated(EnumType.STRING)
  private Job job;
  private boolean deleted;
  
  public enum Job {
    
    PLAYER, COACH
  }
}
