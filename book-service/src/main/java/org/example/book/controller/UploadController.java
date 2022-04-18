package org.example.book.controller;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.example.feign.result.Result;
import org.example.feign.util.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zjh
 */
@RestController
@RequestMapping("/book")
public class UploadController {
    @Resource
    MinioClient minioClient;

    @Value("${minio.endPoint}")
    String endPoint;

    @Value("${minio.bucketName}")
    String bucketName;

    /**
     * 上传封面
     *
     * @param coverFile 封面路径
     * @return 上传成功
     */
    @PostMapping("/upload")
    public Result uploadCover(@RequestPart("cover") MultipartFile coverFile) {
        // 获得用户上传的原始文件名
        String coverOrgFileName = coverFile.getOriginalFilename();
        assert coverOrgFileName != null;
        String coverExt = coverOrgFileName.substring(coverOrgFileName.lastIndexOf(".") + 1).toLowerCase();
        String coverPikId = UUID.randomUUID().toString().replaceAll("-", "");
        String coverPath = "/cover/" + coverPikId + "." + coverExt;

        try {
            // 开始上传封面
            InputStream in = coverFile.getInputStream();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(coverPath)
                            .stream(in, coverFile.getSize(), -1)
                            .contentType(coverFile.getContentType())
                            .build());
            in.close();
            return ResultUtil.buildSuccessResult("上传成功", endPoint + bucketName + coverPath);

        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.buildFailResult("上传失败", null);
        }
    }
}