package com.stackroute.donor.service;

import com.stackroute.donor.Exceptions.DonorAlradyExistsException;
import com.stackroute.donor.model.Donor;
import com.stackroute.donor.repository.DonorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DonorServiceTest {

    @Mock
    private DonorRepository donorRepository;
    private Donor donor;
    private List<Donor> donarList;
    @InjectMocks
    private DonorServiceImpl donorService;

//    @Test
//    public void givenDonorToSaveShouldReturnSavedDonor() throws DonorAlradyExistsException {
//        Donor donor=new Donor(1,"abc","45","7338124788","abc123@gmail.com","Bangalore","O+");
//        when(donorRepository.save(any())).thenReturn(donor);
//        donorService.saveDonor(donor);
//        verify(donorRepository,times(1)).save(any());
//    }

    @Test
    public  void listallTheDonors(){
        donorRepository.save(donor);
        when(donorRepository.findAll()).thenReturn(donarList);
        List<Donor> donorList = donorService.getAllDonor();
        assertEquals(donarList, donorList);
        verify(donorRepository,times(1)).save(donor);
        verify(donorRepository,times(1)).findAll();




    }

}