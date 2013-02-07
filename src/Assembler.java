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
			"SETO 00 00 00",
			"TSTI 01 20 00",
			"BIC 000001",
			"TSTI 00 F0 00",
			"BIC 000006",
			"BUC 000023",
			"SETO 00 FF 80",
			"TSTI 01 20 20",
			"BIC 000007",
			"TSTI 01 20 00",
			"BIC 000009",
			"TSTI 00 F0 70",
			"BIC 00000E",
			"BUC 000028",
			"SETO 00 FF 40",
			"TSTI 01 20 20",
			"BIC 00000F",
			"TSTI 01 20 00",
			"BIC 000011",
			"TSTI 00 F0 00",
			"BIC 000016",
			"BUC 00002D",
			"SETO 00 FF 20",
			"TSTI 01 20 20",
			"BIC 000017",
			"TSTI 01 20 00",
			"BIC 000019",
			"TSTI 00 F0 20",
			"BIC 00001E",
			"BUC 000032",
			"SETO 00 FF 10",
			"TSTI 01 20 20",
			"BIC 00001F",
			"SETO 00 FF 01",
			"BUC 000036",
			"SETO 00 FF 80",
			"TSTI 01 20 20",
			"BIC 000024",
			"TSTI 01 20 00",
			"BIC 000026",
			"SETO 00 FF 40",
			"TSTI 01 20 20",
			"BIC 000029",
			"TSTI 01 20 00",
			"BIC 00002B",
			"SETO 00 FF 20",
			"TSTI 01 20 20",
			"BIC 00002E",
			"TSTI 01 20 00",
			"BIC 000030",
			"SETO 00 FF 10",
			"TSTI 01 20 20",
			"BIC 000033",
			"SETO 00 FF 02",
			"TSTI 01 40 00",
			"BIC 000036",
			"TSTI 01 40 40",
			"BIC 000038",
			"BUC 000000",
			"IUC",
			"IUC",
			"IUC",
			"IUC",
	"IUC" };

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
					if(!instruction[1].equalsIgnoreCase("port") || !instruction[3].matches("[01x]{8}") || !(instruction[2].equals("00") || instruction[2].equals("01")))
						throw iie;
					else
						System.out.println("04" + instruction[2] + convertInstruction(instruction[3]));
				}

				else if(instruction[0].equals("TSTI")) {
					if(instruction[2].equals("00") && instruction[1].equalsIgnoreCase("port") && instruction[3].matches("[01x]{8}"))
						System.out.println("05" + instruction[2] + convertInstruction(instruction[3]));
					else if(instruction[1].equals("01"))
						System.out.println("05" + instruction[1] + instruction[2] + instruction[3]);
					else
						throw iie;
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
