package com.stjh.ssodemo.controller;

import com.stjh.ssodemo.entity.ResponseEntity;
import com.stjh.ssodemo.entity.User;
import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhipeng
 * @date 2019-07-04
 */
@RestController
public class AuthController extends BaseController {
    /**
     * hr单点登录地址
     */
    private String hrUrl = "http://10.136.4.80/oam/server/obrareq.cgi?wh%3DJXSL_GongXiang_WebGate%20wu%3D%2Fhr%2Fsso_auth%20wo%3D1%20rh%3Dhttp%3A%2F%2F192.168.5.190%3A8080%20ru%3D%252Fhr%252Fsso_auth";
    /**
     * 共享单点登录地址
     */
    private String gxUrl = "http://10.136.4.80/oam/server/obrareq.cgi?wh%3DJXSLGX%20wu%3D%2Fmanage%2Fthird_party_login%20wo%3D1%20rh%3Dhttp%3A%2F%2F10.136.6.54%3A8080%20ru%3D%252Fmanage%252Fthird_party_login";
    /**
     * 单点登录接口地址
     */
    private String ssoUrl = "http://10.136.4.80/oam/server/auth_cred_submit";

//    @RequestMapping(value = "/index")
//    public ModelAndView test(ModelAndView mv) {
//        mv.setViewName("/main");
//        mv.addObject("title", "业务跳转");
//        return mv;
//    }

    @RequestMapping(value = "/index")
    public ModelAndView test() {
        ModelAndView v = new ModelAndView("main");
        v.addObject("title", "业务跳转");
        return v;
    }

    @RequestMapping(value = "/mock")
    public ResponseEntity mock(HttpServletResponse response, String userId, String app) throws IOException {
        RawResponse preResp;
        if (app.equals("a")) {
            preResp = Requests.get(hrUrl).headers(getHeader()).send();
        } else {
            preResp = Requests.get(gxUrl).headers(getHeader()).send();
        }
        User u = getUserById(userId);
        RawResponse ssoResp = Requests.post(ssoUrl)
                .params(payload(u.getUserName(), u.getPassword()))
                .cookies(preResp.cookies())
                .followRedirect(false)
                .send();
        if (ssoResp.statusCode() == 200){
            return failResult("登录失败");
        }
        response.sendRedirect(ssoResp.getHeader("Location"));
        return successResult();
    }

    /**
     * 从hr根据userId获取用户名和密码
     * @param userId
     * @return
     */
    private User getUserById(String userId){
        User u = new User();
        u.setUserName("吴智鹏");
        u.setPassword("db61f62dad0f61b76a3ddf476cd7f14b");
        return u;
    }

    private Map<String, String> payload(String userName, String password) {
        Map<String, String> payloads = new HashMap<>();
        payloads.put("username", userName);
        payloads.put("password", password);
        return payloads;
    }

    private Map<String, String> getHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Connection", "keep-alive");
        headers.put("Cache-Control", "max-age=0");
        headers.put("Upgrade-Insecure-Requests", "1");
        headers.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
        headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        headers.put("Accept-Encoding", "gzip, deflate");
        headers.put("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,pl;q=0.6");
        headers.put("Cookie", "");
        return headers;
    }
}
