package com.pinao.retoandroidcp.ui.home;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.domain.usecase.GetPremierUseCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.TestScheduler;

public class HomeViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private GetPremierUseCase getPremierUseCase;

    @Mock
    private Observer<String> imageObserver;

    @Mock
    private Observer<String> descriptionObserver;

    private HomeViewModel viewModel;
    private TestScheduler testScheduler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testScheduler = new TestScheduler();
        viewModel = new HomeViewModel(getPremierUseCase);
        viewModel.getImage().observeForever(imageObserver);
        viewModel.getDescription().observeForever(descriptionObserver);
    }

    @Test
    public void onCreate_successfullyUpdatesLiveData() throws Exception {
        List<PremierItems> premierItems = new ArrayList<>();
        premierItems.add(new PremierItems("Test Image", "Test Description", "Test Title"));
        //when(getPremierUseCase.invoke()).thenReturn(Single.just(premierItems));
        doReturn(Single.just(premierItems)).when(getPremierUseCase).invoke();

        viewModel.onCreate();
        testScheduler.triggerActions();

        verify(imageObserver).onChanged("Test Image");
        verify(descriptionObserver).onChanged("Test Description");
    }

    @Test
    public void onCreate_handlesEmptyList() throws Exception {
        //when(getPremierUseCase.invoke()).thenReturn(Single.just(new ArrayList<>()));
        doReturn(Single.just(new ArrayList<>())).when(getPremierUseCase).invoke();

        viewModel.onCreate();
        testScheduler.triggerActions();

        verify(imageObserver).onChanged(null);
        verify(descriptionObserver).onChanged(null);
    }

    @Test
    public void onCreate_handlesError() throws Exception {
        //when(getPremierUseCase.invoke()).thenReturn(Single.error(new RuntimeException("Test Error")));
        doReturn(Single.error(new RuntimeException("Test Error"))).when(getPremierUseCase).invoke();

        viewModel.onCreate();
        testScheduler.triggerActions();

        // Assuming logError logs an error message, but since it's a void method, we can't verify its internal behavior.
        // This test ensures that the app doesn't crash when an error occurs.
    }
}