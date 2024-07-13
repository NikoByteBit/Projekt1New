package org.projekt2.server.projekt2server.controller;

import org.projekt2.server.projekt2server.model.EmployeeRoom;
import org.projekt2.server.projekt2server.repository.EmployeeRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/employeeRoom")
public class EmployeeRoomController {

    @Autowired
    private EmployeeRoomRepository employeeRoomRepository;

    @PostMapping
    public EmployeeRoom createEmployeeRoom(@RequestBody EmployeeRoom employeeRoom) {
        return employeeRoomRepository.save(employeeRoom);
    }

    @GetMapping("/search")
    public List<EmployeeRoom> getEntriesByEmployeeIdAndDate(
            @RequestParam String employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        return employeeRoomRepository.findByEmployeeIdAndDate(employeeId, startOfDay, endOfDay);
    }

    @GetMapping("/search/dateRange")
    public List<EmployeeRoom> getEntriesByEmployeeIdAndDateRange(
            @RequestParam String employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        return employeeRoomRepository.findByEmployeeIdAndDateRange(employeeId, startDateTime, endDateTime);
    }
}
