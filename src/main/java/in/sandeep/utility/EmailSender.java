/*
 *
 *  *
 *  *  *     Copyright 2023-2024 Sandeep Chatterjee @ https://sandeepc.in/
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package in.sandeep.utility;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSender {

    private final Properties config = new Properties();

    public EmailSender() {
        String fileName = "application.properties";
        // Check if the file exists in the context classloader
        var loader = Thread.currentThread().getContextClassLoader();
        var resource = loader.getResource(fileName);

        if (resource == null) {
            System.err.println("CRITICAL: Could not find " + fileName + " in the classpath.");
            System.err.println("Debug: Current Classpath location: " +
                    System.getProperty("java.class.path"));
            throw new RuntimeException("Unable to find " + fileName);
        }

        try (InputStream input = resource.openStream()) {
            config.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Error loading " + fileName, e);
        }
    }

    public void sendEmail() {

        var props = new Properties();

        props.putAll(java.util.Map.of(

                "mail.smtp.auth", "true",

                "mail.smtp.starttls.enable", "true",

                "mail.smtp.host", "smtp.gmail.com",

                "mail.smtp.port", "587"
        ));

        var session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(

                        config.getProperty("email.username").trim(),

                        config.getProperty("email.password").trim()
                );
            }
        });

        try {
            var message = new MimeMessage(session);

            message.setFrom(new InternetAddress(config.getProperty("email.username"), "My Awesome App"));

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chatterjee.sandeep@outlook.com"));

            message.setSubject("Hello from Java 25!");

            message.setText("Testing resource loading!");

            Transport.send(message);

        } catch (Exception ex) {

            throw new RuntimeException("Failed to send email", ex);
        }
    }
}