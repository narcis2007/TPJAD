/*
 * Copyright 2012-2015 the original author or authors.
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

package hello;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import issue.tracker.domain.Issue;
import issue.tracker.repository.IssueRepository;
import issue.tracker.service.IssueService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = TestContext.class)
//public class ApplicationTest {
//
//	@Mock
//	private IssueRepository issuerepo;
//	private IssueService issueService;
//
//	@Before
//	public void before() {
//		MockitoAnnotations.initMocks(this);
//
//		issueService.setIssueRepository(issuerepo);
//	}
//
//	@Test
//	public void testAdd() {
//
//		Issue issue = new Issue();
//		issue.setDescription("dfh");
//		issue.setTitle("dfh");
//		when(issuerepo.save(issue)).thenReturn(issue);
//		assertNotNull(issueService.saveIssue(issue));
//	}
//
//}
