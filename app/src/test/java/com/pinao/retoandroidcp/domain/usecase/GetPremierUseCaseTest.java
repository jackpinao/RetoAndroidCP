package com.pinao.retoandroidcp.domain.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.pinao.retoandroidcp.data.repository.PremierRepository;
import com.pinao.retoandroidcp.domain.model.PremierItems;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetPremierUseCaseTest {

    @Mock
    private PremierRepository mockPremierRepository;

    private GetPremierUseCase getPremierUseCase;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getPremierUseCase = new GetPremierUseCase(mockPremierRepository);
    }

    @Test
    public void invoke_returnsNonEmptyListFromApi() throws Exception {
        List<PremierItems> expectedItems = Arrays.asList(new PremierItems("Image1", "Description1", "Title1"), new PremierItems("Image2", "Description2", "Title2"));
        when(mockPremierRepository.getPremiersFromApi()).thenReturn(expectedItems);
        when(mockPremierRepository.getAllPremierFromDatabase()).thenReturn(Collections.emptyList());

        List<PremierItems> result = getPremierUseCase.invoke();

        assertEquals(expectedItems, result);
    }

    @Test
    public void invoke_returnsEmptyListWhenApiAndDatabaseAreEmpty() throws Exception {
        when(mockPremierRepository.getPremiersFromApi()).thenReturn(Collections.emptyList());
        when(mockPremierRepository.getAllPremierFromDatabase()).thenReturn(Collections.emptyList());

        List<PremierItems> result = getPremierUseCase.invoke();

        assertEquals(Collections.emptyList(), result);
    }

    @Test(expected = Exception.class)
    public void invoke_throwsExceptionWhenApiFails() throws Exception {
        when(mockPremierRepository.getPremiersFromApi()).thenThrow(new RuntimeException("API failure"));

        getPremierUseCase.invoke();
    }

    @Test
    public void invoke_returnsListFromDatabaseWhenApiReturnsEmptyList() throws Exception {
        List<PremierItems> expectedItems = Arrays.asList(new PremierItems("ImageDB1", "DescriptionDB1", "TitleDB1"));
        when(mockPremierRepository.getPremiersFromApi()).thenReturn(Collections.emptyList());
        when(mockPremierRepository.getAllPremierFromDatabase()).thenReturn(expectedItems);

        List<PremierItems> result = getPremierUseCase.invoke();

        assertEquals(expectedItems, result);
    }

}