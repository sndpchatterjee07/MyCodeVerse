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

import jakarta.mail.Transport;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class EmailSenderTest {

    @Test(expected = RuntimeException.class)
    public void testEmailSender_ThrowsExceptionWhenConfigMissing() {
        // This assumes application.properties is NOT in the test classpath
        new EmailSender();
    }

    @Test
    public void testSendEmail_Success() {
        // We use mockStatic to intercept the static call to Transport.send
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            // Note: This test requires a valid application.properties
            // in your src/test/resources folder for the constructor to pass
            EmailSender sender = new EmailSender();

            sender.sendEmail();

            // Verify that transport was called once
            mockedTransport.verify(() -> Transport.send(any()), times(1));
        }
    }
}