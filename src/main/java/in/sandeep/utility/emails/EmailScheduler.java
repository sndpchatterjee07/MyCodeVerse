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

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The type EmailScheduler
 *
 * @author Sandeep
 * @version 1.0
 */
public class EmailScheduler { // Begins the declaration of the EmailScheduler class

    ScheduledExecutorService scheduler; // Keep a reference to the scheduler so we can stop it later

    public void startScheduling() { // Begins the method to initialize the background task scheduler

        scheduler = Executors.newSingleThreadScheduledExecutor(); // Creates a single-threaded executor to run tasks periodically

        scheduler.scheduleAtFixedRate(() -> { // Starts the scheduler to execute the task at a fixed interval

            try { // Begins the block to handle email operations safely

                EmailSender sender = new EmailSender(); // Instantiates the EmailSender class

                sender.sendEmail(); // Calls the method to perform the email sending logic

            } catch (Throwable throwable) { // Catches any unexpected errors during the email sending process

                System.err.println("Task failed: " + throwable.getMessage()); // Prints the error details to the standard error stream

            } // Closes the try-catch block

        }, 0, 5, TimeUnit.MINUTES); // Configures the schedule: start immediately, then run every 5 minutes

    } // Closes the startScheduling method

    public void stopScheduling() { // Begins the method to gracefully shut down the scheduler

        if (scheduler != null) { // Checks if the scheduler instance exists before attempting to stop it

            scheduler.shutdown(); // Initiates an orderly shutdown to stop accepting new tasks

            try { // Starts a try block to handle potential interruption during the wait period

                if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) { // Waits up to 60 seconds for currently running tasks to finish

                    scheduler.shutdownNow(); // Forcefully cancels all running tasks if the timeout is reached

                } // Closes the conditional check for task termination

            } catch (InterruptedException e) { // Catches an exception if the thread is interrupted while waiting

                scheduler.shutdownNow(); // Cancels all tasks immediately if the shutdown sequence is interrupted

            } // Closes the catch block

        } // Closes the null-check block

    } // Closes the stopScheduling method

} // Closes the EmailScheduler class
