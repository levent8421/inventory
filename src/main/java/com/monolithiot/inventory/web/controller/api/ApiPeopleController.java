package com.monolithiot.inventory.web.controller.api;

import com.monolithiot.inventory.commons.entity.People;
import com.monolithiot.inventory.service.general.PeopleService;
import com.monolithiot.inventory.web.controller.commons.AbstractController;
import com.monolithiot.inventory.web.vo.GeneralResult;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Create By Levent8421
 * Create Time: 2020/2/14 18:27
 * Class Name: ApiPeopleController
 * Author: Levent8421
 * Description:
 * 联系人相关API数据访问控制器
 *
 * @author Levent8421
 */
@RestController
@RequestMapping("/api/people")
public class ApiPeopleController extends AbstractController {
    private final PeopleService peopleService;

    public ApiPeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * 全部联系人
     *
     * @return GR
     */
    @GetMapping("/")
    public GeneralResult<List<People>> all() {
        val all = peopleService.all();
        return GeneralResult.ok(all);
    }
}
