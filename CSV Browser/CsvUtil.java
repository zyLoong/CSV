package cn.threatbook.tim.util;

import au.com.bytecode.opencsv.CSVWriter;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CsvUtil {
// 传入要写入CSV文件的list
    public static void writeToPath(List<String[]> list, HttpServletResponse response) {
//        随机文件名
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HHmmss").format(new Date());
        String random = Integer.toString((int) (new Random().nextDouble() * 90000 + 10000));
        String fileName = dateTime + "-" + random;

//        设置response头信息
        response.reset();
        response.setContentType("APPLICATION/OCTET-STREAM");
        fileName = new String(fileName.getBytes());
        response.setHeader("Content-disposition", "attachment;  filename=" + fileName + ".csv");
        response.setCharacterEncoding("UTF-8");

        try {
            CSVWriter writer = new CSVWriter(response.getWriter(), '|');
            writer.writeAll(list);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
