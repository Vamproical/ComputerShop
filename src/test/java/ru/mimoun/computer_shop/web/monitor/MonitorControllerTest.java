package ru.mimoun.computer_shop.web.monitor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.computer_shop.model.Monitor;
import ru.mimoun.computer_shop.repository.MonitorRepository;
import ru.mimoun.computer_shop.to.MonitorTo;
import ru.mimoun.computer_shop.util.JsonUtil;
import ru.mimoun.computer_shop.web.AbstractControllerTest;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.computer_shop.web.monitor.MonitorController.REST_URL;
import static ru.mimoun.computer_shop.web.monitor.MonitorTestData.*;

class MonitorControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private MonitorRepository monitorRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + MONITOR_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MONITOR_MATCHER.contentJson(monitor1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        MonitorTo updated = getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + MONITOR_1_ID)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        MONITOR_MATCHER.assertMatch(monitorRepository.getExisted(MONITOR_1_ID), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        MonitorTo newComputer = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(newComputer)))
                .andExpect(status().isCreated());

        Monitor created = MONITOR_MATCHER.readFromJson(action);
        UUID newId = created.id();
        newComputer.setId(newId);
        MONITOR_MATCHER.assertMatch(monitorRepository.getExisted(newId), created);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MONITOR_MATCHER.contentJson(monitor1, monitor2));
    }
}