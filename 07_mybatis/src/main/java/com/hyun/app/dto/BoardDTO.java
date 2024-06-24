package com.hyun.app.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BoardDTO {
  private int boardNo;
  private String title;
  private String contents;
  private Date createDt;
  private Date modifyDt;
}