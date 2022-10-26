package common;

import com.opencsv.CSVWriter;
import dto.CellPhone;
import dto.CellPhoneUsageByMonth;
import java.io.File;
import java.io.FileWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Common {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_PATTERN);

    public void populateMonthDetailsCSV(List<CellPhone> list, List<CellPhoneUsageByMonth> listCellPhoneUsageByMonth) {

        try {

            //Read the detail csv file
            File fileDetails = new File("Details.csv");
            if (fileDetails.createNewFile()) {
                System.out.println("File created: " + fileDetails.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter outputFileDetails = null;
            outputFileDetails = new FileWriter(fileDetails);

            CSVWriter writerOutputFileDetails = populateDetailCSVHeader(outputFileDetails);

            populateDetailCSVData(list, listCellPhoneUsageByMonth, writerOutputFileDetails);

            writerOutputFileDetails.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void populateDetailCSVData(List<CellPhone> list, List<CellPhoneUsageByMonth> listCellPhoneUsageByMonth, CSVWriter writerOutputFileDetails) {
        for (CellPhone cellPhone : list) {

            float minutesUsageJan = 0, minutesUsageFeb = 0, minutesUsageMar = 0, minutesUsageApr = 0, minutesUsageMay = 0, minutesUsageJun = 0, minutesUsageJuly = 0, minutesUsageAug = 0, minutesUsageSep = 0, minutesUsageOct = 0, minutesUsageNov = 0, minutesUsageDec = 0;

            float dataUsageJan = 0, dataUsageFeb = 0, dataUsageMar = 0, dataUsageApr = 0, dataUsageMay = 0, dataUsageJun = 0,  dataUsageJuly = 0, dataUsageAug = 0, dataUsageSep = 0, dataUsageNov = 0, dataUsageDec = 0, dataUsageOct = 0;

            for (CellPhoneUsageByMonth cellPhoneUsageByMonth : listCellPhoneUsageByMonth) {

                if (cellPhone.getEmployeeId().equals(cellPhoneUsageByMonth.getEmployeeId())) {

                    String[] dates = cellPhoneUsageByMonth.getDate().split("/");
                    if (dates[0].length() == 1){dates[0] = '0' + dates[0];}
                    if (dates[1].length() == 1){dates[1] = '0' + dates[1];}
                    LocalDate date = LocalDate.parse(dates[0] + '/' + dates[1] + '/' + dates[2], formatter);

                    switch (date.getMonthValue()) {
                        case 1:
                            dataUsageJan += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageJan += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 2:
                            dataUsageFeb += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageFeb += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 3:
                            dataUsageMar += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageMar += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 4:
                            dataUsageApr += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageApr += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 5:
                            dataUsageMay += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageMay += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 6:
                            dataUsageJun += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageJun += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 7:
                            dataUsageJuly += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageJuly += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 8:
                            dataUsageAug += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageAug += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 9:
                            dataUsageSep += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageJan += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 10:
                            dataUsageOct += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageOct += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 11:
                            dataUsageNov += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageNov += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                        case 12:
                            dataUsageDec += Float.parseFloat(cellPhoneUsageByMonth.getTotalData());
                            minutesUsageDec += Float.parseFloat(cellPhoneUsageByMonth.getTotalMinutes());
                            break;
                    }
                }

            }
            String[] cellPhoneDetailData = { cellPhone.getEmployeeId(), cellPhone.getEmployeeName(), cellPhone.getModel(), cellPhone.getPurchaseDate(), Float.toString(minutesUsageJan),  Float.toString(minutesUsageFeb), Float.toString(minutesUsageMar), Float.toString(minutesUsageApr),Float.toString(minutesUsageMay), Float.toString(minutesUsageJun), Float.toString(minutesUsageJuly),  Float.toString(minutesUsageAug), Float.toString(minutesUsageSep), Float.toString(minutesUsageOct),  Float.toString(minutesUsageNov), Float.toString(minutesUsageDec),  Float.toString(dataUsageJan), Float.toString(dataUsageFeb), Float.toString(dataUsageMar), Float.toString(dataUsageApr), Float.toString(dataUsageMay), Float.toString(dataUsageJun), Float.toString(dataUsageJuly), Float.toString(dataUsageAug),   Float.toString(dataUsageSep), Float.toString(dataUsageOct), Float.toString(dataUsageNov), Float.toString(dataUsageDec)};
            writerOutputFileDetails.writeNext(cellPhoneDetailData);

        }
    }

    private CSVWriter populateDetailCSVHeader(FileWriter outputFileDetails) {
        CSVWriter writerOutputfileDetails = new CSVWriter(outputFileDetails);
        String[] headerOutputfileDetails = {"Employee Id", "Employee Name", "Model", "Purchase Date",
                "Minutes Usage-Jan", "Minutes Usage-Feb", "Minutes Usage-Mar", "Minutes Usage-Apr", "Minutes Usage-May",
                "Minutes Usage-Jun", "Minutes Usage-July", "Minutes Usage-Aug", "Minutes Usage-Sep", "Minutes Usage-Oct",
                "Minutes Usage-Nov", "Minutes Usage-Dec", "Data Usage-Jan", "Data Usage-Feb", "Data Usage-Mar", "Data Usage-Apr",
                "Data Usage-May", "Data Usage-June", "Data Usage-July", "Data Usage-Aug", "Data Usage-Sep", "Data Usage-Oct", "Data Usage-Nov", "Data Usage-Dec"};
        writerOutputfileDetails.writeNext(headerOutputfileDetails);
        return writerOutputfileDetails;
    }

    public void populateHeaderCsv(List<CellPhoneUsageByMonth> listCellPhoneUsageByMonth) {

        try {
            //Read the Header csv
            File file = new File("Headers.csv");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileWriter outputFile = null;
            outputFile = new FileWriter(file);

            CSVWriter writer = setHeaders(outputFile);

            Set<String> employeeIds = new HashSet<>();
            for (CellPhoneUsageByMonth cellPhoneDetail : listCellPhoneUsageByMonth) {
                employeeIds.add(cellPhoneDetail.getEmployeeId());
            }

            populateDataHeadersCSV(listCellPhoneUsageByMonth, writer, employeeIds);

            writer.close();

        }
        catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }

    private void populateDataHeadersCSV(List<CellPhoneUsageByMonth> listCellPhoneUsageByMonth, CSVWriter writer, Set<String> employeeIds) {
        for (String eIds : employeeIds) {
            float numberOfPhones = 0;
            float totalMinutes = 0;
            float totalData = 0;
            float averageMinutes = 0;
            float averageData = 0;

            for (CellPhoneUsageByMonth request : listCellPhoneUsageByMonth) {
                if (request.getEmployeeId().equals(eIds)) {
                    numberOfPhones++;
                    totalMinutes = totalMinutes + Float.parseFloat(request.getTotalMinutes());
                    totalData = totalData + Float.parseFloat(request.getTotalData());
                }

            }
            averageMinutes = totalMinutes / numberOfPhones;
            averageData = totalData / numberOfPhones;
            Format formatter = new SimpleDateFormat(Constant.DATE_PATTERN);
            String s = formatter.format(new Date());
            String[] data1 = {eIds, s, Float.toString(numberOfPhones), Float.toString(totalMinutes), Float.toString(totalData), Float.toString(averageMinutes), Float.toString(averageData)};
            writer.writeNext(data1);

        }
    }

    private CSVWriter setHeaders(FileWriter outputFile) {
        CSVWriter writer = new CSVWriter(outputFile);
        String[] header = {"EmployeeId", "Report Run Date", "Number of Phones", "Total Minutes", "Total Data", "Average Minutes", "Average Data"};
        writer.writeNext(header);
        return writer;
    }


}
