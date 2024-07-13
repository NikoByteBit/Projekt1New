package org.projekt2.server.projekt2server.repository;

import org.projekt2.server.projekt2server.model.EmployeeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeRoomRepository extends JpaRepository<EmployeeRoom, Long> {

    @Query("SELECT e FROM EmployeeRoom e WHERE e.employeeId = :employeeId AND e.timestamp BETWEEN :startOfDay AND :endOfDay")
    List<EmployeeRoom> findByEmployeeIdAndDate(
            @Param("employeeId") String employeeId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT e FROM EmployeeRoom e WHERE e.employeeId = :employeeId AND e.timestamp BETWEEN :startDate AND :endDate")
    List<EmployeeRoom> findByEmployeeIdAndDateRange(
            @Param("employeeId") String employeeId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
