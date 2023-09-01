package com.stac.daijin.domain.appointment.service;

import com.stac.daijin.domain.appointment.presentation.dto.response.AppointmentListResponse;
import com.stac.daijin.domain.appointment.presentation.dto.response.AppointmentResponse;
import com.stac.daijin.domain.appointment.repository.AppointmentRepository;
import com.stac.daijin.domain.user.User;
import com.stac.daijin.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryAppointmentListService {
    private final AppointmentRepository appointmentRepository;
    private final UserFacade userFacade;

    public AppointmentListResponse execute(
            String accountId
    ) {
        User user = userFacade.findUserByAccountId(accountId);
        return new AppointmentListResponse(
                appointmentRepository.findAllByUser(user)
                        .stream()
                        .map(appointment -> AppointmentResponse.of(appointment.getClinic(), appointment))
                        .collect(Collectors.toList())
        );
    }
}