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

import in.sandeep.utility.emails.EmailScheduler;

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
    static void main(String[] args) {

        System.out.println("*** LAUNCHING APPLICATION *** ");

        try {

            EmailScheduler emailScheduler = new EmailScheduler(); // Create an instance of the scheduler

            emailScheduler.startScheduling(); // Start the background process

            System.out.println("SCHEDULER STARTED........");

            Thread.currentThread().join(); // Keep the main thread alive so the scheduler doesn't get killed. This waits indefinitely

            Runtime.getRuntime().addShutdownHook(new Thread(emailScheduler::stopScheduling)); // Register a JVM shutdown hook to clean up resources when the app stops

        } catch (InterruptedException e) {

            System.err.println("Main thread interrupted.");

        }catch (Exception exception) {

            System.err.println("Error sending email: " + exception.getMessage());

        } finally {

            System.out.println("*** EXITING APPLICATION ***");
        }
    }
}
