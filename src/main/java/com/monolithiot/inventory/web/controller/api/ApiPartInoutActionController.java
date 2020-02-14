package com.monolithiot.inventory.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.monolithiot.inventory.commons.entity.PartInoutAction;
import com.monolithiot.inventory.service.general.PartInoutActionService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 19:52
 * Class Name: ApiPartInoutActionController
 * Author: Levent8421
 * Description:
 * 物料进出库记录相关API数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part-inout-action")
public class ApiPartInoutActionController extends AbstractController {
    private final PartInoutActionService partInoutActionService;

    public ApiPartInoutActionController(PartInoutActionService partInoutActionService) {
        this.partInoutActionService = partInoutActionService;
    }

    /**
     * 获取全部进出库记录
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<PartInoutAction>> all() {
        val all = partInoutActionService.all();
        return GeneralResult.ok(all);
    }

    /**
     * 分页查询基础库记录
     *
     * @param page page
     * @param rows rows per page
     * @return GR
     */
    @GetMapping("/_pagination")
    public GeneralResult<PageInfo<PartInoutAction>> pagination(Integer page, Integer rows) {
        page = defaultPage(page);
        rows = defaultRows(rows);

        val resPageInfo = partInoutActionService.list(page, rows);
        return GeneralResult.ok(resPageInfo);
    }
}
