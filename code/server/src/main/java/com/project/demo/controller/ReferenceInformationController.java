package com.project.demo.controller;

import com.project.demo.entity.ReferenceInformation;
import com.project.demo.service.ReferenceInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 参考信息：(ReferenceInformation)表控制层
 *
 */
@RestController
@RequestMapping("/reference_information")
public class ReferenceInformationController extends BaseController<ReferenceInformation, ReferenceInformationService> {

    /**
     * 参考信息对象
     */
    @Autowired
    public ReferenceInformationController(ReferenceInformationService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(reference_information_id) AS max FROM "+"`reference_information`";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = ("SELECT count(*) count FROM `ordinary_users` INNER JOIN `reference_information` ON ordinary_users.user_name=reference_information.user_name WHERE ordinary_users.user_points < reference_information.reference_integral AND reference_information.reference_information_id="+max).replaceAll("&#60;","<");
        select = service.runCountSql(sql);
        Integer count = Integer.valueOf(String.valueOf(select.getSingleResult()));
        if(count>0){
            sql = "delete from "+"reference_information"+" WHERE "+"reference_information_id"+" ="+max;
            select = service.runCountSql(sql);
            select.executeUpdate();
            return error(30000,"积分不足!");
        }
        sql = "UPDATE `ordinary_users` INNER JOIN `reference_information` ON ordinary_users.user_name=reference_information.user_name SET ordinary_users.user_points= ordinary_users.user_points - reference_information.reference_integral WHERE reference_information.reference_information_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
