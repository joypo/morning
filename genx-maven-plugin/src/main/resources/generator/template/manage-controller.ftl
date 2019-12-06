package ${controllerPackage};

import com.biz.primus.base.vo.result.MessageResult;
import com.biz.primus.common.pagehelper.PageInfo;
import com.biz.primus.csm.feign.FeedbackFeignClient;
import com.biz.primus.management.base.controller.BaseController;
import com.biz.primus.model.csm.page.PageResult;
import ${voPackage}.${modelNameUpperCamel}Vo;
import ${reqVoPackage}.${modelNameUpperCamel}ReqVo;
import com.biz.primus.redis.ro.UserRo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author ${author}
 * @date ${date}
 * @description ${table.comment}前端控制
 */
@Controller
@RequestMapping("/csm/${module}/${modelNameLowerCamel}")
@Slf4j
public class ${modelNameUpperCamel}Controller extends BaseController {

    @Autowired
    private ${modelNameUpperCamel}FeignClient ${modelNameLowerCamel}FeignClient;

    /**
     * 跳转${table.comment}列表
     *
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        log.info("跳转${table.comment}列表");
        ModelAndView view = new ModelAndView("${module}/${modelNameLowerCamel}/list");
        return view;
    }

    @PostMapping("/getAll")
    @ResponseBody
    public PageResult<${modelNameUpperCamel}Vo> getAll(${modelNameUpperCamel}ReqVo reqVo) {
        log.info("csm/${modelNameLowerCamel}/getAll [{}]", reqVo);
        PageInfo<${modelNameUpperCamel}Vo> result = ${modelNameLowerCamel}FeignClient.selectPageList(reqVo);
        return new PageResult<>(result);
    }

    /**
     * 跳转新增页面
     *
     * @param id
     * @return
     */
    @GetMapping("/save")
    public ModelAndView save(String id) {
        log.debug("跳转新增或编辑页面,id [{}]", id);
        ModelAndView view = new ModelAndView("${module}/${modelNameLowerCamel}/add");
        if (StringUtils.isNotBlank(id)) {
            view.addObject("vo", get${modelNameUpperCamel}VoById(id));
        }
        return view;
    }

    /**
     * 新增/编辑${table.comment}
     *
     * @param vo
     * @return
     */
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public MessageResult saveOrUpdate(@Valid ${modelNameUpperCamel}Vo vo) {
        log.info("csm/${modelNameLowerCamel}/saveOrUpdate, [{}]", vo);
        MessageResult messageResult = MessageResult.success();
        try {
            UserRo userRo = this.getCurrentUserFromRedis();
            vo.setCreateId(Long.valueOf(userRo.getUserId()));
            vo.setCreateUser(userRo.getName());
            ${modelNameLowerCamel}FeignClient.save(vo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            messageResult = MessageResult.error("操作失败，请稍后重试!");
        }
        return messageResult;
    }

    /**
     * 跳转${table.comment}详情页面
     *
     * @param id
     * @return
     */
    @GetMapping("/view")
    public ModelAndView view(String id) {
        log.info("跳转${table.comment}详情页面[{}]", id);
        ModelAndView view = new ModelAndView("${module}/${modelNameLowerCamel}/view");
        view.addObject("vo", get${modelNameUpperCamel}VoById(id));
        return view;
    }

    /**
     * 获取${table.comment}
     *
     * @param id
     * @return
     */
    private ${modelNameUpperCamel}Vo get${modelNameUpperCamel}VoById(String id) {
        try {
            ${modelNameUpperCamel}Vo vo = ${modelNameLowerCamel}FeignClient.detail(id);
            return vo;
        } catch (Exception e) {
            log.error("获取信息失败[{}],[{}]", e.getMessage(), id);
        }
        return null;
    }
}
