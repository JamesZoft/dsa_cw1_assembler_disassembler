import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DisassemblerAndCommenter {
		
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
				out += "IUC || Increment unconditionally";
			}
			else if(s.charAt(1) == '1')
			{
				out += "HUC || Hold unconditionally";
			}
			else if(s.charAt(1) == '2')
			{
				out += "BUC|" + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7) + "	||Branch unconditionally to address " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '3')
			{
				out += "BIC|" + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7)+ "	||Increment or branch conditionally to address " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '4')
			{
				out += "SET0" + "|port:" + s.charAt(2) + s.charAt(3) + "|anded with:" + s.charAt(4) + s.charAt(5) + "|or'd with:" + s.charAt(6) + s.charAt(7) + "	||Specify port " + s.charAt(2) + s.charAt(3) + ", AND with " + s.charAt(4) + s.charAt(5) + ", OR with " + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '5')
			{
				out += "TSTI" + "|port:" + s.charAt(2) + s.charAt(3) + "|anded with:" + s.charAt(4) + s.charAt(5) + "|or'd with:" + s.charAt(6) + s.charAt(7) + "	||Test input port " + s.charAt(2) + s.charAt(3) + ", AND with " + s.charAt(4) + s.charAt(5) + ", OR with " + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '6')
			{
				out += "BSR|" + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7) + "	||Branch to subroutine at address " + s.charAt(2) + s.charAt(3) + s.charAt(4) + s.charAt(5) + s.charAt(6) + s.charAt(7);
			}
			else if(s.charAt(1) == '7')
			{
				out += "RSR || Return from subroutine";
			}
			else if(s.charAt(1) == '8')
			{
				out += "RIR || Return from interrupt";
			}
			else if(s.charAt(1) == '9')
			{
				out += "CLI || Clear interrupt enable flag";
			}
			else if(s.charAt(1) == 'A')
			{
				out += "SEI || Set interrupt enable flag";
			}
			System.out.println(out);
		}
		
	}

}
