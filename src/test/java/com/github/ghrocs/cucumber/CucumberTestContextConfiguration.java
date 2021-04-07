package com.github.ghrocs.cucumber;

import com.github.ghrocs.JhApp;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = JhApp.class)
@WebAppConfiguration
public class CucumberTestContextConfiguration {}
