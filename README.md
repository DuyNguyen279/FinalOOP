# Ứng dụng Quản Lý Bán Hàng

## Giới thiệu

Phần mềm quản lý bán hàng được xây dựng bằng ngôn ngữ Java, sử dụng thư viện giao diện FlatLaf hiện đại và kết nối cơ sở dữ liệu MySQL. Ứng dụng hỗ trợ đăng nhập, phân quyền người dùng (Quản lý/Nhân viên), quản lý tài khoản, nhân viên, sản phẩm, nhà cung cấp, hóa đơn và báo cáo thống kê.

## Tính năng nổi bật

- **Đăng nhập hệ thống**: Xác thực tài khoản, kiểm tra trạng thái (hoạt động/khóa), phân quyền chuyển giao diện phù hợp.
- **Quản lý tài khoản**: Thêm, xóa, đổi mật khẩu, khóa/mở khóa tài khoản.
- **Quản lý nhân viên**: Thêm mới, chỉnh sửa, xóa, tìm kiếm, xem chi tiết thông tin nhân viên.
- **Quản lý sản phẩm**: Thêm mới, chỉnh sửa, xóa, tìm kiếm, xem chi tiết sản phẩm.
- **Quản lý nhà cung cấp**: Thêm, sửa, xóa, tìm kiếm nhà cung cấp.
- **Quản lý hóa đơn**: Tạo hóa đơn, thêm/xóa sản phẩm vào hóa đơn, xem chi tiết, tìm kiếm hóa đơn theo ngày.
- **Báo cáo - Thống kê**: Thống kê sản phẩm bán ra, sản phẩm hư hỏng, vẽ biểu đồ doanh thu, sản phẩm.

## Công nghệ sử dụng

- **Ngôn ngữ**: Java
- **Giao diện**: Java Swing, FlatLaf
- **Cơ sở dữ liệu**: MySQL
- **Thư viện bổ sung**: JFreeChart (vẽ biểu đồ), MySQL Connector/J (kết nối CSDL)

## Cấu trúc thư mục

```
src/
  ├── com/doan/controller/   // Xử lý logic nghiệp vụ
  ├── com/doan/dao/          // Truy xuất dữ liệu (DAO)
  ├── com/doan/database/     // Kết nối, cấu hình CSDL
  ├── com/doan/icon/         // Icon giao diện
  ├── com/doan/model/        // Định nghĩa các lớp đối tượng
  └── com/doan/view/         // Giao diện người dùng (Form)
jar/
  ├── flatlaf-3.6.jar
  ├── mysql-connector-j-9.2.0.jar
  └── jfreechart-1.0.19/
```

## Hướng dẫn cài đặt & sử dụng

1. **Clone dự án**
   ```
   git clone (https://github.com/DuyNguyen279/FinalOOP.git)
   ```

2. **Cài đặt thư viện**
   - Đảm bảo đã thêm các file jar (FlatLaf, MySQL Connector/J, JFreeChart) vào project.

3. **Cấu hình cơ sở dữ liệu**
   - Cài đặt MySQL, tạo database và các bảng theo file script SQL (nếu có).
   - Sửa thông tin kết nối trong các file DAO cho phù hợp với cấu hình máy bạn.

4. **Chạy chương trình**
   - Mở project bằng IDE (NetBeans, IntelliJ, VS Code...)
   - Chạy file `LoginForm.java` để khởi động ứng dụng.

5. **Đăng nhập và sử dụng**
   - Đăng nhập bằng tài khoản đã có sẵn trong CSDL.
   - Sử dụng các chức năng quản lý theo phân quyền.

## Đóng góp

Mọi ý kiến đóng góp, báo lỗi hoặc đề xuất tính năng mới vui lòng gửi về email hoặc tạo issue trên GitHub.

---

**Tác giả:**   
- Email liên hệ: ntduy279@gmail.com
