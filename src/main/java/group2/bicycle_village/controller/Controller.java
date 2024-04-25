package group2.bicycle_village.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface Controller {
	ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException;
}
