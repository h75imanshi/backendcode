package com.eoffice.service;

import com.eoffice.dto.PressDetailDto;
import com.eoffice.dto.PressSearchResultDto;
import com.eoffice.model.Press;
import com.eoffice.repository.PressRepository;
import com.eoffice.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PressService {

    private final PressRepository pressRepository;
    private final StateRepository stateRepository;

    public List<PressSearchResultDto> searchByName(String name) {
        return toResultList(
                pressRepository.findByPressNameContainingIgnoreCase(name)
        );
    }

    public List<PressSearchResultDto> searchByAppNo(String appNo) {
        return toResultList(
                pressRepository.findByPressApplicationNoContainingIgnoreCase(appNo)
        );
    }

    public List<PressSearchResultDto> searchByPrinterName(String printerName) {
        return toResultList(
                pressRepository.findByKeeperNameContainingIgnoreCase(printerName)
        );
    }

    public PressDetailDto getFullDetail(String id) {

        Press p = pressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Press not found"));

        PressDetailDto dto = new PressDetailDto();

        dto.setId(p.getId());
        dto.setAppNo(p.getPressApplicationNo());
        dto.setRegNo(p.getRegNo());
        dto.setPressName(p.getPressName());
        dto.setPressType(p.getPressType());
        dto.setPressAddress(p.getPressAddress());
        dto.setPressPincode(p.getPressPincode());
        System.out.println("PRESS = " + p.getPressName());
            System.out.println("STATE ID = " + p.getPressStateId());
            System.out.println("DISTRICT ID = " + p.getPressDistrictId());

            String stateName =
                    stateRepository.getStateName(p.getPressStateId());

            String districtName =
                  (
                            stateRepository.getDistrictName(
                                    p.getPressDistrictId()
                            )
                    );

            System.out.println("STATE NAME = " + stateName);
            System.out.println("DISTRICT NAME = " + districtName);

            dto.setState(stateName);
            dto.setDistrict(districtName);
            dto.setKeeperName(p.getKeeperName());
        dto.setKeeperMobileNo(p.getKeeperMobileNo());
        dto.setKeeperEmail(p.getKeeperEmail());
        dto.setKeeperAddress(p.getKeeperAddress());
        dto.setKeeperPincode(p.getKeeperPincode());

        dto.setKeeperState(
                stateRepository.getStateName(
                        p.getKeeperStateId()
                )
        );

        dto.setKeeperDistrict(
                stateRepository.getDistrictName(
                        p.getKeeperDistrictId()
                )
        );

            return dto;
        }


    public List<String> getStates() {
        return stateRepository.getStates();
    }

    public List<String> getDistricts(String stateName) {
        return stateRepository.getDistrictsByState(stateName);
    }
    public List<PressSearchResultDto> searchByState(String state, String district) {
        return toResultList(
                pressRepository.findByKeeperNameContainingIgnoreCase(district)
        );
    }

    private List<PressSearchResultDto> toResultList(List<Press> presses) {
        return presses.stream()
                .map(this::toResultDto)
                .collect(Collectors.toList());
    }

    private PressSearchResultDto toResultDto(Press p) {

        PressSearchResultDto dto = new PressSearchResultDto();

        dto.setId(p.getId());
        dto.setAppNo(p.getPressApplicationNo());
        dto.setPressName(p.getPressName());
        dto.setPressType(p.getPressType());
        dto.setPrinterName(p.getKeeperName());
        dto.setStatus(p.getApplicationStatus());
        dto.setRegNo(p.getRegNo());
        dto.setPressType(p.getPressType());
        dto.setPressAddress(p.getPressAddress());
        dto.setPressPincode(p.getPressPincode());
        System.out.println("PRESS = " + p.getPressName());
        System.out.println("STATE ID = " + p.getPressStateId());
        System.out.println("DISTRICT ID = " + p.getPressDistrictId());

        dto.setState(
                stateRepository.getStateName(
                        p.getPressStateId()
                )
        );

        dto.setDistrict(
                stateRepository.getDistrictName(
                        p.getPressDistrictId()
                )
        );
        dto.setKeeperName(
                p.getKeeperName()
        );

        dto.setKeeperMobileNo(
                p.getKeeperMobileNo()
        );

        dto.setKeeperEmail(
                p.getKeeperEmail()
        );

        dto.setKeeperAddress(
                p.getKeeperAddress()
        );

        dto.setKeeperPincode(
                p.getKeeperPincode()
        );

        dto.setKeeperState(
                stateRepository.getStateName(
                        p.getKeeperStateId()
                )
        );

        dto.setKeeperDistrict(
                stateRepository.getDistrictName(
                        p.getKeeperDistrictId()
                )
        );



        return dto;
    }
}