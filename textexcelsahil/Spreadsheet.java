

// Update this file with your own code.

import java.util.Arrays;

public class Spreadsheet implements Grid {
	private Cell spreadSheet[][];


	public Spreadsheet() {
		spreadSheet = new Cell[20][12];
		for (Cell[] row: spreadSheet)
			Arrays.fill(row, new EmptyCell());

	}


@Override
    public String processCommand(String command) 	{

		if (command.equals("")) {
			return "";
		}
		if (command.toLowerCase().contains("sorta")) {
			Cell cell = new FormulaCell(command.trim(), spreadSheet);
			return getGridText();
		}
		// cell insepction ... get the value
		if(!command.contains("=") && !command.contains(" ") && !command.toLowerCase().equals("clear")) {

			SpreadsheetLocation spreadsheetLocation = new SpreadsheetLocation(command);
			return spreadSheet[spreadsheetLocation.getRow()][spreadsheetLocation.getCol()].fullCellText();
		}
		// cell assignment ... asign a celll
		// eg: command = "A1 = "Hello""   "B2 = """ --> B2 = "        "
		// at the location A1 we need to assign it the value "hello"
		// Steps:
		// 1. get the value after the "=" ... find the index of "=", then substring
		// 2. need to create a Cell object with that value
		// 3. assign the value to the cell ... need to create a spreadsheetlocation

		if(command.contains("=")) {
			int index = command.indexOf("=") + 2;
			String value = command.substring(index);
			Cell cell = new TextCell(value);

			if(command.contains("%")) {
				cell = new PercentCell(value);
			}
			if (command.contains("(") && command.contains(")") && !value.contains("=")) {
				int index2 = value.indexOf("(");
				if (index2 > 0 ) {
					value = value.substring(index2-1, value.length());
				}
			    cell = new FormulaCell(value, spreadSheet);
            }
			if(isDouble(value)) {
				cell = new RealCell(value);

			}
			SpreadsheetLocation spreadsheetLocation = new SpreadsheetLocation(command.substring(0, index - 3));
			spreadSheet[spreadsheetLocation.getRow()][spreadsheetLocation.getCol()] =  cell;
			return getGridText();
		}
		if(command.toLowerCase().equals("clear")) {

			spreadSheet = new Cell[20][12];
			for (int i = 0; i < spreadSheet.length; i++) {
				Arrays.fill(spreadSheet[i], new EmptyCell());
			}
			return getGridText();

		}
		if(command.contains(" ") && !command.contains("=")) {
			int index = command.indexOf(" ") + 1;
			SpreadsheetLocation spreadsheetLocation = new SpreadsheetLocation(command.substring(index, command.length()));
			spreadSheet[spreadsheetLocation.getRow()][spreadsheetLocation.getCol()] = new EmptyCell();
			return getGridText();
		}
		return "";



	}

	private boolean isDouble(String str) {
		try {
			double x = Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}

	}

	@Override
	public int getRows() {
		return 20;
	}

	@Override
	public int getCols() {
		return 12;

	}

	@Override
	public Cell getCell(Location loc) {
	    //get rows would be i in this case
	    Cell s = spreadSheet[loc.getRow()][loc.getCol()];
	    return s;
	}

	public static String format(String s) {

		return String.format(String.format("%%-%d.%ds", 10, 10), s);
	}

	@Override
	public String getGridText() {
//		char[] charArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K','L'};
//		String grid = "    |";
//		for (int i = 0; i < 12; i++) {
//			grid = grid + charArray[i] + "|";
//			grid = grid + "\n";
//			for (int x = 0; x < 20; x++) {
//				grid = grid + String.format("%-3d|", x + 1);
//				for (int j = 0; j < 12; j++) {
//
//					grid = grid + spreadSheet[x][i].abbreviatedCellText() + "|";
//				}
//				grid = grid + "\n";
//
//			}
//			return grid;
//		}
//		return null;
		{
			String ret = "   |";
			for (int j = 0; j < 12; j++)
				ret = ret + format(Character.toString((char)('A' + j))) + "|";
			ret = ret + "\n";
			for (int i = 0; i < 20; i++)
			{
				ret += String.format("%-3d|", i + 1);
				for (int j = 0; j < 12; j++)
				{
					String str = spreadSheet[i][j].abbreviatedCellText();
					str = str.replaceAll("^\"|\"$", "");
					ret += str;
					int lenght = str.length();
					if (lenght < 10) {
						for (int k = 0; k < 10 - lenght; k++) {
							ret += " ";
						}
					}
					ret += "|";
				}
				ret += "\n";
			}
			return ret;
		}
	}
}
