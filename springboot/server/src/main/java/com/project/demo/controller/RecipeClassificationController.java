package com.project.demo.controller;

import com.project.demo.entity.RecipeClassification;
import com.project.demo.service.RecipeClassificationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 食谱分类：(RecipeClassification)表控制层
 *
 */
@RestController
@RequestMapping("/recipe_classification")
public class RecipeClassificationController extends BaseController<RecipeClassification, RecipeClassificationService> {

    /**
     * 食谱分类对象
     */
    @Autowired
    public RecipeClassificationController(RecipeClassificationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
