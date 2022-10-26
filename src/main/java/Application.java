import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import common.Common;
import common.Constant;
import dto.CellPhone;
import dto.CellPhoneUsageByMonth;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws Exception {
        new Application().run();
    }

    private void run() throws Exception {

        Common common = new Common();

        Map<String, String> mapping = new HashMap<>();
        mapping.put(Constant.EMPLOYEE_ID, Constant.EMPLOYEE_ID);
        mapping.put(Constant.EMPLOYEE_NAME, Constant.EMPLOYEE_NAME);
        mapping.put(Constant.PURCHASE_DATE, Constant.PURCHASE_DATE);
        mapping.put(Constant.MODEL, Constant.MODEL);

        HeaderColumnNameTranslateMappingStrategy<CellPhone> strategy = new HeaderColumnNameTranslateMappingStrategy<CellPhone>();
        strategy.setType(CellPhone.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader("CellPhone.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();

        // call the parse method of CsvToBean
        // pass strategy, csvReader to parse method
        List<CellPhone> list = csvToBean.parse(strategy, csvReader);

        Map<String, String> mappingCellPhoneUsagePerMonth = new HashMap<>();
        mappingCellPhoneUsagePerMonth.put(Constant.EMPLOYEE_ID, Constant.EMPLOYEE_ID);
        mappingCellPhoneUsagePerMonth.put("date", "date");
        mappingCellPhoneUsagePerMonth.put("totalMinutes", "totalMinutes");
        mappingCellPhoneUsagePerMonth.put("totalData", "totalData");

        HeaderColumnNameTranslateMappingStrategy<CellPhoneUsageByMonth> strategyCellPhoneUsagePerMonth = new HeaderColumnNameTranslateMappingStrategy<CellPhoneUsageByMonth>();
        strategyCellPhoneUsagePerMonth.setType(CellPhoneUsageByMonth.class);
        strategyCellPhoneUsagePerMonth.setColumnMapping(mappingCellPhoneUsagePerMonth);

        CSVReader csvReaderCellPhoneUsagePerMonth = null;
        try {
            csvReaderCellPhoneUsagePerMonth = new CSVReader(new FileReader("CellPhoneUsageByMonth.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<CellPhoneUsageByMonth> listCellPhoneUsageByMonth = csvToBean.parse(strategyCellPhoneUsagePerMonth, csvReaderCellPhoneUsagePerMonth);

        common.populateHeaderCsv(listCellPhoneUsageByMonth);

        common.populateMonthDetailsCSV(list, listCellPhoneUsageByMonth);

    }

}
