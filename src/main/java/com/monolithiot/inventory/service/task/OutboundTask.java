package com.monolithiot.inventory.service.task;

import com.monolithiot.inventory.commons.entity.Part;
import com.monolithiot.inventory.commons.entity.PartQuantity;
import com.monolithiot.inventory.commons.entity.PartRelation;
import com.monolithiot.inventory.service.general.PartQuantityService;
import com.monolithiot.inventory.service.general.PartRelationService;
import com.monolithiot.inventory.service.task.dto.PartOutboundRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ArrayStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By Levent8421
 * Create Time: 2020/12/21 18:02
 * Class Name: OutboundTask
 * Author: Levent8421
 * Description:
 * 出库任务
 *
 * @author Levent8421
 */
@Slf4j
public class OutboundTask {
    private static final int MAX_DEEP = 20;
    private final PartQuantityService partQuantityService;
    private final PartRelationService partRelationService;
    private Part part;
    private Integer storageLocationId;
    private double count;
    private final Map<Integer, PartOutboundRecord> outboundRecordTable = new HashMap<>(128);
    private final Map<Integer, PartQuantity> quantityCache = new HashMap<>(128);
    private int recursionDeep = 0;
    private ArrayStack dependencies = new ArrayStack();

    public OutboundTask(PartQuantityService partQuantityService,
                        PartRelationService partRelationService) {
        this.partQuantityService = partQuantityService;
        this.partRelationService = partRelationService;
    }

    /**
     * 初始化出库任务
     *
     * @param part            物料
     * @param storageLocation 库位ID
     * @param count           数量
     * @return this
     */
    public OutboundTask init(Part part, Integer storageLocation, double count) {
        if (this.part != null) {
            throw new IllegalStateException("Can not re init this task!");
        }
        if (part == null) {
            throw new NullPointerException("Can not set a null part for this task!");
        }
        if (storageLocation == null) {
            throw new NullPointerException("can not set a null storage location for this task!");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("The outbound count must be greater than zero!");
        }
        this.count = count;
        this.part = part;
        this.storageLocationId = storageLocation;
        return this;
    }

    /**
     * 虚拟出库
     * 该操作将计算所有需要出库的物料和出库数量
     *
     * @throws PartOutboundException e
     */
    public synchronized void virtualOutbound() throws PartOutboundException {
        if (outboundRecordTable.size() > 0) {
            throw new PartOutboundException("the task are already virtualOutbound");
        }
        recursionDeep = 0;
        dependencies.clear();
        findOutboundParts(part, count, storageLocationId);
    }

    private void findOutboundParts(Part part, double count, Integer storageLocationId) throws PartOutboundException {
        recursionDeep++;
        if (recursionDeep > MAX_DEEP) {
            throw new PartOutboundException("Can not outbound this part, recursion deep=" + recursionDeep);
        }
        dependencies.push(part);
        final PartQuantity quantity = findQuantity(part.getId(), storageLocationId);
        final double quantityNum = quantity.getQuantity();

        double surplus = quantityNum - count;
        if (surplus >= 0) {
            countDownQuantity(part, count);
            log.info("Part [{}] countdown [{}] , stock is greater than count!", part.getId(), count);
            dependencies.pop();
            recursionDeep--;
            return;
        }
        countDownQuantity(part, quantityNum);
        log.info("Part [{}] countdown [{}] , stock is letter than count, surplus=[{}]",
                part.getId(), quantityNum, surplus);
        surplus = Math.abs(surplus);

        final List<PartRelation> childParts = findChildParts(part);
        if (childParts.size() <= 0) {
            final String errorStr = String.format("The part[%s/%s] stock[%s] less than outbound count[%s], and the sub part does not exist! deep=%s, %s",
                    part.getPartNo(), part.getDescription(), quantityNum, count, recursionDeep, getDependenciesString());
            throw new PartOutboundException(errorStr);
        }
        for (PartRelation partRelation : childParts) {
            final Part childPart = partRelation.getChildPart();
            final double childPartCountDownNum = surplus * partRelation.getQuantity();
            findOutboundParts(childPart, childPartCountDownNum, storageLocationId);
        }
        dependencies.pop();
        recursionDeep--;
    }

    private String getDependenciesString() {
        final StringBuilder stringBuilder = new StringBuilder("DependenciesStack:[");
        for (Object obj : dependencies) {
            final Part part = (Part) obj;
            stringBuilder.append('[')
                    .append(part.getPartNo())
                    .append(':')
                    .append(part.getDescription())
                    .append("]->");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private void countDownQuantity(Part part, double count) {
        if (count <= 0) {
            return;
        }
        final Integer partId = part.getId();
        if (outboundRecordTable.containsKey(partId)) {
            final PartOutboundRecord record = outboundRecordTable.get(partId);
            record.setCount(record.getCount() + count);
        }
        final PartOutboundRecord record = new PartOutboundRecord();
        record.setCount(count);
        record.setPart(part);
        record.setStorageLocationId(storageLocationId);
        outboundRecordTable.put(partId, record);

        // update the quantity cache
        final PartQuantity quantity = quantityCache.get(partId);
        assert quantity != null;
        quantity.setQuantity(quantity.getQuantity() - count);
    }

    private List<PartRelation> findChildParts(Part part) {
        return partRelationService.findByPrimaryPart(part.getId());
    }

    private PartQuantity findQuantity(Integer partId, Integer storageLocationId) {
        if (quantityCache.containsKey(partId)) {
            return quantityCache.get(partId);
        }
        PartQuantity quantity = partQuantityService.findByPartAndStorageLocation(partId, storageLocationId);
        if (quantity == null) {
            quantity = new PartQuantity();
            quantity.setQuantity(0D);
            quantity.setPart(part);
            quantity.setStorageLocationId(storageLocationId);
        }
        quantityCache.put(partId, quantity);
        return quantity;
    }

    public List<PartOutboundRecord> getRecords() {
        return new ArrayList<>(outboundRecordTable.values());
    }
}
