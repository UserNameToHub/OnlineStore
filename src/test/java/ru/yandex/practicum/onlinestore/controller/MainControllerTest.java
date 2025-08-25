package ru.yandex.practicum.onlinestore.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.yandex.practicum.onlinestore.config.AppConfig;
import ru.yandex.practicum.onlinestore.entity.Item;
import ru.yandex.practicum.onlinestore.enumiration.SortConstant;
import ru.yandex.practicum.onlinestore.service.ItemService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MainController.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
class MainControllerTest {

//    @MockitoBean
//    private ItemService itemService;
//
//    @Autowired
//    private MockMvc mvc;
//
//    private static List<Item> items;
//
//    @BeforeAll
//    static void start() {
//        items = List.of(Item.builder().build(), Item.builder().build());
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void testGetWhenRedirect() throws Exception {
//        mvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(status().isPermanentRedirect());
//    }
//
//    @Test
//    void testGetWhenIsOk() throws Exception {
//
//        when(itemService.getAll(any(), any(), any(), any()))
//                .thenReturn(items);
//
//        mvc.perform(get("/main/items")
//                .param("search", "")
//                .param("sort", "NO")
//                .param("pageNumber", "1")
//                .param("pageSize", "5"))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_HTML))
//                .andExpect(view().name("main.html"))
//                .andExpect(model().attributeExists("items"))
//                .andExpect(xpath("//table/tbody/tr").nodeCount(2));
//
//
//        verify(itemService, times(1)).getAll("", SortConstant.NO, 1, 5);
//    }
}