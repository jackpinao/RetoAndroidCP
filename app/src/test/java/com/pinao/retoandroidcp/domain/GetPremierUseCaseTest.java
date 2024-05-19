package com.pinao.retoandroidcp.domain;

import static org.mockito.Mockito.*;

import com.pinao.retoandroidcp.domain.model.PremierItems;
import com.pinao.retoandroidcp.data.repository.PremierRepository;
import com.pinao.retoandroidcp.domain.usecase.GetPremierUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GetPremierUseCaseTest {
    //@Mock
    //@RelaxedMockK
    private PremierRepository premierRepository;
    private GetPremierUseCase getPremierUseCase;

    @Before
    public void onBefore(){
        MockitoAnnotations.initMocks(this);
        premierRepository = mock(PremierRepository.class);
        getPremierUseCase = new GetPremierUseCase(premierRepository);

    }

//    @Test
//    public void cuandoElApiNoRetornaNingunValorDeDatabase() throws ExecutionException, InterruptedException{
//        //Given
//        when(premierRepository.getAllPremierFromDatabase()).thenReturn((List<PremierItems>) CompletableFuture.completedFuture(Collections.emptyList()));
//        //When
//        CompletableFuture<List<PremierItems>> future = getPremierUseCase.invoke();
//        future.thenAccept(result ->{
//            //Then
//            try {
//                verify(premierRepository,times(1)).getAllPremierFromDatabase();
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.e("GetPremierUseCaseTest", Objects.requireNonNull(e.getMessage()));
//            }
//        });
//        future.get();
//        //Then
//    }

//    @Test
//    public void cuandoElApiRetornaValoresDeDatabase() throws Exception {
//        //Given
//        List<PremierItems> premierItems = Collections.singletonList(new PremierItems("TEST","TESTDESC","https://es.wikipedia.org/wiki/Anas_platyrhynchos_domesticus#/media/Archivo:Anas_platyrhynchos_qtl1.jpg"));
//        when(premierRepository.getAllPremierFromDatabase()).thenReturn((List<PremierItems>) CompletableFuture.completedFuture(premierItems));
//        //When
//        CompletableFuture<List<PremierItems>> future = (CompletableFuture<List<PremierItems>>) getPremierUseCase.invoke();
//        //Then
//        future.thenAccept(result ->{
//            verify(premierRepository,times(1)).clearPremiers();
//            verify(premierRepository,times(1)).insertPremiers(any());
//            verify(premierRepository,never()).getAllPremierFromDatabase();
//            assert result.equals(premierItems);
//        }).get();
//    }
    @Test
    public void cuandoElApiRetornaValoresDeDatabase() throws Exception {
        List<PremierItems> premierItems = Collections.singletonList(new PremierItems("TEST", "TESTDESC", "https://es.wikipedia.org/wiki/Anas_platyrhynchos_domesticus#/media/Archivo:Anas_platyrhynchos_qtl1.jpg"));
        when(premierRepository.getAllPremierFromDatabase()).thenReturn(premierItems);

        CompletableFuture<List<PremierItems>> future = (CompletableFuture<List<PremierItems>>) getPremierUseCase.invoke();
        List<PremierItems> result = future.get();
        future.thenAccept(res -> {
            verify(premierRepository, times(1)).clearPremiers();
            verify(premierRepository, times(1)).insertPremiers(any());
            verify(premierRepository, never()).getAllPremierFromDatabase();
            assert res.equals(premierItems);
        }).get();
    }

}
