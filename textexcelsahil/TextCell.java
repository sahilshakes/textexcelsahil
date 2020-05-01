public class TextCell implements Cell, Comparable<TextCell>  {
    private String aCT;
    public TextCell(String s) {
        aCT = s;
    }

    public int compareTo(TextCell cell) {
        return this.aCT.compareTo(cell.fullCellText());
    }

    @Override
    public String abbreviatedCellText() {
        if (aCT.equals("\"\"")) {
            return "          ";
        }
        if(aCT.length() > 10) {
            return aCT.substring(1,11);
        }
        if (aCT.contains("\"")) {
            String result = aCT.replaceAll("^\"|\"$", "");

            int length = 10 - result.length();
            for (int i = 0; i < length; i++) {
                result += " ";
            }
            return result;
        }


        return aCT;


    }

    @Override
    public String fullCellText() {
        return aCT;

    }
} 


