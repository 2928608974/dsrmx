package com.bdqn.service.impl;
import com.bdqn.dao.AdminUserMapper;
import com.bdqn.dao.FrontUserMapper;
import com.bdqn.pojo.AdminUser;
import com.bdqn.pojo.FrontUser;
import com.bdqn.service.AdminUserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Autowired
    AdminUserMapper userMapper;
    @Autowired
    FrontUserMapper frontUserMapper;

    @Override
    public List<AdminUser> findAdminUserAll() {
        return userMapper.findAdminUserAll();
    }

    @Override
    public AdminUser selAdminUserByName(String name) {
        return userMapper.selAdminUserByName(name);
    }



    /**
     * 导入
     * @param is
     */
    @Override
    public void doImport(InputStream is) {
        //创建一个工作簿
        HSSFWorkbook wb = null;
        try {
            // 工作薄从上传的文件中获取
            wb = new HSSFWorkbook(is);
            // 获取第一个工作表
            HSSFSheet sheet = wb.getSheetAt(0);
            // 获取数据总行数
            int lastRow = sheet.getLastRowNum();
            // 创建一个对象
            FrontUser front = null;
            // 循环获取文件中的每一行数据
            for (int i = 1; i <= lastRow; i++) {
                front = new FrontUser();
                // 获取每一行的单元格的值并给对象赋值
                front.setUserName(sheet.getRow(i).getCell(1).getStringCellValue());
                front.setIdCard(sheet.getRow(i).getCell(2).getStringCellValue());
                front.setPhone(sheet.getRow(i).getCell(3).getStringCellValue());
                front.setProvinceData(sheet.getRow(i).getCell(4).getStringCellValue());
                front.setProvince(sheet.getRow(i).getCell(5).getStringCellValue());
                front.setCityData(sheet.getRow(i).getCell(6).getStringCellValue());
                front.setCity(sheet.getRow(i).getCell(7).getStringCellValue());
                front.setAreaData(sheet.getRow(i).getCell(8).getStringCellValue());
                front.setArea(sheet.getRow(i).getCell(9).getStringCellValue());
                front.setAddress(sheet.getRow(i).getCell(10).getStringCellValue());
                Double money = sheet.getRow(i).getCell(11).getNumericCellValue();
                front.setMoney(money);
                // 如果是数字需要转换
                Integer  state=(int) sheet.getRow(i).getCell(12).getNumericCellValue();
                front.setState(state);
                // 如果单元格是空值需要判断
                if(sheet.getRow(i).getCell(13)!=null){
                    front.setRemark(sheet.getRow(i).getCell(13).getStringCellValue());
                }
                // 保存frontuser对象
                frontUserMapper.addUser(front);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * @param os   输出流
     */
    @Override
    public void userExport(OutputStream os, FrontUser front) {
        // 按条件查询
        List<FrontUser> frontList = frontUserMapper.selUser(front);
        //创建一个工作簿
        HSSFWorkbook book = new HSSFWorkbook();
        //创建一个工作表并起名称
        HSSFSheet sheet = book.createSheet("用户信息");
        //创建一个行并写入表头
        HSSFRow row = sheet.createRow(0);

        // 定义好每一列的标题
        String[] headerNames = {"编号", "名称", "身份证", "手机号码", "省份编号","省份名称","市区编号","市区名称","区县编号","区县名称", "邮寄地址", "金钱", "状态", "备注"};
        // 指定每一列的宽度
        int[] columnWidths = {4000, 8000, 8000, 8000,8000,8000,8000, 8000,8000,8000, 16000, 4000, 5000, 16000};
        //创建一个单元格
        HSSFCell cell = null;
        for (int i = 0; i < headerNames.length; i++) {
            // 创建一个单元格
            cell = row.createCell(i);
            //设置单元格的值
            cell.setCellValue(headerNames[i]);
            // 设置单元格行宽
            sheet.setColumnWidth(i, columnWidths[i]);
        }
        // 写入内容
        int i = 1;
        for (FrontUser frontUser : frontList) {
            row = sheet.createRow(i);
            //必须按照hderarNames的顺序来
            row.createCell(0).setCellValue(frontUser.getUserId());//编号
            row.createCell(1).setCellValue(frontUser.getUserName());//名称
            row.createCell(2).setCellValue(frontUser.getIdCard());//身份证
            row.createCell(3).setCellValue(frontUser.getPhone());//手机号码
            row.createCell(4).setCellValue(frontUser.getProvinceData());//省份编号
            row.createCell(5).setCellValue(frontUser.getProvince());//省份名称
            row.createCell(6).setCellValue(frontUser.getCityData());//市级编号
            row.createCell(7).setCellValue(frontUser.getCity());//市级名称
            row.createCell(8).setCellValue(frontUser.getAreaData());//区县编号
            row.createCell(9).setCellValue(frontUser.getArea());//区县名称
            row.createCell(10).setCellValue(frontUser.getAddress());//地址
            row.createCell(11).setCellValue(frontUser.getMoney());//金钱
                row.createCell(12).setCellValue(frontUser.getState());//状态
            row.createCell(13).setCellValue(frontUser.getRemark());//备注
            i++;
        }
        try {
            // 写入到输出流中
            book.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭工作簿
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
