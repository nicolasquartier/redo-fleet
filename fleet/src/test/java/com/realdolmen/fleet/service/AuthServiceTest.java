// TODO: Class is untestable because it all roots back to SecurityContextHolder to get the context's state
// unable to mock this class hence...

//package com.realdolmen.fleet.service;
//
//import com.realdolmen.fleet.domain.User;
//import com.realdolmen.fleet.repository.UserRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AuthServiceTest {
//
//    @Mock
//    private User user;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private SecurityContext securityContext;
//
//    @Mock
//    private Authentication authentication;
//
//    @InjectMocks
//    private AuthService authService = new AuthService();
//
//    private static final String EXPECTED_USER_NAME = "user";
//
//    @Before
//    public void init() {
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        when(authentication.getName()).thenReturn(EXPECTED_USER_NAME);
//
//        when(userRepository.findByUsername(EXPECTED_USER_NAME)).thenReturn(user);
//    }
//
//    @Test
//    public void getCurrentUserGetsTheUserFromRepository() {
//
//        User result = authService.getCurrentUser();
//        assertSame(user, result);
//
//        verify(userRepository, times(1)).findByUsername(EXPECTED_USER_NAME);
//        verifyNoMoreInteractions(userRepository.findByUsername(EXPECTED_USER_NAME));
//    }
//
//
//    @Test
//    public void getNameGetsTheUsernameFromTheContext() {
//        String result = authService.getName();
//        assertEquals(EXPECTED_USER_NAME, result);
//
//        verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    public void getAuthenticationGetsTheAuthenticationObjectOfTheContext() {
//        Authentication result = authService.getAuthentication();
//        assertSame(authentication, result);
//
//        verify(securityContext, times(1)).getAuthentication();
//        verifyNoMoreInteractions(securityContext);
//        verifyNoMoreInteractions(userRepository);
//    }
//
//
//}