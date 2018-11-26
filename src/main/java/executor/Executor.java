package executor;

import com.create.verify.receipt.readJsonData;
import reporting.GenerateReport;

public class Executor {

    public static void main(String[] args){

        GenerateReport generateReport=new GenerateReport();

        readJsonData readJsonData=new readJsonData();
        readJsonData.executeReadJSONData();

        generateReport.endReport();
    }
}
