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

package in.sandeep.utility.emails;

import jakarta.mail.Transport;
import org.junit.Test;
import org.mockito.MockedStatic;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;

public class EmailSenderTest {

    @Test
    public void testConstructor_LoadsPropertiesSuccessfully() {
        // Verifies the object is created and properties are loaded
        EmailSender sender = new EmailSender();
        assertNotNull(sender);
    }

    @Test
    public void testSendEmail_Success() {
        // Mocking the static Transport.send method to prevent real network calls
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            EmailSender sender = new EmailSender();

            sender.sendEmail();

            // Verifies that Transport.send was invoked exactly once
            mockedTransport.verify(() -> Transport.send(any()), times(1));
        }
    }

    @Test(expected = RuntimeException.class)
    public void testSendEmail_ThrowsExceptionOnFailure() {
        // Force the static method to throw an error to test exception handling
        try (MockedStatic<Transport> mockedTransport = mockStatic(Transport.class)) {
            mockedTransport.when(() -> Transport.send(any())).thenThrow(new RuntimeException("SMTP Error"));

            EmailSender sender = new EmailSender();
            sender.sendEmail(); // This should trigger the RuntimeException
        }
    }
}