# TÀI LIỆU PHÂN TÍCH YÊU CẦU NGHIỆP VỤ (SRS ANALYSIS)
## DỰ ÁN: HỆ THỐNG THƯƠNG MẠI ĐIỆN TỬ TECHNOVA

* **Vai trò phân tích:** Senior Business Analyst (BA)
* **Phiên bản:** 1.0
* **Ngày lập:** 03/07/2026

---

## 1. Danh sách các Tác nhân (Actors) và Vai trò
Dưới đây là danh sách các tác nhân tương tác trực tiếp và gián tiếp với hệ thống:

| STT | Tác nhân (Actor) | Phân loại | Mô tả vai trò và trách nhiệm trong hệ thống |
| :--- | :--- | :--- | :--- |
| **1** | **Khách hàng (Customer)** | External | Người dùng cuối truy cập trang web để tìm kiếm, xem thông tin sản phẩm (điện thoại, laptop), quản lý giỏ hàng cá nhân và thực hiện mua hàng/thanh toán sau khi đăng ký/đăng nhập. |
| **2** | **Nhân viên kho (Warehouse Staff)** | Internal | Nhân sự vận hành thuộc trang Admin, chịu trách nhiệm quản lý danh mục sản phẩm (thêm mới, chỉnh sửa thông tin, cập nhật trạng thái hoặc xóa sản phẩm). |
| **3** | **Quản lý (Manager)** | Internal | Nhân sự cấp cao thuộc trang Admin, có quyền xem báo cáo thống kê doanh thu và quản lý phân quyền (cấp quyền/thu hồi quyền) cho các tài khoản nhân viên khác. |
| **4** | **Cổng thanh toán (Payment Gateway)** | System | *([Đề xuất bởi BA])* Hệ thống bên thứ ba (như Momo, VNPay, Stripe...) liên kết để xử lý giao dịch thanh toán trực tuyến an toàn của khách hàng. |

---

## 2. Danh sách Yêu cầu chức năng (Functional Requirements - FR)
Dưới đây là các yêu cầu chức năng được chuẩn hóa và phân nhóm chi tiết theo từng phân hệ người dùng:

### A. Phân hệ Khách hàng (Customer Portal)
* **FR-1.1: Đăng ký tài khoản (Register)**
  * Cho phép người dùng đăng ký tài khoản mới bằng Email hoặc Số điện thoại.
  * Yêu cầu nhập các thông tin bắt buộc: Họ tên, Mật khẩu (độ phức tạp cao), Email/SĐT.
* **FR-1.2: Đăng nhập / Đăng xuất (Login / Logout)**
  * Cho phép khách hàng đăng nhập hệ thống bằng tài khoản đã đăng ký.
  * Cung cấp tính năng đăng xuất để bảo vệ phiên làm việc.
* **FR-1.3: Xem danh mục và chi tiết sản phẩm (Browse/Search Products)**
  * Hiển thị danh mục sản phẩm (Điện thoại, Laptop).
  * Cho phép khách hàng xem chi tiết thông tin sản phẩm (tên, hình ảnh, thông số kỹ thuật, giá bán, trạng thái tồn kho).
* **FR-1.4: Quản lý giỏ hàng (Shopping Cart Management)**
  * Cho phép khách hàng thêm sản phẩm vào giỏ hàng.
  * Cho phép cập nhật số lượng sản phẩm hoặc xóa sản phẩm khỏi giỏ hàng.
  * Tính tổng tiền tạm tính tự động trong giỏ hàng.
* **FR-1.5: Đặt hàng và Thanh toán (Checkout & Payment)**
  * *Điều kiện tiên quyết:* Khách hàng phải đăng nhập trước khi tiến hành thanh toán (yêu cầu bắt buộc).
  * Cho phép khách hàng nhập thông tin giao hàng (địa chỉ, số điện thoại người nhận).
  * Tích hợp cổng thanh toán trực tuyến hoặc chọn phương thức COD (thanh toán khi nhận hàng).

### B. Phân hệ Quản trị dành cho Nhân viên Kho (Warehouse Staff - Admin Portal)
* **FR-2.1: Đăng nhập trang Admin (Admin Login)**
  * Chỉ cho phép nhân viên có tài khoản được cấp quyền tương ứng truy cập và đăng nhập vào trang Admin.
* **FR-2.2: Quản lý sản phẩm (Product Catalog Management)**
  * **Thêm sản phẩm mới (Create):** Nhập tên sản phẩm, danh mục (điện thoại/laptop), hình ảnh, giá bán, mô tả, số lượng tồn kho.
  * **Sửa thông tin sản phẩm (Update):** Cập nhật giá, hình ảnh hoặc các thông số kỹ thuật của sản phẩm hiện có.
  * **Xóa sản phẩm (Delete):** Cho phép xóa sản phẩm khỏi hệ thống (hoặc chuyển trạng thái sang "Ngừng kinh doanh" để bảo toàn dữ liệu lịch sử đơn hàng).

### C. Phân hệ Quản trị dành cho Quản lý (Manager - Admin Portal)
* **FR-3.1: Xem báo cáo thống kê doanh thu (Revenue Reporting & Analytics)**
  * Hiển thị biểu đồ và bảng số liệu về doanh thu theo thời gian (ngày, tuần, tháng, năm).
  * Thống kê sản phẩm bán chạy nhất, doanh thu theo danh mục sản phẩm (điện thoại vs. laptop).
* **FR-3.2: Quản lý người dùng & Cấp quyền nhân viên (Role & Permission Management)**
  * Cho phép tạo mới tài khoản nhân viên.
  * Cấp quyền tương ứng cho nhân viên (ví dụ: gán quyền "Nhân viên kho" cho một tài khoản mới).
  * Thu hồi hoặc thay đổi quyền hạn của nhân viên khi cần thiết.

