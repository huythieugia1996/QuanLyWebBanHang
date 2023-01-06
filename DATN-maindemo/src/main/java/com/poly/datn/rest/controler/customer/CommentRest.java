package com.poly.datn.rest.controler.customer;

import com.poly.datn.common.Constant;
import com.poly.datn.service.CommentService;
import com.poly.datn.vo.ColorVO;
import com.poly.datn.vo.CommentVO;
import com.poly.datn.vo.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(Constant.CROSS_ORIGIN)
@RequestMapping("/api/customer/comment")
public class CommentRest {

    @Autowired
    CommentService commentService;

    @GetMapping("get/{bid}")
    public ResponseEntity<ResponseDTO<Object>> getComment(@PathVariable("bid") Integer bid) {
        return ResponseEntity.ok(ResponseDTO.builder().object(commentService.getListByBlogId(bid))
                .code(Constant.RESPONSEDTO_CODE).message(Constant.RESPONSEDTO_MESS).build());
    }

    @PutMapping("update")
    public ResponseEntity<ResponseDTO<Object>> updateComment(@RequestBody CommentVO commentVO) {
        return ResponseEntity.ok(ResponseDTO.builder().object(commentService.updateComment(commentVO))
                .code(Constant.RESPONSEDTO_CODE).message(Constant.RESPONSEDTO_MESS).build());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO<Object>> deleteComment(@PathVariable Integer id) {
        return ResponseEntity.ok(ResponseDTO.builder().object(commentService.deleteComment(id))
                .code(Constant.RESPONSEDTO_CODE).message(Constant.RESPONSEDTO_MESS).build());
    }

    @PostMapping("new")
    public ResponseEntity<ResponseDTO<Object>> addComment(@RequestBody CommentVO commentVO) {
        return ResponseEntity.ok(ResponseDTO.builder().object(commentService.newComment(commentVO))
                .code(Constant.RESPONSEDTO_CODE).message(Constant.RESPONSEDTO_MESS).build());
    }
}
