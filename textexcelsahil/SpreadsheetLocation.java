 

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
    private String column;
    private String row;

    public SpreadsheetLocation(String cellName)
    {
        column = cellName.substring(0,1).toUpperCase();
        row = cellName.substring(1);
    }

    @Override
    public int getRow()
    {

        return Integer.parseInt(row) - 1;
    }

    @Override
    public int getCol()
    {

        char c = column.charAt(0);
        return (int) Character.toLowerCase(c) - 97;
    }

    


}
