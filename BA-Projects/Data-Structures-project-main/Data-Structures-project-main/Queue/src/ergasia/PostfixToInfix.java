package ergasia;
import java.io.PrintStream; 
import java.util.NoSuchElementException;
import java.util.Scanner;

class PostfixToInfix { 
		
	static boolean isCharacter(char x) {      //Boolean Methodos elegxou aritmon eisodoy.
	  return (x>='0' && x<='9');
	} 
	static boolean isSymbol(char y) {
	  return (y=='+' || y=='-' || y=='*' || y=='/'); //Boolean Methodos elegxou simvolon prakseon eisodoy.
	}
	
	static String metatropi(String str){ // Methodos (kormos)
	  	StringDoubleEndedQueue<String> list= new StringDoubleEndedQueueImpl<>(); //dimioyrgia ouras 2 akron
	  	String temp1;
	  	String temp2;
	    for (int i = 0; i < str.length(); i++){         // for pou diatrexei tin metathematiki grammatoseira.
			if (isCharacter(str.charAt(i))){            // elegxos stoixeion tis eisodou
				list.addFirst(str.charAt(i) + "");          
			}                                           // kai metatropi apo  metathematiki se enthematiki
			else if(isSymbol(str.charAt(i))){ 
				temp1 = list.getFirst();        
				list.removeFirst();
				temp2 = list.getFirst(); 
				list.removeFirst();
				list.addFirst("(" + temp2 + str.charAt(i)+temp1 + ")"); 
			}
			else {
				System.out.println("Wrong input");
			}
	    } 
		list.printQueue(System.out);                //ektiposi apotelesmatos
		return list.getFirst();
	} 

	public static void takeApostfix(){           //methodos gia tin eisodo tis metathematikis
	  	Scanner s = new Scanner(System.in);
	  	System.out.println("give a Postfix: ");
	  	String str = s.nextLine();
	  	metatropi(str);                          // klisi methodoy elegxoy & metatropis
	}

	public static void main(String[] args){        // methodos main
	  	takeApostfix();                            //klisi methodoy gia tin leitoyrgia toy programmatos 
	}
}
