package com.poly.datn.service.impl;

import com.poly.datn.dao.*;
import com.poly.datn.entity.*;
import com.poly.datn.service.OrdersService;
import com.poly.datn.utils.CheckRole;
import com.poly.datn.utils.PriceUtils;
import com.poly.datn.utils.StringFind;
import com.poly.datn.vo.*;
import com.poly.datn.vo.VoBoSung.NoteOrderManagementVo;
import com.poly.datn.vo.VoBoSung.OrderDTO.OrdersVO;
import com.poly.datn.vo.VoBoSung.ShowProductWarrantyVO;
import com.poly.datn.vo.mailSender.InfoSendOrder;
import com.poly.datn.vo.mailSender.InfoSendStatusOrder;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdersServicesImpl implements OrdersService {
    @Autowired
    OrdersDAO ordersDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    OrderDetailsDAO orderDetailsDAO;

    @Autowired
    OrderManagementDAO orderManagementDAO;

    @Autowired
    WarrantyDAO warrantyDAO;

    @Autowired
    CheckRole checkRole;

    @Autowired
    ColorDAO colorDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    StringFind stringFind;

    @Autowired
    PriceUtils priceUtils;

    @Autowired
    ProductSaleDAO productSaleDAO;

    @Autowired
    CartDetailDAO cartDetailDAO;

    @Autowired
    AccountDAO accountDAO;

    @Autowired
    SendMail sendMail;
    @Autowired
    ProductColorDAO productColorDAO;

    private List<InfoSendStatusOrder> infoSendStatusOrder = new ArrayList<>();


    @Scheduled(cron = "0 0 0/1 1/1 * ?")
    public void taskSendMailStatus() {
        try {
            if (infoSendStatusOrder.size() > 0) {
                for (InfoSendStatusOrder infoSendStatusOrder2 : infoSendStatusOrder
                ) {
                    sendMail.sentMailStatusOrder(infoSendStatusOrder2);
                }
                infoSendStatusOrder.clear();
            } else {
                return;
            }
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private InfoSendStatusOrder sendMailUpdateStatus(OrderManagement orderManagement) {
        Orders orders = ordersDAO.findMotById(orderManagement.getOrderId());
        Customer customer = customerDAO.findCustomerById(orders.getCustomerId());
        InfoSendStatusOrder infoSendStatusOrder3 = new InfoSendStatusOrder();
        infoSendStatusOrder3.setName(customer.getFullname());
        infoSendStatusOrder3.setPhone(customer.getPhone());
        infoSendStatusOrder3.setEmail(customer.getEmail());
        infoSendStatusOrder3.setAddress(customer.getAddress());
        infoSendStatusOrder3.setOrderId(orders.getId());
        List<OrderManagement> orderManagement1 = orderManagementDAO.findByOrderId(orders.getId());
        List<OrderManagementVO> orderManagementVOS = new ArrayList<>();
        for (OrderManagement orderManagement2 : orderManagement1) {
            OrderManagementVO orderManagementVO = new OrderManagementVO();
            BeanUtils.copyProperties(orderManagement2, orderManagementVO);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
            orderManagementVO.setTimeChange(sdf.format(new Date(orderManagement2.getTimeChange().getTime())));
            orderManagementVOS.add(orderManagementVO);
        }
        OrderManagementVO[] voss = new OrderManagementVO[orderManagement1.size()];
        orderManagementVOS.stream().collect(Collectors.toList()).toArray(voss);
        infoSendStatusOrder3.setOrderManagementVO(voss);
        return infoSendStatusOrder3;
    }


    @Override
    public OrdersVO getByIdAndUserName(Integer id, Principal principal) throws SecurityException, NullPointerException {
        if (principal == null) {
            return null;
        }
        Orders orders = ordersDAO.findByIdAndUsername(id, principal.getName()).orElseThrow(() -> new SecurityException("Not your order"));

        OrdersVO vo = getDetailOrders(orders, null);

        return getStatusLine(vo);
    }


    //, OrderDetailsVO orderDetailsVO, CustomerVO customerVO
    @Override
    public OrdersVO newOrder(NewOrdersVO ordersVO, Principal principal) {

        String changeBy = principal != null ? principal.getName() : "guest";
        if (ordersVO.getOrderDetails() == null) {
            throw new NotImplementedException("Kh??ng c?? s???n ph???m trong h??a ????n");
        }
        List<OrderDetailsVO> orderDetailsVOS = ordersVO.getOrderDetails();
        for (OrderDetailsVO orderDetailsVO: orderDetailsVOS ) {
            if(orderDetailsVO.getQuantity() <=0){
                 throw new NotImplementedException("S???n ph???m: "+ orderDetailsVO.getProductName() + " ??ang c?? s??? l?????ng nh??? h??n 1, vui l??ng ch???nh s???a l???i");
            }
        }
        // save customer
        Orders orders = createOders(ordersVO, changeBy);

        saveDetails(orders, ordersVO);
        OrdersVO vo = managerOrderStatus(orders, changeBy, "Ch??? x??c nh???n");
        if (principal != null) {
            List<CartDetail> details = cartDetailDAO.getCartDetailsByUsername(principal.getName());
            for (CartDetail detail : details) {
                cartDetailDAO.deleteById(detail.getId());
            }
        }
        if (ordersVO.getCustomer().getEmail() == null) {
            throw new NotImplementedException("Email kh??ng h???p l???");
        }

        List<OrderDetailsVO> vos = vo.getOrderDetails();
        InfoSendOrder infoSendOrder = new InfoSendOrder();

        Long totalPrice = 0L;
        Long totalDiscount = 0L;
        Long price = 0L;
        for (OrderDetailsVO detailsVO : vos) {
            if (detailsVO.getQuantity() <= 0) {
                throw new NotImplementedException("S??? l?????ng s???n ph???m t??n: " + detailsVO.getProductName() + " ??ang kh??ng h???p l???( <1), vui l??ng ki???m tra l???i");
            } else {
                price += detailsVO.getQuantity() * detailsVO.getPrice();
                Long discount = detailsVO.getQuantity() * detailsVO.getDiscount();
                totalDiscount += discount;
                totalPrice += detailsVO.getQuantity() * (detailsVO.getPrice() - detailsVO.getDiscount());
            }
        }
/**
 * send order mail
 */
        infoSendOrder.setDiscount(totalDiscount);
        infoSendOrder.setTotalPrice(totalPrice);
        infoSendOrder.setPrice(price);
        infoSendOrder.setName(ordersVO.getCustomer().getFullname());
        infoSendOrder.setAddress(ordersVO.getCustomer().getAddress());
        infoSendOrder.setEmail(ordersVO.getCustomer().getEmail());
        infoSendOrder.setPhone(ordersVO.getCustomer().getPhone());
        OrderDetailsVO[] voss = new OrderDetailsVO[vos.size()];
        vos.stream().map(
                detail -> {
                    Product product = productDAO.getById(detail.getProductId());
                    detail.setWarranty(product.getWarranty());
                    return detail;
                }
        ).collect(Collectors.toList()).toArray(voss);
        infoSendOrder.setOrderDetails(voss);
        sendMail.sentMailOrder(infoSendOrder);
        return vo;
    }

    @Override
    public OrdersVO getByIdAndUserNameAdmin(Integer id, Principal principal) {
        checkPrincipal(principal);
        Orders orders = ordersDAO.findById(id).orElseThrow(() -> new SecurityException("Not found"));
        OrdersVO vo = getDetailOrders(orders, null);
        return getStatusLine(vo);
    }

    public Integer updateQuantityForProductBeffoCancerOrder(Integer id) {
        Integer countQuantity = 0;
        List<OrderDetails> orderDetails = orderDetailsDAO.findAllByOrderIdEquals(id);
        for (OrderDetails orderDetails1 : orderDetails
        ) {
            ProductColor productColor = productColorDAO.findByProductIdAndColorId(orderDetails1.getProductId(), orderDetails1.getColorId());
            productColor.setQuantity(orderDetails1.getQuantity() + productColor.getQuantity());
            productColorDAO.save(productColor);
            Product product = productDAO.getById(orderDetails1.getProductId());
            if (product.getStatus().equals("H???t h??ng")) {
                product.setStatus("??ang b??n");
                productDAO.save(product);
            }
        }
        return countQuantity;
    }

    public void updateStatus(String status, String note, Integer id, Orders orders, NoteOrderManagementVo noteOrderManagementVo, String username) {
        try {
            OrderManagement orderManagement1 = new OrderManagement();
            orderManagement1.setOrderId(orders.getId());
            orderManagement1.setTimeChange(Timestamp.valueOf(LocalDateTime.now()));
            orderManagement1.setChangedBy(username);
            orderManagement1.setStatus(status);
            if (noteOrderManagementVo.getNote() == "") {
                orderManagement1.setNote(note);
            } else {
                orderManagement1.setNote(noteOrderManagementVo.getNote());
            }
            orderManagement1 = orderManagementDAO.save(orderManagement1);
            InfoSendStatusOrder infoSendStatusOrder1 = sendMailUpdateStatus(orderManagement1);
            infoSendStatusOrder.add(infoSendStatusOrder1);
        } catch (Exception e) {
            throw new NotImplementedException("C?? l???i khi thay ?????i tr???ng th??i ????n h??ng");
        }
    }

    public void checkPrincipal(Principal principal) {
        if (principal == null) {
            throw new NotImplementedException("Ch??a ????ng nh???p");
        }
        if (!(checkRole.isHavePermition(principal.getName(), "Director") ||
                checkRole.isHavePermition(principal.getName(), "Staff"))) {
            throw new NotImplementedException("User n??y kh??ng c?? quy???n");
        }
    }

    public Orders checkOrderAdmin(Integer id, Principal principal) {
        checkPrincipal(principal);
        Orders orders = ordersDAO.findMotById(id);
        if (orders == null) {
            throw new NotImplementedException("Kh??ng t???n t???i ????n h??ng");
        }
        return orders;
    }

    @Override
    public boolean cancerOrder(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Ch??? x??c nh???n") || orderManagement.getStatus().equals("???? x??c nh???n") || orderManagement.getStatus().equals("Y??u c???u h???y")) {
            String status = "???? h???y";
            String note = "Th???c hi???n h???y ????n h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            updateQuantityForProductBeffoCancerOrder(id);
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? x??c nh??n ????n h??ng ???? h???y ho???c giao th??nh c??ng");
        }
    }

    @Override
    public boolean confimOrder(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Ch??? x??c nh???n")) {
            String status = "???? x??c nh???n";
            String note = "Th???c hi???n x??c nh???n ????n h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? x??c nh??n ????n h??ng ???? h???y ho???c giao th??nh c??ng");
        }
    }

    @Override
    public boolean confimTransport(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("???? x??c nh???n")) {
            String status = "??ang giao h??ng";
            String note = "Th???c hi???n x??c nh???n ????n h??ng ??ang ???????c giao";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? x??c nh??n giao h??ng ????n h??ng ch??a ???????c x??c nh???n");
        }
    }

    @Override
    public boolean requestReturns(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("??ang giao h??ng")) {
            String status = "??ang ho??n h??ng";
            String note = "Th???c hi???n y??u c???u ho??n tr??? ????n h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? tr??? h??ng, h??y ch???n h???y ????n");
        }
    }

    @Override
    public boolean comfimReturns(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("??ang ho??n h??ng") || orderManagement.getStatus().equals("Y??u c???u tr??? h??ng")) {
            String status = "???? nh???n l???i h??ng ho??n v???";
            String note = "Th???c hi???n x??c nh???n ho??n tr??? ????n h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            updateQuantityForProductBeffoCancerOrder(id);
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? tr??? h??ng ????n h??ng n??y");
        }
    }

    @Override
    public boolean unCancerOrder(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("??ang ho??n h??ng")  ) {
            String status = "??ang giao h??ng";
            String note = "Kh??ng th???c hi???n ho??n h??ng, ti???p t???c giao h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            updateQuantityForProductBeffoCancerOrder(id);
            return true;
        } else if (orderManagement.getStatus().equals("Y??u c???u tr??? h??ng") ) {
            String status = "Giao h??ng th??nh c??ng";
            String note = "Kh??ng ch???p nh???n y??u c???u tr??? h??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            updateQuantityForProductBeffoCancerOrder(id);
            return true;
        }
        else {
            throw new NotImplementedException("Kh??ng th??? tr??? h??ng ????n h??ng n??y");
        }
    }


    @Override
    public boolean confimSell(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkOrderAdmin(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("??ang giao h??ng")) {
            String status = "Giao h??ng th??nh c??ng";
            String note = "Th???c hi???n giao h??ng th??nh c??ng";
            updateStatus(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? tr??? h??ng, h??y ch???n h???y ????n");
        }
    }


    public void requestStatusUser(String status, String note, Integer id, Orders orders, NoteOrderManagementVo noteOrderManagementVo, String username) {
        try {
            OrderManagement orderManagement1 = new OrderManagement();
            orderManagement1.setTimeChange(Timestamp.valueOf(LocalDateTime.now()));
            orderManagement1.setChangedBy(username);
            orderManagement1.setOrderId(orders.getId());
            orderManagement1.setStatus(status);
            orderManagement1.setNote(note);
            orderManagement1 = orderManagementDAO.save(orderManagement1);
            InfoSendStatusOrder infoSendStatusOrder1 = sendMailUpdateStatus(orderManagement1);
            infoSendStatusOrder.add(infoSendStatusOrder1);
        } catch (Exception e) {
            throw new NotImplementedException("C?? l???i khi thay ?????i tr???ng th??i ????n h??ng");
        }

    }

    public Orders checkUser(Integer id, Principal principal) {
        if (principal == null) {
            throw new NotImplementedException("Ch??a ????ng nh???p");
        }
        Orders orders = ordersDAO.findMotById(id);
        if (orders == null) {
            throw new NotImplementedException("Kh??ng t???n t???i ????n h??ng");
        }
        if (!orders.getUsername().equals(principal.getName())) {
            throw new NotImplementedException("Kh??ng c?? quy???n c???p nh???p ????n h??ng n??y");
        }
        return orders;
    }

    @Override
    public boolean cancerOrderUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkUser(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Ch??? x??c nh???n")) {
            String status = "???? h???y";
            String note;
            if (noteOrderManagementVo.getNote() == "") {
                note = "Th???c hi???n h???y ????n h??ng do ng?????i d??ng y??u c???u";
            } else {
                note = "Th???c hi???n h???y ????n h??ng do ng?????i d??ng y??u c???u, l?? do: " + noteOrderManagementVo.getNote();
            }
            requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
            updateQuantityForProductBeffoCancerOrder(id);
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        }
    }

    @Override
    public boolean requestCancerOrderUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkUser(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("???? x??c nh???n")) {
            String status = "Y??u c???u h???y";
            String note;
            if (noteOrderManagementVo.getNote() == "") {
                note = "Y??u c???u h???y ????n h??ng do ng?????i d??ng y??u c???u";
            } else {
                note = "Y??u c???u h???y ????n h??ng do ng?????i d??ng y??u c???u, l?? do: " + noteOrderManagementVo.getNote();
            }
            requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        }
    }

    @Override
    public boolean unCancerOrderUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkUser(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Y??u c???u tr??? h??ng")) {
            String status = "Giao h??ng th??nh c??ng";
            String note;
            if (noteOrderManagementVo.getNote() == "") {
                note = "???? h???y y??u c???u tr??? h??ng";
            } else {
                note = "???? h???y y??u c???u tr??? h??ng, l?? do: " + noteOrderManagementVo.getNote();
            }
            requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        }
    }
