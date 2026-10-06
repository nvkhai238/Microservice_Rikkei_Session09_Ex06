# Session 09 - Bài 06

Config Server chạy ở `8888`, Pharmacy-Service ở `8090`. Cấu hình dùng trong bài nằm tại `pharmacy-config-repo/pharmacy-service.properties` và được Config Server đọc từ Git của repo này. PostgreSQL chạy ở cổng `5433`.

## Chạy

```bash
git status --short
git add pharmacy-config-repo/pharmacy-service.properties
git commit -m 'Update pharmacy config'
cd config-server && gradle bootRun
```

Terminal khác:

```bash
docker compose up -d
cd pharmacy-service && gradle bootRun
```

Chạy thêm `billing-service` bằng `cd billing-service && gradle bootRun` để kiểm tra gửi hóa đơn thành công. Chạy `insurance-service` bằng `cd insurance-service && gradle bootRun` để kiểm tra xác thực bảo hiểm.

## Bài 06

Luồng xác thực bảo hiểm bất đồng bộ có TimeLimiter, Retry và Circuit Breaker. Timeout mặc định 3 giây lấy từ Config Server; đổi `pharmacy.insurance.timeout` trong file Git, commit rồi gọi `/actuator/refresh`. Khi thất bại, hệ thống trả nguyên giá cùng ghi chú `Xác thực bảo hiểm sau`. Billing-Service mock chạy ở `8092`; dịch vụ bảo hiểm giả lập chạy ở `8093`; có thể đặt `INSURANCE_RESPONSE_DELAY=5000` để mô phỏng timeout.
