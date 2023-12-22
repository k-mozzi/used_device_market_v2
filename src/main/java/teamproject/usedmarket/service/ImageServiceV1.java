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
    public Optional<ItemImage> findByitemId(Long itemid) {
        Optional<ItemImage> findImage = imageRepository.findByItemId(itemid);
        return findImage;

    }



    @Override
    public ItemImage save(Long itemId, List<MultipartFile> file) throws IOException {


        ItemImage itemImage = new ItemImage();
        if (file.isEmpty()) {
            itemImage.setItemId(itemId);
            itemImage.setCreateDatetime(new Date());
            itemImage.setImageFiles(file);

            ItemImage savedImage = imageRepository.save(itemImage);
            return savedImage;
        } else {

           List<String> fileTong = new ArrayList<>();
            for (MultipartFile multipartFile : file) {

                UUID uuid = UUID.randomUUID();

                String un_fileName = uuid + "_" + multipartFile.getOriginalFilename();
                String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);
                fileTong.add(fileName);


                File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

                multipartFile.transferTo(saveFile); //저장

            }
            log.info("fileTong크기= {}",fileTong.size());


            if (fileTong !=null && file.size() > 0){
                itemImage.setFileName1(fileTong.get(0));
            }
            if (fileTong !=null && file.size() > 1) {
                itemImage.setFileName2(fileTong.get(1));
            }
            if (fileTong !=null && file.size() > 2) {
                itemImage.setFileName3(fileTong.get(2));
            }
            if (fileTong !=null && file.size() > 3) {
                itemImage.setFileName4(fileTong.get(3));
            }
            if (fileTong !=null && file.size() > 4) {
                itemImage.setFileName5(fileTong.get(4));
            }

            itemImage.setFileNames(fileTong);
            itemImage.setItemId(itemId);
            itemImage.setCreateDatetime(new Date());
            ItemImage savedImage = imageRepository.save(itemImage);
            return savedImage;
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
}
