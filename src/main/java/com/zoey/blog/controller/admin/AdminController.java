package com.zoey.blog.controller.admin;

import com.zoey.blog.entity.AdminUser;
import com.zoey.blog.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: Zoey He
 * @data: 2022/4/3  14:08
 * CREATE BY IDEA
 * ZoeyBlog: AdminController.java
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminUserService adminUserService;
    @Resource
    private BlogService blogService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private TagService tagService;
    @Resource
    private CommentService commentService;

    /**
     * 管理员登陆页面
     * @return
     */
    @GetMapping({"/login"})
    public String login(){
        return "admin/login";
    }

    /**
     * 管理员登陆处理
     * 验证用户的用户名、密码、验证码、session 信息
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session){
        if (StringUtils.isEmpty(verifyCode)){
            session.setAttribute("errorMsg", "请输入验证码");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)){
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser adminUser = adminUserService.login(userName, password);
        if (adminUser != null){
            // 与数据库匹配成功，设置 session 信息
            String nick_name = adminUser.getNickName().substring(0, 1).toUpperCase() + adminUser.getNickName().substring(1);    // 首字母大写
            session.setAttribute("loginUser", nick_name);
            session.setAttribute("loginUserId", adminUser.getAdminUserId());
            return "redirect:/admin/index";
        }else {
            session.setAttribute("errorMsg", "用户名或密码错误");
            return "admin/login";
        }
    }

    /**
     * 后台主页
     */
    @GetMapping({"", "/", "/index", "/index.html"})
    public String index(HttpServletRequest request){
        request.setAttribute("path", "index");
        request.setAttribute("categoryCount", categoryService.getTotalCategories());
        request.setAttribute("blogCount", blogService.getTotalBlogs());
        request.setAttribute("tagCount", tagService.getTotalTags());
        request.setAttribute("commentCount", commentService.getTotalComments());
        return "admin/index";
    }

    /**
     * 修改页面 input 内容放置
     */
    @GetMapping({"/profile"})
    public String profile(HttpServletRequest request){
        Integer adminUserId = (int) request.getSession().getAttribute("loginUserId"); // 获取到当前用户的 id
        AdminUser adminUser = adminUserService.getUserDetailById(adminUserId);   // 根据 ID 查询用户
        if (adminUser == null)
            return "admin/login";

        request.setAttribute("path", "profile");
        request.setAttribute("adminUserName", adminUser.getAdminUserName());
        request.setAttribute("nickName", adminUser.getNickName());
        return "admin/profile";
    }
    /**
     * 修改用户昵称
     */
    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(nickName)) {
            return "昵称不能为空";
        }
        Integer adminUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateNickName(adminUserId, nickName)) {
            return "success";
        }
        return "修改失败";
    }

    /**
     * 修改用户密码
     */
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request,
                                 @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "密码不能为空";
        }
        Integer adminUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(adminUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return "success";
        } else {
            return "修改失败";
        }
    }

    /**
     * 退出登陆
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
