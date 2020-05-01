public class RealCell implements Cell, Comparable<RealCell> {
    private String realCell;
    public RealCell(String r) {
        realCell = r;
    }

    public int compareTo(RealCell cell) {
        Double num1 = (Double) this.getDoubleValue();
        Double num2 = (Double) cell.getDoubleValue();
        return num1.compareTo(num2);
    }

    @Override
    public String abbreviatedCellText() {
        String result = realCell;
        if (isInt(realCell)) {
            result += ".0";
        }
        if(result.length() > 10) {
            return result.substring(0,10);
        } else {
            boolean y = false;
            result = result.replaceAll("0+$", "");
            if (result.charAt(result.length()-1) == '.') {
                result += "0";
            }
            int length = 10 - result.length();
            for(int i = 0; i < length; i++) {
                result = result + " ";
            }
            return result;
        }

    }
    public String fullCellText() {
        return realCell;
    }

    public double getDoubleValue() {
        double x = Double.parseDouble(realCell);
        return x;
    }

    private boolean isInt(String str) {
        try {
            int x = Integer.parseInt(str);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }

    }

}
