package com.bdqn.controller;

import com.bdqn.message.SendCode;
import com.bdqn.pojo.FrontUser;
import com.bdqn.service.FrontUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/front")
public class FrontUserController {
    @Autowired
    FrontUserService frontUserService;

    /**
     * 显示后台用户数据并分页
     */
    @RequestMapping("/frontUser")
    private String findUserByPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize, Model model, FrontUser user) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<FrontUser> list = frontUserService.selUser(user);
        model.addAttribute("frontUserPage", page);
        model.addAttribute("myuser", user);
        return "admin/userlist";
    }

    /**
     * 后台删除用户
     *
     * @param request
     * @return
     */
    @RequestMapping("/del")
    public String del(HttpServletRequest request) {
        String[] ids = request.getParameterValues("IDCheck");
        frontUserService.delBatch(ids);
        return "redirect:/front/frontUser";
    }

    /**
     * 后台新增用户
     *
     * @param user
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/frontuser", method = RequestMethod.POST)
    public void saveFrontUser(FrontUser user, HttpServletResponse response) throws IOException {
        /**
         * 设置确认状态
         */
        System.out.println("***"+user);
        user.setState(1);
        frontUserService.addUser(user);

        response.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");

        response.getWriter().print("<script>alert('添加成功');parent.$.fancybox.close();</script>");
    }

    /**
     * 查询要修改的用户根据id
     *
     * @param model
     * @param uid
     * @return
     */
    @RequestMapping("/frontById")
    public String findById(Model model, Integer uid) {
        FrontUser user = frontUserService.findById(uid);
        model.addAttribute("user", user);
        return "/admin/addfrontuser";
    }

    /**
     * 后台修改用户
     *
     * @param user
     * @param response
     * @throws IOException
     */
    @RequestMapping("/upd")
    public void updfront(FrontUser user, HttpServletResponse response) throws IOException {
        int row = frontUserService.updateFrontUser(user);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("UTF-8");
        response.getWriter().print("<script>alert('修改成功');parent.$.fancybox.close();</script>");
    }

    /**
     * 跳前台主页
     *
     * @return
     */
    @RequestMapping("/frontLogin")
    public String frontLogin() {
        return "/front/frontlogin";
    }
    /**
             * 前台登录成功跳转的页面
             *
             * @return
             */
            @ResponseBody
            @RequestMapping("/indexCode")
            public int indexCode(FrontUser user,HttpServletRequest request,String code) {
                FrontUser LoginUser = frontUserService.loginUser(user.getUserName(), user.getIdCard(), user.getPhone());
                if (LoginUser != null) {
                    if (request.getSession().getAttribute("CUDE").equals(code)){
                        System.out.println("登陆成功");
                        return 1;
                    }else {
                        System.out.println("验证码错误");
                        return -1;
                    }
        }
                System.out.println("登录失败");
        return 0;
    }
    /**
     * 短信验证
     * @param phone
     * @param request
     */
    @ResponseBody
    @GetMapping("/SendCode/{phone}")
    public void sueCode(@PathVariable("phone")String phone,HttpServletRequest request){
        request.getSession().setAttribute("CUDE","123");
    }

    /**
     * 加载页面
     *
     * @return
     */
    @RequestMapping("/house_list")
    public String loadhouselist() {
        return "/front/house_list";
    }

    @GetMapping("/index")
    public String index(){
        return "/front/index";
    }

    // 单个图片上传

    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile , HttpServletRequest request) throws IOException {
        // 判断文件是否成功上传
        if(uploadFile!=null) {
            // 指定上传图片的保存路径
            String path = "D:\\image\\";
            // 获取上传的文件名全称
            String fileName = uploadFile.getOriginalFilename();
            // 获取上传文件的后缀名
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            // 给文件定义一个新的名称,杜绝文件重名覆盖现象
            String newFileName = UUID.randomUUID().toString() + suffix;

            // 创建File对象,注意这里不是创建一个目录或一个文件,你可以理解为是 获取指定目录中文件的管理权限(增改删除判断等 . . .)
            File tempFile = new File(path);
            // 判断File对象对应的目录是否存在
            if (!tempFile.exists()) {
                // 创建以此抽象路径名命名的目录,注意mkdir()只能创建一级目录,所以它的父级目录必须存在
                tempFile.mkdir();
            }
            // 在指定路径中创建一个文件(该文件是空的)
            File file = new File(path + newFileName);
            // 将上传的文件写入指定文件
            uploadFile.transferTo(file);

            // 将新文件名添加到HttpServletRequest
            request.setAttribute("newFileName", newFileName);
        }
        return "front/file";
    }


}






