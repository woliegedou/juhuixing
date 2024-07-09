package com.ruoyi.program.util;

import com.ruoyi.program.entity.DtsMemberManagement;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelUtil<T> {

    private Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    public void exportExcel(HttpServletResponse response, List<T> dataList, String sheetName, String fileSuffix) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        // 创建表头
        Row headerRow = sheet.createRow(0);
        // 这里假设您的数据对象有一些属性，例如 id, username, number 等
        String[] headers = {"ID", "用户名", "编号", "性别", "用户等级", "状态", "创建时间", "更新时间"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // 填充数据行
        int rowNum = 1;
        for (T data : dataList) {
            Row row = sheet.createRow(rowNum++);
            // 这里根据实际情况填充每个单元格的数据
            // 示例中假设 T 类型是 DtsMemberManagement，您可以根据您的实际情况进行调整
            DtsMemberManagement member = (DtsMemberManagement) data;
            row.createCell(0).setCellValue(member.getId());
            row.createCell(1).setCellValue(member.getUsername());
            row.createCell(2).setCellValue(member.getNumber());
            row.createCell(3).setCellValue(member.getGender());
            row.createCell(4).setCellValue(member.getUserlevel());
            row.createCell(5).setCellValue(member.getState());
            row.createCell(6).setCellValue(member.getCreatedTime());
            row.createCell(7).setCellValue(member.getUpdatedTime());
            // 其他属性填充逻辑，如性别、用户等级等

            // 如果需要格式化日期等操作，请确保正确处理
        }

        // 设置响应头
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"members." + fileSuffix + "\"");

        // 将Workbook写入响应流
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
