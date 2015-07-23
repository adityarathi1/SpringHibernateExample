package com.lumiplan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.lumiplan.dao.ApplicationDAO;
import com.lumiplan.hibernate.Country;



public class SpringServlet extends HttpServlet{

	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
   	ApplicationDAO countryService = (ApplicationDAO) applicationContext.getBean("load");
	    
	    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            List<Country> country = countryService.readCountry();
            for(int i=0;i<country.size();i++){
            	out.println("<table><tr><td>"+country.get(i).getCountryName()+"</td></tr></table>");
           	}
	    }
	    public void destroy(){
	    	
	    }
	    }