package com.zoey.blog.controller.admin;

import com.zoey.blog.service.TagService;
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
 * @data: 2022/4/6  16:52
 * CREATE BY IDEA
 * ZoeyBlog: TagController.java
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Resource
    private TagService tagService;

    @GetMapping("/tags")
    public String list(HttpServletRequest request){
        request.setAttribute("path", "tags");
        return "admin/tag";
    }

    /**
     * 标签列表
     */
    @GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }

    /**
     * 新增标签
     */
    @PostMapping(value = "/tags/add")
    @ResponseBody
    public Result add(@RequestParam("tagName") String tagName){
        if (StringUtils.isEmpty(tagName)) return ResultGenerator.genFailResult("标签不可为空");
        if (tagService.addTag(tagName)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("已存在此标签");
        }
    }

    /**
     * 删除标签
     */
    @PostMapping(value = "/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (tagService.deleteTag(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败" + "\n" + "此标签可能关联您的博客文章");
        }
    }

}
