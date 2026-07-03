# SƠ ĐỒ HỆ THỐNG TECHNOVA (USE CASE & ERD)

Tài liệu này chứa mã nguồn Mermaid.js cho sơ đồ Use Case và sơ đồ Thực thể Kết nối (ERD) của hệ thống thương mại điện tử TechNova.

---

## 1. Sơ đồ Use Case Tổng quan (Use Case Diagram)

Sơ đồ này thể hiện mối quan hệ giữa các Actor (Guest, Customer, Staff/Warehouse, Manager) và các ca sử dụng cốt lõi, được phân vùng (Boundary) rõ ràng giữa phân hệ **Public** và phân hệ **Admin**.

```mermaid
graph TB
    %% Định nghĩa Actors (Sử dụng các class để làm nổi bật tác nhân)
    Guest["👤 Guest (Khách vãng lai)"]
    Customer["👤 Customer (Khách hàng)"]
    Staff["👤 Staff/Warehouse (Nhân viên kho)"]
    Manager["👤 Manager (Quản lý)"]

    classDef actorStyle fill:#e1f5fe,stroke:#0288d1,stroke-width:2px,rx:10px;
    class Guest,Customer,Staff,Manager actorStyle;

    %% Phân hệ Public (Public Module Boundary)
    subgraph Public["Boundary: Phân hệ Public"]
        UC_Reg(["Đăng ký tài khoản"])
        UC_Login(["Đăng nhập"])
        UC_Browse(["Xem sản phẩm"])
        UC_Cart(["Quản lý giỏ hàng"])
        UC_Checkout(["Thanh toán"])
    end

    %% Phân hệ Admin (Admin Module Boundary)
    subgraph Admin["Boundary: Phân hệ Admin (Trang quản trị)"]
        UC_Prod(["Quản lý sản phẩm<br>(Thêm, Sửa, Xóa)"])
        UC_Report(["Xem thống kê doanh thu"])
        UC_Role(["Cấp quyền nhân viên"])
    end

    %% Mối liên kết giữa Actor và Use Case
    %% Guest
    Guest --> UC_Reg
    Guest --> UC_Browse

    %% Customer
    Customer --> UC_Login
    Customer --> UC_Browse
    Customer --> UC_Cart
    Customer --> UC_Checkout

    %% Staff/Warehouse
    Staff --> UC_Login
    Staff --> UC_Prod

    %% Manager
    Manager --> UC_Login
    Manager --> UC_Report
    Manager --> UC_Role

    %% Các quan hệ phụ thuộc nội bộ hệ thống (Đề xuất thêm để tăng tính chuyên nghiệp)
    UC_Checkout -.->|"<< include >>"| UC_Login
    UC_Prod -.->|"<< include >>"| UC_Login
    UC_Report -.->|"<< include >>"| UC_Login
    UC_Role -.->|"<< include >>"| UC_Login

    %% Style cho subgraphs
    style Public fill:#f9f9f9,stroke:#333,stroke-width:2px;
    style Admin fill:#fff3e0,stroke:#ffb74d,stroke-width:2px;
```

---

## 2. Sơ đồ Thực thể Kết nối (ERD - Entity Relationship Diagram)

Thiết kế cơ sở dữ liệu quan hệ cho các bảng cốt lõi của hệ thống bao gồm: `Users`, `Roles`, `User_Roles`, `Products`, `Orders` và `Order_Items`. Mối quan hệ giữa `Users` và `Roles` là nhiều-nhiều (N-N) thông qua bảng trung gian `User_Roles`.

```mermaid
erDiagram
    USERS {
        bigint id PK "Khóa chính tự tăng"
        string username "Tên đăng nhập"
        string password "Mật khẩu mã hóa"
        string email "Email liên hệ"
        string phone "Số điện thoại"
        timestamp created_at "Thời gian tạo"
    }

    ROLES {
        bigint id PK "Khóa chính"
        string name "Tên vai trò (ROLE_CUSTOMER, ROLE_STAFF, ROLE_MANAGER)"
        string description "Mô tả vai trò"
    }

    USER_ROLES {
        bigint user_id FK "Khóa ngoại tham chiếu đến USERS(id)"
        bigint role_id FK "Khóa ngoại tham chiếu đến ROLES(id)"
    }

    PRODUCTS {
        bigint id PK "Khóa chính tự tăng"
        string name "Tên sản phẩm (Laptop, Điện thoại)"
        string category "Danh mục sản phẩm"
        decimal price "Giá bán"
        int stock "Số lượng tồn kho"
        string image_url "Đường dẫn ảnh sản phẩm"
        text description "Mô tả chi tiết"
        timestamp created_at "Ngày tạo"
    }

    ORDERS {
        bigint id PK "Khóa chính tự tăng"
        bigint user_id FK "Khóa ngoại liên kết USERS(id)"
        decimal total_price "Tổng tiền đơn hàng"
        string status "Trạng thái (PENDING, PAID, SHIPPING, CANCELLED)"
        string shipping_address "Địa chỉ nhận hàng"
        string phone_number "Số điện thoại nhận hàng"
        timestamp created_at "Ngày tạo đơn"
    }

    ORDER_ITEMS {
        bigint id PK "Khóa chính tự tăng"
        bigint order_id FK "Khóa ngoại liên kết ORDERS(id)"
        bigint product_id FK "Khóa ngoại liên kết PRODUCTS(id)"
        int quantity "Số lượng mua"
        decimal price "Giá tại thời điểm mua"
    }

    %% Các quan hệ quan hệ thực thể (Relationships)
    USERS ||--o{ USER_ROLES : "sở hữu"
    ROLES ||--o{ USER_ROLES : "gán cho"
    
    USERS ||--o{ ORDERS : "đặt hàng"
    
    ORDERS ||--|{ ORDER_ITEMS : "bao gồm"
    PRODUCTS ||--o{ ORDER_ITEMS : "được mua trong"
```
