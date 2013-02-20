import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Disassembler {
	
	public static void main(String[] args) throws FileNotFoundException
	{
		String[] nums = new String[64];
		nums = new String[]{
				"02000036",
				"08000000",
				"08000000",
				"08000000",
				"08000000",
				"08000000",
				"08000000",
				"08000000",
				"0A000000",
				"04000008",
				"06000033",
				"0500F040",
				"0300000E",
				"02000022",
				"0400FF80",
				"06000031",
				"0500F0D0",
				"03000013",
				"02000024",
				"0400FF40",
				"06000031",
				"0500F050",
				"03000018",
				"02000026",
				"0400FF20",
				"06000031",
				"0500F0A0",
				"0300001D",
				"02000028",
				"0400FF10",
				"05012020",
				"0300001E",
				"0400FF01",
				"0200002C",
				"0400FF80",
				"06000031",
				"0400FF40",
				"06000031",
				"0400FF20",
				"06000031",
				"0400FF10",
				"05012020",
				"03000029",
				"0400FF02",
				"05014000",
				"0300002C",
				"05014040",
				"0300002E",
				"02000008",
				"05012020",
				"03000031",
				"05012000",
				"03000033",
				"07000000",
				"0400FF0C",
				"08000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000"};
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
