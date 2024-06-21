package com.hyun.app.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter

public class PageUtils {
  
  private int total;    // 전체 게시글 개수 (데이터베이스에서 구하는 정보)
  private int display;  // 한 페이지에 표시할 개수 (요청 파라미터, 기본 20개)
  private int page;     // 페이지 번호 (요청 파라미터, 기본 1 페이지)
  private int begin;    // 각 페이지의 표시할 시작 번호 (계산, setPaging() 메소드)
  private int end;      // 각 페이지의 표시할 종료 번호 (계산, setPaging() 메소드)
  
  public void setPaging(int total, int display, int page) {
    this.total = total;
    this.display = display;
    this.page = page;
    
    begin = (page - 1) * display + 1;
    end   = begin + display - 1;
    
  }
  
  
  
}
