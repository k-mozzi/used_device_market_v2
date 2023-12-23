package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;
import teamproject.usedmarket.web.item.StoreFileName;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class ImageServiceV1 implements ImageService {




//    public static final String FILE_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
    private final ImageRepository imageRepository;

    String FILE_PATH = "C:\\Users\\82109\\Desktop\\spring_img";

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
                        itemImage.setFilePath("/files/"+fileName);
                        imageRepository.save(itemImage);
                    }
                }

        }



    @Override
    public void update(Long itemId, ItemUpdateDto updateParam, MultipartFile file) throws IOException {

    }

    @Override
    public Optional<ItemImage> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ItemImage> findImages() {
        return imageRepository.findAll();
    }

    @Override
    public void delete(Long itemImageId) {
        imageRepository.delete(itemImageId);
    }


}
