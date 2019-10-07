package service;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import dto.Email;
import exception.ServerError;
import lombok.extern.log4j.Log4j2;

import com.google.common.annotations.Beta;

@Component
@Log4j2
@ConditionalOnProperty("MAILGUN_ENABLED")
@Beta
// Mailgun integration with gmail or Uptake.com domains did not work on 10/8.
// Hence the @Beta tag
public class MailgunEmailServiceImpl implements EmailService {

    private String mailgunApiKey;
    private CloseableHttpClient client;
    private String authHeader;

    @Autowired
    public void setup(Environment environment) {
        mailgunApiKey = environment.getRequiredProperty("MAILGUN_API_KEY");
        client = HttpClients.createDefault();
        setupAuthHeader();
    }

    @Override
    public void sendEmail(Email email) {
        HttpPost request = buildRequest(email);
        try {
            CloseableHttpResponse response = client.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            log.info(String.format("Sent email response code: %s", statusCode));
            if (statusCode != HttpStatus.SC_OK) {
                throw new ServerError("Problem sending email. If the problem persists, please contact Customer Support.");
            }
        } catch (IOException e) {
            log.error("Caught exception sending email.");
            log.error(e.getMessage());
            throw new ServerError("Caught exception sending email.");
        }
    }

    private HttpPost buildRequest(Email email) {
        HttpPost httpPost = new HttpPost("https://api.mailgun.net/v3/messages");
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
        HttpEntity entity = MultipartEntityBuilder.create()
                .addTextBody("from", buildEmailWithName(email.getFromName(), email.getFrom()))
                .addTextBody("to", buildEmailWithName(email.getToName(), email.getTo()))
                .addTextBody("subject", email.getSubject())
                .addTextBody("text", email.getBody())
                .build();
        httpPost.setEntity(entity);
        return httpPost;
    }

    private void setupAuthHeader() {
        String auth = String.format("api:%s", mailgunApiKey);
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        authHeader = String.format("Basic %s", new String(encodedAuth));
    }

    private static String buildEmailWithName(String name, String email) {
        return String.format("%s <%s>", name, email);
    }
}
