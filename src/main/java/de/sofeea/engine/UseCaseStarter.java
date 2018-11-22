package de.sofeea.engine;
 
import java.sql.SQLException;
import java.util.Iterator;

import Engine.IUseCaseStart;
import Engine.Multiplier;
  
public class UseCaseStarter {
	public static void main(String args[]) throws InterruptedException{
		try {
			Multiplier lMultiplier = new Multiplier();
			while(true){ 
				Iterator<IUseCaseStart>  lUseCaseStartIterator;
	
				lUseCaseStartIterator = lMultiplier.getAllUseCases();
				
				while(lUseCaseStartIterator.hasNext()){  
					IUseCaseStart iUseCaseStart;
					
					iUseCaseStart = lUseCaseStartIterator.next();
					System.out.println(iUseCaseStart.getClass());
					iUseCaseStart.run( );  
					lUseCaseStartIterator.remove();  
				}
				 
				System.out.println("* Sleep *");
				Thread.sleep(10 * 60 * 1000);
			} 
		} catch (SQLException e) {
			// SQL-Fehler in Multiplier
			e.printStackTrace();
		} 
	} 
}
