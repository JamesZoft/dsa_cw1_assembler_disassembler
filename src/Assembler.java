import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;


public class Assembler {

	String[] assembly = new String[]{
			"SEI",
			"SETO 00 00 08",
			"BSR 000033",
			"TSTI 00 F0 40",
			"BIC 00000E",
			"BUC 000022",
			"SETO 00 FF 80",
			"BSR 000031",
			"TSTI 00 F0 D0",
			"BIC 000013",
			"BUC 000024",
			"SETO 00 FF 40",
			"BSR 000031",
			"TSTI 00 F0 50",
			"BIC 000018",
			"BUC 000026",
			"SETO 00 FF 20",
			"BSR 000031",
			"TSTI 00 F0 A0",
			"BIC 00001D",
			"BUC 000028",
			"SETO 00 FF 10",
			"TSTI 01 20 20",
			"BIC 00001E",
			"SETO 00 FF 01",
			"BUC 00002C",
			"SETO 00 FF 80",
			"BSR 000031",
			"SETO 00 FF 40",
			"BSR 000031",
			"SETO 00 FF 20",
			"BSR 000031",
			"SETO 00 FF 10",
			"TSTI 01 20 20",
			"BIC 000029",
			"SETO 00 FF 02",
			"TSTI 01 40 00",
			"BIC 00002C",
			"TSTI 01 40 40",
			"BIC 00002E",
			"BUC 000008",
			"TSTI 01 20 20",
			"BIC 000031",
			"TSTI 01 20 00",
			"BIC 000033",
			"RSR",
			"SETO 00 FF 0C",
			"RIR",
			"IUC",
			"IUC",
			"IUC",
			"IUC",
			"IUC",
			"IUC",
			"IUC",
			"IUC"
 };

	private HashMap<String, AndXorMask> SETOValues;

	private static int programCounterLength = 6;
	
	private HashMap<String, Integer> labels;

	private String convertInstruction(String instruction)
	{
		char[] binaryAndMask = new char[8];
		char[] binaryXorMask = new char[8];
		for(int i = 0; i < instruction.length(); i++)
		{

			if(instruction.charAt(i) == '0')
			{
				binaryAndMask[i] = '0';
				binaryXorMask[i] = '0';
			}
			else if(instruction.charAt(i) == '1')
			{
				binaryAndMask[i] = '1';
				binaryXorMask[i] = '1';
			}
			else if(instruction.charAt(i) == 'x')
			{
				binaryAndMask[i] = '1';
				binaryXorMask[i] = '0';
			}

		}
		String andMask = new String(binaryAndMask);
		String xorMask = new String(binaryXorMask);
		System.out.println("AndMask: " + andMask);
		String hexAnd = Integer.toHexString(Integer.parseInt(andMask, 2));
		if(hexAnd.length() == 1)
			hexAnd = "0" + hexAnd;
		String hexXor = Integer.toHexString(Integer.parseInt(xorMask, 2));
		if(hexXor.length() == 1)
			hexXor = "0" + hexXor;

		return (hexAnd + hexXor);
	}

	public void assemble(File f) throws FileNotFoundException
	{
		Scanner s = new Scanner(f);

		int lineNumber = 0;
		
		/*//first pass for labels
		labels = new HashMap<String, Integer>();
		while(s.hasNext())
		{
			String next = s.nextLine();
			lineNumber++;
			String[] instruction = next.split(" ");
			//Label can be any 8 letters barring x
			String label = instruction[instruction.length - 1];
			if(label.matches("([a-w][A-W][y-z][Y-Z]){8}")) 
			{
				labels.put(label, lineNumber);
				System.out.println("label: " + label + " | linenumber: " + lineNumber);
			}
		}
		s = new Scanner(f);
		//second pass
		lineNumber = 0;*/
		while(s.hasNext())
		{
			String next = s.nextLine();
			lineNumber++;
			
			InvalidInputException iie = new InvalidInputException("Invalid assembly on line " + lineNumber);
			String[] instruction = next.split(" ");
			if(instruction[0].contains("//") || instruction[0].contains("#")) //If line is a comment
			{
				continue;
			}
			try{
				if(instruction[0].equals("IUC")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("00000000");
				}

				else if(instruction[0].equals("HUC")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("01000000");
				}

				else if(instruction[0].equals("BUC")) {
					if(instruction[1].length() != programCounterLength)
						throw iie;
					else
						System.out.println("02" + instruction[1]);

				}

				else if(instruction[0].equals("BIC")) {
					if(instruction[1].length() != programCounterLength)
						throw iie;
					else
						System.out.println("03" + instruction[1]);
				}

				else if(instruction[0].equals("SETO")) {
					//System.out.println("asdasasdas");
					//if(instruction[1].equalsIgnoreCase("port") || instruction[3].matches("[01x]{8}") || (instruction[1].equals("00") == true || instruction[1].equals("01") == true) )
					//	System.out.println("AAA");

					if(instruction[1].equalsIgnoreCase("port") && instruction[3].matches("[01x]{8}") && (instruction[1].equals("00") == true || instruction[1].equals("01") == true) )
						System.out.println("04" + instruction[2] + convertInstruction(instruction[3]));
					else if( (instruction[1].equals("00") == true || instruction[1].equals("01") == true) && instruction[2].length() == 2 && instruction[3].length() == 2)
						System.out.println("04" + instruction[1] + instruction[2] + instruction[3]);
					else
						throw iie;
				}

				else if(instruction[0].equals("TSTI")) {
					if(instruction[2].equals("00") && instruction[1].equalsIgnoreCase("port") && instruction[3].matches("[01x]{8}"))
						System.out.println("05" + instruction[2] + convertInstruction(instruction[3]));
					else if( (instruction[1].equals("00") == true || instruction[1].equals("01") == true) && instruction[2].length() == 2 && instruction[3].length() == 2)
						System.out.println("05" + instruction[1] + instruction[2] + instruction[3]);
					else if(instruction[1].equals("01"))
						System.out.println("05" + instruction[1] + instruction[2] + instruction[3]);
					else
						throw iie;
				}
				
				else if(instruction[0].equals("BSR"))
				{
					if(instruction[1].length() != programCounterLength)
						throw iie;
					else
						System.out.println("06" + instruction[1]);
				}
				
				else if(instruction[0].equals("RSR")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("07000000");
				}
				
				else if(instruction[0].equals("RIR")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("08000000");
				}
				else if(instruction[0].equals("SEI")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("09000000");
				}
				else if(instruction[0].equals("CLI")) {
					if(instruction.length != 1)
						throw iie;
					else
						System.out.println("0A000000");
				}
				
				
			}
			catch (InvalidInputException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		Assembler a = new Assembler();
		try {
			a.assemble(new File("assembly.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
