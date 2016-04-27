package com.realdolmen.fleet.controller.admin;

import com.realdolmen.fleet.domain.CompanyCar;
import com.realdolmen.fleet.domain.UserCarHistory;
import com.realdolmen.fleet.repository.UserCarHistoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration(value = "resources/templates/")
public class AdminCompanyCarRequestControllerMockTest {

    private MockMvc mvc;


    @Mock
    private UserCarHistory userCarHistoryOne, userCarHistoryTwo, userCarHistoryThree, userCarHistoryFour;

    @Mock
    private CompanyCar companyCar;

    @Mock
    private UserCarHistoryRepository userCarHistoryRepository;

    @InjectMocks
    private AdminRequestController adminRequestControllers;

    @Before
    public void init() {
        mvc = MockMvcBuilders
                .standaloneSetup(adminRequestControllers)
                .build();

        when(userCarHistoryRepository.findAllByCompanyCarApprovedFalse()).thenReturn(listOf4NonApproveCompanyCars());
        when(userCarHistoryRepository.findAllByCompanyCarApprovedTrue()).thenReturn(listOf4NonApproveCompanyCars());
        when(userCarHistoryRepository.findOne(anyLong())).thenReturn(userCarHistoryOne);
        when(userCarHistoryOne.getCompanyCar()).thenReturn(companyCar);
    }

    @Test
    public void requestsAreAvailableOnView() throws Exception {

        listOf4NonApproveCompanyCars();


        mvc.perform(get("/admin/requests"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/requests"))
                .andExpect(model().attributeExists("userCarHistories"))
                .andExpect(model().attribute("userCarHistories", hasSize(4)))
        ;
    }

    @Test
    public void approveRequestShouldApproveUserCarHistory() throws Exception {
        mvc.perform(get("/admin/requests/approve/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/requests"))
                .andExpect(model().attributeExists("userCarHistories"))
                .andExpect(model().attribute("userCarHistories", hasSize(4)))
        ;
        verify(userCarHistoryRepository, times(1)).findOne(anyLong());
        verify(userCarHistoryRepository, times(1)).save(userCarHistoryOne);
        verify(userCarHistoryRepository, times(1)).findAllByCompanyCarApprovedFalse();
    }

    @Test
    public void declineRequestShouldDeleteUserCarHistory() throws Exception {
        mvc.perform(get("/admin/requests/decline/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/requests"))
                .andExpect(model().attributeExists("userCarHistories"))
                .andExpect(model().attribute("userCarHistories", hasSize(4)))
        ;
        verify(userCarHistoryRepository, times(1)).delete(anyLong());
        verify(userCarHistoryRepository, times(1)).findAllByCompanyCarApprovedFalse();
    }

    @Test
    public void companyCarsAreAvailableOnView() throws Exception {

        listOf4NonApproveCompanyCars();

        mvc.perform(get("/admin/companyCars"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/companyCars"))
                .andExpect(model().attributeExists("userCarHistories"))
                .andExpect(model().attribute("userCarHistories", hasSize(4)))
        ;
    }

    private List<UserCarHistory> listOf4NonApproveCompanyCars() {
        return Arrays.asList(userCarHistoryOne, userCarHistoryTwo, userCarHistoryThree, userCarHistoryFour);
    }
}
