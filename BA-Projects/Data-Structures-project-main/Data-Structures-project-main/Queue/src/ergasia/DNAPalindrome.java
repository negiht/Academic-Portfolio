package ergasia;
import java.io.PrintStream;  
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DNAPalindrome {
		
	public static void main(String[]args) {
		StringDoubleEndedQueue<Character> list = new StringDoubleEndedQueueImpl<>();
		String str;		
		
		System.out.println("Enter a sequence of letters of DNA\n"
				+ "to check if it is WATCHON-CRICK complement palindrome.\n"
				+ "The letters must be \"A\" or \"T\" or \"C\" or \"G\",\n"
				+ "all capitals and without any spaces between.\n"
				+ "\"A\" is complement of \"T\" and backward.\n"
				+ "\"C\" is complement of \"G\" and backward.");
		System.out.println("Enter the sequence of DNA. ");
		
		Scanner input = new Scanner(System.in);
		
		str = input.nextLine();
		input.close();		
		System.out.println("The sequence of DNA you entered is:\n" + str);
		
			for(int i=0; i<str.length(); i++) {
																
				if (str.equals(" ")){
					list.addFirst(' ');
					System.out.println("Empty string is Watchon-Crick complement palindrome\n"
										+ "by definition."); break;
					}				
				if(str.length()%2 !=0) {
					System.out.println("The length of the letters must be even number.\n"
							+ "You entered not a Watchon-Crick sequence"); break;
					}
				
				if(str.charAt(i) == 'A') {
					list.addFirst('T');
				}
				else if(str.charAt(i) == 'T') {
					list.addFirst('A');
				}
				else if(str.charAt(i) == 'C') {
					list.addFirst('G');
				}
				else if(str.charAt(i) == 'G') {
					list.addFirst('C');
				}
				else {
					System.out.println("You entered wrong letters.\n"
							+ "The sequence is not a Watchon-Crick.");
					break; }
			}							
			
			if (list.size() == str.length()) {
			for(int i =0; i<str.length(); i++) {
				if(list.removeFirst().equals(str.charAt(i)) ) {
					if(list.isEmpty()){
					System.out.println("it is Watchon-Crick sequence.");}
				}
				else {
				System.out.println("it is not Watchon-Crick sequence.");
				break;
				}
			}
		}
	}
}

