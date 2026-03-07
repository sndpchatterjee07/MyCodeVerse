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

package in.sandeep.utility;

import org.junit.Test;


public class EmailSchedulerTest {

    /*
    @Test
    public void testStartScheduling_InitializesScheduler() {
        // We wrap the EmailScheduler in a spy to monitor its internal state
        EmailScheduler scheduler = spy(new EmailScheduler());

        // Start the scheduler
        scheduler.startScheduling();

        // Assert that the scheduler object is not null
        org.junit.Assert.assertNotNull(scheduler.scheduler);

        // Cleanup
        scheduler.stopScheduling();
    }*/

    @Test
    public void testStopScheduling_ShutsDownProperly() {
        EmailScheduler scheduler = new EmailScheduler();
        scheduler.startScheduling();

        // Act: Stop it
        scheduler.stopScheduling();

        // Assert: Check if it is shutdown
        org.junit.Assert.assertTrue(scheduler.scheduler.isShutdown());
    }
}