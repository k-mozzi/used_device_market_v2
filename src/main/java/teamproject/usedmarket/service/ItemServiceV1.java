package teamproject.usedmarket.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void save(Item item, MultipartFile file) throws IOException {

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";


        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName); //경로, 파일이름 지정

        file.transferTo(saveFile);

        item.setFilename(fileName);
        item.setFilepath("/files/"+fileName);

        itemRepository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemRepository.update(itemId, updateParam);
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