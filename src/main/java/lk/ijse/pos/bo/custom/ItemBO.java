package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO dto);

    boolean updateItem(ItemDTO dto);

    ItemDTO findItemById(int id);

    boolean deleteItem(int id);

    List<ItemDTO> findAllItems();
}
