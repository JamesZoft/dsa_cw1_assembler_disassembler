import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Disassembler {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		File hexFile = new File("hex.txt");
		Scanner scan = new Scanner(hexFile);
		
		while(scan.hasNext())
		{
			String s = scan.nextLine();
			String out = "";
			if(s.charAt(1) == '0')
			{
				out += "IUC";
			}
			else if(s.charAt(1) == '1')
			{
				out += "HUC";
			}
			else if(s.charAt(1) == '2')
			{
				out += "BUC " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '3')
			{
				out += "BIC " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '4')
			{
				out += "SETO " + s.charAt(2) + s.charAt(3) + " " + s.charAt(4) + s.charAt(5) + " " + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '5')
			{
				out += "TSTI " + s.charAt(2) + s.charAt(3) + " " + s.charAt(4) + s.charAt(5) + " " + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '6')
			{
				out += "BSR " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '7')
			{
				out += "RSR";
			}
			else if(s.charAt(1) == '8')
			{
				out += "RIR";
			}
			else if(s.charAt(1) == '9')
			{
				out += "SEI";
			}
			else if(s.charAt(1) == 'A')
			{
				out += "CLI";
			}
			System.out.println(out);
		}
		
	}

}
