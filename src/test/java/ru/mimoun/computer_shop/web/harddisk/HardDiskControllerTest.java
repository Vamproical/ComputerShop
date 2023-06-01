package ru.mimoun.computer_shop.web.harddisk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.computer_shop.model.HardDisk;
import ru.mimoun.computer_shop.repository.HardDiskRepository;
import ru.mimoun.computer_shop.to.HardDiskTo;
import ru.mimoun.computer_shop.util.JsonUtil;
import ru.mimoun.computer_shop.web.AbstractControllerTest;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.computer_shop.web.harddisk.HardDiskController.REST_URL;
import static ru.mimoun.computer_shop.web.harddisk.HardDiskTestData.*;

class HardDiskControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private HardDiskRepository hardDiskRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + HARD_DISK_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(HARD_DISK_MATCHER.contentJson(hardDisk1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        HardDiskTo updated = getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + HARD_DISK_1_ID)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        HARD_DISK_MATCHER.assertMatch(hardDiskRepository.getExisted(HARD_DISK_1_ID), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        HardDiskTo newComputer = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(newComputer)))
                .andExpect(status().isCreated());

        HardDisk created = HARD_DISK_MATCHER.readFromJson(action);
        UUID newId = created.id();
        newComputer.setId(newId);
        HARD_DISK_MATCHER.assertMatch(hardDiskRepository.getExisted(newId), created);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(HARD_DISK_MATCHER.contentJson(hardDisk1, hardDisk2));
    }
}