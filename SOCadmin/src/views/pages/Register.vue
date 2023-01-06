<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="5">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <CForm>
                <h1>Quên mật khẩu</h1>
                <p class="text-muted">Sử dụng email đăng ký của bạn!</p>
                <CInput
                  placeholder="Email"
                  autocomplete="email"
                  prepend="@"
                />
                
              </CForm>
            </CCardBody>
            <CCardFooter class="p-1">
              <CRow>
                  <CCol col="6">
                    <CButton block to="/pages/login">
                      <CButton color="danger" block style="margin-top: 15px">Hủy</CButton>
                    </CButton>
                  </CCol>
                  <CCol col="6">
                    <CButton block >
                      <CButton color="success" block style="margin-top: 15px">Gửi xác nhận</CButton>
                    </CButton>
                  </CCol>
                </CRow>
            </CCardFooter>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
export default {
  name: 'Register'
}
</script>
