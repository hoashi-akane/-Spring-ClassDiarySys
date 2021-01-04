package com.madder.diary1801182.Controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



@WebMvcTest(MenuController.class)
@DisplayName("メニューコントローラテスト")
@ExtendWith(SpringExtension.class)
class MenuControllerTest {

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp(){

    }

    @Test
    void Menuへアクセス可能か() throws Exception {
        mockMvc.perform(get("/Menu"))
                .andExpect(status().isOk());
    }

}