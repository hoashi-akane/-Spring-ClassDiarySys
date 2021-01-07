package com.madder.diary1801182.Controller;

import com.madder.diary1801182.Dto.DiaryDto;
import com.madder.diary1801182.Dto.LoginInfoDto;
import com.madder.diary1801182.Service.DiaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(DiaryController.class)
@DisplayName("Diaryコントローラテスト")
@ExtendWith(SpringExtension.class)
class DiaryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DiaryService diaryService;

    @SpyBean
    DiaryController diaryController;

    @BeforeEach
    void 前準備(){
    }

    @Nested
    class 日誌表示系{

        List<DiaryDto> diaryDtoList;
        LoginInfoDto loginInfoDto;

        @BeforeEach
        void 日誌表示系_前準備(){
            loginInfoDto = LoginInfoDto.builder()
                    .classCode("1")
                    .build();

        }

        @Test
        void 日誌一覧が存在しない場合() throws Exception{
            when(diaryService.getDiaryList(any())).thenReturn(diaryDtoList);

            mockMvc.perform(get("/DispDiaryList")
                    .sessionAttr("loginInfoDto", loginInfoDto))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeExists("msg"));
        }

        @Test
        void 日誌一覧が存在する場合() throws Exception{
            diaryDtoList = List.of(DiaryDto.builder()
                    .build());

            when(diaryService.getDiaryList(any())).thenReturn(diaryDtoList);

            mockMvc.perform(get("/DispDiaryList")
                    .sessionAttr("loginInfoDto", loginInfoDto))
                    .andExpect(status().isOk())
                    .andExpect(model().attributeDoesNotExist("msg"));
        }
    }

    @Test
    void inputTodayDiary() {
    }

    @Test
    void insertTodayDiary() {
    }

    @Test
    void inputOldDiary() {
    }

    @Test
    void delDiary() {
    }

    @Test
    void revisionDiary() {
    }

    @Test
    void inputRevisionDiary() {
    }
}