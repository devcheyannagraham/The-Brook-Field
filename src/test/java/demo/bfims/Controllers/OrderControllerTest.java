package demo.bfims.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = OrderController.class)
class OrderControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    OrderService orderService;


    @Autowired
    public OrderControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void newOrder() throws Exception {
        Mockito.when(orderService.newOrder(Mockito.any(OrderDto.class))).thenReturn(new OrderDto());
        mockMvc.perform(post("/api/order")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(new OrderDto())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

    }
}