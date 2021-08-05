package com.work.note.util;

import com.work.note.properties.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @ClassName emailUtils
 * @Description 发送邮件工具类
 * @Author LiuZhao
 * @Date 2021/7/14 16:56
 * @Version 1.0
 **/
@Component
@Slf4j
public class EmailUtils {

    @Autowired
    private EmailProperties emailProperties;

    public  void sendEmail(String title,String content,String receiveMailAccount,String receiveName,String filePath){
        //1.创建连接邮件服务器的参数配置
        Properties props = new Properties();
        // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.transport.protocol", "smtp");
        // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.host", emailProperties.getHost());
        props.setProperty("mail.smtp.port","465");
        // 需要请求认证
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);

        Transport transport=null;
        try{
            // 3. 创建一封邮件
            MimeMessage message = createMimeMessage(title,content,session, emailProperties.getUsername(), receiveMailAccount,receiveName,filePath);

            // 4. 根据 Session 获取邮件传输对象
            transport = session.getTransport();

            // 5. 使用 邮箱账号 和 密码 连接邮件服务器
            //    这里认证的邮箱必须与 message 中的发件人邮箱一致，否则报错
            transport.connect(emailProperties.getUsername(), emailProperties.getPassword());

            // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(message, message.getAllRecipients());
            log.debug("===================邮件发送至{}成功！",receiveMailAccount);
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }finally{
            if(transport!=null){
                // 7. 关闭连接
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }

    }

    public static MimeMessage createMimeMessage(String title,String content,Session session, String sendMail, String receiveMail,String receiveName,String filePath) throws Exception {
        // 1. 创建邮件对象
        MimeMessage message = new MimeMessage(session);

        //发送人
        message.setFrom(new InternetAddress(sendMail, "体育荟微信小程序", "UTF-8"));
        //收件人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveMail, receiveName, "UTF-8"));
        //邮件主题
        message.setSubject(title, "UTF-8");
        //邮件正文（可以使用html标签）
        message.setContent(content, "text/html;charset=UTF-8");

        // 2. 创建附件节点
        MimeBodyPart attachment = new MimeBodyPart();
        // 读取本地文件
        DataHandler dh2 = new DataHandler(new FileDataSource(filePath));
        // 将附件数据添加到“节点”
        attachment.setDataHandler(dh2);
        // 设置附件的文件名（需要编码）
        attachment.setFileName(MimeUtility.encodeText(dh2.getName()));

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(attachment);
        mm.setSubType("mixed");

        // 3. 设置整个邮件的关系（将最终的混合“节点”作为邮件的内容添加到邮件对象）
        message.setContent(mm);

        // 4. 设置发件时间
        message.setSentDate(new Date());

        // 5. 保存上面的所有设置
        message.saveChanges();

        return message;
    }
}
