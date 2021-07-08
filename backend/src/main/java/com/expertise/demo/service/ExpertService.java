package com.expertise.demo.service;

import com.expertise.demo.dao.ExpertDao;
import com.expertise.demo.entity.Expert;
import com.expertise.demo.entity.PageableExperts;
import com.expertise.demo.util.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ExpertService {

    @Autowired
    private ExpertDao expertdao;

    final private String[] validBoolean = {"是", "否"};

    final private String[] validSecretLevel = {"一般", "重要", "核心"};

    final private String[] validType = {"技术", "管理", "财务", "其他"};

    final private String[] validArea = {"集成电路", "人工智能", "生物医药", "网络空间", "新能源", "核能", "航天",
        "航空", "船舶（含海洋工程）", "电子信息", "新材料", "智能装备", "应急", "空间信息（含北斗导航）", "其他（含财务）"};

    public Expert insert(Expert expert) {
        Expert oldExpert = expertdao.findById(expert.getId());
        if (oldExpert != null) {
            throw new RuntimeException("ID为" + expert.getId() + "的专家已经在专家库中，不可重复添加");
        }
        validateSecret(expert);
        validateSecretLevel(expert);
        validateType(expert);
        validateArea(expert);
        validateBlocked(expert);
        return expertdao.insert(expert);
    }

    private void validateBlocked(Expert expert) {
        if (!StringUtils.hasText(expert.getIsBlocked())) {
            expert.setIsBlocked("否");
        }
        if (!ValidateUtils.validate(expert.getSecret(), validBoolean)) {
            throw new RuntimeException("无法识别字段（是否拉黑）：" + expert.getIsBlocked() );
        }
    }

    private void validateArea(Expert expert) {
        if (!StringUtils.hasText(expert.getArea())) {
            expert.setType("其他（含财务）");
        }
        for (String area: expert.getArea().split(",")) {
            if (!ValidateUtils.validate(area, validArea)) {
                throw new RuntimeException("不存在的字段类型（领域）：" + area);
            }
        }
    }

    private void validateType(Expert expert) {
        if (!StringUtils.hasText(expert.getType())) {
            expert.setType("其他");
        }
        if (!ValidateUtils.validate(expert.getType(), validType)) {
            throw new RuntimeException("不存在的字段类型（类型）：" + expert.getType() );
        }
    }

    private void validateSecretLevel(Expert expert) {
        if (!StringUtils.hasText(expert.getSecretLevel())) {
            if ("是".equals(expert.getSecret())) {
                expert.setSecretLevel("一般");
            }
        }
        if (!ValidateUtils.validate(expert.getSecretLevel(), validSecretLevel)) {
            throw new RuntimeException("不存在的字段类型（密级）：" + expert.getSecretLevel() );
        }
    }

    private void validateSecret(Expert expert) {
        if (!StringUtils.hasText(expert.getSecret())) {
            expert.setSecret("否");
        }
        if (!ValidateUtils.validate(expert.getSecret(), validBoolean)) {
            throw new RuntimeException("无法识别字段（是否机密）：" + expert.getSecret() );
        }
    }

    public void update(Expert expert) {
        expertdao.deleteById(expert.getId());
        expertdao.insert(expert);
    }

    public List<Expert> findAll() {
        return expertdao.findAll();
    }

    public PageableExperts findByPageable(String type, String search, int offset) {
        return expertdao.findByPageable(type, search, offset);
    }

    public List<Expert> findByBlocked() {
        return expertdao.findByBlocked("是");
    }

    public Expert findById(String id) {
        return expertdao.findById(id);
    }
}
