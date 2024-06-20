package service;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface BookService {
  ActionForward getBooks(HttpServletRequest request, HttpServletResponse response);
  ActionForward getBookByNo(HttpServletRequest request, HttpServletResponse response);
}
