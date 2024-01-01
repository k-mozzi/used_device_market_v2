package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
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




    public static final String FILE_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
    private final ImageRepository imageRepository;

//    String FILE_PATH = "C:\\Users\\82109\\Desktop\\spring_img";
//    String DIRECTORY_PATH = "c:/Users/82109/Desktop/spring_img/";
    String DIRECTORY_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
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


                        File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

                        multipartFile.transferTo(saveFile); //저장

                        itemImage.setFileName(fileName);
                        itemImage.setFilePath(DIRECTORY_PATH+fileName);
                        imageRepository.save(itemImage);
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
        String filePath = DIRECTORY_PATH + fileName;
        File file = new File(filePath);

        try {
            // 파일을 삭제합니다.
            if (file.delete()) {
                log.info(filePath + "가 성공적으로 삭제되었습니다.");
            } else {
                log.info(filePath + "를 삭제할 수 없습니다.");
            }
        } catch (SecurityException e) {
            log.info(filePath + "에 대한 삭제 권한이 없습니다.");
        } catch (Exception e) {
            log.info("오류가 발생했습니다: " + e.getMessage());
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
            imageRepository.update(itemImages.get(0).getItemImageId(),updateDto);
        }




    }

    @Override
    public void update(int itemImageId, ImageUpdateDto updateDto) throws IOException {
        imageRepository.update(itemImageId, updateDto);
    }


}
