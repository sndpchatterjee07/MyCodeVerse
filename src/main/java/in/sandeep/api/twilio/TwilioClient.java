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

package in.sandeep.api.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

/**
 * The type TwilioClient.
 *
 * @author sandeep
 * @version 1.0
 * @see @link https://console.twilio.com/develop/explore
 */
public class TwilioClient {

    private static final String ACCOUNT_SID = "AC7f8b9362820a0dc30c36fe1301b47147";

    private static final String AUTH_TOKEN = "1a5ffaeabbea46b795037503bab73cf5";

    private static final String TWILIO_PHONE_NUMBER = "+1 858 386 7573";

    /**
     * Send Message.
     */
    public void sendMessage() {
        Twilio.init (ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator (
                        new com.twilio.type.PhoneNumber ("+918884086073"), // NEED TO Upgrade TO SEND SMS TO ANYONE.
                        new com.twilio.type.PhoneNumber (TWILIO_PHONE_NUMBER),
                        "Twilio says hi....")
                .create ();

        System.out.println ("MESSAGE SENT");
    }
}
