package teamproject.usedmarket.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

    String projectPath = "C:\\Users\\82109\\Desktop\\spring_img";
    @Override
    public void save(Item item, MultipartFile file) throws IOException {
        if (file.getSize() == 0) {
            itemRepository.save(item);
        } else {
            UUID uuid = UUID.randomUUID();

            String un_fileName = uuid + "_" + file.getOriginalFilename();
            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

            File saveFile = new File(projectPath, fileName); //경로, 파일이름 지정

            file.transferTo(saveFile);

            item.setFilename(fileName);
            item.setFilepath("/files/"+fileName);

            itemRepository.save(item);
        }

    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam,MultipartFile file) throws IOException {
        log.info("filesize2={}", file.getSize());
        if (file.getSize()==0) {

            Item findItem = itemRepository.findByItemId(itemId).get();
            updateParam.setFilename(findItem.getFilename());
            updateParam.setFilepath(findItem.getFilepath());
            itemRepository.update(itemId, updateParam);
        }else {
            UUID uuid = UUID.randomUUID();
            String un_fileName = uuid + "_" + file.getOriginalFilename();
            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

            File saveFile = new File(projectPath, fileName); //경로, 파일이름 지정

            file.transferTo(saveFile);

            updateParam.setFilename(fileName);
            updateParam.setFilepath("/files/"+fileName);


            itemRepository.update(itemId, updateParam);
        }

    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findByItemId(id);
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }


}