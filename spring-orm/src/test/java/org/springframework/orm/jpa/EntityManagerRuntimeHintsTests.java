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

package org.springframework.orm.jpa;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.predicate.RuntimeHintsPredicates;
import org.springframework.beans.factory.aot.AotServices;
import org.springframework.util.ClassUtils;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link EntityManagerRuntimeHints}.
 *
 * @author Sebastien Deleuze
 */
class EntityManagerRuntimeHintsTests {

	private final RuntimeHints hints = new RuntimeHints();

	@BeforeEach
	void setup() {
		AotServices.factories().load(RuntimeHintsRegistrar.class)
				.forEach(registrar -> registrar.registerHints(this.hints,
						ClassUtils.getDefaultClassLoader()));
	}

	@Test
	void entityManagerFactoryInfoHasHibernateHints() {
		assertThat(RuntimeHintsPredicates.proxies().forInterfaces(SessionFactory.class, EntityManagerFactoryInfo.class))
				.accepts(this.hints);
	}

	@Test
	void entityManagerProxyHasHibernateHints() {
		assertThat(RuntimeHintsPredicates.proxies().forInterfaces(Session.class, EntityManagerProxy.class))
				.accepts(this.hints);
	}

	@Test
	void entityManagerFactoryHasReflectionHints() {
		assertThat(RuntimeHintsPredicates.reflection().onMethodInvocation(EntityManagerFactory.class, "getCriteriaBuilder")).accepts(this.hints);
		assertThat(RuntimeHintsPredicates.reflection().onMethodInvocation(EntityManagerFactory.class, "getMetamodel")).accepts(this.hints);
	}
}
