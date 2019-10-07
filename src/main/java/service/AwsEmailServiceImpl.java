package service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.AmazonSimpleEmailServiceException;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import dto.Email;
import exception.ServerError;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@ConditionalOnProperty("AWS_SES_ENABLED")
public class AwsEmailServiceImpl implements EmailService {

    private AmazonSimpleEmailService client;

    @Autowired
    public void setup(Environment environment) {
        DefaultAWSCredentialsProviderChain creds = new DefaultAWSCredentialsProviderChain();

        // Implementing AWS SES Signature Version 4 Signing Process is outside the scope of this project
        // https://docs.aws.amazon.com/general/latest/gr/signature-version-4.html
        // Hence the use of the AWS client
        client = AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(creds)
                .withRegion(Regions.US_EAST_1).build();
    }

    @Override
    public void sendEmail(Email email) {
        try {
            SendEmailRequest request = buildRequest(email);
            SendEmailResult response = client.sendEmail(request);
            if (response.getSdkHttpMetadata().getHttpStatusCode() != HttpStatus.SC_OK) {
                throw new ServerError("Problem sending email. If the problem persists, please contact Customer Support.");
            }
        } catch (AmazonSimpleEmailServiceException e) {
            log.error("Caught exception sending email.");
            log.error(e.getMessage());
            throw new ServerError("Caught exception sending email.");
        }
    }

    private SendEmailRequest buildRequest(Email email) {
        SendEmailRequest request = new SendEmailRequest()
                .withSource(email.getFrom())
                .withDestination(new Destination().withToAddresses(email.getTo()))
                .withMessage(new Message()
                        .withSubject(new Content()
                                .withCharset("UTF-8")
                                .withData(email.getSubject()))
                        .withBody(new Body()
                                .withText(new Content()
                                        .withCharset("UTF-8")
                                        .withData(email.getBody()))));

        return request;
    }

}
