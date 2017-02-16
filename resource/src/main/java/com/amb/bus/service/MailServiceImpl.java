package com.amb.bus.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.amb.bus.BusOrder;

import freemarker.template.Configuration;

@Service("mailingService")
public class MailServiceImpl implements MailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
    Configuration freemarkerConfiguration;
 
	@Async
    @Override
    public void sendEmail(Object object) {
 
        BusOrder order = (BusOrder)object;
         
        MimeMessagePreparator preparator = getMessagePreparator(order);
        
        System.out.println("mailSender is " + mailSender);
    	
        try {
            mailSender.send(preparator);
            System.out.println("Message has been sent.............................");
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    private MimeMessagePreparator getMessagePreparator(final BusOrder order){
         
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
  
                helper.setSubject("Bus Booking Confirmation #"+ order.getBookingRefId());
                helper.setFrom("phonaylin@gmail.com");
                helper.setTo(order.getBuyerEmailAddress());
      
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("order", order);
                 
                String text = geFreeMarkerTemplateContent(model);
                System.out.println("Template content : "+text);
 
                // use the true flag to indicate you need a multipart message
                helper.setText(text, true);
 
                //Additionally, let's add a resource as an attachment as well.
//                helper.addAttachment("cutie.png", new ClassPathResource("linux-icon.png"));
 
            }
        };
        return preparator;
    }
     
    public String geFreeMarkerTemplateContent(Map<String, Object> model){
        StringBuffer content = new StringBuffer();
        try{
        	System.out.println("sending geFreeMarkerTemplateContent");
        	
        	
         content.append(FreeMarkerTemplateUtils.processTemplateIntoString( 
                 freemarkerConfiguration.getTemplate("fm_busConfirmTemplate.html"),model));
         return content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }
          return "";
    }

}