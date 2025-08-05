package EquipmentsMaintenanceReport;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * MaintenanceReport 엔티티에 대한 데이터베이스 작업을 처리하는 리포지토리 인터페이스입니다.
 * Spring Data JPA의 JpaRepository를 상속받아, 기본적인 CRUD(Create, Read, Update, Delete)
 * 메서드(예: save(), findById(), findAll())를 자동으로 제공받습니다.
 * 별도의 구현 클래스 없이 인터페이스만으로 데이터베이스 접근 로직을 사용할 수 있습니다.
 *
 * @param <MaintenanceReport> 리포지토리가 관리할 엔티티 타입
 * @param <Long> 엔티티의 기본 키(Primary Key) 타입
 */
public interface MaintenanceReportRepository extends JpaRepository<MaintenanceReport, Long> {
}
