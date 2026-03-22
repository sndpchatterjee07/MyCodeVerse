/*
 *
 *  *
 *  *  *     Copyright 2026 Sandeep Chatterjee @ https://sndpchatterjee07.github.io//
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

package in.sandeep.utility.emails;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type EmailSender
 *
 * @author Sandeep
 * @version 1.0
 *
 */
public class EmailSender {

    private final Properties config = new Properties(); // Initializes the local properties object to store configuration

    /**
     * Constructor to initialize settings upon object creation
     *
     */
    public EmailSender() {

        String fileName = ".env";  // Sets the target configuration file name

        var loader = Thread.currentThread().getContextClassLoader(); // Gets the current thread's class loader to find resources

        var resource = loader.getResource(fileName); // Locates the configuration file in the classpath

        if (resource == null) { // Checks if the file was found by the class loader

            System.err.println("CRITICAL: Could not find " + fileName + " in the classpath."); // Prints error to console if file is missing

            System.err.println("Debug: Current Classpath location: " + // Logs debug info about the classpath

                               System.getProperty("java.class.path")); // Retrieves and prints the current system classpath

            throw new RuntimeException("Unable to find " + fileName); // Terminates with an exception if file is missing
        }

        try (InputStream input = resource.openStream()) { // Attempts to open an input stream for the resource file

            config.load(input); // Loads the property data from the stream into the configuration object

        } catch (IOException e) {

            throw new RuntimeException("Error loading " + fileName, e); // Re-throws as a runtime exception with context
        }
    }

    /**
     * Defines the method to handle email composition and sending
     *
     */
    public void sendEmail() {

        var props = new Properties(); // Creates a new properties set for SMTP configuration

        props.putAll(java.util.Map.of( // Populates SMTP settings into the properties map

                "mail.smtp.auth", "true",  // Enables SMTP authentication

                "mail.smtp.starttls.enable", "true", // Enables STARTTLS security protocol

                "mail.smtp.host", "smtp.gmail.com",  // Specifies the Gmail SMTP host address

                "mail.smtp.port", "587" // Specifies the SMTP port for TLS
        ));

        var session = Session.getInstance(props, new Authenticator() { // Creates a mail session with authentication logic
            @Override
            protected PasswordAuthentication getPasswordAuthentication() { // Defines how credentials are retrieved

                return new PasswordAuthentication(

                        config.getProperty("email.username").trim(), // Retrieves and cleans the username from config

                        config.getProperty("email.password").trim() // Retrieves and cleans the password from config

                ); // Ends credential object

            } // Ends authentication method

        }); // Ends session instance

        try {
            var message = new MimeMessage(session); // Creates a new MIME message linked to the current session

            // Define HTML content
            String htmlContent = """
                    <!DOCTYPE html>
                           <html lang="en">
                           <head>
                               <meta charset="UTF-8">
                               <style>
                                   body { font-family: Arial, sans-serif; line-height: 1.6; color: #333333; }
                                   .container { padding: 20px; border: 1px solid #e0e0e0; border-radius: 8px; max-width: 600px; }
                                   .header { font-weight: bold; color: #2c3e50; margin-bottom: 20px; }
                                   .content { margin-bottom: 20px; }
                                   ul { margin: 10px 0; padding-left: 20px; }
                                   li { margin-bottom: 5px; }
                                   .footer { font-size: 0.9em; color: #777; margin-top: 30px; }
                               </style>
                           </head>
                           <body>
                               <div class="container">
                                   <div class="content">
                                       <p>Hello Mr. Gopal,</p>
                                      <p>I hope this email finds you well.</p>
                                      <p>I’m reaching out to get a quick pulse check on the community election process. We’ve had quite a few neighbors asking for an update, as the current timeline has shifted a bit from what was originally anticipated.</p>
                                      <p>Could you provide some clarity on where things stand? We are trying to align our community planning for the coming months, so knowing the current stage and a rough estimate for the remaining steps would be a huge help.</p>
                                      <p>Thank you for your time and for your ongoing efforts to ensure a fair and organized process. We look forward to hearing from you at your earliest convenience.</p>
                                   </div>
                    
                                   <div class="footer">
                                       <p>Thanks & Regards,<br>
                                       Sandeep Chatterjee<br>
                                       [Residing Owner | Tower 12, Flat 705 | Janaadhar Shubha Phase II]</p>
                                   </div>
                               </div>
                           </body>
                           </html>
                    """;

            message.setFrom(new InternetAddress(config.getProperty("email.username"), "My Awesome App")); // Sets sender details

            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("chatterjee.sandeep@outlook.com")); // Sets the recipient address

            //message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("neighbor1@example.com, neighbor2@example.com")); // Add CC (Carbon Copy)

            //message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse("sndpchatterjee07@gmail.com")); // Add BCC (Blind Carbon Copy)

            message.setSubject("Inquiry Regarding Election Timeline – Janaadhar Shubha Phase 2"); // Sets the subject line of the email

            message.setContent(htmlContent, "text/html; charset=utf-8"); // Set the content type to text/html

            Transport.send(message); // Transmits the email to the SMTP server

            System.out.println("Email sent successfully."); // Prints a success confirmation to the console

        } catch (Exception ex) {

            throw new RuntimeException("Failed to send email", ex); // Wraps and re-throws the exception to indicate failure
        }
    }
}