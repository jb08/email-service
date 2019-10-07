package service;

import dto.Email;

public interface EmailService {

    /**
     * Send an email.
     *
     * @param email the Email to send
     */
    void sendEmail(Email email);
}
