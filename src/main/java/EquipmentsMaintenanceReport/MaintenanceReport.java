package EquipmentsMaintenanceReport;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * LLM이 생성한 유지보수 분석 결과를 데이터베이스에 저장하기 위한 엔티티 클래스입니다.
 * JPA(Java Persistence API)를 통해 데이터베이스 테이블과 매핑됩니다.
 * `@Entity` 어노테이션은 이 클래스가 데이터베이스 테이블에 대응하는 엔티티임을 나타냅니다.
 */
@Entity
@Data // Lombok을 사용하여 Getter, Setter, toString 등의 메서드를 자동으로 생성합니다.
public class MaintenanceReport {

    /**
     * 리포트의 고유 식별자(Primary Key)입니다.
     * `@Id` 어노테이션으로 기본 키 필드임을 나타냅니다.
     * `@GeneratedValue`는 기본 키 값을 자동으로 생성하도록 설정합니다. (AUTO 전략 사용)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * LLM이 분석한 예상 유지보수 조치 내용입니다.
     * (예: "램프 교체", "패널 세척")
     */
    private String action;

    /**
     * 장비의 폐기/교체/유지 여부에 대한 LLM의 결정과 그 사유입니다.
     * (예: "수리 비용이 신규 구매 비용보다 저렴하여 유지보수 후 계속 사용 권장")
     */
    private String decision;

    /**
     * 예상되는 조치 비용에 대한 정보입니다.
     * (예: "총 50,000원 (부품비 30,000원 + 인건비 20,000원)")
     */
    private String cost;
}
