package com.sparta.plusweekreviewassignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponseDto {
  @JsonInclude(Include.NON_NULL)
  private String msg;
  @JsonInclude(Include.NON_NULL)
  private Integer status;

}
