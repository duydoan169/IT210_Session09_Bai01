Phần 1 - Phân tích logic: 

Tại sao hệ thống "mất trí nhớ"? Vấn đề nằm ở việc sử dụng Request Scope (phạm vi yêu cầu) 
để lưu trữ dữ liệu cần duy trì qua nhiều trang.

Cơ chế của HttpServletRequest: Một đối tượng HttpServletRequest chỉ có vòng đời tồn tại trong duy nhất một HTTP Request.
Ngay sau khi phản hồi (Response) được gửi về trình duyệt, đối tượng Request đó sẽ bị hủy bỏ.

Lỗi do lệnh Redirect: Khi bạn sử dụng return "redirect:/checkout", 
trình duyệt sẽ nhận được mã trạng thái 302 và thực hiện một HTTP Request hoàn toàn mới đến URL /checkout.

Ở API 1, giỏ hàng được lưu vào request cũ.

Ở API 2, một request mới được tạo ra, và tất nhiên, nó không hề chứa thuộc tính "myCart" từ request trước đó.

Kết luận: Lỗi do bạn đang dùng sai công cụ lưu trữ. 
HttpServletRequest chỉ dùng để chuyển dữ liệu từ Controller sang View trong cùng một lượt request (Forward), 
không dùng được cho các luồng có redirect hoặc duy trì trạng thái người dùng (Session).