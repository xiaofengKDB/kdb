package com.soecode.lyf.web;

import com.soecode.lyf.common.Constant;
import com.soecode.lyf.common.CookieUtil;
import com.soecode.lyf.entity.WxOrder;
import com.soecode.lyf.service.WxOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/wxOrder")
public class WxOrderController {

    @Resource
    private WxOrderService wxOrderService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addOrder(WxOrder wxOrder, HttpServletRequest rq, HttpServletResponse rp) throws IOException {
        Cookie cookie = CookieUtil.getCookieByName(rq, Constant.KEY_OPEN_ID);
        if (cookie == null){
            //把用户跳转到错误页面
            rp.sendRedirect("./wxUser/error");
            return;
        }
        String openid = cookie.getValue();
        wxOrder.setWxopenid(openid);
        wxOrder.setOrderid("wx000000");
        wxOrderService.insertWxOrder(wxOrder);
        System.out.print(wxOrder.toString());
    }
}
