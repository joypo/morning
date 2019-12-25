package com.example.demo.drools.service;

import com.example.demo.drools.bean.Person;
import com.example.demo.drools.bean.Point;
import com.example.demo.drools.dao.RulesDao;
import com.example.demo.drools.entity.Rules;
import io.swagger.models.auth.In;
import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author sunx
 * @date 2019-12-23
 * @description
 */
@Service
public class RulesService {
    @Autowired
    private RulesDao rulesDao;

    /**
     * 动态获取KieSession
     *
     * @param rules rule
     * @return KieSession
     */
    public KieSession getKieSession(String rules) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/rules.drl", rules.getBytes());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new RuntimeException(results.getMessages().toString());
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();

        return kieBase.newKieSession();
    }

    /**
     * 动态加载已经部署的rule
     *
     * @param id rule id
     * @param t  对象
     * @return 结果对象
     */
    /**
     * 动态加载已经部署的rule
     *
     * @param id rule id
     * @param t  对象
     * @return 结果对象
     */
    public Person getRulesWrite(Integer id, Person t) {
        String rules;
        Rules ru = rulesDao.getById(id);
        if (ru != null && ru.getRule() != null) {
            rules = ru.getRule();
        } else throw new RuntimeException("RULE_IS_NULL");

        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/rules.drl", rules.getBytes());
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(org.kie.api.builder.Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        KieBase kieBase = kieContainer.getKieBase();
        KieSession ksession = kieBase.newKieSession();

        ksession.insert(t);
        ksession.fireAllRules();
        return t;
    }

    public void test(Integer id, List<Person> list) {
        KieHelper kieHelper = new KieHelper();
        String rules;
        Rules ru = rulesDao.getById(id);
        if (ru != null && ru.getRule() != null) {
            rules = ru.getRule();
        } else {
            throw new RuntimeException("RULE_IS_NULL");
        }
        KieSession ksession = new KieHelper().addContent(rules, ResourceType.DRL).build().newKieSession();
        List<Person> list1 = new ArrayList<>();
        ksession.setGlobal("list", list1);
        for (Person p : list) {
            ksession.insert(p);
        }

        ksession.fireAllRules();
        System.out.println("完结");
        for (Person person : list1) {
            System.out.println(person.getName());
        }
        ksession.dispose();
    }

    public void test1(Integer id, List<Map<String,Object>> list) {
        KieHelper kieHelper = new KieHelper();
        String rules;
        Rules ru = rulesDao.getById(4);
        if (ru != null && ru.getRule() != null) {
            rules = ru.getRule();
        } else {
            throw new RuntimeException("RULE_IS_NULL");
        }
        KieSession ksession = new KieHelper().addContent(rules, ResourceType.DRL).build().newKieSession();
        List<Map<String,Object>> list1 = new ArrayList<>();
        ksession.setGlobal("list", list1);
        for (Map p : list) {
            ksession.insert(p);
        }

        ksession.fireAllRules();
        System.out.println("完结");
        for (Map person : list1) {
            System.out.println(person.get("A"));
        }
        ksession.dispose();
    }
}
