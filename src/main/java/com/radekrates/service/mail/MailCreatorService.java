package com.radekrates.service.mail;

import com.radekrates.config.AdminConfig;
import com.radekrates.config.CompanyDetails;
import com.radekrates.domain.Transaction;
import com.radekrates.domain.User;
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

    public String buildTransactionEmail(String message, User user, Transaction transaction) {
        context = new Context();
        context.setVariable("message", message);
        context.setVariable("TransactionPair", transaction.getPairOfCurrencies());
        context.setVariable("TransactionInputValue", transaction.getInputValue());
        context.setVariable("TransactionOutputValue", transaction.getOutputValue());
        context.setVariable("TransactionInputIban", transaction.getInputIbanNumber());
        context.setVariable("TransactionOutputIban", transaction.getOutputIbanNumber());
        context.setVariable("purchased", transaction.getApiCurrencyPurchaseMultiplier());
        context.setVariable("sold", transaction.getCurrencySaleMultiplier());
        context.setVariable("date", transaction.getDate());
        makeSharedDataContext(user);
        return templateEngine.process("mail/transaction_email", context);
    }

    public String buildSignInNotificationEmail(String message, User user) {
        context = new Context();
        context.setVariable("message", message);
        makeSharedDataContext(user);
        return templateEngine.process("mail/sign_in_notification", context);
    }

    public String buildScheduledEmail(final String message, final String weatherData) {
        context = new Context();
        context.setVariable("currencyData", message);
        context.setVariable("weatherData", weatherData);
        return templateEngine.process("mail/scheduled_mail_currency_weather", context);
    }

    private void makeSharedDataContext(User user) {
        context.setVariable("userFirstName", user.getUserFirstName());
        context.setVariable("userLastName", user.getUserLastName());
        context.setVariable("dear", "Dear ");
        context.setVariable("coma", ",");
        context.setVariable("goodbye", "Best regards: " + adminConfig.getAdminName());
        context.setVariable("details", "Company Details:");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("app_name", "Company name: " + companyDetails.getAppName());
        context.setVariable("app_description", "Company description: " + companyDetails.getAppDescription());
        context.setVariable("company_mail", "Company e-mail: " + companyDetails.getMail());
        context.setVariable("company_mobile", "Company mobile: " + companyDetails.getMobile());
    }
}
