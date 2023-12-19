package com.sparta.plusweekreviewassignment.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeStamp {

  @CreatedDate
  @Column(updatable = false)  // update가 되지 않게 막아준다
  @Temporal(TemporalType.TIMESTAMP) // date나 calendar 의 날짜데이터를 맵핑 할 때 사용함
  private LocalDateTime createAt;

  @LastModifiedDate // 변경이 생길때 마다 변경시간을 적용
  @Column
  @Temporal(TemporalType.TIMESTAMP) // date나 calendar 의 날짜데이터를 맵핑 할 때 사용함
  private LocalDateTime modifiedAt;


}
