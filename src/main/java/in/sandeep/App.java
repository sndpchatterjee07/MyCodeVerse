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

import in.sandeep.utility.EmailScheduler;

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

            // Create an instance of the scheduler
            EmailScheduler emailScheduler = new EmailScheduler();

            // Start the background process
            emailScheduler.startScheduling();

            System.out.println("Scheduler started. Press Ctrl+C to stop.");

            // Keep the main thread alive so the scheduler doesn't get killed. This waits indefinitely
            Thread.currentThread().join();

            // Register a JVM shutdown hook to clean up resources when the app stops
            Runtime.getRuntime().addShutdownHook(new Thread(emailScheduler::stopScheduling));

        } catch (InterruptedException e) {

            System.err.println("Main thread interrupted.");

        }catch (Exception exception) {

            // System.out.println("INSIDE EXCEPTION BLOCK IN APP.JAVA");

            System.err.println("Error sending email: " + exception.getMessage());

        } finally {

            System.out.println("*** EXITING APPLICATION ***");
        }
    }
}
