package ru.mimoun.computer_shop.web.laptop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mimoun.computer_shop.model.Laptop;
import ru.mimoun.computer_shop.repository.LaptopRepository;
import ru.mimoun.computer_shop.to.LaptopTo;
import ru.mimoun.computer_shop.util.JsonUtil;
import ru.mimoun.computer_shop.web.AbstractControllerTest;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.mimoun.computer_shop.web.laptop.LaptopController.REST_URL;
import static ru.mimoun.computer_shop.web.laptop.LaptopTestData.*;

class LaptopControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private LaptopRepository laptopRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + LAPTOP_1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LAPTOP_MATCHER.contentJson(laptop1));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NOT_FOUND))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void update() throws Exception {
        LaptopTo updated = getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + LAPTOP_1_ID)
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        LAPTOP_MATCHER.assertMatch(laptopRepository.getExisted(LAPTOP_1_ID), getUpdated());
    }

    @Test
    void createWithLocation() throws Exception {
        LaptopTo newComputer = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                                                             .contentType(MediaType.APPLICATION_JSON)
                                                             .content(JsonUtil.writeValue(newComputer)))
                .andExpect(status().isCreated());

        Laptop created = LAPTOP_MATCHER.readFromJson(action);
        UUID newId = created.id();
        newComputer.setId(newId);
        LAPTOP_MATCHER.assertMatch(laptopRepository.getExisted(newId), created);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LAPTOP_MATCHER.contentJson(laptop1, laptop2));
    }
}