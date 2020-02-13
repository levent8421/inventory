package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.StorageLocation;
import com.monolithiot.inventory.service.general.StorageLocationService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/13 10:32
 * Class Name: ApiStorageLocationController
 * Author: Levent8421
 * Description:
 * 库存位置相关API数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/storage-location")
public class ApiStorageLocationController extends AbstractController {
    private final StorageLocationService storageLocationService;

    public ApiStorageLocationController(StorageLocationService storageLocationService) {
        this.storageLocationService = storageLocationService;
    }

    /**
     * Find All Locations
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<StorageLocation>> all() {
        val allLocations = storageLocationService.all();
        return GeneralResult.ok(allLocations);
    }
}
