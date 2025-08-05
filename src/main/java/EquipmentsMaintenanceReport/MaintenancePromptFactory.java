package EquipmentsMaintenanceReport;

/**
 * LLM(Gemini)에게 전달할 프롬프트(지시사항)를 생성하는 클래스입니다.
 * 정적 메서드를 사용하여, 입력된 장비 정보(MaintenanceRequest)를 바탕으로
 * 구체적이고 체계적인 프롬프트 문자열을 동적으로 생성합니다.
 */
public class MaintenancePromptFactory {

    /**
     * MaintenanceRequest 객체에 담긴 장비 정보를 바탕으로 LLM에게 보낼 프롬프트를 생성합니다.
     *
     * @param req 분석할 장비의 상세 정보가 담긴 DTO 객체
     * @return LLM이 분석을 수행할 수 있도록 가공된 전체 프롬프트 문자열
     */
    public static String buildPrompt(MaintenanceRequest req) {
        StringBuilder sb = new StringBuilder();

        // 프롬프트의 기본 지시사항을 추가합니다.
        sb.append("장비 정보를 바탕으로 유지보수 조치 및 비용 분석을 수행해주세요.\n");
        sb.append("다음은 장비의 상세 정보입니다.\n\n");

        // 공통 장비 정보를 프롬프트에 추가합니다.
        sb.append(String.format("- 분류: %s\n", req.getCategory()));
        sb.append(String.format("- 제조사: %s\n", req.getManufacturer()));
        sb.append(String.format("- 구매 금액: %d원\n", req.getPurchase()));
        sb.append(String.format("- 구매일: %s\n", req.getPurchase_date()));
        sb.append(String.format("- 고장 기록: %d건\n", req.getFailure()));
        sb.append(String.format("- 보호 등급: %s\n", req.getProtection_rating()));
        sb.append(String.format("- 월 평균 가동 시간: %d시간\n", req.getRuntime()));
        sb.append(String.format("- 내용 연수: %d년\n", req.getService_years()));
        sb.append(String.format("- 예측 유지보수 비용: %d원\n", req.getMaintenance_cost()));
        sb.append(String.format("- 수리 비용: %d원\n", req.getRepair_cost()));
        sb.append(String.format("- 수리 시간: %d시간\n", req.getRepair_time()));
        sb.append(String.format("- 시간당 인건비: %d원\n", req.getLabor_rate()));
        sb.append(String.format("- 평균 수명: %d년\n", req.getAvg_life()));

        sb.append("\n--- 카테고리별 세부 정보 ---\n");

        // 장비 카테고리에 따라 특화된 정보를 프롬프트에 추가합니다.
        switch (req.getCategory()) {
            case "조명":
                sb.append(String.format("- 램프 유형: %s\n", req.getLamp_type()));
                sb.append(String.format("- 소비 전력: %dW\n", req.getPower_consumption()));
                break;
            case "기상관측장비":
                sb.append(String.format("- 설치 형태: %s\n", req.getMount_type()));
                break;
            case "표지판":
                sb.append(String.format("- 패널 크기: %d x %d mm\n", req.getPanel_width(), req.getPanel_height()));
                sb.append(String.format("- 재질: %s\n", req.getMaterial()));
                sb.append(String.format("- 색상: %s\n", req.getSign_color()));
                break;
        }

        // LLM에게 원하는 응답 형식을 명확하게 지시합니다.
        sb.append("\n이 정보를 기반으로 아래 내용을 JSON 형식으로 작성해주세요:\n");
        sb.append("1. 예상 유지보수 조치 (조치명, 설명)\n");
        sb.append("2. 폐기/교체/유지 여부 및 사유\n");
        sb.append("3. 조치 예상 비용 (총액 포함)\n");

        return sb.toString();
    }
}
