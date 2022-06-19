package mashup.spring.jsmr.advice;

import mashup.spring.jsmr.adapter.api.AdviceController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AdviceController.class)
public class AdviceControllerTest {

    @Autowired
    MockMvc mvc;

    @Disabled
    @DisplayName("HTTP Method 잘못 요청했을 때 405")
    @Test
    void methodNotSupportException() throws Exception {
        ResultActions actions = mvc.perform(
                post("/server-error")
        );

        actions
                .andExpect(status().isMethodNotAllowed());
    }

    @Disabled
    @DisplayName("예상치 못한 에러 500")
    @Test
    void serverException() throws Exception {
        // when
        ResultActions actions = mvc.perform(
                get("/server-error")
        );

        actions
                .andExpect(status().is5xxServerError());
    }

}
