package spebooking2.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.*;
import java.util.*;
@Controller
public class aidan {

	public static void main(String[] args) {
		SpringApplication.run(aidan.class, args);
	}

  @GetMapping("/")
  public String get(Model x){
    String index = "index";
    x.addAttribute("time", new Date());
    return index;
  }
}
