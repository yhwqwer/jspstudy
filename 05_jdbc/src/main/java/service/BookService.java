package service;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public interface BookService {
  ActionForward getBooks(HttpServletRequest request);
  ActionForward getBookByNo(HttpServletRequest request);
  ActionForward registerBook(HttpServletRequest request);
  ActionForward removeBook(HttpServletRequest request);
  ActionForward editBook(HttpServletRequest request);
  ActionForward modifyBook(HttpServletRequest request);
}
