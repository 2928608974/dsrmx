package com.bdqn.controller;
import com.bdqn.pojo.AdminUser;
import com.bdqn.pojo.FrontUser;
import com.bdqn.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = {"/admin","/"},method = RequestMethod.GET)
public class  AdminUserController {
    @Autowired
    AdminUserService adminUserservice;

    /**
     * 后台登录窗口
     */
   @RequestMapping(value = {"/user","/"},method = RequestMethod.POST)
    public String login(String loginName,String userPassword){
     AdminUser user= adminUserservice.selAdminUserByName(loginName);
     if(user!=null){
       if(user.getUserPassword().equals(userPassword)) {
           return "/admin/main";
       }
     }
       return "/admin/userLogin";
   }
    /**
     *  后台导出功能
     */
    @ResponseBody
    @RequestMapping("/userExport")
    public  String   export(FrontUser front, HttpServletResponse response){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        // 设置文件名称
        String filename = "用户信息"+format.format(new Date())+".xls";
        try {
            // 设置输出流表头信息
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    new String(filename.getBytes(),"ISO-8859-1"));//中文名称进行转码
            //调用导出业务
            adminUserservice.userExport(response.getOutputStream(), front);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<script type=\"text/javascript\">\n" +
                "        refresh();\n" +
                "        function refresh() {\n" +
                "            $(\"#submitForm\").attr(\"action\", \"${pageContext.request.contextPath}/front/frontUser\")\n" +
                "        }\n" +
                "    </script>";
    }
    /**
     * 后台导入文件
     * @param file
     */
    @ResponseBody
    @RequestMapping(value = "/fileLoadUP",method = RequestMethod.POST)
    public String  loadUp(@PathVariable("file") MultipartFile file) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            // 判断文件类型是不是excel
            if ("application/vnd.ms-excel".equals(file.getContentType())) {
                try {
                    // 调用读取文件中数据的方法
                    adminUserservice.doImport(file.getInputStream());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return " <script type=\"text/javascript\">\n" +
                "        myclose();\n" +
                "        function myclose(){\n" +
                "            parent.$.fancybox.close();\n" +
                "            \n" +
                "        }\n" +
                "    </script>";

    }

    /**
     *后台进入导入文件的窗口
     */
    @RequestMapping("/infile")
    private String file(){
        return "/admin/fileloadUp";
    }

    /**
     * 后台跳入新增用户页面
     * @return
     */
    @RequestMapping("/addfrontuser")
    public String jumpAddPage(){
        return "admin/addfrontuser";
    }
}
