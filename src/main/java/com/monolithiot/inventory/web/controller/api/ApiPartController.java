package com.monolithiot.inventory.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.service.general.PartService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 18:08
 * Class Name: ApiPartController
 * Author: Levent8421
 * Description:
 * Part API物料相关数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part")
public class ApiPartController extends AbstractController {
    private final PartService partService;

    public ApiPartController(PartService partService) {
        this.partService = partService;
    }

    /**
     * 全部物料
     *
     * @param page page
     * @param rows rows per page
     * @return GR
     */
    @GetMapping("/_pagination")
    public GeneralResult<PageInfo<Part>> all(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);
        val parts = partService.list(page, rows);
        return GeneralResult.ok(parts);
    }
}
