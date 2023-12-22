package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ImageRepository;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceV1 implements ImageService {


    //    public static final String FILE_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
    private final ImageRepository imageRepository;

    String FILE_PATH = "C:\\Users\\82109\\Desktop\\spring_img";

    @Override
    public ItemImage save(Long itemId, MultipartFile file) throws IOException {

        ItemImage itemImage = new ItemImage();
        if (file.getSize() == 0) {
            itemImage.setItemId(itemId);
            itemImage.setCreateDatetime(new Date());

            ItemImage savedImage = imageRepository.save(itemImage);
            return savedImage;
        } else {

            UUID uuid = UUID.randomUUID();

            String un_fileName = uuid + "_" + file.getOriginalFilename();
            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

            File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

            file.transferTo(saveFile);

            itemImage.setItemId(itemId);
            itemImage.setFileName(fileName);
            itemImage.setFilePath("/files/" + fileName);
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
