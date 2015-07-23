
package com.lumiplan.hibernate.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;



import com.lumiplan.dao.*;
import com.lumiplan.hibernate.Country;

/**
 * this class serves as the application layer, where user can perform crud operation.
 *
 */

public class Application { 
	public static void main(String args[])
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        
        ApplicationDAO a = context.getBean(ApplicationDAO.class);
         
       a.readCountry();
      
                context.close();    
		
	
	}
}
