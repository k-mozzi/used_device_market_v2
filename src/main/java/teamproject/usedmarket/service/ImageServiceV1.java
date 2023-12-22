package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

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

            itemImage.setImageFiles(file);
            List<MultipartFile> imageFiles = itemImage.getImageFiles();
            List<String> fileNames = new ArrayList<>();
            



                for (MultipartFile imageFile : imageFiles) {

                    UUID uuid = UUID.randomUUID();

                    String un_fileName = uuid + "_" + imageFile.getOriginalFilename();
                    String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);
                    fileNames.add(fileName);


                    File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

                    imageFile.transferTo(saveFile); //저장

                }


            for (int i = 0; i < fileNames.size(); i++) {
                itemImage.setFileNames(Collections.singletonList(fileNames.get(i)));
            }

            itemImage.setFilePath("/files/" + fileNames);
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
