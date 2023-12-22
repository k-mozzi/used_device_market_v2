package teamproject.usedmarket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceV1 implements ImageService {


    @Override
    public void save(Long itemId, MultipartFile imageFiles) throws IOException {

//        if (imageFiles.getSize() == 0) {
//            itemRepository.save(item);
//        } else {
//
//            UUID uuid = UUID.randomUUID();
//
//            String un_fileName = uuid + "_" + file.getOriginalFilename();
//            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);
//
//            File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정
//
//            file.transferTo(saveFile);
//
//            item.setFilename(fileName);
//            item.setFilepath("/files/" + fileName);
//
//            itemRepository.save(item);
//    }

}

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam, MultipartFile file) throws IOException {

    }

    @Override
    public Optional<ItemImage> findById(Long id) {
        return Optional.empty();
    }
}
