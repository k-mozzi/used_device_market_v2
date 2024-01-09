package teamproject.usedmarket.service.image;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.config.S3Config;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
import teamproject.usedmarket.service.image.ImageService;
import teamproject.usedmarket.web.itemimage.ImageUpdateDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImageServiceV1 implements ImageService {
    private final S3Config s3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    public static final String FILE_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
    private final ImageRepository imageRepository;

        String FILE_PATH = "/home/ubuntu/spring_img/";
//    String DIRECTORY_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";

    @Override
    public List<ItemImage> findByItemId(Long itemid) {
        List<ItemImage> findImages = imageRepository.findByItemId(itemid);
        return findImages;

    }

    @Override
    public void save(Long itemId, List<MultipartFile> file) throws IOException {
        ItemImage itemImage = new ItemImage();
        for (MultipartFile multipartFile : file) {
            if (multipartFile.getSize() == 0) {

            } else {
                itemImage.setRepImageCheck(false);

                if (imageRepository.findByItemId(itemId).isEmpty()) {
                    itemImage.setRepImageCheck(true);
                }

                itemImage.setItemId(itemId);
                itemImage.setCreateDatetime(new Date());

                UUID uuid = UUID.randomUUID();
                String un_fileName = uuid + "_" + multipartFile.getOriginalFilename();
                String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

                String localPath = FILE_PATH + fileName;

                File localFile = new File(localPath); //경로, 파일이름 지정
                multipartFile.transferTo(localFile); //저장


                //s3에 이미지 올림
                s3Config.amazonS3Client().putObject(new PutObjectRequest(bucket, fileName, localFile).withCannedAcl(CannedAccessControlList.PublicRead));
                String s3Url = s3Config.amazonS3Client().getUrl(bucket, fileName).toString();


                itemImage.setFileName(fileName);
                itemImage.setFilePath(s3Url);
                imageRepository.save(itemImage);

                //서버에 저장한 이미지를 삭제
                localFile.delete();



            }
        }
    }

    @Override
    public ItemImage findById(int itemImageId) {
        return imageRepository.findById(itemImageId);
    }

    @Override
    public List<ItemImage> findImages() {
        return imageRepository.findAll();
    }

    @Override
    public void delete(int itemImageId) {
        ItemImage findImageToDelete = imageRepository.findById(itemImageId);

        String fileName = findImageToDelete.getFileName();



        //S3 파일 삭제 요청
        try {
            s3Config.amazonS3Client().deleteObject(new DeleteObjectRequest(bucket, fileName));
            log.info("파일 삭제 성공");
        } catch (AmazonServiceException e) {
            log.info("Amazon S3 서비스 예외: ",e.getErrorMessage());
            e.printStackTrace();
        } catch (SdkClientException e) {
            log.info("AWS SDK 클라이언트 예외: ",e.getMessage());
            e.printStackTrace();
        }





        imageRepository.delete(itemImageId);

        ItemImage repImage = null;
        List<ItemImage> itemImages = findByItemId(findImageToDelete.getItemId());
        for (ItemImage itemImage : itemImages) {
            if (itemImage.isRepImageCheck() == true) {
                repImage = itemImage;
            }
        }
        if (repImage == null) {
            ImageUpdateDto updateDto = new ImageUpdateDto();
            updateDto.setUpdateDatetime(new Date());
            updateDto.setRepImageCheck(true);
            imageRepository.update(itemImages.get(0).getItemImageId(), updateDto);
        }

    }

    @Override
    public void update(int itemImageId, ImageUpdateDto updateDto) throws IOException {
        imageRepository.update(itemImageId, updateDto);
    }


}
