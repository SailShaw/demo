package com.springboot.demo;

import cn.hutool.extra.mail.MailUtil;

/**
 * Create By SINYA
 * Description:
 */
public class TestSendMail {


    public static void main(String[] args) {
        MailUtil.send(
                "shawsail@live.cn",
                "邮箱测试-2019年3月15日",
                "rt \n- 来自Hutools",
                false);
    }
}
