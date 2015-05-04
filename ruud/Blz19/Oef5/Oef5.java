/**
* Deze klasse is een java programma
* @author Ruud Luijten
* @version 1.5
*/

import java.lang. *;
import java.util.Arrays;

public class Oef5{

	public static void main(String args[]) 
	{
        for(int i=3; i < 100; i++)
        {          
        	boolean prime = true;
                       
            for(int j = 2; j < i ; j++)
            {             
            	if(i % j == 0)
            	{
            		prime = false;
            		break;
        		}
            }
            
            if(prime) System.out.print(i + " ");
		}
	}
}