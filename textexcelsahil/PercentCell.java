public class PercentCell extends RealCell {
    private String percentCell;
    public PercentCell(String s) {
        super(s);
        percentCell = s;
    }


    @Override
    public String abbreviatedCellText() {
        // should only return one zero at end
        // get first letter
        int x = percentCell.indexOf(".");
        String result = "";
        if (x > 0) {
            result = percentCell.substring(0,x);
        }

        // add percnt sign "
        result += "%";

        if(result.length() > 10) {
            return result.substring(0,10);
        } else {
            int length = 10 - result.length();
            for(int i = 0; i < length; i++) {
                result = result + " ";
            }
            return result;
        }
        // return result
     
    }

    @Override
    public String fullCellText() {
        String x = percentCell.substring(0,percentCell.length()-1);
        double result = Double.parseDouble(x) / 100.0;
        return "" + result;
    }

    @Override
    public double getDoubleValue() {

        String r = fullCellText();
        if(r.contains("%")) {
            r.substring(0, r.length() - 1);
        }
        double y = Double.parseDouble(r);
        return y;


    }

}
