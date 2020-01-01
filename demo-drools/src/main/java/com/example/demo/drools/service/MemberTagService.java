package com.example.demo.drools.service;

import com.example.demo.drools.dao.MemberTagDao;
import com.example.demo.drools.dao.RulesDao;
import com.example.demo.drools.entity.MemberTag;
import com.example.demo.drools.entity.Rules;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunx
 * @date 2019-12-27
 * @description
 */
@Service
public class MemberTagService {
    @Autowired
    private MemberTagDao memberTagDao;

    @Autowired
    private RulesDao rulesDao;


    public List<Integer> validateTag() {
        List<MemberTag> tagList = memberTagDao.findAll();
        KieHelper kieHelper = new KieHelper();
        String rules;
        Rules ru = rulesDao.getById(5);
        if (ru != null && ru.getRule() != null) {
            rules = ru.getRule();
        } else {
            throw new RuntimeException("RULE_IS_NULL");
        }
        KieSession ksession = new KieHelper().addContent(rules, ResourceType.DRL).build().newKieSession();
        List<Integer> list1 = new ArrayList<>();
        ksession.setGlobal("list", list1);
        ksession.setGlobal("list1", tagList);
//        ksession.insert(tagList);
        ksession.fireAllRules();
        System.out.println("完结");
        ksession.dispose();
        return list1;
    }
}
