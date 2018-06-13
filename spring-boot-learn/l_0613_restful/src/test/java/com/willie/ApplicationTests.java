package com.willie;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	/**
	 * 1、get查一下user列表，应该为空
	 */
	@Test
	public void test000GetList() throws Exception {
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity("/users", String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("[]");
	}
	
	/**
	 * 2、post提交一个user
	 */
	@Test
	public void test001PostUser() throws Exception{
		MultiValueMap<String,String> paraMap= new LinkedMultiValueMap<>();
		paraMap.add("id", "1");
		paraMap.add("name", "Willie");
		paraMap.add("age", "81");
		ResponseEntity<String> entity =this.testRestTemplate.postForEntity("/users", paraMap, String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("success");
	}
	
	
	
	/**
	 * 4、put修改id为1的user
	 */
	@Test
	public void test003UpdateUser() throws Exception{
		MultiValueMap<String,String> headers= new LinkedMultiValueMap<>();
		MultiValueMap<String,String> paraMap= new LinkedMultiValueMap<>();
		//paraMap.add("id", "1");
		paraMap.add("name", "Willie");
		paraMap.add("age", "18");
		HttpEntity formEntity=new HttpEntity(paraMap,headers);
		ResponseEntity<String> entity =this.testRestTemplate.exchange("/users/1", HttpMethod.PUT,formEntity,String.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).isEqualTo("success");
	}
	
	
	
//  	测试UserController
//		RequestBuilder request = null;
//
//		// 1、get查一下user列表，应该为空
//		request = get("/users/");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[]")));
//
//		// 2、post提交一个user
//		request = post("/users/")
//				.param("id", "1")
//				.param("name", "测试大师")
//				.param("age", "20");
//		mvc.perform(request)
////				.andDo(MockMvcResultHandlers.print())
//				.andExpect(content().string(equalTo("success")));
//
//		// 3、get获取user列表，应该有刚才插入的数据
//		request = get("/users/");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));
//
//		// 4、put修改id为1的user
//		request = put("/users/1")
//				.param("name", "测试终极大师")
//				.param("age", "30");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("success")));
//
//		// 5、get一个id为1的user
//		request = get("/users/1");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("{\"id\":1,\"name\":\"测试终极大师\",\"age\":30}")));
//
//		// 6、del删除id为1的user
//		request = delete("/users/1");
//		mvc.perform(request)
//				.andExpect(content().string(equalTo("success")));
//
//		// 7、get查一下user列表，应该为空
//		request = get("/users/");
//		mvc.perform(request)
//				.andExpect(status().isOk())
//				.andExpect(content().string(equalTo("[]")));


}
