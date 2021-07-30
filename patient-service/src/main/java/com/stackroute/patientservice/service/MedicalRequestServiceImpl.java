package com.stackroute.patientservice.service;
import com.stackroute.patientservice.exception.RequestAlreadyExistsException;
import com.stackroute.patientservice.model.MedicalRequest;
import com.stackroute.patientservice.repository.MedicalRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MedicalRequestServiceImpl implements MedicalRequestService {

    private MedicalRequestRepository medicalRequestRepository;

    @Autowired
    public MedicalRequestServiceImpl(MedicalRequestRepository medicalRequestRepository) {
        this.medicalRequestRepository = medicalRequestRepository;
    }

    @Override
    public MedicalRequest savePatientRequest(MedicalRequest medicalRequest) throws RequestAlreadyExistsException {
        if (medicalRequestRepository.existsById(medicalRequest.getId())) {
            throw new RequestAlreadyExistsException();
        }
        MedicalRequest savedPatient = medicalRequestRepository.save(medicalRequest);
        return savedPatient;
    }

    @Override
    public List<MedicalRequest> getAllPatientRequest() {
        return medicalRequestRepository.findAll();
    }
}



