package com.sj.feign;

import com.sj.config.MultipartSupportConfig;
import com.sj.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "user",configuration = MultipartSupportConfig.class)
public interface UploadFeign {

    @PostMapping(value = "/UserInfo/uploadIcon",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultVO uploadIcon(MultipartFile file);
}
