public class DisassemblerAndCommenter {
		
	public static void main(String[] args)
	{
		String[] nums = new String[64];
		nums = new String[]{
				"04000000",
				"05012000",
				"03000001",
				"0500F000",
				"03000006",
				"02000023",
				"0400FF80",
				"05012020",
				"03000007",
				"05012000",
				"03000009",
				"0500F070",
				"0300000E",
				"02000028",
				"0400FF40",
				"05012020",
				"0300000F",
				"05012000",
				"03000011",
				"0500F000",
				"03000016",
				"0200002D",
				"0400FF20",
				"05012020",
				"03000017",
				"05012000",
				"03000019",
				"0500F020",
				"0300001E",
				"02000032",
				"0400FF10",
				"05012020",
				"0300001F",
				"0400FF01",
				"02000036",
				"0400FF80",
				"05012020",
				"03000024",
				"05012000",
				"03000026",
				"0400FF40",
				"05012020",
				"03000029",
				"05012000",
				"0300002B",
				"0400FF20",
				"05012020",
				"0300002E",
				"05012000",
				"03000030",
				"0400FF10",
				"05012020",
				"03000033",
				"0400FF02",
				"05014000",
				"03000036",
				"05014040",
				"03000038",
				"02000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000",
				"00000000"};
		for(String s : nums)
		{
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
			System.out.println(out);
		}
		
	}

}