//    @Override
//    public boolean requestModifyOrderUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
//        Orders orders = checkUser( id,  principal);
//            OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
//            if (orderManagement.getStatus().equals("Ch??? x??c nh???n")) {
//                String status = "Y??u c???u s???a";
//                String note;
//                if (noteOrderManagementVo.getNote() == "") {
//                    note = "Y??u c???u s???a ????n h??ng do ng?????i d??ng y??u c???u, nh??n vi??n s??? li??n h??? l???i ????? x??c nh???n";
//                } else {
//                    note = "Y??u c???u s???a ????n h??ng do ng?????i d??ng y??u c???u, l?? do: " + noteOrderManagementVo.getNote();
//                }
//                requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
//                return true;
//            } else {
//                throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
//            }
//    }

    @Override
    public boolean confimReturnsUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkUser(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Giao h??ng th??nh c??ng")) {
            String status = "Y??u c???u tr??? h??ng";
            String note;
            if (noteOrderManagementVo.getNote() == "") {
                note = "Y??u c???u tr??? h??ng do ng?????i d??ng y??u c???u";
            } else {
                note = "Y??u c???u tr??? h??ng do ng?????i d??ng y??u c???u, l?? do:  " + noteOrderManagementVo.getNote();
            }
            requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        }
    }

    @Override
    public boolean unConfimReturnsUser(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        Orders orders = checkUser(id, principal);
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("Y??u c???u h???y")) {
            String status = "???? x??c nh???n";
            String note;
            if (noteOrderManagementVo.getNote() == "") {
                note = "???? h???y y??u c???u h???y ????n";
            } else {
                note = "???? h???y y??u c???u h???y ????n, l?? do: " + noteOrderManagementVo.getNote();
            }
            requestStatusUser(status, note, id, orders, noteOrderManagementVo, principal.getName());
            return true;
        } else {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        }
    }

    @Override
    public boolean updateNoteOrderManagement(NoteOrderManagementVo noteOrderManagementVo, Integer id, Principal principal) {
        checkPrincipal(principal);
        Orders orders = ordersDAO.findMotById(id);
        if (orders == null) {
            throw new NotFoundException("api.error.API-003");
        }
        OrderManagement orderManagement = orderManagementDAO.getLastManager(orders.getId());
        if (orderManagement.getStatus().equals("???? h???y") || orderManagement.equals("Giao h??ng th??nh c??ng") || orderManagement.equals("????n h??ng l???i")) {
            throw new NotImplementedException("Kh??ng th??? c???p nh???p ????n h??ng n??y");
        } else {
            if (noteOrderManagementVo.getNote() == "") {
                return false;
            } else {
                OrderManagement orderManagement1 = new OrderManagement();
                orderManagement1.setTimeChange(Timestamp.valueOf(LocalDateTime.now()));
                orderManagement1.setChangedBy(principal.getName());
                orderManagement1.setOrderId(orders.getId());
                orderManagement1.setStatus(orderManagement.getStatus());
                orderManagement1.setNote(noteOrderManagementVo.getNote());
                orderManagementDAO.save(orderManagement1);
                return true;
            }
        }
    }

    @Override
    public ShowProductWarrantyVO getWarranty(Integer orderId, Principal principal) {
        checkPrincipal(principal);
        ShowProductWarrantyVO showProductWarrantyVO = new ShowProductWarrantyVO();
        Orders orders1 = ordersDAO.findMotById(orderId);
        if (orders1 == null) {
            throw new NotFoundException("api.error.API-003");
        }
        showProductWarrantyVO.setStatus(getStatus(orderId));
        if (showProductWarrantyVO.getStatus().equals("Giao h??ng th??nh c??ng")) {
            List<OrderDetails> orderDetails = orderDetailsDAO.findAllByOrderIdEquals(orderId);
            List<ProductVO> productVOS = new ArrayList<>();
            for (OrderDetails orderDetail : orderDetails
            ) {
                Product product = orderDetail.getProduct();
                if (warrantyDAO.findOneByOrderIdAndProductId(orderId, product.getId()) != null) {
                    continue;
                } else {
                    ProductVO productVO = new ProductVO();
                    BeanUtils.copyProperties(product, productVO);
                    productVOS.add(productVO);
                }
            }

            BeanUtils.copyProperties(orders1, showProductWarrantyVO);
            showProductWarrantyVO.setProductVOS(productVOS);

        } else {
            throw new NotImplementedException("Kh??ng c?? h??a ????n n??y " + orderId);
        }
        return showProductWarrantyVO;
    }

    @Override
    public OrdersVO newOrderAdmin(NewOrdersVO ordersVO, Principal principal) {
        checkPrincipal(principal);
        String changeBy = principal != null ? principal.getName() : "guest";
        Orders orders = createOders(ordersVO, changeBy);
        OrdersVO vo = new OrdersVO();
        vo = ordersVO;
//        BeanUtils.copyProperties(ordersVO,vo);
        saveDetails(orders, vo);
        return managerOrderStatus(orders, changeBy, "???? x??c nh???n");
    }

    @Override
    public OrdersVO updateOrderAdmin(Optional<Integer> id, Optional<String> status, Principal principal) {
        checkPrincipal(principal);
        Orders orders = ordersDAO.getById(id.get());
        return managerOrderStatus(orders, principal.getName(), status.get());
    }

    @Override
    public List<OrdersVO> getList(Principal principal, Optional<Integer> id, Optional<String> email, Optional<String> name, Optional<String> phone) {
        checkPrincipal(principal);
        List<Orders> orders = ordersDAO.findAll();
        List<OrdersVO> ordersVOS = new ArrayList<>();
        for (Orders order : orders) {
            Customer customer = customerDAO.findCustomerById(order.getCustomerId());
            Boolean idok = true;
            Boolean nameok = true;
            Boolean mailok = true;
            Boolean phoneok = true;
            if (id.isPresent()) {
                idok = id.get() == order.getId();
            }
            if (name.isPresent()) {
                nameok = checkName(customer, name.get());
            }
            if (email.isPresent()) {
                mailok = checkEmail(customer, email.get());
            }
            if (phone.isPresent()) {
                phoneok = checkPhone(customer, phone.get());
            }
            if (idok || nameok || mailok || phoneok) {
                OrdersVO ordersVO = new OrdersVO();
                BeanUtils.copyProperties(order, ordersVO);
                ordersVO.setStatus(getStatus(order.getId()));
                ordersVOS.add(ordersVO);
            }
        }
        return ordersVOS;
    }

    @Override
    public List<OrdersVO> getByUsername(Principal principal) {
        if (principal == null) {
            throw new NotImplementedException("Ch??a ????ng nh???p");
        }
        List<OrdersVO> ordersVOS = new ArrayList<>();
        for (Orders orders : ordersDAO.getByUsername(principal.getName())) {
            OrdersVO vo = new OrdersVO();
            BeanUtils.copyProperties(orders, vo);
            vo.setStatus(getStatus(orders.getId()));
            vo.setNumOfProduct(orderDetailsDAO.getCountProductOf(vo.getId()));
            ordersVOS.add(vo);
        }
        Collections.sort(ordersVOS, Comparator.comparing(OrdersVO::getDateCreated).reversed());
        return ordersVOS;
    }

    @Override
    public List<OrdersVO> getAll(Principal principal) {
        checkPrincipal(principal);
        List<Orders> orders = ordersDAO.findAll();
        List<OrdersVO> ordersVOS = new ArrayList<>();
        for (Orders order : orders) {
            OrdersVO vo = new OrdersVO();
            BeanUtils.copyProperties(order, vo);

            //listcustomer
            Customer customer = customerDAO.findCustomerById(order.getCustomerId());
            if (customer == null) {
                throw new NotFoundException("api.error.API-003");
            } else {
                CustomerVO customerVO = new CustomerVO();
                BeanUtils.copyProperties(customer, customerVO);

                vo.setCustomer(customerVO);
            }
            //list ordermanager
            List<OrderManagement> orderManagement = orderManagementDAO.findByOrderId(order.getId());
            if (orderManagement.size() == 0) {

            } else {
                List<OrderManagementVO> orderManagements = new ArrayList<>();
                orderManagement.forEach(orderManagement1 -> {
                    OrderManagementVO orderManagementVO = new OrderManagementVO();
                    BeanUtils.copyProperties(orderManagement1, orderManagementVO);
                    orderManagements.add(orderManagementVO);
                });
                vo.setOrderManagements(orderManagements);
            }
            //list order detail
            List<OrderDetails> orderDetails = orderDetailsDAO.findAllByOrderIdEquals(order.getId());
            if (orderDetails.size() == 0) {

            } else {
                List<OrderDetailsVO> orderDetailsVOS = new ArrayList<>();
                for (OrderDetails orderDetails1 : orderDetails) {
                    OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
                    BeanUtils.copyProperties(orderDetails1, orderDetailsVO);
                    orderDetailsVO.setProductName(productDAO.getById(orderDetails1.getProductId()).getName());
                    orderDetailsVOS.add(orderDetailsVO);
                }
                vo.setOrderDetails(orderDetailsVOS);
            }
            //list warranty
            List<Warranty> warranty = warrantyDAO.findByOrderId(order.getId());
            if (warranty.size() == 0) {

            } else {
                WarrantyVO warrantyVO = new WarrantyVO();
                BeanUtils.copyProperties(warranty, warrantyVO);
                vo.setWarranty(warrantyVO);
            }
            vo.setStatus(getStatus(order.getId()));
            vo.setNumOfProduct(orderDetailsDAO.getCountProductOf(vo.getId()));
            ordersVOS.add(vo);
        }
        Collections.sort(ordersVOS, Comparator.comparing(OrdersVO::getDateCreated).reversed());
        return ordersVOS;
    }

    private OrdersVO getDetailOrders(Orders orders, String status) {
        Customer customer = customerDAO.findById(orders.getCustomerId()).orElseThrow(() -> new NullPointerException("Cannot find customer"));
        OrdersVO ordersVO = new OrdersVO();
        CustomerVO vo = new CustomerVO();
        BeanUtils.copyProperties(customer, vo);
        BeanUtils.copyProperties(orders, ordersVO);
        ordersVO.setCustomer(vo);
        List<OrderDetailsVO> orderDetailsVOS = new ArrayList<>();
        for (OrderDetails orderDetails : orderDetailsDAO.findAllByOrderIdEquals(orders.getId())) {
            OrderDetailsVO orderDetailsVO = new OrderDetailsVO();
            BeanUtils.copyProperties(orderDetails, orderDetailsVO);
            orderDetailsVO.setProductName(productDAO.getById(orderDetails.getProductId()).getName());
            orderDetailsVO.setColorName(colorDAO.getById(orderDetailsVO.getColorId()).getColorName());
            orderDetailsVOS.add(orderDetailsVO);
        }
        ordersVO.setOrderDetails(orderDetailsVOS);
        ordersVO.setStatus(status != null ? status : getStatus(orders.getId()));
        return ordersVO;
    }

    //new
    private Orders createOders(@Valid NewOrdersVO ordersVO, String changeBy) {

        List<OrderDetailsVO> orderDetailsVO = ordersVO.getOrderDetails();
        Long totalPrice = 0L;
//        Long totalDiscount = 0L;
//        totalDiscount += discount;
//        Long discount = orderDetailsVO1.getQuantity() * maxDiscount;
        for (OrderDetailsVO orderDetailsVO1 : orderDetailsVO) {
            if (orderDetailsVO1.getQuantity() <= 0) {
                continue;
            } else {
                Product product = productDAO.getOneProductById(orderDetailsVO1.getProductId());
               Long maxDiscount = priceUtils.maxDiscountAtPresentOf(product.getId());
                totalPrice += orderDetailsVO1.getQuantity() * (product.getPrice() - maxDiscount);
            }
        }
        if (!totalPrice.equals(ordersVO.getSumprice())) {
            throw new NotImplementedException("Gi?? s???n ph???m ???? thay ?????i. xin m???i xem l???i ch????ng tr??nh khuy???n m???i");
        }

        Customer customer = createCustomer(ordersVO.getCustomer());
//        Customer customer = new Customer();
//        BeanUtils.copyProperties(ordersVO.getCustomer(), customer);
//        customer = customerDAO.save(customer);
        //save order
        Orders orders = new Orders();
        orders.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
        orders.setUsername(changeBy);
        orders.setCustomerId(customer.getId());
        orders.setTypePayment(false);
        orders.setSumprice(totalPrice);
        orders = ordersDAO.save(orders);

        return orders;
    }