---

## 3. Danh sách Yêu cầu phi chức năng (Non-functional Requirements - NFR)
Dựa trên mục tiêu xây dựng một hệ thống hoạt động ổn định dưới tải trọng cực lớn từ "hàng chục ngàn người truy cập cùng lúc", các yêu cầu phi chức năng được thiết kế như sau:

### A. Bảo mật (Security)
* **NFR-1.1: Xác thực & Phân quyền (Authentication & Authorization)**
  * Áp dụng mô hình phân quyền dựa trên vai trò (Role-Based Access Control - RBAC).
  * **Chặn truy cập trái phép:** Thiết lập hệ thống bảo mật ở cả tầng Client (Router Guard) và tầng Server (Middleware/API Gatekeeper) để ngăn chặn tuyệt đối trường hợp Khách hàng tìm cách truy cập vào các đường dẫn hoặc gọi API của trang Admin.
* **NFR-1.2: Bảo mật kênh truyền dữ liệu (Data-in-Transit Security)**
  * Bắt buộc sử dụng giao thức HTTPS với chứng chỉ TLS 1.3 cho toàn bộ hệ thống để mã hóa dữ liệu truyền tải giữa thiết bị người dùng và máy chủ.
* **NFR-1.3: Bảo mật lưu trữ dữ liệu (Data-at-Rest Security)**
  * Mã hóa mật khẩu người dùng trong cơ sở dữ liệu bằng các thuật toán băm mạnh một chiều (như Bcrypt hoặc Argon2id).
  * Không lưu thông tin nhạy cảm của khách hàng dưới dạng văn bản thuần (plain text).
* **NFR-1.4: Phòng chống các cuộc tấn công phổ biến (OWASP Top 10)**
  * Hệ thống phải có cơ chế phòng chống các lỗ hổng bảo mật phổ biến như SQL Injection, Cross-Site Scripting (XSS), Cross-Site Request Forgery (CSRF) và Broken Object Level Authorization (BOLA/IDOR).

### B. Hiệu năng (Performance)
* **NFR-2.1: Thời gian phản hồi API (Response Time)**
  * Thời gian phản hồi (Latency) cho các API truy vấn sản phẩm (Xem sản phẩm, Tìm kiếm) phải đạt $\le 200ms$ đối với 95% số lượng request ($p95$).
  * Thời gian phản hồi cho các giao dịch nghiệp vụ nặng (Thanh toán, Đặt hàng) phải đạt $\le 1000ms$ ($p95$).
* **NFR-2.2: Cơ chế bộ nhớ đệm (Caching Strategy)**
  * Áp dụng Redis Caching cho các dữ liệu tĩnh hoặc dữ liệu ít biến động nhưng tần suất truy cập cao (danh mục sản phẩm, thông tin chi tiết sản phẩm) để giảm tải trực tiếp cho Cơ sở dữ liệu.
* **NFR-2.3: Tối ưu hóa Cơ sở dữ liệu (Database Optimization)**
  * Thiết lập Index phù hợp cho các trường dữ liệu thường dùng để tìm kiếm và lọc (ví dụ: `product_name`, `category_id`, `price`).
  * Sử dụng Database Connection Pooling (như HikariCP) với cấu hình tối ưu để tái sử dụng kết nối hiệu quả.

### C. Khả năng mở rộng & Độ tin cậy (Scalability & Reliability)
* **NFR-3.1: Khả năng chịu tải đồng thời (High Concurrency)**
  * Hệ thống phải đáp ứng tốt khi có hàng chục ngàn người truy cập cùng lúc. Thiết kế chịu tải tối thiểu **10,000 người dùng hoạt động đồng thời (Concurrent Users)** và xử lý ổn định mức lưu lượng từ **2,000 đến 5,000 requests mỗi giây (Requests Per Second - RPS)**.
* **NFR-3.2: Khả năng mở rộng ngang (Horizontal Scalability)**
  * Thiết kế dịch vụ API dưới dạng Stateless (không lưu trạng thái phiên trên máy chủ đơn lẻ) để dễ dàng scale-out (thêm nhiều instance backend) sau bộ cân bằng tải (Load Balancer như Nginx hoặc AWS Application Load Balancer).
* **NFR-3.3: Khả năng chống sập Cơ sở dữ liệu (Database Resilience)**
  * Sử dụng kiến trúc Cơ sở dữ liệu Read/Write Splitting (1 Database Master chuyên ghi và nhiều Database Replica chuyên đọc) để phân phối tải đọc khổng lồ.
  * Tích hợp cơ chế Circuit Breaker (như Resilience4j) để cô lập lỗi khi có sự cố kết nối tới các dịch vụ bên thứ ba.
* **NFR-3.4: Khả năng tự phục hồi (Auto-scaling & Monitoring)**
  * Triển khai hệ thống trên hạ tầng container (Docker/Kubernetes) cấu hình Auto-scaling tự động tăng số lượng instance API khi tài nguyên vượt quá ngưỡng quy định (ví dụ: CPU > 70%).

---

## 4. Các điểm cần làm rõ thêm với Giám đốc TechNova (BA Recommendations)
1. **Phương thức thanh toán cụ thể:** Ngoài việc đăng ký tài khoản, TechNova muốn tích hợp cụ thể những cổng thanh toán nào?
2. **Quy trình xử lý đơn hàng:** Khi khách hàng thanh toán xong, trạng thái đơn hàng sẽ được chuyển đi đâu? Nhân viên kho có cần tính năng quản lý trạng thái đơn hàng trên trang Admin không?
3. **Chính sách khôi phục dữ liệu (Backup & Disaster Recovery):** TechNova yêu cầu tần suất sao lưu dữ liệu (Backup) như thế nào?
