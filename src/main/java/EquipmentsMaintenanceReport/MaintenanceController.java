package EquipmentsMaintenanceReport;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 유지보수 관련 API 요청을 처리하는 컨트롤러 클래스입니다.
 * 클라이언트로부터 들어오는 HTTP 요청을 받아 서비스 계층으로 전달하고,
 * 그 결과를 클라이언트에게 응답으로 반환하는 관문 역할을 합니다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/maintenance")
public class MaintenanceController {

    // 비즈니스 로직을 처리하는 서비스 계층 의존성 주입
    private final MaintenanceService maintenanceService;
    // 데이터베이스 작업을 위한 리포지토리 의존성 주입
    private final MaintenanceReportRepository maintenanceReportRepository;

    /**
     * 장비 정보에 대한 유지보수 분석을 요청하는 API입니다.
     * HTTP POST 요청으로 장비 상세 정보(MaintenanceRequest)를 받아,
     * 서비스 계층에 분석 및 저장을 요청하고, 생성된 리포트를 반환합니다.
     *
     * @param request 클라이언트가 보낸 장비 상세 정보 DTO
     * @return 데이터베이스에 저장된 MaintenanceReport 객체
     * @throws JsonProcessingException Gemini API 응답 파싱 중 발생할 수 있는 예외
     */
    @PostMapping("/analyze")
    public ResponseEntity<MaintenanceReport> analyze(@RequestBody MaintenanceRequest request) throws JsonProcessingException {
        MaintenanceReport report = maintenanceService.analyzeAndSave(request);
        return ResponseEntity.ok(report);
    }

    /**
     * 데이터베이스에 저장된 모든 유지보수 리포트를 조회하는 API입니다.
     * HTTP GET 요청을 통해 모든 리포트 목록을 반환합니다.
     *
     * @return 모든 MaintenanceReport 객체 리스트
     */
    @GetMapping("/reports")
    public List<MaintenanceReport> getAllReports() {
        return maintenanceReportRepository.findAll();
    }
}
