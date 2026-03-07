/*
 *
 *  *     Copyright 2026 Sandeep Chatterjee @ https://sndpchatterjee07.github.io//
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *          http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package in.sandeep;

import in.sandeep.utility.EmailSender;

/**
 * The type App.
 *
 * @author sandeep
 * @version 1.0
 */
public class App {
    /**
     * The entry point of application.
     *
     */
    static void main() {

        System.out.println("*** LAUNCHING APPLICATION *** ");

        try {

            EmailSender sender = new EmailSender();

            sender.sendEmail();

            System.out.println("EMAIL SENT SUCCESSFULLY...");

        } catch (Exception exception) {

            // System.out.println("INSIDE EXCEPTION BLOCK IN APP.JAVA");

            System.err.println("Error sending email: " + exception.getMessage());

        } finally {

            System.out.println("*** EXITING APPLICATION ***");
        }
    }
}
