package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

            itemImage.setFileName1("3bb22ce5-31d2-48ac-b53f-953314e48bf7_efefef.PNG");
            itemImage.setFileName2("3bb22ce5-31d2-48ac-b53f-953314e48bf7_efefef.PNG");
            itemImage.setFileName3("3bb22ce5-31d2-48ac-b53f-953314e48bf7_efefef.PNG");
            itemImage.setFileName4("3bb22ce5-31d2-48ac-b53f-953314e48bf7_efefef.PNG");
            itemImage.setFileName5("3bb22ce5-31d2-48ac-b53f-953314e48bf7_efefef.PNG");

            if (!fileTong.isEmpty()){
                itemImage.setFileName1(fileTong.get(0));
            }
            if (!fileTong.get(1).isEmpty()) {
                itemImage.setFileName2(fileTong.get(1));
            }
            if (!fileTong.get(2).isEmpty()) {
                itemImage.setFileName3(fileTong.get(2));
            }
            if (!fileTong.get(3).isEmpty()) {
                itemImage.setFileName4(fileTong.get(3));
            }
            if (!fileTong.get(4).isEmpty()) {
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
