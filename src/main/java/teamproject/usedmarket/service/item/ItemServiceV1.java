package teamproject.usedmarket.service.item;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import teamproject.usedmarket.config.S3Config;
import teamproject.usedmarket.domain.item.Item;
import teamproject.usedmarket.domain.item.ItemImage;
import teamproject.usedmarket.repository.ItemRepository;
import teamproject.usedmarket.web.item.ItemUpdateDto;
import teamproject.usedmarket.service.image.ImageService;
import teamproject.usedmarket.service.item.ItemService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceV1 implements ItemService {
    private final ImageService imageService;
    private final ItemRepository itemRepository;

    private final S3Config s3Config;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public Item save(Item item, HttpSession session) throws IOException {
        //세션에서 멤버 아이디 가져온 후 아이템 객체의 setSellerMemberId에 바인딩
        Long currentMemberId = (Long) session.getAttribute("memberId");
        item.setSellerMemberId(currentMemberId);

        Item savedItem = itemRepository.save(item);
        return savedItem;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) throws IOException {

        itemRepository.update(itemId, updateParam);

    }

    @Override
    public void updateStatus(Long itemId, ItemUpdateDto updateParam) throws IOException {
        itemRepository.updateStatus(itemId, updateParam);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepository.findByItemId(id);
    }

    @Override
    public List<Item> findByBuyerId(Long id) {
       return itemRepository.findByBuyerId(id);
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
    public void delete(Long itemId) {
        List<ItemImage> itemImages = imageService.findByItemId(itemId);
        for (ItemImage itemImage : itemImages) {
            String fileName = itemImage.getFileName();
            //S3 파일 삭제 요청
            try {
                s3Config.amazonS3Client().deleteObject(new DeleteObjectRequest(bucket, fileName));
                log.info("파일 삭제 성공");
            } catch (AmazonServiceException e) {
                log.info("Amazon S3 서비스 예외: ",e.getErrorMessage());
                e.printStackTrace();
            } catch (SdkClientException e) {
                log.info("AWS SDK 클라이언트 예외: ",e.getMessage());
                e.printStackTrace();
            }
        }


        itemRepository.delete(itemId);

    }

    @Override
    public List<Item> findItemsWithPaging(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsWithPaging(page, pageSize, itemType, regionId);
    }

    @Override
    public int countItems() {
        return itemRepository.countItems();
    }

    @Override
    public List<Item> findItemsSortedByRegistrationDate(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByRegistrationDate(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsSortedByViewsCount(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByViewsCount(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsSortedByLikesCount(int page, int pageSize, String itemType, String regionId) {
        return itemRepository.findItemsSortedByLikesCount(page, pageSize, itemType, regionId);
    }

    @Override
    public List<Item> findItemsWithPagingAndSearch(int page, int pageSize, String searchText) {
        return itemRepository.findItemsWithPagingAndSearch(page, pageSize, searchText);
    }
}
