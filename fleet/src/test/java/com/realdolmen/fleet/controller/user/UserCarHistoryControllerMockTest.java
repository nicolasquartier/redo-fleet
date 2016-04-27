package com.realdolmen.fleet.controller.user;

import com.realdolmen.fleet.domain.User;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import com.realdolmen.fleet.service.impl.AuthServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class UserCarHistoryControllerMockTest {

    private MockMvc mvc;

    @Mock
    private UserCarHistoryRepository userCarHistoryRepository;

    @Mock
    private UserCarHistory history, historyTwo, HistoryThree;

    @Mock
    private User user;

    @InjectMocks
    private UserCarHistoryController userCarHistoryController/* = new UserCarHistoryController()*/;

    @Mock
    private Date date;

    @Mock
    private AuthServiceImpl authService;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .standaloneSetup(userCarHistoryController)
                .build();

        when(authService.getCurrentUser()).thenReturn(user);
}

    @Test
    public void userCarHistoryIsOnView() throws Exception {
        when(userCarHistoryRepository.findByUserAndEndDateAfter(isA(User.class), isA(LocalDate.class))).thenReturn(history);
        when(userCarHistoryRepository.findByUserAndEndDateBefore(isA(User.class), isA(LocalDate.class))).thenReturn(Arrays.asList(historyTwo, HistoryThree));


        mvc.perform(get("/user/car"))
                .andExpect(view().name("user/car"))
                .andExpect(model().attributeExists("carHistoryObj"))
        ;

        mvc.perform(get("/user/car"))
                .andExpect(view().name("user/car"))
                .andExpect(model().attributeExists("userOlderCarHistory"))
                .andExpect(model().attribute("userOlderCarHistory", hasSize(2)))
        ;
    }

    @Test
    public void noCarHistoryFoundPutsErrorOnTheModel() throws Exception {
        when(userCarHistoryRepository.findByUserAndEndDateAfter(isA(User.class), isA(LocalDate.class))).thenReturn(null);
        when(userCarHistoryRepository.findByUserAndEndDateBefore(isA(User.class), isA(LocalDate.class))).thenReturn(null);

        mvc.perform(get("/user/car"))
                .andExpect(view().name("user/car"))
                .andExpect(model().attributeDoesNotExist("userOlderCarHistory"))
        ;

        mvc.perform(get("/user/car"))
                .andExpect(view().name("user/car"))
                .andExpect(model().attributeExists("noCarFoundError"))
                .andExpect(model().attribute("noCarFoundError", is("There are no cars linked to this user.")))
        ;

    }

}