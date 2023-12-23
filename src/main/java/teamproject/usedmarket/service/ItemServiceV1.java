package teamproject.usedmarket.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.repository.ItemUpdateDto;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {

    public static final String FILE_PATH = "/Users/kimgang/Documents/SpringProject/imageFile";
    private final ItemRepository itemRepository;

//    String FILE_PATH = "C:\\Users\\82109\\Desktop\\spring_img";

    @Override
    public Item save(Item item, MultipartFile file, HttpSession session) throws IOException {

        //세션에서 멤버 아이디 가져온 후 아이템 객체의 setSellerMemberId에 바인딩
        Long currentMemberId = (Long) session.getAttribute("memberId");
        item.setSellerMemberId(currentMemberId);

        if (file == null) {
            Item itemzero1 = itemRepository.save(item);
            return itemzero1;
        } else if (file.getSize() == 0) {
            Item itemzero = itemRepository.save(item);
            return itemzero;
        } else {

            UUID uuid = UUID.randomUUID();

            String un_fileName = uuid + "_" + file.getOriginalFilename();
            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

            File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

            file.transferTo(saveFile);

            item.setFilename(fileName);
            item.setFilepath("/files/" + fileName);

            Item itemYes = itemRepository.save(item);
            return itemYes;
        }
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam, MultipartFile file) throws IOException {
        if (file == null) {

            Item findItem = itemRepository.findByItemId(itemId).get();
            updateParam.setFilename(findItem.getFilename());
            updateParam.setFilepath(findItem.getFilepath());
            itemRepository.update(itemId, updateParam);
        } else {
            UUID uuid = UUID.randomUUID();
            String un_fileName = uuid + "_" + file.getOriginalFilename();
            String fileName = UriUtils.encode(un_fileName, StandardCharsets.UTF_8);

            File saveFile = new File(FILE_PATH, fileName); //경로, 파일이름 지정

            file.transferTo(saveFile);

            updateParam.setFilename(fileName);
            updateParam.setFilepath("/files/" + fileName);


            itemRepository.update(itemId, updateParam);
        }

    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findByItemId(id);
    }

    @Override
    public String findMemberNameBySellerMemberId(Long sellerMemberId, Long itemId) {
        return itemRepository.findMemberNameBySellerMemberId(sellerMemberId, itemId);
    }

    @Override
    public void incrementViewsCount(Long itemId) {
        Optional<Item> findItem = itemRepository.findByItemId(itemId);
        findItem.ifPresent(item -> {
            itemRepository.incrementViewsCount(itemId);
            item.setViewsCount(item.getViewsCount() + 1);
        });
    }

    @Override
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Override
    public void delete(Long itemId) {
        itemRepository.delete(itemId);
    }


}