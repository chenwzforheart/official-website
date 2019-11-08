package com.cwzsmile.distributed.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author csh9016
 * @date 2019/11/8
 */
@RestController
@RequestMapping("/to")
public class ToController {

    @Autowired
    private ToService toService;

    @RequestMapping("/one")
    public String home(@Valid NextVO nextVO) {
        nextVO.setId(null);
        toService.check("12",nextVO);
        return "one" + nextVO.toString();
    }
}
