package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartCategory;
import com.monolithiot.inventory.service.general.PartCategoryService;
import com.monolithiot.inventory.service.general.PartTypeService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import com.monolithiot.inventory.web.vo.PartTypeVo;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/12 10:12
 * Class Name: ApiPartCategoryController
 * Author: Levent8421
 * Description:
 * API 物料类别相关数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part-category")
public class ApiPartCategoryController extends AbstractController {
    private final PartCategoryService partCategoryService;
    private final PartTypeService partTypeService;

    public ApiPartCategoryController(PartCategoryService partCategoryService,
                                     PartTypeService partTypeService) {
        this.partCategoryService = partCategoryService;
        this.partTypeService = partTypeService;
    }

    /**
     * 所有类别
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<PartCategory>> all() {
        val all = partCategoryService.all();
        return GeneralResult.ok(all);
    }

    /**
     * Find By PartTypeId
     *
     * @param typeId typeId
     * @return GR
     */
    @GetMapping("/_type")
    public GeneralResult<List<PartCategory>> findByTypeId(@RequestParam("typeId") Integer typeId) {
        val categories = partCategoryService.findByTypeId(typeId);
        return GeneralResult.ok(categories);
    }

    /**
     * 分类-类别树
     *
     * @return GR
     */
    @GetMapping("/_tree")
    public GeneralResult<List<PartTypeVo>> asTree() {
        val types = partTypeService.all();
        val categories = partCategoryService.all();
        val tree = partCategoryService.asTree(types, categories);
        return GeneralResult.ok(tree);
    }
}
