package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.PartCluster;
import com.monolithiot.inventory.service.general.PartClusterService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 0:07
 * Class Name: ApiPartClusterController
 * Author: Levent8421
 * Description:
 * 物料类型相关数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/part-cluster")
public class ApiPartClusterController extends AbstractController {
    private final PartClusterService partClusterService;

    public ApiPartClusterController(PartClusterService partClusterService) {
        this.partClusterService = partClusterService;
    }

    /**
     * Find All PartCluster
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<PartCluster>> all() {
        val allCluster = partClusterService.all();
        return GeneralResult.ok(allCluster);
    }
}
