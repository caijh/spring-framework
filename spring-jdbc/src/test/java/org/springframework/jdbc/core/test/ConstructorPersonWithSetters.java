/*
 * Copyright 2002-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.jdbc.core.test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Juergen Hoeller
 */
public class ConstructorPersonWithSetters {

	private String name;

	private long age;

	private Date birthDate;

	private BigDecimal balance;


	public ConstructorPersonWithSetters(String name, long age, BigDecimal balance) {
		this.name = name.toUpperCase();
		this.age = age;
		this.balance = balance;
	}


	public void setName(String name) {
		throw new UnsupportedOperationException();
	}

	public void setAge(long age) {
		throw new UnsupportedOperationException();
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public void setBalance(BigDecimal balance) {
		throw new UnsupportedOperationException();
	}

	public String name() {
		return this.name;
	}

	public long age() {
		return this.age;
	}

	public Date birthDate() {
		return this.birthDate;
	}

	public BigDecimal balance() {
		return this.balance;
	}

}
