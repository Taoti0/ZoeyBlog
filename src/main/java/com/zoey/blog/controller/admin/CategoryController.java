package com.zoey.blog.controller.admin;

import com.zoey.blog.service.CategoryService;
import com.zoey.blog.util.PageQueryUtil;
import com.zoey.blog.util.Result;
import com.zoey.blog.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author: Zoey He
 * @data: 2022/4/6  13:35
 * CREATE BY IDEA
 * ZoeyBlog: CategoryController.java
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request){   // 显示博客分类前端页面
        request.setAttribute("path", "categories");     // 激活仪表盘中的分类管理
        return "admin/category";
    }

    /**
     * 分类列表
     * 查询博客分类数据表，将内容显示在前端页面中
     */
    @GetMapping(value = "/categories/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
    }

    /**
     * 新增分类
     */
    @PostMapping(value = "/categories/add")
    @ResponseBody
    public Result add(@RequestParam("categoryName") String categoryName,
                      @RequestParam("categoryIcon") String categoryIcon){
        if (StringUtils.isEmpty(categoryIcon)) return ResultGenerator.genFailResult("请输入类别名");
        if (StringUtils.isEmpty(categoryIcon)) return ResultGenerator.genFailResult("请选择图标");
        if (categoryName.equals("默认分类")) return ResultGenerator.genFailResult("分类不能设置为 '默认分类'");
        if (categoryService.addCategory(categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("已存在此分类");
        }
    }

    /**
     * 修改分类
     */
    @PostMapping(value = "/categories/update")
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon){
        if (StringUtils.isEmpty(categoryIcon)) return ResultGenerator.genFailResult("请输入类别名");
        if (StringUtils.isEmpty(categoryIcon)) return ResultGenerator.genFailResult("请选择图标");
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 删除分类
     */
    @PostMapping(value = "/categories/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){       // 支持多条一起删除（js 文件根据选中的行数获取到这些被选中的分类的id）
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");  // 在 js 中已处理过一次，这里再写一次以防万一
        }
        if (categoryService.deleteCategory(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
}
