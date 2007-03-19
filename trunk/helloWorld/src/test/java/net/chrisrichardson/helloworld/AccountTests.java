/*
 * Copyright (c) 2005 Chris Richardson
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 


package net.chrisrichardson.helloworld;

import net.chrisrichardson.helloworld.Account;
import junit.framework.*;

public class AccountTests extends TestCase {

    private Account account;

    public void setUp() {
        account = new Account(10.0);
    }

    public void test_normal() {
        assertEquals(10.0, account.getBalance(), 0.0);
        account.debit(5);
        assertEquals(5.0, account.getBalance(), 0.0);
        account.credit(10);
        assertEquals(15.0, account.getBalance(), 0.0);
        account.debit(15);
        assertEquals(0, account.getBalance(), 0.0);
    }

    
}
