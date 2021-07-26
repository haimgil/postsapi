package com.steps.postsapi;

import com.steps.postsapi.persistence.Post;
import com.steps.postsapi.persistence.repositories.PostRepository;
import com.steps.postsapi.services.domain.IdsService;
import com.steps.postsapi.services.domain.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PostsapiApplicationTests {

	private static final IdsService idsService = new IdsService();

	@Test
	void whenGenerateId_postPopulatedWithUniqueId(){
		Post post = new Post("post title", "post body");

		assert post.getId() == null;

		idsService.generateUniqueId(post);

		assert post.getId() != null;
	}

	@Test
	void whenConvertCommaSeparatedIdsListTo_correctItemsInList(){
		String commaSeparatedIds = "1234567,7654321,1237654";
		List<Long> idsList = IdsService.commaSeparatedToList(commaSeparatedIds);

		assert idsList.contains(1234567L);
		assert idsList.contains(7654321L);
		assert idsList.contains(1237654L);
	}

}
