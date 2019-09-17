package com.cwzsmile.distributed.base.file;

import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wenzheng.chen on 2017/12/12.
 */
@Slf4j
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    private FileUtil() {
    }

    public static List<List<String>> readDataFromFile(String filePath, int columnSize) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            return readFromStream(inputStream, columnSize);
        } catch (IOException e) {
            logger.error("读取文件错误", e);
        }
        return null;
    }

    public static List<List<String>> readFromStream(InputStream inputStream, int columnSize) throws IOException {
        Workbook workbook = new XSSFWorkbook(inputStream);
        //1.取得第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        logger.info("sheet名：{}，行数：{}", sheet.getSheetName(), totalRows);
        //2.循环行，获取每列数据
        //获取列数据
        List<List<String>> data = new ArrayList<>();
        Row tempRow = null;
        for (int i = 0; i < totalRows; i++) {
            tempRow = sheet.getRow(i);
            List row = new ArrayList(columnSize);
            for (int j = 0; j < columnSize; j++) {
                Cell tempCell = tempRow.getCell(j);
                String value = convertToString(tempCell);
                row.add(value);
            }
            data.add(row);
        }
        return data;
    }

    private static List<String> getRowData(Row row, int colSize) {
        List<String> data = Lists.newArrayListWithCapacity(colSize);
        for (int i = 0; i < colSize; i++) {
            Cell tempCell = row.getCell(i);
            data.add(convertToString(tempCell));
        }
        return data;
    }

    /**
     * 数字类型，其他类型统一转为String
     *
     * @param filePath
     * @return
     */
    public static List<String> getFirstRowFromExcel(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            logger.info("表名：{}，行数：{}", sheet.getSheetName(), totalRows);
            List<String> result = new ArrayList<>();
            Row tempRow = null;
            Cell cell0 = null;
            for (int i = 0; i < totalRows; i++) {
                tempRow = sheet.getRow(i);
                cell0 = tempRow.getCell(0);
                result.add(convertToString(cell0));
            }
            return result;
        } catch (IOException e) {
            logger.error("getFirstRowFromExcel读取文件错误", e);
        }
        return Collections.emptyList();
    }

    public static List<List<String>> getFromExcel(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath);
             Workbook workbook = filePath.endsWith(".xlsx") ?
                     new XSSFWorkbook(inputStream) : new HSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            logger.info("表名：{}，行数：{}", sheet.getSheetName(), totalRows);
            List<List<String>> result = new ArrayList<>();
            Row tempRow = null;
            for (int i = 0; i < totalRows; i++) {
                List<String> resultCell = new ArrayList<>();
                tempRow = sheet.getRow(i);
                for (Cell cell : tempRow) {
                    resultCell.add(convertToString(cell));
                }
                result.add(resultCell);
            }

            return result;
        } catch (IOException e) {
            logger.error("getFromExcel读取文件错误", e);
        }
        return Collections.emptyList();
    }

    private static String convertToString(Cell cell) {
        String value = "";
        if (cell == null) {
            return value;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                //value = String.valueOf(cell.getNumericCellValue());
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    value = String.valueOf(new BigDecimal(cell.getNumericCellValue()).stripTrailingZeros().toPlainString());
                }
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                value = String.valueOf(cell.getDateCellValue());
                break;
            default:
        }
        return value;
    }
}
