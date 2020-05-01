
import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel implements Cell
{
    private static String inputLine;

    public static void main(String[] args)
    {

        Grid sheet = new Spreadsheet();

        System.out.println(sheet.getGridText());

        Scanner scanner = new Scanner(System.in);
        inputLine = scanner.nextLine();
        while (!inputLine.equalsIgnoreCase("quit"))
        {
            String outputLine = sheet.processCommand(inputLine);
            System.out.println(outputLine);
            inputLine = scanner.nextLine();
        }
    }

    @Override
    public String abbreviatedCellText() {
        return inputLine.substring(0,10);
    }

    @Override
    public String fullCellText() {
        return inputLine;
    }
}
