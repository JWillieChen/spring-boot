package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 创建包时操作错误，创建了个controller的包，本来应该是com.willie.controller的
 * 导出出现以下错误
 * There was an unexpected error (type=Not Found, status=404).
 * No message available
 * 百度结果：
 * application.java文件的包必须是项目下的父路径，其他类的包路径必须是其子路径，即应该com.willie.controller才对
 * 如果不在相同包，需要在application.java增加注解@ComponentScan去扫描其他的包,但是注解后，会导致默认规则失效
 */
@RestController
public class HomeController {

	@RequestMapping("/")
	public String index() {
		return "Hello Restful Api";
	}
}