//old

//    private Orders createOders(@Valid NewOrdersVO ordersVO, String changeBy) {
//
//        List<OrderDetailsVO> orderDetailsVO = ordersVO.getOrderDetails();
//        Long totalPrice = 0L;
//        Long totalDiscount = 0L;
//        for (OrderDetailsVO orderDetailsVO1 : orderDetailsVO) {
//            if (orderDetailsVO1.getQuantity() <= 0) {
//                continue;
//            } else {
//                Long discount = orderDetailsVO1.getQuantity() * orderDetailsVO1.getDiscount();
//                totalDiscount += discount;
//                totalPrice += orderDetailsVO1.getQuantity() * (orderDetailsVO1.getPrice() - orderDetailsVO1.getDiscount());
//            }
//        }
//        if (!totalPrice.equals(ordersVO.getSumprice())) {
//            throw new NotImplementedException("Gi?? s???n ph???m ???? thay ?????i. xin m???i xem l???i ch????ng tr??nh khuy???n m???i");
//        }
//
//        Customer customer = createCustomer(ordersVO.getCustomer());
////        Customer customer = new Customer();
////        BeanUtils.copyProperties(ordersVO.getCustomer(), customer);
////        customer = customerDAO.save(customer);
//        //save order
//        Orders orders = new Orders();
//        orders.setDateCreated(Timestamp.valueOf(LocalDateTime.now()));
//        orders.setUsername(changeBy);
//        orders.setCustomerId(customer.getId());
//        orders.setTypePayment(false);
//        orders.setSumprice(totalPrice);
//        orders = ordersDAO.save(orders);
//
//        return orders;
//    }

    private Customer createCustomer(@Valid CustomerVO customerVO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerVO, customer);
        return customerDAO.save(customer);
    }

    private List<OrderDetailsVO> saveDetails(Orders orders, OrdersVO ordersVO) {
        // save order detail
        List<OrderDetailsVO> vos = new ArrayList<>();
        List<OrderDetailsVO> orderDetailsVOS = ordersVO.getOrderDetails();
        for (OrderDetailsVO orderDetailsVO1 : orderDetailsVOS) {
            if (orderDetailsVO1.getQuantity().equals(0)) {
                continue;
            }
            OrderDetails orderDetails = new OrderDetails();
            BeanUtils.copyProperties(orderDetailsVO1, orderDetails);
            orderDetails.setOrderId(orders.getId());
            orderDetails.setDiscount(priceUtils.maxDiscountAtPresentOf(orderDetails.getProductId()));
            ProductColor productColor = productColorDAO.findByProductIdAndColorId(orderDetails.getProductId(), orderDetails.getColorId());
            if (productColor == null || productColor.getQuantity() - orderDetails.getQuantity() < 0) {
                throw new NotImplementedException("S???n ph???m kh??ng c?? m??u " + colorDAO.findColorById(orderDetails.getColorId()).getColorName() + " ho???c s??? l?????ng s???n ph???m m??u n??y kh??ng ?????");
            }
            productColor.setQuantity(productColor.getQuantity() - orderDetails.getQuantity());
            productColorDAO.save(productColor);
            orderDetails.setStatusWarranty(false);
            orderDetails.setStatusWarranty(false);
            orderDetails = orderDetailsDAO.save(orderDetails);
            ProductSale productSale = priceUtils.getSaleHavingMaxDiscountOf(orderDetails.getProductId());
            if (productSale == null)
                continue;
            productSale.setQuantity(productSale.getQuantity() - orderDetails.getQuantity());
            productSaleDAO.save(productSale);
            OrderDetailsVO vo = new OrderDetailsVO();
            BeanUtils.copyProperties(orderDetails, vo);
            vos.add(vo);
            Integer left = productColorDAO.getNumerProductOf(orderDetailsVO1.getProductId());
            if (left <= 0) {
                Product product = productDAO.getById(orderDetailsVO1.getProductId());
                product.setStatus("H???t h??ng");
                productDAO.save(product);
            }
        }
        return vos;
    }

    private OrdersVO managerOrderStatus(Orders orders, String changeBy, String status) {
        //save ordermanagement

        String note = "";
        if (status.equals("???? x??c nh???n")) {
            if (orders.getTypePayment().equals(Boolean.TRUE)) note = "???? thanh to??n";
        } else {
            OrderManagement last = orderManagementDAO.getLastManager(orders.getId());
            if (orders.getTypePayment().equals(Boolean.TRUE)) note = last.getNote();
        }

        OrdersVO ordersVO = getDetailOrders(orders, status);
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setOrderId(orders.getId());
        orderManagement.setTimeChange(Timestamp.valueOf(LocalDateTime.now()));
        orderManagement.setChangedBy(changeBy);
        orderManagement.setNote(note);
        orderManagement.setStatus(status);
        orderManagementDAO.save(orderManagement);
        BeanUtils.copyProperties(orders, ordersVO);
        ordersVO.setStatus(status);
        return ordersVO;
    }

