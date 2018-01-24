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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

import issue.tracker.Application;
import issue.tracker.domain.Issue;
import issue.tracker.service.IssueService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	private IssueService issueService;

	@Test
	public void testAdd() {
		Issue issue = new Issue();
		issue.setDescription("dfh");
		issue.setTitle("dfh");
		Issue issue2 = issueService.saveIssue(issue);
		assertNotNull(issue2);
		assertEquals(issue2.getDescription(), issue.getDescription());
	}

	@Test
	public void testGet() {
		Issue issue = new Issue();
		issue.setDescription("dfh");
		issue.setTitle("dfh");
		Issue issue2 = issueService.saveIssue(issue);
		assertEquals(issue2.getDescription(), issueService.findById(issue2.getId()).getDescription());
	}

	@Test
	public void testDelete() {
		Issue issue = new Issue();
		issue.setDescription("dfh");
		issue.setTitle("dfh");
		Issue issue2 = issueService.saveIssue(issue);
		issueService.deleteIssue(issue2.getId());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public void testDeleteNotFound() {
		issueService.deleteIssue(Long.valueOf(345));
	}

	@Test
	public void testFilter() {
		Issue issue = new Issue();
		issue.setDescription("dfha");
		issue.setTitle("dfha");
		issueService.saveIssue(issue);

		Issue issue2 = new Issue();
		issue2.setDescription("dfha2");
		issue2.setTitle("dfh2a");
		issueService.saveIssue(issue2);

		Issue issue3 = new Issue();
		issue3.setDescription("dfh3");
		issue3.setTitle("dfh3");
		issueService.saveIssue(issue3);

		assertEquals(2, issueService.findByTitleOrDescription("a").size());
	}

}
