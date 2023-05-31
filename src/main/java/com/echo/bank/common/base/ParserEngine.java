package com.echo.bank.common.base;

import com.echo.bank.framework.exception.BankException;
import com.echo.bank.framework.ioc.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.apache.commons.digester3.xmlrules.FromXmlRulesModule;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Map;
import java.util.Properties;


/**
 * @author echo
 * @version 1.0
 * Create by 2023/5/24 14:24
 */
@Slf4j
@Component
public class ParserEngine {

    /**
     * construct velocityEngine
     */
    @PostConstruct
    @Bean("velocityEngine")
    public VelocityEngine getVelocityEngine() {
        Properties p = new Properties();
        p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.put(Velocity.ENCODING_DEFAULT, "UTF-8");
        p.put(Velocity.OUTPUT_ENCODING, "UTF-8");
        return new VelocityEngine(p);
    }

    /**
     * merge .vm Bean to String xml
     */
    public String beanToStringXml(String ruleName, Map<String, Object> map) {
        VelocityEngine velocityEngine = SpringContextUtils.getBean("velocityEngine", VelocityEngine.class);
        Template template = velocityEngine.getTemplate(ruleName);
        VelocityContext context = new VelocityContext(map);
        StringWriter stringWriter = new StringWriter();
        template.merge(context, stringWriter);
        return stringWriter.toString();
    }

    /**
     * @param msgBody  内容
     * @param ruleName xml rule 文件
     * @return T
     * @Description: parseXml2Object
     */
    public <T> T parseXml2Object(String msgBody, String ruleName) {
        try {
            Digester digester = DigesterLoader.newLoader(new FromXmlRulesModule() {
                /**
                 * 加载解析规则文件
                 */
                @Override
                protected void loadRules() {
                    String fullPath = "templates/" + ruleName + ".xml";
                    URL url = ParserEngine.class.getClassLoader().getResource(fullPath);
                    loadXMLRules(url);
                }
            }).newDigester();
            return digester.parse(new StringReader(msgBody));
        } catch (Exception e) {
            log.info("解析String类型的xml出现问题，错误信息msg: {}", e.getMessage());
            throw new BankException(e.getMessage());
        }
    }

}