//    private void

    boolean checkName(Customer customer, String name) {
        return stringFind.checkContains(customer.getFullname(), name);
    }

    boolean checkEmail(Customer customer, String email) {
        return customer.getEmail().equalsIgnoreCase(email);
    }

    boolean checkPhone(Customer customer, String phone) {
        return customer.getPhone().equalsIgnoreCase(phone);
    }

    private String getStatus(Integer id) {
        OrderManagement orderManagement = orderManagementDAO.getLastManager(id);
        if (orderManagement == null) {
            OrderManagement orderManagement1 = new OrderManagement();
            orderManagement1.setOrderId(id);
            orderManagement1.setStatus("????n h??ng l???i");
            orderManagement1.setChangedBy("SYSTEM");
            orderManagement1.setNote("????n h??ng l???i, h??? th???ng t??? th??m tr???ng th??i");
            orderManagement1.setTimeChange(Timestamp.valueOf(LocalDateTime.now()));
            orderManagement1 = orderManagementDAO.save(orderManagement1);
            return orderManagement1.getStatus();
        }
        return orderManagement.getStatus();
    }

    private OrdersVO getStatusLine(OrdersVO ordersVO) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        OrdersVO vo = ordersVO;
        List<OrderManagement> oms = orderManagementDAO.findByOrderId(vo.getId());
        Collections.sort(oms, Comparator.comparing(OrderManagement::getTimeChange).reversed());
        List<OrderManagementVO> omvos = new ArrayList<>();
        for (OrderManagement orderManagement : oms) {
            OrderManagementVO managementVO = new OrderManagementVO();
            BeanUtils.copyProperties(orderManagement, managementVO);
            managementVO.setTimeChange(sdf.format(new Date(orderManagement.getTimeChange().getTime())));
            omvos.add(managementVO);
        }
        vo.setOrderManagements(omvos);
        return vo;
    }

    Boolean checkOderDetailPrice(List<OrderDetailsVO> orderDetailsVOS, Principal principal) {

        return true;
    }
}
