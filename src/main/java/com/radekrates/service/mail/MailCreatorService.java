package com.radekrates.service.mail;

import com.radekrates.config.AdminConfig;
import com.radekrates.config.CompanyDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {
    private AdminConfig adminConfig;
    private CompanyDetails companyDetails;
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    private Context context;


    @Autowired
    public MailCreatorService(AdminConfig adminConfig, CompanyDetails companyDetails, TemplateEngine templateEngine) {
        this.adminConfig = adminConfig;
        this.companyDetails = companyDetails;
        this.templateEngine = templateEngine;
    }

    public String buildTransactionEmail(String message) {
        context = new Context();
        context.setVariable("message", message);
        makeSharedDataContext();
        return templateEngine.process("mail/transaction_email", context);
    }

    public String buildSignInNotificationEmail(String message) {
        context = new Context();
        context.setVariable("message", message);
        makeSharedDataContext();
        return templateEngine.process("mail/sign_in_notification", context);
    }

    private void makeSharedDataContext() {
        context.setVariable("goodbye", "Best regards: " + adminConfig.getAdminName());
        context.setVariable("details", "Company Details:");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("app_name", "Company name: " + companyDetails.getAppName());
        context.setVariable("app_description", "Company description: " + companyDetails.getAppDescription());
        context.setVariable("company_mail", "Company e-mail: " + companyDetails.getMail());
        context.setVariable("company_mobile", "Company mobile: " + companyDetails.getMobile());
    }
}
