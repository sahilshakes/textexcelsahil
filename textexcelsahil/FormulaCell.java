public class FormulaCell extends RealCell {
    String formulacell;
    private Cell spreadSheet[][];
    public FormulaCell(String x, Cell[][] y) {
        super(x);
        spreadSheet = y;
        checkSort(x);
    }

    private void checkSort(String str) {
        //System.out.println("str " +str);
        if (str.toLowerCase().contains("sorta")) {
            // need to sort
            String[] arr = str.split(" ");
            String range = arr[1];
            int index = range.indexOf("-");
            String x = ""; // start
            String y = ""; // end
            if (index > 0) {
                x = range.substring(0, index);
                y = range.substring(index+1, range.length());
            }
            //System.out.println("x and y "+ x + " " + y);
            SpreadsheetLocation rangeStart = new SpreadsheetLocation(x);
            SpreadsheetLocation rangeEnd = new SpreadsheetLocation(y);
            double num1 = rangeEnd.getRow() - rangeStart.getRow() + 1;
            double num2 = rangeEnd.getCol() - rangeStart.getCol() + 1;
            double num = num1*num2;
            Cell cell = spreadSheet[rangeStart.getRow()][rangeStart.getCol()];
            if (isDouble(cell.fullCellText())) {
                RealCell[] result = new RealCell[(int) num];
                int num3 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        RealCell cell1 = (RealCell) spreadSheet[i][j];
                        result[num3] = (cell1);
                        num3++;
                    }
                }

                // sorting here
                int length = result.length;
                for (int i = 0; i < length - 1; i++) {
                    for (int j = i + 1; j < length; j++) {
                        if (result[i].compareTo(result[j]) > 0) {
                            RealCell temp = result[i];
                            result[i] = result[j];
                            result[j] = temp;
                        }
                    }
                }

//                System.out.println("");
//                for (int i = 0; i < length; i++) {
//                    System.out.print(result[i].fullCellText());
//
//                }
//                System.out.println("");

                int num4 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        Cell cell1 = new RealCell(result[num4].fullCellText());
                        spreadSheet[i][j] = cell1;
                        num4++;
                    }
                }
            } else if (cell.fullCellText().contains("%")){
                PercentCell[] result = new PercentCell[(int) num];
                int num3 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        PercentCell cell1 = (PercentCell) spreadSheet[i][j];
                        result[num3] = (cell1);
                        num3++;
                    }
                }

                // sorting here
                int length = result.length;
                for (int i = 0; i < length - 1; i++) {
                    for (int j = i + 1; j < length; j++) {
                        if (result[i].compareTo(result[j]) > 0) {
                            PercentCell temp = result[i];
                            result[i] = result[j];
                            result[j] = temp;
                        }
                    }
                }

                System.out.println("");
                for (int i = 0; i < length; i++) {
                    System.out.print(result[i].fullCellText());

                }
                System.out.println("");

                int num4 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        Cell cell1 = new PercentCell(result[num4].fullCellText());
                        spreadSheet[i][j] = cell1;
                        num4++;
                    }
                }
            } else {

                TextCell[] result = new TextCell[(int) num];
                int num3 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        TextCell cell1 = (TextCell) spreadSheet[i][j];
                        result[num3] = (cell1);
                        num3++;
                    }
                }

                // sorting here
                int length = result.length;
                for (int i = 0; i < length - 1; i++) {
                    for (int j = i + 1; j < length; j++) {
                        if (result[i].compareTo(result[j]) > 0) {
                            TextCell temp = result[i];
                            result[i] = result[j];
                            result[j] = temp;
                        }
                    }
                }

                System.out.println("");
                for (int i = 0; i < length; i++) {
                    System.out.print(result[i].fullCellText());

                }
                System.out.println("");

                int num4 = 0;
                for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
                    for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                        Cell cell2 = new TextCell(result[num4].fullCellText());
                        spreadSheet[i][j] = cell2;
                        num4++;
                    }
                }
            }


        }
    }

    @Override
    public String abbreviatedCellText() {
        double x = getDoubleValue();
        String result = "" + x;
        if(result.length() > 10) {
            return result.substring(0,10);
        } else {
            int length = 10 - result.length();
            for (int i = 0; i < length; i++) {
                result = result + " ";
            }
            return result;
        }
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

    private double getValue(String str) {
        SpreadsheetLocation spreadsheetLocation = new SpreadsheetLocation(str);
        RealCell cell = (RealCell) spreadSheet[spreadsheetLocation.getRow()][spreadsheetLocation.getCol()];
        return cell.getDoubleValue();
    }

    private double getAvg(String range) {
        double sum = getSum(range);
        int index = range.indexOf("-");
        String x = ""; // start
        String y = ""; // end
        if (index > 0) {
            x = range.substring(0, index);
            y = range.substring(index+1, range.length());
        }
        SpreadsheetLocation rangeStart = new SpreadsheetLocation(x);
        SpreadsheetLocation rangeEnd = new SpreadsheetLocation(y);
        double num1 = rangeEnd.getRow() - rangeStart.getRow() + 1;
        double num2 = rangeEnd.getCol() - rangeStart.getCol() + 1;
        double num = num1*num2;
        System.out.println("num1 and num2 " + num1 + " " + num2);
        return sum/num;
    }

    private double getSum(String range) {
        int index = range.indexOf("-");
        String x = ""; // start
        String y = ""; // end
        if (index > 0) {
            x = range.substring(0, index);
            y = range.substring(index+1, range.length());
        }
        SpreadsheetLocation rangeStart = new SpreadsheetLocation(x);
        SpreadsheetLocation rangeEnd = new SpreadsheetLocation(y);
        double result = 0;
        for (int i = rangeStart.getRow(); i <= rangeEnd.getRow(); i++) {
            for (int j = rangeStart.getCol(); j <= rangeEnd.getCol(); j++) {
                RealCell cell = (RealCell) spreadSheet[i][j];
                result += cell.getDoubleValue();
            }
        }

        return result;
    }

    @Override
    public double getDoubleValue() {
        String fullText = fullCellText();
        fullText = fullText.substring(1, fullText.length()-1).trim();
        if (fullText.charAt(0) == '(') {
            fullText = fullText.substring(1, fullText.length()-1).trim();
        }
        //System.out.println("fulltext:" + fullText);
        // 2 + 2 + 2 ==> 6 .. 4
        // 2 + A3 + 2 == > 2+A3+2  ===>  processCommand("A3") ==> gets value for you
        String[] arr = fullText.split(" ");
//
//        System.out.println("");
//        for (String str : arr) {
//            System.out.print(str + ",");
//        }
//        System.out.println("");
        if (arr.length == 1) {
            try {
               return Double.parseDouble(arr[0]);
            } catch (NumberFormatException e) {
                return getValue(arr[0]);
            }
        }
        if (arr.length == 2) {
            if (arr[0].toLowerCase().contains("sum")) {
                return getSum(arr[1]);
            }
            if (arr[0].toLowerCase().contains("avg")) {
                return getAvg(arr[1]);
            }
         
        }
        double result = 0;
        boolean isFirst = true;
        for (int i = 0; i < arr.length-2; i+=2) {

            String str = arr[i+1];
            double num1 = result;
            if (isFirst) {
                if (isDouble(arr[i])) {
                    num1 = Double.parseDouble(arr[i]);
                } else {
                    num1 = getValue(arr[i]);
                }
                isFirst = false;
            }
            double num2 = 0;
            if (isDouble(arr[i+2])) {
                num2 = Double.parseDouble(arr[i+2]);
            } else {
                num2 = getValue(arr[i+2]);
            }


            //System.out.println("num1 " + num1 + " num2 " + num2);
            if(str.equals("+")) {
                result = num1 + num2;
            }
            else if(str.equals("-")){
                result = num1 - num2;
            }
            else if(str.equals("*")){
                result = num1 * num2;
            }
            else if(str.equals("/")){
                result = num1/num2;
            }
        }

        return result;
    }

   



}
